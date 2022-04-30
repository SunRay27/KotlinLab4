package gridGame.player

import gridGame.Vector2Int
import gridGame.render.Color
import gridGame.render.RenderMatrix

class PlayerView(private val renderMatrix: RenderMatrix) {
    fun render(pos: Vector2Int) {
        renderMatrix.setCharBGColorAtPos(pos, Color.YELLOW)
    }
}