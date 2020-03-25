package math

class Point(val x: Float, val y: Float) {

    fun toArray(): FloatArray {
        return floatArrayOf(x, y)
    }

    override fun toString(): String {
        return "[Point: x = $x, y = $y]"
    }

    companion object {
        @JvmStatic
        fun random(): Point {
            val x = Math.random().toFloat()
            val y = Math.random().toFloat()
            return Point(x, y)
        }
    }
}
