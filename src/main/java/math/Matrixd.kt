package math

import java.util.*

class Matrixd(private val rows: Int, private val cols: Int = rows) {

    //
    // rows = 4, cols = 2
    //
    //      cols
    // rows 00 10 20 30
    //      01 11 21 31
    //

    private val matrix: Array<DoubleArray> = Array(rows) { DoubleArray(cols) { 0.0 } }

    private fun set(mapper: () -> Double) =
            map { _, _, _ -> mapper() }

    private fun map(mapper: (Double) -> Double) =
            map { d, _, _ -> mapper(d) }

    private fun map(mapper: (Int, Int) -> Double) =
            map { _, i, j -> mapper(i, j) }

    private fun map(mapper: (Double, Int, Int) -> Double) {
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                matrix[i][j] = mapper(matrix[i][j], i, j)
            }
        }
    }

    fun forEach(mapper: (Double) -> Unit) {
        for (array in matrix) {
            for (i in array.indices) {
                mapper(array[i])
            }
        }
    }

    fun randomize(min: Double, max: Double) {
        val range = max - min
        set { Math.random() * range + min }
    }

    operator fun plus(t: Double): Matrixd {
        val result = copy()
        result.map { d -> d + t }
        return result
    }

    operator fun plus(t: Matrixd): Matrixd {
        val result = copy()
        result.map { d, i, j -> d + t[i, j] }
        return result
    }

    operator fun minus(t: Double): Matrixd {
        val result = copy()
        result.map { d -> d - t }
        return result
    }

    operator fun minus(t: Matrixd): Matrixd {
        val result = copy()
        result.map { d, i, j -> d - t[i, j] }
        return result
    }

    operator fun div(t: Double): Matrixd {
        val result = copy()
        result.map { d -> d / t }
        return result
    }

    /*
    operator fun div(t: Matrix): Matrix {
        val result = copy()
        result.map { d, i, j -> d / t[i, j] }
        return result
    }*/

    operator fun times(t: Double): Matrixd {
        val result = copy()
        result.map { d -> d * t }
        return result
    }

    operator fun times(t: Matrixd): Matrixd {
        if (cols != t.rows) {
            throw IllegalArgumentException()
        }
        val result = copy()
        map { i, j ->
            var sum = 0.0
            for (k in 0..cols) {
                sum += this[i, k] * t[k, j]
            }
            sum
        }
        return result
    }

    fun add(t: Double): Matrixd = this + t
    fun add(t: Matrixd): Matrixd = this + t
    fun sub(t: Double): Matrixd = this - t
    fun sub(t: Matrixd): Matrixd = this - t
    fun mul(t: Double): Matrixd = this * t
    fun mul(t: Matrixd): Matrixd = this * t

    operator fun get(i: Int, j: Int): Double = matrix[i][j]

    fun copy(): Matrixd {
        val result = Matrixd(rows, cols)
        result.map { i, j -> this[i, j] }
        return result
    }

    override fun toString(): String =
            Arrays.deepToString(matrix).replace("],", "],\n")

}