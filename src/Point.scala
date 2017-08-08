class Point(val x: Int, val y: Int) {

  def canEqual(a: Any) = a.isInstanceOf[Point]

  def compare(p: Point): Boolean = this.x == p.x && this.y == p.y

  override def equals(that: Any): Boolean = 
    that match {
      case that: Point => this.x == that.x && this.y == that.y
      case _ => false
    } 

  override def toString: String = "|" + x + ", " + y + "|"

  override def hashCode() = (x.hashCode() * 10) * y.hashCode()
}