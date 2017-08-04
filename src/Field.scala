class Field {
    val points = (for (i <- 0 to 10;
                       j <- 0 to 10)
                       yield new Point(i, j)) map (p => p -> pStates.empty) toMap
}