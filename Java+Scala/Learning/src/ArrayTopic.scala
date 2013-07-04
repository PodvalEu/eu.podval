import scala.collection.mutable.ArrayBuffer

/**
 * Created with IntelliJ IDEA.
 * User: Martin
 * Date: 7/3/13
 * Time: 4:55 PM
 * To change this template use File | Settings | File Templates.
 */
class ArrayTopic {
  def method() {
    var array: scala.Array[Int] = Array(1, 2, 3, 4, 66, 7)
    val array2 = new Array[Int](10);
    array2(8) = 16;

    val b = new ArrayBuffer[Long]();
    b += 1;
    b += (3,6,890);
    b.insert(2, 12);
    var g = b.groupBy(v => v%2)
    for(gg <- g) {
       println("Key: " + gg._1 + " values: " + gg._2.mkString(" "))
    }

    println(b.mkString(" "))

//    for(i <- 0 to b.length) {
    for(i <- 0.to(b.length,2).reverse) {
      println(b(i))
    }
  }
}
