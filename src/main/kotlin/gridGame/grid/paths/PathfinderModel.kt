package gridGame.grid.paths

import gridGame.Vector2Int
import gridGame.grid.GridModel
import gridGame.grid.node.GridNode
import gridGame.grid.node.NodeDirection
import gridGame.grid.node.NodeType

class PathfinderModel(private val grid: GridModel) {
    val pathList: MutableList<GridNode> = mutableListOf()
    private var currentNodeIndex = 0

    fun getNextPathInput(): Char {
        if (pathList.isEmpty())
            return ' '

        val oldPos = pathList[currentNodeIndex].pos

        currentNodeIndex++

        val newPos = pathList[currentNodeIndex].pos

        val xDelta = (newPos - oldPos).x

        if (xDelta > 0)
            return 'd'
        else if (xDelta < 0)
            return 'a'

        val yDelta = (newPos - oldPos).y

        if (yDelta > 0)
            return 's'
        else if (yDelta < 0)
            return 'w'

        return ' '
    }

    //good old A*
    fun calculatePath(fromPos: Vector2Int, toPos: Vector2Int) {
        val fromNode = grid.getNodeAtPos(fromPos)!!
        val toNode = grid.getNodeAtPos(toPos)!!
        val searchFrontier: MutableList<GridNode> = mutableListOf()

        for (i in 0 until grid.nodes.size)
            grid.nodes[i]!!.distance = Int.MAX_VALUE

        fromNode.distance = 0
        fromNode.pathFrom = null
        searchFrontier.add(fromNode)

        while (searchFrontier.isNotEmpty()) {
            val current: GridNode = searchFrontier[0]
            searchFrontier.removeAt(0)

            if (current == toNode) {
                getPathForNode(current)
                return
            }
            for (i in NodeDirection.values()) {
                val neighbor = current.getNeighbor(i)
                if (neighbor == null || neighbor.type == NodeType.Blocked)
                    continue

                val newDistance = current.distance + 1
                if (neighbor.distance == Int.MAX_VALUE) {
                    neighbor.distance = newDistance
                    neighbor.pathFrom = current
                    neighbor.searchHeuristic = neighbor.distanceHeuristic(toNode)
                    searchFrontier.add(neighbor)
                } else if (newDistance < neighbor.distance) {
                    neighbor.distance = newDistance
                    neighbor.pathFrom = current
                }

                //sort frontier
                searchFrontier.sortBy { i -> i.searchHeuristic }
            }
        }
    }

    private fun getPathForNode(node: GridNode) {
        pathList.clear()
        var current: GridNode? = node

        while (current != null) {
            pathList.add(current)
            current = current.pathFrom
        }
        pathList.reverse()
    }
}