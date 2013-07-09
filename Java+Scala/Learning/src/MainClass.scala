import scala.compat.Platform

object MainClass extends App {
  println("Executing Learning main class:")
  val cls = new Objects
  cls.method();
  printf("Finished in %d milliseconds.\n", Platform.currentTime - executionStart)
  printf("Input arguments (in reverse order): %s.", args.reverse.mkString(" "))
}
