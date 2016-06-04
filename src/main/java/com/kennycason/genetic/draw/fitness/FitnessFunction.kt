package com.kennycason.genetic.draw.fitness

import java.awt.image.BufferedImage

/**
 * Created by kenny on 5/23/16.
 */
interface  FitnessFunction {
    fun compare(bi: BufferedImage, bi2: BufferedImage): Double
}