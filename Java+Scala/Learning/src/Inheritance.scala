package eu.podval

import java.util.Date
import java.awt.Color

class Inheritance {

  def method() {
    var iphone: Iphone = new Iphone(3, "muj iphone", Color.CYAN)
    iphone.MethodToWrap

    println(iphone.isInstanceOf[CellPhone])
    println(iphone.getClass == classOf[CellPhone])
    println(iphone.getClass == classOf[Iphone])
    println(iphone.asInstanceOf[Device])
    println(null.asInstanceOf[Device])
    //    println(5.asInstanceOf[Device])

    val d = new Device("computer") {
      val anyField: Int = 5;
      val color: Color = Color.MAGENTA

      protected def SetWarrancy(t: Date) {}

      def MethodToWrap: Int = 12
    };

    method(d);
    val list: List[Nil.type] = List(Nil)
  }

  def method(p: Device) {

  }
}

class Iphone(val version: Int, override val name: String, override val color: Color)
  extends CellPhone("Apple", name, color) {

  override val anyField: Int = 4

  override val MethodToWrap = 5

  override def equals(obj: Any): Boolean = {
    val iphone: Iphone = obj.asInstanceOf[Iphone]
    if (iphone == null) false
    else anyField == iphone.anyField && version == iphone.version && name == iphone.name
  }
}

class CellPhone(val brand: String, override val name: String, val color: Color) extends Device(name, "Cell Phone") {

  private var w: Date = _

  protected def SetWarrancy(t: Date) {
    w = t
  }

  override def MethodToWrap: Int = {
    println( """Calling "MethodToWrap".""")
    this.synchronized {
      this.w = new Date()
    }
    45
  }

  override val anyField: Int = 5
}

private[podval] abstract class Device(val name: String) {

  val anyField: Int
  protected var owner: String = _
  val color: Color

  protected def this(name: String, deviceType: String) {
    this(name)
  }

  protected def SetWarrancy(t: Date);

  final def FinalMethod() {

  }

  def MethodToWrap: Int
}

class AnotherBankAccount(initValue: Double) {
  private var balance: Double = initValue

  def deposit(value: Double) = {
    balance = balance + value
  }

  def withDraw(value: Double) = {
    balance = balance - value
  }
}

class CheckingAccount(initValue: Double) extends AnotherBankAccount(initValue) {
  override def deposit(value: Double) = {
    super.deposit(value)
    super.withDraw(1);
  }

  override def withDraw(value: Double) {
    super.withDraw(value + 1)
  }
}

class SavingsAccount(initValue: Double, monthInterest: Double) extends CheckingAccount(initValue) {
  private var operationsCount = 0;

  override def deposit(value: Double) {
    super.deposit(value)
    operationsCount += 1
  }

  override def withDraw(value: Double) {
    super.withDraw(value)
    operationsCount += 1
  }

  def earnMonthlyInterests() {
    if (operationsCount > 3) {
      withDraw(monthInterest)
    }
    operationsCount = 0
  }
}

abstract class Item {
  def Price: Double

  def Description: String
}

class SimpleItem(price: Double, desc: String) extends Item {
  val Price: Double = price
  val Description: String = desc
}

class Bundle(items: Array[Item]) extends Item {
  private val Items: List[Item] = items.toList

  override def Price: Double = {
    Items.foldLeft(0D)((sum, item) => sum + item.Price)
  }

  def Description: String = "Bundle of items %d".format(Items.size)
}

class MyPoint(val x: Int, val y: Int) {}

class DescribedPoint(override val x: Int, override val y: Int, val desc: String) extends MyPoint(x, y)

abstract class Shape {
  def centerPoint: MyPoint
}

class Rectangle(val leftUp: MyPoint, val height: Int, val width: Int) extends Shape {
  def centerPoint: MyPoint = new MyPoint((leftUp.x + height / 2), (leftUp.y + width / 2))
}

class Square(val centerPoint: MyPoint, val w: Int) extends java.awt.Rectangle(centerPoint.x, centerPoint.y, w, w) {
  def this(width: Int) {
    this(new MyPoint(0, 0), width)
  }

  def this() = this(0)
}

class Creature() {
  def range: Int = 10
  val env: Array[Int] = Array[Int](range)
}

class Ant extends Creature {
  override def range: Int = 2;
}