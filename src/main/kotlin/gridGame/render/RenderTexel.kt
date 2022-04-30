package gridGame.render

class RenderTexel {

    private var charValue: Char = ' '
    private var color: Color = Color.WHITE
    private var bgcolor: Color = Color.BLACK

    fun setValue(charValue: Char) {
        this.charValue = charValue
    }

    fun setColor(newColor: Color) {
        this.color = newColor
    }

    fun setBGColor(newColor: Color) {
        this.bgcolor = newColor
    }

    fun render() {
        print("\u001B[${bgcolor.toBGInt()}m\u001B[${color.toInt()}m$charValue\u001B[0m")
    }

}