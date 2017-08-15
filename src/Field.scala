class Field(val points: Map[Point, Char] = (for (
              i <- 0 until 10;
              j <- 0 until 10
            ) yield new Point(i, j)) map (p => p -> pStates.empty) toMap,
            val ships: Map[Point, Ship] = Map()) {

  def updateState(points: Map[Point, Char], pos: Point, state: Char): Map[Point, Char] = points + (pos -> state)

  def hits: Int = points.values.count(p => p == 'h')

  override def toString: String = points + "\n" + ships

  // Atualiza os Status das posições afetadas pelo novo navio
  def updateStatus(ship: Ship, pos: Point): Field = {
    def go(points: Map[Point, Char], npos: Point, acc: Int): Field = {
      if (acc == ship.len) new Field(points = points)
      else go(updateState(points, npos, 'o'), ship.dir match {
        case 'h' => new Point(npos.x + 1, npos.y)
        case 'v' => new Point(npos.x, npos.y + 1)
      }, acc + 1)

    }
    go(this.points, pos, 0)
  }

  def placeShip(ship: Ship, thatPos: Point): Field = {
    def go(ships: Map[Point, Ship], ship: Ship, pos: Point, acc: Int): Field = {
      if (acc == ship.len) {
        val f = new Field(points = updateStatus(ship, thatPos).points, ships = ships.updated(thatPos, ship))
        //println(f.points)
        f
      } else if (pos.x < 0 || pos.y < 0 || pos.x >= 10 || pos.y >= 10) this

      else if (this.points.exists(p => p._1.compare(pos) && p._2 != 'e'))
        this

      else go(ships, ship, ship.dir match {
        case 'h' => new Point(pos.x + 1, pos.y)
        case 'v' => new Point(pos.x, pos.y + 1)
      }, acc + 1)
    }
    go(this.ships, ship, thatPos, 0)
  }

  def hit(pos: Point): (Field, Boolean) = {
    if (points.exists(p => p._1.compare(pos) && (p._2 == 'o' || p._2 == 'h')))
      (new Field(points = points.updated(pos, 'h')), true)
    else (new Field(points = points.updated(pos, 'm')), false)
  }
}