

object Game {
  
  def createEmptyField: Field = new Field
  
  private def gameOverHits: Int = 4 + 3 + 2 + 1
  
  def hit(attack: Player, defender: Player, pos: Point): Field = {
    if (defender.myField.hit(pos)._2)
      attack.opField.hit(pos)._1
    else attack.opField
  }
  
  def checkGame(p1: Player, p2: Player): Char = {
    if (p1.opField.hits == gameOverHits &&
        p2.opField.hits == gameOverHits) gStates.draw
    else if (p1.opField.hits == gameOverHits) gStates.fWon
    else if (p2.opField.hits == gameOverHits) gStates.sWon
    else gStates.inProgress
  }
}