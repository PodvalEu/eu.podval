package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import eu.podval.psi.model.Category
import views._
import scala.collection.mutable

object Application extends Controller {
  val categories = new mutable.HashMap[Int, Category]()

  val categoryForm = Form(
    ("name" -> nonEmptyText)
  )

  def index = Action {
    Redirect("/category", MOVED_PERMANENTLY)
  }

  def category = Action {
    Ok(views.html.index(categories.values.toList, categoryForm))
  }

  def newCategory = Action {
    implicit request => {
      categoryForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.index(categories.values.toList, formWithErrors)), {
        case (name) => {
          categories.put(categories.keySet.max, new Category(categories.size, name))
          Ok(html.index(categories.values.toList, categoryForm))
        }
      }
      )
    }
  }

  def deleteCategory(id: Int) = Action {
    categories.remove(id)
    Ok(html.index(categories.values.toList, categoryForm))
  }
}