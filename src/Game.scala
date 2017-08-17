

object Game {

  def createEmptyField: Field = new Field

  private val gameOverHits: Int = 10

  def hit(attack: Player, defender: Player, pos: Point): (Player, Boolean) = {
    if (defender.myField.hit(pos)._2)
      (new Player(
        myField = attack.myField,
        opField = new Field(attack.opField.updateState(attack.opField.points, pos, pStates.hit)),
        name = attack.name), true)
    else
      (new Player(
        myField = attack.myField,
        opField = new Field(attack.opField.updateState(attack.opField.points, pos, pStates.miss)),
        name =   attack.name), false)
  }

  def checkGame(p1: Player, p2: Player): Char = {
    if (p1.opField.hits == gameOverHits &&
      p2.opField.hits == gameOverHits) gStates.draw
    else if (p1.opField.hits == gameOverHits) gStates.fWon
    else if (p2.opField.hits == gameOverHits) gStates.sWon
    else gStates.inProgress
  }
}