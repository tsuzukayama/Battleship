class Field(val points: Map[Point, Char] = (for (
              i <- 0 to 10;
              j <- 0 to 10
            ) yield new Point(i, j)) map (p => p -> pStates.empty) toMap,
            val ships: Map[Point, Ship] = Map()) {

  def updateState(pos: Point, state: Char): Field = new Field(points = points + (pos -> state))

  def hits: Int = points.values.count(p => p == 'h')

  // Atualiza os Status das posições afetadas pelo novo navio
  def updateStatus(ship: Ship, pos: Point): Field = {
    def go(points: Map[Point, Char], pos: Point, acc: Int): Field = {
      if (acc == ship.len) new Field(points = points)
      else updateState(pos, 'o')
    }
    go(this.points, pos, 0)
  }

  def placeShip(ship: Ship, pos: Point): Field = {
    def go(ships: Map[Point, Ship], ship: Ship, pos: Point, acc: Int): Field = {
      if (acc == ship.len) new Field(points = updateStatus(ship, pos).points, ships.updated(pos, ship))
      
      else if (pos.x < 0 || pos.y < 0 || pos.x >= 10 || pos.y >= 10) this
      
      else if (points.get(pos) != Some(pStates.empty)) this
      
      else go(ships, ship, ship.dir match {
        case 'H' => new Point(pos.x + 1, pos.y)
        case 'V' => new Point(pos.x, pos.y + 1)
      }, acc + 1)
    }
    go(this.ships, ship, pos, 0)
  }
  
  def hit(pos: Point): (Field,Boolean) = {
    val point = points.getOrElse(pos, 'm')
    if (point == 'o' || point == 'h') (new Field(points = points.updated(pos, 'h')), true)
    else (new Field(points = points.updated(pos, 'm')), false)
  }
}