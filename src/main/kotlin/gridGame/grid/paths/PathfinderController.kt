package gridGame.grid.paths

import gridGame.Vector2Int

class PathfinderController(private val model: PathfinderModel, private val view: PathfinderView) {

    fun getNextPathInput(): Char {
        return model.getNextPathInput()
    }

    fun displayPath() {
        view.render(model)
    }

    fun calculatePath(fromPos: Vector2Int, toPos: Vector2Int) {
        model.calculatePath(fromPos, toPos)
    }
}