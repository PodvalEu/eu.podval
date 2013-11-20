package quickcheck


import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  property("min1") = forAll {
    a: Int =>
      val h = insert(a, empty)
      findMin(h) == a
  }

  property("minOfHigherAdded") = forAll {
    (h: H) =>
      val min = findMin(h)
      if (min < Int.MaxValue)
        findMin(insert(min + 1, h)) == min
      else
        findMin(insert(min, h)) == min
  }

  property("removeMinByMin") = forAll {
    (h: H) => {
      var copy = h
      var local = Int.MinValue
      var result: Boolean = true
      while (!isEmpty(copy)) {
        result = (findMin(copy) >= local) && result
        local = findMin(copy)
        copy = deleteMin(copy)
      }
      result
    }
  }

  property("removeLastMin") = forAll {
    a: Int =>
      isEmpty(deleteMin(insert(a, empty)))
  }

  property("meldNonEmptyAndEmpty") = forAll {
    (h: H) => {
      findMin(meld(empty, h)) == findMin(h)
    }
  }

  property("meldTwoEmpty") = forAll {
    (h: H) => {
      isEmpty(meld(empty, empty))
    }
  }

  property("meldTwoNonEmptiesMinus") = forAll {
    (h: H) => {
      val local: QuickCheckHeap.this.type#A = findMin(h)
      if (local > Int.MinValue)
        findMin(meld(insert(local - 1, empty), h)) == local - 1
      else true
    }
  }

  property("meldTwoNonEmptiesPlus") = forAll {
    (h: H) => {
      val local: QuickCheckHeap.this.type#A = findMin(h)
      if (local < Int.MaxValue)
        findMin(meld(h, insert(local + 1, empty))) == local
      else true
    }
  }

  lazy val genHeap: Gen[H] = for {
    v <- arbitrary[A]
    m <- oneOf(value(empty), genHeap)
  } yield insert(v, m)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

}
