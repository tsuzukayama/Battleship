object Main {
  def main(args: Array[String]) {

    val p1 = new Player(posShips(new Field))

  }

  def posShips(f: Field): Field = {
    def go(f: Field): Field = {
      if (f.ships.size >= 4) f
      else {
        println("Adicione o navio de tamanho " + (f.ships.size + 1))

        val x = scala.io.StdIn.readInt()
        val y = scala.io.StdIn.readInt()
        val dir = scala.io.StdIn.readChar()
        f.ships.size + 1 match {
          case 1 => go(f.placeShip(new shipOne(dir), new Point(x, y)))
          case 2 => go(f.placeShip(new shipTwo(dir), new Point(x, y)))
          case 3 => go(f.placeShip(new shipThree(dir), new Point(x, y)))
          case 4 => go(f.placeShip(new shipFour(dir), new Point(x, y)))          
        }
      }
    }
    val a = go(f)
    println(a)
    a
  }
}