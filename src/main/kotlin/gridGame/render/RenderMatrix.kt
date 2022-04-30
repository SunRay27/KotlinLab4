package gridGame.render

import gridGame.Vector2Int

class RenderMatrix(private val matrixSize : Vector2Int) {

    private val matrix: Array<Array<RenderTexel?>> = Array(matrixSize.y) { arrayOfNulls(matrixSize.x) }

    init {
        for (i in 0 until matrixSize.y)
            for (j in 0 until matrixSize.x)
                matrix[i][j] = RenderTexel()
    }

    fun setPos(pos: Vector2Int, color: Color = Color.WHITE, bgcolor: Color = Color.BLACK, charValue: Char = ' ') {
        matrix[pos.y][pos.x]!!.setValue(charValue)
        matrix[pos.y][pos.x]!!.setColor(color)
        matrix[pos.y][pos.x]!!.setBGColor(bgcolor)
    }

    fun setCharAtPos(pos: Vector2Int, charValue: Char) {
        matrix[pos.y][pos.x]!!.setValue(charValue)
    }

    fun setCharColorAtPos(pos: Vector2Int, newColor: Color) {
        matrix[pos.y][pos.x]!!.setColor(newColor)
    }

    fun setCharBGColorAtPos(pos: Vector2Int, newColor: Color) {
        matrix[pos.y][pos.x]!!.setBGColor(newColor)
    }

    fun render() {
        for (i in 0 until 10) {
            println()
        }
        for (i in 0 until matrixSize.y) {
            for (j in 0 until matrixSize.x) {
                matrix[i][j]!!.render()
                //reset color and char value
                matrix[i][j]!!.setColor(Color.WHITE)
                matrix[i][j]!!.setValue(' ')
            }
            println()
        }

    }

}