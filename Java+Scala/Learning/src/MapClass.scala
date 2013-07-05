import java.util
import java.util.Scanner
import scala.collection.immutable.{SortedMap, HashMap}
import scala.collection.{JavaConversions, mutable}

class MapClass {

  def method() {
    var map: Map[Int, String] = Map(1 -> "ahoj", 3 -> """bla""", 4 -> "adfg")
    var map2 = new HashMap[Long, String]();
    var map3: mutable.HashMap[Long, String] = new scala.collection.mutable.HashMap[Long, String]()

    var hi: String = map(1)
    println(hi)
    //    var notExistent: String = map(222)

    println(map.getOrElse(3, "FO!"))
    println(map.getOrElse(45, "FO!"))

    var option: Option[String] = map.get(345)
    println("Option: " + option.isDefined)

    map3.put(12, "aaa")
    map3 = map3 - 3;

    println(map3.mkString(" "))
    for ((k, v) <- map)
      println(k + " " + v)

    val sm = SortedMap(33 -> "hello", 2 -> "die", 4 -> "kk")
    println(sm.mkString(" "))

    val t = (23, 34L, 34.5);
    val (age, salary, _) = t

    var partition: (String, String) = "Hey Man How".partition(_.isUpper)
    println(partition._1 + ":" + partition._2)

    val c = new mutable.HashMap[String, Int]()
    val s = new Scanner(new java.io.File( """c:\Data\Virtualization\hg-svn\hg-svn\net\All.sln"""));
    while (s.hasNext) {
      val n = s.next();
      if (c.contains(n)) c(n) += 1;
      else c.put(n, 1)
    }

    var array: Array[(Int, String)] = (for ((k, v) <- c) yield (v, k)).toArray
    var sorted = SortedMap.apply(array: _*)

    new util.TreeMap[Int, String]().putAll(JavaConversions.mutableMapAsJavaMap((for ((k, v) <- c) yield (v, k))))

    for ((k, v) <- sorted)
      println("Occurence: " + k + " - " + v)

    var map1: util.TreeMap[Int, String] = new util.TreeMap[Int, String](JavaConversions.mutableMapAsJavaMap((for ((k, v) <- c) yield (v, k))))
    for (k <- map1.keySet().toArray)
      println("Occurence TreeMap: " + k + " V: " + map1.get(k))
  }
}
