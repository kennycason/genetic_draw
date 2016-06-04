package com.kennycason.genetic.draw.fitness

import java.awt.Rectangle
import java.awt.image.BufferedImage

/**
 * Created by kenny on 5/23/16.
 *
 * Like the standard image difference fitness function except it will prioritize a specific region of the image to evolve.
 */
class PriorityRegionImageDifference(val stepSize: Int, val priorityRegion: Rectangle, val priorityMultiplier: Double = 10.0): FitnessFunction {
    override fun compare(bi: BufferedImage, bi2: BufferedImage): Double {
        var error = 0.0
        (0..bi.width-1 step stepSize).forEach{ x ->
            (0..bi.height-1 step stepSize).forEach{ y ->
                val rgb = bi.getRGB(x, y)
                val rgb2 = bi2.getRGB(x, y)
                var localError = 0.0
                localError += Math.abs((rgb and 0xFF) - (rgb2 and 0xFF))
                localError += Math.abs(((rgb shr 8) and 0xFF) - ((rgb2 shr 8) and 0xFF))
                localError += Math.abs(((rgb shr 16) and 0xFF) - ((rgb2 shr 16) and 0xFF))
                if (x >= priorityRegion.x && x <= priorityRegion.x + priorityRegion.width
                    && y >= priorityRegion.y && x <= priorityRegion.y + priorityRegion.height) {
                    localError /= priorityMultiplier
                }

                error += localError
            }
        }
        return error
    }
}