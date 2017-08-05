class Player (val myField: Field = null, val opField: Field = Game.createEmptyField, val name: String = "") {
    val isDraw = false;
    
    override def toString: String = "myField: " + myField + "\n opField: " + opField 
}

trait PlayerDraw extends Player{
  override val isDraw = true;
}


