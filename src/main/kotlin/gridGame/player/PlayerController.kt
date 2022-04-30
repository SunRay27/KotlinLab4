package gridGame.player

import gridGame.grid.GridModel

class PlayerController(private val model: PlayerModel, private val view: PlayerView) {

    fun display() {
        view.render(model.currentPos)
    }

    fun applyInput(gridModel: GridModel, c: Char) {

        val direction = when (c.lowercaseChar()) {
            'w' -> MoveDirection.Up
            's' -> MoveDirection.Down
            'a' -> MoveDirection.Left
            'd' -> MoveDirection.Right
            else -> MoveDirection.None
        }

        model.moveOnGrid(gridModel, direction)
    }

}