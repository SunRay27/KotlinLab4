package gridGame.grid

import gridGame.Vector2Int
import gridGame.grid.node.GridNode
import gridGame.grid.node.NodeDirection
import gridGame.grid.node.NodeType
import java.security.InvalidParameterException

class GridModel(private var info: GridInfo) {

    private val BLOCKEDCHAR = '#'
    private val STARTCHAR = 'S'
    private val ENDCHAR = 'E'

    internal var startPos = Vector2Int(-1, -1)
        private set

    internal var endPos = Vector2Int(-1, -1)
        private set

    //encapsulated by <val>?
    internal var sizeX: Int = info.sizeX
        private set
    internal var sizeY: Int = info.sizeY
        private set
    internal var nodes: Array<GridNode?> = arrayOfNulls(sizeY * sizeX)
        private set


    init {
        build()
    }

    fun reBuild(newInfo: GridInfo) {
        info = newInfo
        sizeX = info.sizeX
        sizeY = info.sizeY
        nodes = arrayOfNulls(sizeY * sizeX)
        build()
    }

    private fun build() {
        //make grid <line count> X <the shortest line char count>
        //create nodes
        var index = 0
        for (y in 0 until sizeY) {
            val line = info.inputLines[y]
            for (x in 0 until sizeX) {
                createNode(x, y, index, line[x])
                index++
            }
        }

        if (startPos == Vector2Int(-1, -1))
            throw InvalidParameterException("start pos is not present in file")

        if (endPos == Vector2Int(-1, -1))
            throw InvalidParameterException("end pos is not present in file")
    }

    private fun createNode(x: Int, y: Int, i: Int, char: Char) {
        if (char == STARTCHAR)
            startPos = Vector2Int(x, y)
        if (char == ENDCHAR)
            endPos = Vector2Int(x, y)

        val type = if (char == BLOCKEDCHAR) NodeType.Blocked else NodeType.Walkable
        val newNode = GridNode(Vector2Int(x, y), type)

        if (x > 0)
            newNode.setNeighbor(NodeDirection.W, nodes[i - 1]!!)
        if (y > 0)
            newNode.setNeighbor(NodeDirection.N, nodes[i - sizeX]!!)

        nodes[i] = newNode
    }

    fun getNodeAtPos(pos: Vector2Int): GridNode? {
        return if (pos.x + pos.y * sizeX < nodes.size && pos.x + pos.y * sizeX >= 0)
            nodes[pos.x + pos.y * sizeX]
        else
            null
    }
}

