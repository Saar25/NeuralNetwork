package math

class Matrixf(val rows: Int, val cols: Int = rows) {

    /*
     *
     *  rows = 2, cols = 4
     *
     *       cols
     *  rows 00 01 02 03
     *       10 11 21 13
     *
     */

    private val matrix: Array<FloatArray> = Array(rows) { FloatArray(cols) { 0f } }

    constructor(m: Matrixf) : this(m.rows, m.cols) {
        map0 { i, j -> m[i, j] }
    }

    private fun set0(mapper: () -> Float) = map0 { _, _, _ -> mapper() }

    private fun map0(mapper: (Float) -> Float) = map0 { d, _, _ -> mapper(d) }

    private fun map0(mapper: (Int, Int) -> Float) = map0 { _, i, j -> mapper(i, j) }

    private fun map0(mapper: (Float, Int, Int) -> Float) {
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                matrix[i][j] = mapper(matrix[i][j], i, j)
            }
        }
    }

    fun set(mapper: () -> Float) = map { _, _, _ -> mapper() }

    fun map(mapper: (Float) -> Float) = map { d, _, _ -> mapper(d) }

    fun map(mapper: (Int, Int) -> Float) = map { _, i, j -> mapper(i, j) }

    fun map(mapper: (Float, Int, Int) -> Float): Matrixf {
        val matrix = Matrixf(this)
        matrix.map0(mapper)
        return matrix
    }

    fun forEach(consumer: (Float) -> Unit) {
        forEach { f, _, _ -> consumer(f) }
    }

    fun forEach(consumer: (Float, Int, Int) -> Unit) {
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                consumer(matrix[i][j], i, j)
            }
        }
    }

    operator fun plus(t: Float): Matrixf = map { d -> d + t }
    operator fun minus(t: Float): Matrixf = map { d -> d - t }
    operator fun times(t: Float): Matrixf = map { d -> d * t }
    operator fun div(t: Float): Matrixf = map { d -> d / t }

    operator fun plus(t: Matrixf): Matrixf {
        requiredSameDimensions(t, "addition")
        return map { d, i, j -> d + t[i, j] }
    }

    operator fun minus(t: Matrixf): Matrixf {
        requiredSameDimensions(t, "subtraction")
        return map { d, i, j -> d - t[i, j] }
    }

    operator fun times(t: Matrixf): Matrixf {
        requiredSameDimensions(t, "multiplication")
        return map { d, i, j -> d * t[i, j] }
    }

    /*
    operator fun div(t: Matrixf): Matrixf {
        requiredSameDimensions(t, "division")
        return map { d, i, j -> d * t[i, j] }
    }
    */
    private fun requiredSameDimensions(t: Matrixf, operation: String) {
        if (!isSameDimensions(t)) {
            throw IllegalArgumentException("Cannot perform $operation to matrix (size = $rows, $cols) with (rows = ${t.rows}, ${t.cols})")
        }
    }

    fun transpose(): Matrixf {
        val matrix = Matrixf(cols, rows)
        matrix.map0 { i, j -> this[j, i] }
        return matrix
    }

    fun dot(t: Matrixf): Matrixf {
        if (!canMultiply(t)) {
            throw IllegalArgumentException("Cannot multiply matrix (cols = $cols) with (rows = ${t.rows})")
        }
        val matrix = Matrixf(rows, t.cols)
        matrix.map0 { i, j ->
            var sum = 0f
            for (k in 0 until cols) {
                sum += this[i, k] * t[k, j]
            }
            sum
        }
        return matrix
    }

    fun canMultiply(m: Matrixf) = cols == m.rows
    fun isSameDimensions(m: Matrixf) = cols == m.cols && rows == m.rows

    fun add(t: Float): Matrixf = this + t
    fun sub(t: Float): Matrixf = this - t
    fun mul(t: Float): Matrixf = this * t
    fun add(t: Matrixf): Matrixf = this + t
    fun sub(t: Matrixf): Matrixf = this - t
    fun mul(t: Matrixf): Matrixf = this * t

    fun mutate(mutation: Float): Matrixf {
        if (mutation == 0f) return Matrixf(this)
        return map { f -> f + Maths.randomf(-mutation, mutation) }
    }

    fun toArray(): FloatArray {
        val array = FloatArray(rows * cols)
        forEach { f, i, j -> array[i * cols + j] = f }
        return array
    }

    operator fun get(i: Int, j: Int): Float = matrix[i][j]

    override fun toString(): String {
        val string = StringBuilder("[")
        for ((arrayIndex, array) in matrix.withIndex()) {
            string.append("[")
            for ((index, element) in array.withIndex()) {
                if (element >= 0) string.append(" ")
                string.append(String.format("%.4f", element))
                if (index != cols - 1) string.append(",")
            }
            string.append("]")
            if (arrayIndex != rows - 1) string.append("\n ")
        }
        return string.append("]").toString()
    }

    companion object Factory {
        @JvmStatic
        fun fromArray(inputs: FloatArray): Matrixf {
            val matrix = Matrixf(inputs.size, 1)
            matrix.map0 { i, _ -> inputs[i] }
            return matrix
        }

        @JvmStatic
        fun randomize(rows: Int, cols: Int, min: Float, max: Float): Matrixf {
            val range = max - min
            val matrix = Matrixf(rows, cols)
            matrix.set0 { (Math.random() * range + min).toFloat() }
            return matrix
        }

        @JvmStatic
        fun randomize(rows: Int, cols: Int): Matrixf {
            return randomize(rows, cols, -1f, +1f)
        }
    }
}