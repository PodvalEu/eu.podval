package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 * - run the "test" command in the SBT console
 * - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   * - test
   * - ignore
   * - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   * val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect") {
    val i1 = intersect(i => i % 2 == 0, i => i % 3 == 0)
    assert(contains(i1, 0))
    assert(!contains(i1, 1))
    assert(contains(i1, 6))
    assert(!contains(i1, 7))
    assert(contains(i1, -12))

    val i2 = intersect(i1, i => i % 5 == 0)
    assert(!contains(i2, 32))
    assert(contains(i2, 30))
  }

  test("diff") {
    val d = diff(i => i < 35, i => i > 15)
    assert(contains(d, -125))
    assert(contains(d, 0))
    assert(contains(d, 15))
    assert(!contains(d, 16))
    assert(!contains(d, 45616))
  }

  test("filter") {
    val f = filter(i => Math.abs(i) > 5, i => i > -2)
    assert(!contains(f, 3))
    assert(contains(f, 6))
    assert(!contains(f, -6))
  }

  test("forall") {
    assert(forall(i => i > bound, i => false))
    assert(forall(i => i % 4 == 0, i => i % 2 == 0))
    assert(!forall(i => i % 2 == 0, i => i % 4 == 0))
    assert(forall(i => true, i => i <= bound))
  }

  test("exists") {
    assert(exists(i => i % 100 == 0, i => i > 968))
    assert(exists(i => i % 100 == 0, i => i < -968))
    assert(!exists(i => i % 713 == 0, i => i > 968))
  }

  test("map") {
/*    val m = map(i => i > -35, i => Math.abs(i))
    assert(!contains(m, 0))
    assert(contains(m, 315))
    assert(contains(m, -15))

    val m2 = map(i => i % 2 == 0, i => i * 2)
    assert(contains(m2, 0))
    assert(contains(m2, 1))
    assert(contains(m2, 457))
    assert(contains(m2, 131317))

    assert(contains(map(i => i == 1, i => i + 1), 0))
    assert(contains(map(i => i == 3, i => i + 1), 2))
    assert(contains(map(i => i == 4, i => i + 1), 3))
    assert(contains(map(i => i == 5, i => i + 1), 4))
    assert(contains(map(i => i == 7, i => i + 1), 6))
    assert(contains(map(i => i == 1000, i => i + 1), 999))

    assert(contains(map(i => i % 2 == 1, i => i - 1), 2))
    assert(!contains(map(i => i % 2 == 1, i => i - 1), 1))
  */
    println("...")
    assert(forall(map(i => true, i => i * 2), i => i % 2 == 0))

    val m3: Set = i => List(1, 3, 4, 5, 7, 1000).contains(i)
    assert(forall(map(m3, i => i - 1), i => List(0, 2, 3, 4, 6, 999).contains(i)))
  }
}
