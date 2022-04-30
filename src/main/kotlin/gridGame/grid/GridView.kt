package gridGame.grid

import gridGame.Vector2Int
import gridGame.grid.node.NodeType
import gridGame.render.Color
import gridGame.render.RenderMatrix

class GridView(private val renderMatrix: RenderMatrix) {
    fun displayGrid(gridModel: GridModel) {

        for (y in 0 until gridModel.sizeY) {
            for (x in 0 until gridModel.sizeX) {

                val targetNode = gridModel.nodes[x + y * gridModel.sizeX]

                if (Vector2Int(x, y) == gridModel.startPos) {
                    renderMatrix.setCharAtPos(gridModel.startPos, 'S')
                    renderMatrix.setCharBGColorAtPos(gridModel.startPos, Color.RED)

                } else if (Vector2Int(x, y) == gridModel.endPos) {
                    renderMatrix.setCharAtPos(gridModel.endPos, 'E')
                    renderMatrix.setCharBGColorAtPos(gridModel.endPos, Color.GREEN)

                } else {
                    if (targetNode!!.type == NodeType.Walkable)
                        renderMatrix.setCharBGColorAtPos(targetNode.pos, Color.BLACK)
                    else
                        renderMatrix.setCharBGColorAtPos(targetNode.pos, Color.WHITE)
                }

            }
        }
    }

    fun displayPoints(gridModel: GridModel) {
        for (y in 0 until gridModel.sizeY) {
            for (x in 0 until gridModel.sizeX) {
                if (Vector2Int(x, y) == gridModel.startPos) {
                    renderMatrix.setCharAtPos(gridModel.startPos, 'S')
                    renderMatrix.setCharBGColorAtPos(gridModel.startPos, Color.RED)

                } else if (Vector2Int(x, y) == gridModel.endPos) {
                    renderMatrix.setCharAtPos(gridModel.endPos, 'E')
                    renderMatrix.setCharBGColorAtPos(gridModel.endPos, Color.GREEN)

                }
            }
        }
    }

}