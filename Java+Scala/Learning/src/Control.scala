import java.io.FileNotFoundException
import scala.io.Source

/**
 * Created with IntelliJ IDEA.
 * User: Martin
 * Date: 7/2/13
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 */
class Control {

  def method() {
    def signum(v: Int): Int = {
      if (v == 0) 0;
      else
      if (v > 0) 1
      else -1
    }
    println(signum(7))

    var x = 7;
    var z = ();

    z = x = 5;

    var r = 10;
    while(r > 0) {
      println(r)
      r=r-1;
    }

    for(i <- Range.inclusive(10,1,-1)) {
      println("L: " + i)
    }

    def countdown(i: Int): Unit = {
      println("C: " + i)
      if(i >0)
        countdown(i-1);
    }
    countdown(34)

    def unicodeProduct(v: String) :Long = {
      var array: Array[Char] = v.toCharArray
      array.foldLeft(1L)((a,b) => {println("b: " + b.toInt); a * b.toInt});
    }

    println("Product: " + unicodeProduct("hello"));


  }

  def method5() {
    lazy val str = Source.fromFile("not-existing-file.txt").mkString;

    try {
      if (str != "a")
        throw new IllegalArgumentException;
      println(str);
    } catch {
      case _: NullPointerException => println("nullll")
      case ex: IndexOutOfBoundsException => println("Out of bound " + ex)
      case _: FileNotFoundException => println("fnf")
    }
    finally {

    }
  }

  def method4() {

    def nothingToDo(): Unit = {};
    nothingToDo()

    def recSum(arg: Int*): Int = {
      if (arg.length == 0) {
        return 0;
      }
      else {
        return arg.head + recSum(arg.tail: _*);
      }
    }

    println(recSum(1, 3, 4, 6, 444));

    def sumVarargs(arg: Long*) = {
      var sum: Long = 0;
      for (v <- arg) {
        sum = sum.+(v);
      }
      sum
    }

    println(sumVarargs(1, 3, 4, 6, 444));

    def addAndPercentage(v1: Long, v2: Long = 0, percentageChar: Char = '%') = {
      (v1 + v2).toString + percentageChar
    }

    println(addAndPercentage(v1 = 154L, percentageChar = '/', v2 = 12L));

    def myFc(i: Int): Int = {
      return i;
      //      i
    };

    val value = myFc(4);
    println( """Result of myFce: """ + value);
  }

  def method3() {
    for (i <- 1.to(12)) {
      println(i)
    }

    val s = "my string";

    //    for (i <- 0 to s.length - 1) {
    for (i <- 0 until s.length) {
      println(s.charAt(i));
    }

    for (ch <- s) println(ch.toUpper);

    for (t <- 1 until 15; z = 4; x = 15) {
      println(t + " " + z + " " + x)
    }

    println("New:")
    for (i <- -3 until 4; j <- -10 until i)
      println(i + " " + j)

    var s1: String = for (ch <- "Hi there!") yield return ch
  }

  def method1() {
    val x = 5;
    val v = if (x < 5) {
      val v2: Int = method2
      return v2 + 3
    } else 7;
    println(v)

    val value: AnyVal = if (v > 3) 6 else ()
    value;

    val v3 = 5 +
      +3
    printf("value is %d\n", v3)

    var name: String = readLine("Give me your name:")
    var age: Int = readLine("Gime me your age:").toInt

    printf("Your name is %s and you are %d years old.", name, age)
  }

  def method2: Int = {
    print(333);
    val r = 5;
    if (r < 3)
      return 55;
    print(333);
    return 7
  }
}
