package gridGame

data class Vector2Int(var x:Int, var y: Int ) {

    operator fun plus (other : Vector2Int) : Vector2Int {
        return  Vector2Int(x+other.x,y+other.y)
    }
    operator  fun  minus(other: Vector2Int): Vector2Int {
        return  Vector2Int(x-other.x,y-other.y)
    }
     operator fun unaryMinus(): Vector2Int {
        return Vector2Int(-x,-y)
    }


}