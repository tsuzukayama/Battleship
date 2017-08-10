import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Failure, Success }
import scala.concurrent.Await
import scala.concurrent.duration._
import java.io._
import java.util.Scanner

object Main {
  def main(args: Array[String]) {

    println("Entre com o seu nome, jogador 1: ")

    val p1Name = scala.io.StdIn.readLine()
    val p1 = new Player(myField = posShips(new Field), name = p1Name)

    println("Entre com o seu nome, jogador 2: ")

    val p2Name = scala.io.StdIn.readLine()
    val p2 = new Player(myField = posShips(new Field), name = p2Name)

    startGame(p1, p2)

    val data: Future[List[String]] = Future {
       Utils.readFile("Ranking.txt") 
    } 

    data.recover {
      case e: FileNotFoundException => {
        List[String]()
      }
    }.onSuccess {
      case v => {
        val m = v(0).split(" ").toList.groupBy(i => i).mapValues(_.size)
        println("-----RANKING GERAL-----")        
        println(m.map(p => p._1 + " -> " + p._2 + " vitórias").mkString("\n"))
      }
    }
    Await.result(data, Duration.Inf)
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

  def startGame(p1: Player, p2: Player) {
    def go(aP: Player, dP: Player) {
      if (Game.checkGame(aP, dP) == gStates.draw) Utils.stateWin(new Player with PlayerDraw)
      else if (Game.checkGame(aP, dP) == gStates.fWon) Utils.stateWin(aP)
      else if (Game.checkGame(aP, dP) == gStates.sWon) Utils.stateWin(dP)
      else {
        Utils.printFields(aP.opField, dP.opField, aP.name, dP.name)
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