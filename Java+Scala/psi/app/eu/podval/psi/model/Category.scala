package eu.podval.psi.model

import org.apache.commons.lang3.Validate
import play.api.i18n.Messages
import scala.collection.mutable
import org.slf4j.{LoggerFactory, Logger}

class Category(val id: Int, val name: String) extends NotNull {
  val logger: Logger = LoggerFactory.getLogger("Category");
  Validate.isTrue(id >= 0, Messages("category.create.idHigher0"))
  Validate.isTrue(name != null && !name.isEmpty, Messages("category.create.nameMustBeFilled"))
  val items = new mutable.HashMap[Int, Item]()

  def addItem(item: Item) = {
    if (logger.isDebugEnabled) logger.debug("Adding item [%s] to category [Name=[%s].".format(item, name))
    items.put(item.number, item)
  }

  def removeItem(iId: Int) = {
    if (logger.isDebugEnabled) logger.debug("Removing item [Id=%d] from category [ID=%d].".format(iId, id))
    if (items.contains(iId)) {
      items.remove(iId)
    }
    else {
      throw new IllegalArgumentException("Can't remove item [Id=%d] from category [Id=%d], it does not exist.".format(id, this.id))
    }
  }

  def getAllItems() = items.values

  override def toString: String = {
    val builder: StringBuilder = new mutable.StringBuilder("Category [Name=%s], Items=".format(name))
    items.values.foreach(i => builder.append(i))
    builder.toString()
  }
}
