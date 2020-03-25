package games

import kotlin.math.ceil

data class GenerationSettings(val populationSize: Int, val survivorsCount: Int, val mutationRate: Float) {

    fun getMutationsPerEach(): Int {
        return ceil(populationSize.toDouble() / survivorsCount).toInt()
    }
}
