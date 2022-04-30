package gridGame.grid

import gridGame.Vector2Int
import gridGame.grid.node.GridNode
import gridGame.grid.node.NodeDirection
import gridGame.grid.node.NodeType
import java.io.File
import java.security.InvalidParameterException

class GridModel(private val filePath: String) {

    private val BLOCKEDCHAR = '#'
    private val STARTCHAR = 'S'
    private val ENDCHAR = 'E'

    internal var startPos = Vector2Int(-1, -1)
    internal var endPos = Vector2Int(-1, -1)

    internal val sizeX: Int
    internal val sizeY: Int

    internal val nodes: Array<GridNode?>

    init {
        val file = File(filePath)
        val lines = file.readText().lines()

        val lineCount = lines.count()
        var minLineSize = Int.MAX_VALUE

        //read grid
        //find the shortest line
        //find line count
        lines.forEach {
            if (it.length < minLineSize)
                minLineSize = it.length
        }

        sizeY = lineCount
        sizeX = minLineSize

        //make grid <line count> X <the shortest line char count>
        nodes = arrayOfNulls(sizeY * sizeX)

        //create nodes
        var index: Int = 0
        for (y in 0 until sizeY) {
            val line = lines[y]
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

