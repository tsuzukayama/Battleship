abstract class Ship(val dir: Char, val len: Int)

class ShipOne(override val dir: Char) extends Ship(dir, 1)
class ShipTwo(override val dir: Char) extends Ship(dir, 2)
class ShipThree(override val dir: Char) extends Ship(dir, 3)
class ShipFour(override val dir: Char) extends Ship(dir, 4)
