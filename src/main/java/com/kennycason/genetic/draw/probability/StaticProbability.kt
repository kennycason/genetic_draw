package com.kennycason.genetic.draw.probability

/**
 * Created by kenny on 6/1/16.
 */
class StaticProbability(val probability: Float): Probability {
    override fun next(): Float {
        return probability
    }
}