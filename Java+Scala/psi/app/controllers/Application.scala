package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import eu.podval.psi.model.{Item, Category}
import views._
import scala.collection.mutable
import play.api.i18n.Messages
import org.slf4j.{LoggerFactory, Logger}

object Application extends Controller {
  val logger: Logger = LoggerFactory.getLogger(Application.getClass);

  val categories = new mutable.HashMap[Int, Category]()
  categories.put(1, new Category(1, "aaa"))
  categories.put(2, new Category(2, "bbb"))
  categories.get(2).get.addItem(new Item(2, "aaa", 1, 3, 4, 1))
  categories.get(2).get.addItem(new Item(1, "bbb", 7, 3, 4, 1))
  categories.get(2).get.addItem(new Item(3, "ccc", 1, 1, 4, 1))
  var categoryCounter: Int = 0;

  val categoryForm = Form(
    ("name" -> text.verifying(Messages("category.create.nameMustBeFilled"), t => t != null && !t.isEmpty))
  )

  val itemForm = Form(
    tuple(
      "number" -> number(1),
      "name" -> text.verifying(Messages("category.create.nameMustBeFilled"), t => t != null && !t.isEmpty),
      "zko" -> number(1),
      "track" -> number(1),
      "docility" -> number(1),
      "defense" -> number(1)
    )
  )

  def index = Action {
    Redirect("/category", MOVED_PERMANENTLY)
  }

  def allCategories = Action {
    Ok(views.html.index.render(categories.values.toList, categoryForm))
  }

  def newCategory = Action {
    implicit request => {
      categoryForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.index.render(categories.values.toList, formWithErrors)), {
        case (name) => {
          categoryCounter += 1
          categories.put(categoryCounter, new Category(categoryCounter, name))
          Ok(html.index.render(categories.values.toList, categoryForm))
        }
      }
      )
    }
  }

  def deleteCategory(id: Int) = Action {
    categories.remove(id)
    Ok(html.index.render(categories.values.toList, categoryForm))
  }

  def category(id: Int) = Action {
    logger.error("Getting category [Id=%s], map contains [Ids=%s].".format(id, categories.keySet))
    if (categories.get(id).nonEmpty)
      Ok(html.category.render(categories.get(id).get, itemForm))
    else
      NotFound
  }

  def newCategoryItem(id: Int) = Action {
    implicit request => {
      if (categories.get(id).nonEmpty) {
        logger.error("Adding item for category [Id=%d].".format(id))
        val category: Category = categories.get(id).get

        itemForm.bindFromRequest.fold(
        formWithErrors => BadRequest(html.category.render(category, formWithErrors)), {
          case (number, name, zko, track, docility, defense) => {
            category.addItem(new Item(number, name, zko, track, docility, defense))
            Ok(html.category.render(category, itemForm))
          }
        }
        )
      }
      else {
        NotFound
      }
    }
  }

  def deleteCategoryItem(cId: Int, iId: Int) = Action {
    logger.error("Removing item [Id=%d] for category [Id=%d].".format(iId, cId))
    if (categories.get(cId).nonEmpty) {
      val category: Category = categories.get(cId).get

      val itemOption: Option[Boolean] = category.getAllItems().map(c => c.number == iId).headOption
      if(itemOption.nonEmpty) {
        category.removeItem(iId)
        Ok(html.category.render(category, itemForm))
      }
      else NotFound
    }
    else
      NotFound
  }
}