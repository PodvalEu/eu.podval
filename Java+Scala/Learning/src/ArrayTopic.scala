package eu.podval {

import scala.collection.mutable.ArrayBuffer
import java.awt.datatransfer._

class ArrayTopic {
  def method() {
    var array: scala.Array[Int] = Array(1, 2, 3, 4, 66, 7)
    val array2 = new Array[Int](10);
    array2(8) = 16;

    val b = new ArrayBuffer[Long]();
    b += 1;
    b +=(3, 6, -13, 5, -12, 890, -78, 4, -8, -9);
    b.insert(2, 12);
    var g = b.groupBy(v => v % 2)
    for (gg <- g) {
      println("Key: " + gg._1 + " values: " + gg._2.mkString(" "))
    }

    println(b.mkString(" "))
    transform(b.toArray)
    math(b.toArray)
    multi()

    println("swap")
    val arr = Array(1, 6, 4, 8, 9);
    swapNeighbors(arr)
    println(arr.mkString(" "))
    println(swapYield(arr).mkString(" "))

    println(positivesFollowedByNegatives(Array(1, 2, -5, -83, 4, -8, 6)).mkString(" "))

    val doubleArray = Array(3.45, 5.23, 5.90, 3.45, 6, 6)
    println(doubleArray.sum / doubleArray.length)

    println(doubleArray.distinct.mkString(" "))

    val america = "America/";
    val americas = for (z <- java.util.TimeZone.getAvailableIDs if z.startsWith(america)) yield z.substring(america.length - 1)
    println(americas.sorted.mkString(" "));

    val flw = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
    println("Image flavors: " + flw.getNativesForFlavor(DataFlavor.imageFlavor).toArray.mkString(" "))
  }

  def positivesFollowedByNegatives(a: Array[Int]): Array[Int] = {
    val positives = new ArrayBuffer[Int]();
    val negatives = new ArrayBuffer[Int]();

    for (v <- a) {
      if (v >= 0) positives += v
      else negatives += v
    }

    positives.appendAll(negatives)
    positives.toArray
  }

  def swapNeighbors(a: Array[Int]) {
    for (i <- 0.until(a.length - 1, 2)) {
      a(i) = a(i) + a(i + 1)
      a(i + 1) = a(i) - a(i + 1);
      a(i) = a(i) - a(i + 1)
    }
  }

  def swapYield(a: Array[Int]): Array[Int] = {
    (for (i <- 0 to a.length - 1)
    yield if (i < a.length - 1) if (i % 2 == 0) a(i + 1) else a(i - 1)
      else a(i)).toArray
  }

  def transform(a: Array[Long]) {
    val aa = {
      for (i: Long <- a.toBuffer) yield i % 10
    };
    println(aa.mkString(" "))

    println("%3")
    for (i <- a if i % 3 == 1) println(i)

    var fa = a.filter(_ % 3 == 1).map(1 * _)
    println("filter")
    println(fa.mkString(" "))

    println("First negative and only positives:")
    val ba = a.toBuffer;
    val negativeIndexes = for (i <- 0 to ba.length - 1 if (ba(i) < 0)) yield i;
    println("Negative indexes: " + negativeIndexes.mkString(" "))
    for (i <- (1 to negativeIndexes.length - 1).reverse) ba.remove(negativeIndexes(i))

    println("Original: " + a.mkString(" "))
    println("W/o negatives on second and next positions: " + ba.mkString(" "))
  }

  def math(a: Array[Long]) {
    println("math:")
    println(a.min)

    println(a.sortWith((a, b) => a < b).mkString(" "))

    scala.util.Sorting.quickSort(a)
    println(a.mkString(" "))

    println(a.count(_ > 3))
  }

  def multi() {
    var ints: Array[Array[Int]] = new Array[Array[Int]](10)
    var ints1: Array[Array[Int]] = Array.ofDim[Int](5, 5)
    ints1(3)(4) = 5;

    AnyJavaClass.ProcessArray(new Array[Int](5));
    var array: Array[Integer] = AnyJavaClass.GetArray()
  }
}

}