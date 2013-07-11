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

    method(d)
  }

  def method(p: Device) {

  }
}

class Iphone(val version: Int, override val name: String, override val color: Color)
  extends CellPhone("Apple", name, color) {

  override val anyField: Int = 4

  override val MethodToWrap = 5
}

class CellPhone(val brand: String, override val name: String, val color: Color) extends Device(name, "Cell Phone") {

  private var w: Date = _

  protected def SetWarrancy(t: Date) {
    w = t
  }

  override def MethodToWrap: Int = {
    println( """Calling "MethodToWrap".""")
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