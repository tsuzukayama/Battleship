import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Failure, Success }
import scala.concurrent.Await
import scala.concurrent.duration._
import java.io._
import java.util.Scanner

object Main extends App {
  val scanner = new java.util.Scanner(System.in)


    def repeatLoop(body: => Unit) = new Until(body)
    repeatLoop {

      println("Entre com o seu nome, jogador 1: ")

      val p1Name = scanner.next()

      val p1 = new Player(myField = posShips(new Field), name = p1Name)

      println("Entre com o seu nome, jogador 2: ")

      val p2Name = scanner.next()
      val p2 = new Player(myField = posShips(new Field), name = p2Name)

      startGame(p1, p2)

      val data: Future[String] = Future {
        Utils.readFile("Ranking.txt")
      }

      data.onComplete {
        case Success(v: String) => {
          println("--------RAKING GERAL--------")
          println(v)
          println("\nJogar de novo?")
        }
        case Failure(e) => e.printStackTrace()
        case _          => println("Erro desconhecido")
      }
      Await.result(data, Duration.Inf)
      
    } until (scanner.next != "n")

  def posShips(f: Field): Field = {
    def go(f: Field): Field = {
      if (f.ships.size >= 4) f
      else {
        println("Adicione o navio de tamanho " + (f.ships.size + 1))
        val x = readPosition()
        val y = readPosition()
        val dir = readDirection()
        f.ships.size + 1 match {
          case 1 => go(f.placeShip(f.updateStatus, new ShipOne(dir), new Point(x, y)))
          case 2 => go(f.placeShip(f.updateStatus, new ShipTwo(dir), new Point(x, y)))
          case 3 => go(f.placeShip(f.updateStatus, new ShipThree(dir), new Point(x, y)))
          case 4 => go(f.placeShip(f.updateStatus, new ShipFour(dir), new Point(x, y)))
        }
      }
    }
    go(f)
  }
  
  def readPosition(): Int = 
    if (scanner.hasNextInt())
      scanner.nextInt()
    else {
      scanner.next()
      readPosition()
    }
  
  def readDirection(): Char = {
    val dir = scanner.next()
    if (dir == "h" || dir == "v")
      dir.charAt(0)
    else
      readDirection()
  }
  
  def startGame(p1: Player, p2: Player) {
    def go(aP: Player, dP: Player) {
      if (Game.checkGame(aP, dP) == gStates.draw) Utils.stateWin(new Player with PlayerDraw)
      else if (Game.checkGame(aP, dP) == gStates.fWon) Utils.stateWin(aP)
      else if (Game.checkGame(aP, dP) == gStates.sWon) Utils.stateWin(dP)
      else {
        Utils.printFields(aP.opField, dP.opField, aP.name, dP.name)
        println(aP.name + ", sua vez: ")
        val hitP = new Point(readPosition(), readPosition())
        val gameHit = Game.hit(aP, dP, hitP)
        if (gameHit._2) println("Acertou")
        else println("Errou")
        go(dP, gameHit._1)
      }
    }
    go(p1, p2)
  }
}