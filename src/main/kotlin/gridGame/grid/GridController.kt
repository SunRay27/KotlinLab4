package gridGame.grid

import gridGame.Vector2Int

class GridController(private val model: GridModel, private val view: GridView) {
    fun getStartPos(): Vector2Int {
        return model.startPos
    }
    fun getEndPos(): Vector2Int {
        return model.endPos
    }
    fun getGridSize() : Vector2Int {
        return Vector2Int(model.sizeX,model.sizeY)
    }


    fun display() {
        view.displayGrid(model)
    }
    fun displayStartEndPoints() {
        view.displayPoints(model)
    }

}