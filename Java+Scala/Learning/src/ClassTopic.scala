package eu.podval

import scala.reflect.BeanProperty

class ClassTopic {

  def method() {
    var c = new MyClass
    val x = c.Value;
    c.Value = 5;

    c.getValue3;
    //  c.value3 = 15;
    val z = c.getValue3;

    val a = new Address("Žižkova", 349, "Velký Osek");
    //    val aa = new Address(null, 349, "Velký Osek");
    println(a.desc)

    val ooo = new MyOtherClass
    val o = new MyOtherClass("", 2);
    //    o.setV(15)

    val ou1 = new Outer
    val ou2 = new Outer

    var i1 = ou1.CreateInner
    var i2 = ou2.CreateInner
    var i3 = ou1.CreateInner
    i3 = ou1.CreateInner
    var i4: Outer#Inner = ou1.CreateInner

    //    ou1.array(0) = ou2.CreateInner
    ou1.compatibleArray(0) = ou2.CreateInner
    ou1.compatibleArray(0) = new Outer().CreateInner

    println(new Outer("aaa").CreateInner.giveMyOuterV)

    val ba = new BankAccount(150)
    println(ba.balanceValue)
    ba.deposit(500)
    println(ba.balanceValue)
    //    ba.withdraw(1455)

    println(new Time(12, 15).beforeGivenTime(new Time(11, 45)))

    val zz = new Person("veruska durdova")
  }
}

class MyClass {

  private var value: Int = 0;

  private def MyClass {

  }

  def MyClass(value: Int) {
    this.value = value
  }

  def SetValue(value: Int) {
    this.value = value
  }

  def GetValue(): Int = {
    return value;
  }

  def Value = value;

  def Value_=(value: Int) {
    this.value = value
  }

  //  var value2: Int;

  //  def ReSetMyClassValue2(c: MyClass) {
  //        c.value2 = 0;
  //  }

  @BeanProperty
  var value3: Int = 0;

  /*
    def getValue3(): Int = {
      return value;
    }

    def value3_=(newValue:Int) {
      value3 = newValue
    }*/
}

class Address(val street: String, val nu: Int, val town: String) {
  if (street == null) {
    throw new IllegalArgumentException
  }
  println("ctor")

  def desc = "Address: " + street
}

class MyOtherClass private(@BeanProperty var v: Int) {

  def this(v: String, v2: Int) {
    this(15)
    this
  }

  def this() = {
    this(15)
  }
}

class Outer {
  outer =>

  var v: String = _

  def this(v: String) {
    this
    this.v = v;
  }

  class Inner {
    def giveMyOuterV = {
      outer.v
    }
  }

  def CreateInner = {
    new Inner
  }

  val array: Array[Inner] = new Array[Inner](10)
  val compatibleArray: Array[Outer#Inner] = new Array[Outer#Inner](10)
}

class BankAccount(private var balance: Int) {
  validateIsPositive(balance)

  private def validateIsPositive(value: Int) {
    if (value < 0) throw new IllegalArgumentException
  }

  def deposit(amount: Int): Int = {
    validateIsPositive(amount)
    balance += amount
    balance
  }

  def withdraw(amount: Int): Int = {
    if (balance < amount) throw new IllegalArgumentException
    balance -= amount
    balance
  }

  def balanceValue = balance
}

class Time(hours: Int, minutes: Int) {
  if (hours < 0 || hours > 23) throw new IllegalArgumentException
  if (minutes < 0 || minutes > 59) throw new IllegalArgumentException
  timeInMinutes = hours * 60 + minutes

  private var timeInMinutes: Int = _;

  def beforeGivenTime(other: Time): Boolean = {
    return timeInMinutes > other.timeInMinutes
  }
}

class Student(@BeanProperty val name: String, @BeanProperty var id: Long) {

}

class Person
//(val name: String, val surname: String) {
(wholeName: String) {
  val (name, surname) = split(wholeName)

  /*def this(wholeName: String) {
    this({
      val split = name.split(' ')
      if (split.length != 2) throw new IllegalArgumentException
      (split(0), split(1))
    }"","")
  }  */
  //    this(wholeName.split(' ')(0), wholeName.split(' ')(1))
  //    if (split.length != 2) throw new IllegalArgumentException


  def split(name: String) = {
    var split = name.split(' ')
    if (split.length != 2) throw new IllegalArgumentException
    (split(0), split(1))
  }

  //  val name = {wholeName.split(' ')(0)}
  //  val surname = {wholeName.split(' ')(1)}

  /* var split: Array[String] = wholeName.split(' ')
   if(split.length != 2) throw new IllegalArgumentException
   val name = split(0);
   val surname = split(1);
    */

  //  override def toString: String = wholeName
}

class Car(val manufacturer: String, val modelName: String, val year: Int, var licencePlate: String) {

  def this(manufacturer: String, modelName: String) = this(manufacturer, modelName, -1, "")

  def this(manufacturer: String, modelName: String, licencePlate: String) = this(manufacturer, modelName, -1, licencePlate)

  def this(manufacturer: String, modelName: String, year: Int) = this(manufacturer, modelName, year, "")

}

class Employee(val name: String, val salary: Double) {
  def this() = this("Karel Gott", 456789.45)
}

class Employee3(val name: String = "Karel Gott", val salary: Double = 456412.45) {
}

class Employee2 {

}