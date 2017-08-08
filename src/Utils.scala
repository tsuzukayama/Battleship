object pStates {
  def occupied: Char = 'o'
  def empty: Char = 'e'
  def hit: Char = 'h'
  def miss: Char = 'm'
}

object gStates {
  def inProgress: Char = 'i'
  def fWon: Char = 'f'
  def sWon: Char = 's'
  def draw: Char = 'd'
}


//desculpa, Professora
object Utils {
  def printFields(f1: Field, f2: Field, p1Name: String, p2Name: String): Unit = {
    val separator: String = "." * 30
    System.out.print(separator + p1Name)
    System.out.print("." * (separator.length() - p1Name.length()))
    System.out.println(p2Name + separator)
    System.out.print("|f|")
    for (n <- 0 until 10) System.out.print("|" + n + "|")
    print(0 until 10)
    System.out.print(separator)
    for (n <- 0 until 10) System.out.print("|" + n + "|")
    println()
    for (i <- 0 until 10) {
      System.out.print("|" + i + "|")
      for (j <- 0 until 10) {
        val p1 = f1.points(new Point(i, j)) match {
          case 'e' => "#"
          case 'o' => "o"
          case 'm' => '~'
          case 'h' => 'x'
        }
        System.out.print("|" + p1 + "|")
      }
      System.out.print(separator)
      for (j <- 0 until 10) {
        val p2 = f2.points(new Point(i, j)) match {
          case 'e' => "#"
          case 'o' => "o"
          case 'm' => '~'
          case 'h' => 'x'
        }
        System.out.print("|" + p2 + "|")
      }
      System.out.println("|" + i + "|")
    }
  }
}