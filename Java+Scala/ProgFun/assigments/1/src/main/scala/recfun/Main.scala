package recfun


object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c > r || r < 0 || c < 0) 0
    else if (c == 0) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean =
    chars.foldLeft(0)((o, ch) => ch match {
      case '(' => o + 1
      case ')' => if (o == 0) Int.MaxValue else o - 1
      case _ => o
    }) == 0

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int =
    if(money <= 0) 0
    else if (coins.isEmpty) 0
    else (0 to money / coins.head).map(x => {
      if (money == x * coins.head) 1
      else countChange(money - x * coins.head, coins.tail)
    }).sum
}
