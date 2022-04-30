package gridGame.grid.node

enum class NodeDirection(val value: Int) {
    N(0),
    E(1),
    S(2),
    W(3),
}

internal fun NodeDirection.opposite(): NodeDirection {
    return if (value < 2)
        NodeDirection.values()[(value + 2)]
    else
        NodeDirection.values()[(value - 2)]
}

internal fun NodeDirection.toInt(): Int {
    return value
}