package controllers;

import models.Address;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.*;
import play.Logger;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.*;
import scala.util.parsing.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {

    static Form<Address> addressesForm = Form.form(Address.class);
    private static Model.Finder<Long, Address> finder = new Model.Finder<Long, Address>(Long.class, Address.class);

    public static Result index() {
        ObjectNode objectNode = new ObjectNode(JsonNodeFactory.instance);
        objectNode.put("int", 5);
        ArrayNode array = new ArrayNode(JsonNodeFactory.instance);
        array.add(123L);
        array.add(7899L);
        array.add(true);
        objectNode.put("a", array);

        Http.Cookie c = request().cookie("c");

        response().setContentType("application/json");
        response().setHeader("h1", "value");
        response().setHeader(ETAG, "654564646");
        if(c != null)
            response().setCookie("c", c.value() +  "aaa");
        else
            response().setCookie("c", "w/o c");
        Status ok = ok(objectNode);
//        return badRequest();
        return ok;

/*        try {
            return ok(new FileInputStream("c:\\Data\\Java\\MyProjects\\testapp\\README"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return badRequest();*/

/*        JsonNode jsonNode = request().body().asJson();
        int intValue = jsonNode.get("int").asInt();
        return ok("" + intValue);
  */  }

    public static Result indexStream() {
        Logger.info("Streaming ...");
        try {
            File file = new File(System.getProperty("user.dir"),"README");
            return ok(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return badRequest();
    }

    public static Result postIndex() {
        response().setContentType("application/json");

        JsonNode jsonNode = request().body().asJson();

        ObjectNode objectNode = new ObjectNode(JsonNodeFactory.instance);
        objectNode.put("whole", jsonNode);
        objectNode.put("anotherInt", new IntNode(66));

        return ok(objectNode);
    }

    public static Result addresses() {
        return ok(views.html.index.render(finder.all(), addressesForm));
    }

    public static Result addNewAddress() {
        Form<Address> filledForm = addressesForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            return badRequest();
        }
        else {
            Address address = filledForm.get();
//            address.Id = finder.all().size();
            address.save();
            return redirect(routes.Application.addresses());
        }
    }

    public static Result deleteAddress(Long id) {
        finder.ref(id).delete();
        return redirect(routes.Application.addresses());
    }
}