object Main {
  def main(args: Array[String]) {
    println("Entre com o seu nome, jogador 1: ")
    val p1Name = scala.io.StdIn.readLine()
    val p1 = new Player(myField = posShips(new Field), name = p1Name)
    println("Entre com o seu nome, jogador 2: ")
    val p2Name = scala.io.StdIn.readLine()
    val p2 = new Player(myField = posShips(new Field), name = p2Name)
    
    startGame(p1, p2)
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
    go(f)
  }
  
  def startGame(p1: Player, p2: Player): Player = {
    def go(aP: Player, dP: Player): Player = {
      if(Game.checkGame(aP, dP) == gStates.draw) new Player with PlayerDraw
      if(Game.checkGame(aP, dP) == gStates.fWon) aP
      if(Game.checkGame(aP, dP) == gStates.sWon) dP
      else {
        println(aP.name + ", sua vez: ")
        val hitP = new Point(scala.io.StdIn.readInt(), scala.io.StdIn.readInt())
        val gameHit = Game.hit(aP, dP, hitP)
        if (gameHit._2) println("Acertou")
        else println("Errou")
        go(dP, gameHit._1)
      }
    }
    go(p1, p2)
  }
}