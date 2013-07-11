package eu.podval

import java.awt.Point

class Objects {

  def method() = {
    val a = Account.NewAccount
    println(a.Id)
  }

  //  val aa = new Account;

  println(new Priority(DefaultPriority.default, DefaultPriority.default))
  println(DefaultPriority)
  println(Priority(2))
  println(CarTypes.SUV)
  println(CarTypes.SUV.id)

  for (e <- CarTypes.values) {
    println(e)
  }

  var sedan: CarTypes.Value = CarTypes.sedan
  println(Priority.get(DefaultPriority, CarTypes.SUV))

  println(CarTypes(2))
  println(CarTypes.withName("Combi"))

  println(Convertions.CmsToInches(10))
  println(CentrimetersToInches.Convert(10))
  println(Convertions.KilometersToMiles(15))
  println(LitersToGallons.Convert(15))
  println(Convertions.LitersToGallons(5))
  println(KilometersToMiles.Convert(5))

  println(Point(1, 3))

  for (c <- Cards.values) println(c)
}

object RGSCorners extends Enumeration {

  val Red = Value(0xff0000)

}

object Cards extends Enumeration {
  val Heart = Value("♥")
  val Diamonds = Value("\u2666")
  val Clubs = Value("\u2663")
  val Spades = Value("\u2660")
}

object Point {

  def apply(x: Int, y: Int): java.awt.Point = {
    new java.awt.Point(x, y)
  }
}

object Origin extends Point {

}

object CentrimetersToInches extends UnitConvertions {
  def Convert(v: Int): Double = v / 2.54
}

object LitersToGallons extends UnitConvertions {
  def Convert(v: Int): Double = v / 3.78
}

object KilometersToMiles extends UnitConvertions {
  def Convert(v: Int): Double = v / 1.609
}

abstract trait UnitConvertions {
  def Convert(v: Int): Double
}

object Convertions {

  def CmsToInches(centimeters: Int): Double = {
    centimeters / 2.54
  }

  def LitersToGallons(liters: Int): Double = {
    liters / 3.78
  }

  def KilometersToMiles(km: Int): Double = {
    km / 1.609
  }
}

object Account extends AccountBase {
  protected var counter: Int = 0

  private def getNextId(): Int = {
    counter += 1
    counter
  }

  def NewAccount: Account = {
    return new Account;
  }
}

class Account private() {
  val Id: Int = Account.getNextId()
}

abstract class AccountBase {
  protected var counter: Int;
}

class Priority(val min: Int, val max: Int) {
  override def toString: String = "Priority[Min=%d, Max=%d]".format(min, max)
}

object DefaultPriority extends Priority(-1, -1) {
  val default: Int = -1
}

object Priority {
  def apply(sameForAll: Int) = new Priority(sameForAll, sameForAll)

  def get(p: Priority, t: CarTypes.Value) = (p, t)
}

object CarTypes extends Enumeration {
  val SUV = Value(3, "esuvéčko")
  val sedan = Value(2)
  val VAN = Value(23)
  val Combi = Value(0)
}