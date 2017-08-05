class Point(val x: Int, val y: Int) {
    
    def compare(p: Point): Boolean = this.x == p.x && this.y == p.y
    
    override def toString: String = "(" + x + ", " + y + ")"
}