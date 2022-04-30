package gridGame.player

import gridGame.Vector2Int
import gridGame.grid.GridModel
import gridGame.grid.node.NodeType

class PlayerModel(startPos: Vector2Int) {

    var currentPos = startPos

    fun moveOnGrid(gridModel: GridModel, direction: MoveDirection) {
        val delta: Vector2Int = when (direction) {
            MoveDirection.Up -> Vector2Int(0, -1)
            MoveDirection.Down -> Vector2Int(0, 1)
            MoveDirection.Left -> Vector2Int(-1, 0)
            MoveDirection.Right -> Vector2Int(1, 0)
            MoveDirection.None -> Vector2Int(0, 0)
        }

        val targetNode = gridModel.getNodeAtPos(currentPos + delta)

        if (targetNode != null && targetNode.type != NodeType.Blocked)
            currentPos = targetNode.pos

    }

}