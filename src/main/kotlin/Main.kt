import gridGame.Vector2Int
import gridGame.grid.*
import gridGame.grid.paths.PathfinderController
import gridGame.grid.paths.PathfinderModel
import gridGame.grid.paths.PathfinderView
import gridGame.player.PlayerController
import gridGame.player.PlayerModel
import gridGame.player.PlayerView
import gridGame.render.RenderMatrix
import java.util.*

fun main() {

    val path = "${System.getProperty("user.dir")}\\grid.txt"

    val reader = Scanner(System.`in`)
    var autoModeChar = ' '
    var exitFlag = false


    val initGridModel = GridInfo(path)
    val gridModel = GridModel(initGridModel)
    val renderMatrix = RenderMatrix(Vector2Int(gridModel.sizeX, gridModel.sizeY))
    val gridView = GridView(renderMatrix)
    val gridController = GridController(gridModel,gridView)

    val playerView = PlayerView(renderMatrix)
    val playerModel = PlayerModel(gridController.getStartPos())
    val playerController = PlayerController(playerModel,playerView)

    gridController.display()
    playerController.display()
    renderMatrix.render()


    println("WELCOME TO LABYRINTH3000!!!")
    println("Controls: WASD = movement, \'e\' = exit game")
    println("DO YOU WANT TO ENTER AUTO-MODE? [y/n]")

    //auto-mode input
    while(autoModeChar != 'n' && autoModeChar != 'y') {
        try {
            autoModeChar = reader.next().single()
        } catch (e: Exception) {
            println(e.message)
        }
    }


    if(autoModeChar == 'n') {

        //manual game loop
        while (!exitFlag) {
            //request input
            val c: Char
            try {
                c = reader.next().single()
            } catch (e: Exception) {
                println(e.message)
                continue
            }

            //perform
            gridController.display()
            gridController.displayStartEndPoints()
            playerController.applyInput(gridModel, c)
            playerController.display()
            renderMatrix.render()

            if (c == 'e' || playerModel.currentPos == gridController.getEndPos())
                exitFlag = true
        }
    }
    else
    {
        //init pathfinder
        val pathfinderModel = PathfinderModel(gridModel)
        val pathfinderView = PathfinderView(renderMatrix)
        val pathfinderController = PathfinderController(pathfinderModel, pathfinderView)
        pathfinderController.calculatePath(gridController.getStartPos(), gridController.getEndPos())
        //auto game loop
        while (!exitFlag) {

            Thread.sleep(200)

            gridController.display()
            pathfinderController.displayPath()
            gridController.displayStartEndPoints()

            playerController.applyInput(gridModel, pathfinderController.getNextPathInput())
            playerController.display()
            renderMatrix.render()

            if (playerModel.currentPos == gridController.getEndPos())
                exitFlag = true

        }
    }

    println("GREAT!!! YOU MADE IT!!!")

}