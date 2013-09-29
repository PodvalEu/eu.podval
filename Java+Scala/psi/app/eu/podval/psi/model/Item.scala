package eu.podval.psi.model

import org.apache.commons.lang3.Validate
import play.api.i18n.Messages

class Item(val number: Int, val name: String, val zko: Int, val track: Int, val docility: Int, val defense: Int) extends NotNull{
  Validate.isTrue(number >= 0, Messages("item.create.numberHigher0"))
  Validate.isTrue(name != null && !name.isEmpty, Messages("item.create.nameNotNullOrEmpty"))
  Validate.isTrue(zko > 0, Messages("item.create.zkoHigher0"))
  Validate.isTrue(track > 0, Messages("item.create.trackHigher0"))
  Validate.isTrue(docility > 0, Messages("item.create.docilityHigher0"))
  Validate.isTrue(defense > 0, Messages("item.create.defenseHigher0"))
  val overallScore: Int = zko + track + docility + defense
}
