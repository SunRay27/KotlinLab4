package gridGame.render

enum class Color(val value: Int) {
    RED(31),
    GREEN(32),
    BLUE(34),
    WHITE(37),
    BLACK(30),
    YELLOW(33)
}

internal fun Color.toInt(): Int {
    return value
}

internal fun Color.toBGInt(): Int {
    return value + 10
}