abstract class Ship(val dir: Char, val len: Int)

case class shipOne(val dir: Char) extends Ship(dir, 1)
case class shipTwo(val dir: Char) extends Ship(dir, 2)
case class shipThree(val dir: Char) extends Ship(dir, 3)
case class shipFour(val dir: Char) extends Ship(dir, 4)
