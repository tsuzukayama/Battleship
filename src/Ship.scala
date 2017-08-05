abstract class Ship  (val dir: Char, val len: Int)

case class shipOne   (override val dir: Char) extends Ship(dir, 1)
case class shipTwo   (override val dir: Char) extends Ship(dir, 2)
case class shipThree (override val dir: Char) extends Ship(dir, 3)
case class shipFour  (override val dir: Char) extends Ship(dir, 4)
