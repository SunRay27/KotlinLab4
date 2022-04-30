package gridGame.grid.node

import gridGame.Vector2Int
import kotlin.math.abs

class GridNode(val pos: Vector2Int, val type: NodeType) {

    internal var searchHeuristic = 0
    internal var distance = 0
    internal var pathFrom: GridNode? = null
    private val neighbors: Array<GridNode?> = arrayOfNulls<GridNode>(4)

    fun setNeighbor(direction: NodeDirection, node: GridNode) {
        neighbors[direction.value] = node
        node.neighbors[direction.opposite().value] = this
    }

    fun getNeighbors(): Array<GridNode?> {
        return neighbors
    }
    fun getNeighbor(direction : NodeDirection): GridNode? {
        return neighbors[direction.toInt()]
    }
    fun distanceHeuristic(target: GridNode): Int {
        return abs(target.pos.x - pos.x) + abs(target.pos.y - pos.y)
    }
}