import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Failure, Success }
import scala.concurrent.Await
import scala.concurrent.duration._
import java.io._
import java.util.Scanner

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
    System.out.print(separator)
    for (n <- 0 until 10) System.out.print("|" + n + "|")
    println()
    for (i <- 0 until 10) {
      System.out.print("|" + i + "|")
      for (j <- 0 until 10) {
        val p1 = f1.points(new Point(i, j)) match {
          case 'e' => ' '
          case 'o' => 'o'
          case 'm' => '~'
          case 'h' => 'x'
        }
        System.out.print("|" + p1 + "|")
      }
      System.out.print(separator)
      for (j <- 0 until 10) {
        val p2 = f2.points(new Point(i, j)) match {
          case 'e' => ' '
          case 'o' => 'o'
          case 'm' => '~'
          case 'h' => 'x'
        }
        System.out.print("|" + p2 + "|")
      }
      System.out.println("|" + i + "|")
    }
  }

  def stateWin(p: Player) {
    if (p.isDraw)
      println("Empate :(")
    else {
      println(p.name + " ganhou!")
      val file = new File("Ranking.txt")
      file.createNewFile()
      val fileWriter = new FileWriter(file, true);
      val scanner = new Scanner(file)
      val bw = new BufferedWriter(fileWriter);

      bw.write(p.name + " ")
      bw.close()
    }    
  }

  def readFile(fileName: String) = {
    io.Source.fromFile(fileName).getLines.toList
  }
}