package com.kennycason.genetic.draw.probability

import java.util.*

/**
 * Created by kenny on 6/1/16.
 */
class DynamicRangeProbability(val min: Float, val max: Float): Probability {
    val random = Random()

    override fun next(): Float {
        return min + random.nextFloat() * (max - min)
    }
}