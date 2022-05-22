package gridGame.grid

import java.io.File

class GridInfo(filePath: String) {

    internal val sizeX: Int
    internal val sizeY: Int
    internal val inputLines: List<String>

    init {
        val file = File(filePath)
        inputLines = file.readText().lines()

        val lineCount = inputLines.count()
        var minLineSize = Int.MAX_VALUE

        //read grid
        //find the shortest line
        //find line count
        inputLines.forEach {
            if (it.length < minLineSize)
                minLineSize = it.length
        }

        sizeY = lineCount
        sizeX = minLineSize

    }
}