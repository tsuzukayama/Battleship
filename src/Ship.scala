abstract class Ship(val dir: Char, val len: Int)

case class ShipOne(override val dir: Char) extends Ship(dir, 1)
case class ShipTwo(override val dir: Char) extends Ship(dir, 2)
case class ShipThree(override val dir: Char) extends Ship(dir, 3)
case class ShipFour(override val dir: Char) extends Ship(dir, 4)
