package eu.podval

import scala.io.{BufferedSource, Source}
import java.io.{FileWriter, FileOutputStream, ObjectOutputStream, File}
import sys.process._
import scala.util.matching.Regex
import scala.util.Random

class FileTopic {
  def method() {
    val path: String = this.getClass.getProtectionDomain.getCodeSource.getLocation.getPath
    val evernote: String = path + "resources\\scala.evernote"
    val random: String = path + "\\resources\\random.dat";
    val powers: String = path + "\\resources\\powers.dat";
    val powersAnData: String = path + "\\resources\\powers-and-data.dat";

    println(path)
    println(fileCount(new File(path), "class"))
    System.exit(0)

    PrintAllSrcInImg("http://idnes.cz")
    ShowNoFloats(powersAnData)
    WritePowers(powers)
    CaculateStats(random, "Unicode")
    WriteRandomNumbers(random, 100)
    PrintWordsWithMoreChars(new File(evernote), "Unicode", 7)
    PrintReverse(new File(evernote), "Unicode")

    val anyObject: AnyObject = new AnyObject
    val stream: FileOutputStream = new FileOutputStream(path + "anyobject.bin")
    val objectOutputStream: ObjectOutputStream = new ObjectOutputStream(stream)
    objectOutputStream.writeObject(anyObject)
    objectOutputStream.flush()
    objectOutputStream.close()
    stream.close()

    val file: File = new File(path)
    for (f <- file.listFiles().filter(_.isDirectory)) {
      println(f)
    }

    val source: BufferedSource = Source.fromFile(evernote, "Unicode")

    //    for (line <- source.getLines()) {
    //      println(line)
    //    }

    //    for(character: Char <- source) {
    //      println(character)
    //    }

    val r: Regex = "\\w+".r
    val content: String = Source.fromURL( """http://idnes.cz""", "windows-1250").mkString.mkString
    val firstMatchIn: Option[Regex.Match] = r.findFirstMatchIn(content)
    if (!firstMatchIn.isEmpty) {
      println("First: " + firstMatchIn.get.matched)
    }

    val tokens = r.findAllIn(content)
    for (t <- tokens.map(_.toUpperCase)) {
      println(t)
    }

    val tags: Array[String] = Source.fromURL( """http://idnes.cz""", "windows-1250").mkString.split(" ")
    println(tags.length)
    //    for(m <- tags)
    //      println(m)

    source.close()
  }

  def PrintReverse(f: File, enc: String) {
    for (l: String <- Source.fromFile(f, enc).getLines().toArray.reverse) {
      println(l)
    }
  }

  def PrintWordsWithMoreChars(f: File, enc: String, minSize: Int) {
    for (t <- Source.fromFile(f, enc).mkString.split(" ").map(_.trim).filter(_.length > minSize).map(m => (m, m.length))) {
      println(t._1 + " l:%d".format(t._2))
    }
  }

  def WriteRandomNumbers(fileName: String, count: Int) {
    val file: File = new File(fileName)
    val writer: FileWriter = new FileWriter(file)

    1.to(count).map(_ => writer.write(Random.nextDouble().toString + " "))

    writer.flush()
    writer.close()
  }

  def CaculateStats(fileName: String, enc: String) = {
    val doubles: Array[Double] = Source.fromFile(fileName).mkString.split(" ").map(s => s.toDouble)
    val min: Double = doubles.foldLeft(1D)((min, d) => Math.min(min, d))
    val max: Double = doubles.foldLeft(0D)((max, d) => Math.max(max, d))
    val avg: Double = doubles.foldLeft(0D)((sum, d) => sum + d) / doubles.length

    println("Min: %f, Max: %f, Avg: %f.".format(min, max, avg))
  }

  def WritePowers(fileName: String) {
    val file: File = new File(fileName)
    val writer: FileWriter = new FileWriter(file)

    for (i <- 0 to 10) {
      writer.write("%f\t%f\n".format(Math.pow(2, i), 1 / Math.pow(2, i)));
    }

    writer.flush()
    writer.close()
  }

  def ShowNoFloats(fileName: String) {
    val content: String = Source.fromFile(fileName, "Windows-1250").mkString
    val pattern: Regex = """(?!([+-]?([0-9]*\.?[0-9]+)))\w+""".r
    for (value: String <- pattern.findAllIn(content)) {
      println(value)
    }
  }

  def PrintAllSrcInImg(url: String) {
    val regex: Regex = new Regex( """<img.*src=".*".*>""")
    val in: Iterator[Regex.Match] = regex.findAllMatchIn(Source.fromURL(url, "Windows-1250").mkString)
    for (m <- in) {
      println(m)
    }
  }

  def fileCount(file: File, extension: String): Int = {
    if (file.isDirectory) {
      file.list().foldLeft(0)((s, item) => s + fileCount(new File(file, item), extension))
    }
    else {
      if (file.getName.dropWhile(_ != '.').equals(".class")) 1
      else 0
    }
  }
}

class AnyObject extends Serializable {
  val anyValue: Double = 45;
  val longString: String = "aaaalksjhflksjfljs"
}