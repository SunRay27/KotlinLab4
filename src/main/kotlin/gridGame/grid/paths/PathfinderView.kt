package gridGame.grid.paths

import gridGame.render.Color
import gridGame.render.RenderMatrix

class PathfinderView(private val renderMatrix: RenderMatrix) {
    fun render(model: PathfinderModel) {
        model.pathList.forEach {
            renderMatrix.setCharBGColorAtPos(it.pos, Color.BLUE)
        }
    }
}