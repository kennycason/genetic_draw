package com.kennycason.genetic.draw.gene.mutate

import com.kennycason.genetic.draw.Context
import com.kennycason.genetic.draw.gene.*
import java.awt.Color
import java.util.*

/**
 * Created by kenny on 5/30/16.
 *
 * Performs like the PolygonIncrementalMutator only it mutates entire logical components.
 * For example ALL of the position vectors, or all of the color components.
 */
class PolygonLargeIncrementalMutator(val context: Context) : Mutator {
    val random = Random()
    val genetic = Genetic(context)
    
    override fun mutate(gene: Gene, probability: Float): Gene {
        val copy = genetic.copy(gene)
        // don't mutate
        if (random.nextDouble() > probability) { return copy }

        // for moving/size
        val maxDelta = ((context.width + context.height) / 2) / 2
        val halfDelta = maxDelta / 2 + 2
        // for color
        val maxColorDelta = 100
        val halfMaxColorDelta = maxColorDelta / 2

        val mutationType = random.nextInt(4)
        when (mutationType) {
            0 -> {
                copy.x = genetic.bound(gene.x + random.nextInt(maxDelta) - halfDelta, 0, context.width - gene.w)
                copy.y = genetic.bound(gene.y + random.nextInt(maxDelta) - halfDelta, 0, context.height - gene.h)
                copy.z = genetic.bound(gene.z + random.nextInt(maxDelta) - halfDelta, 0, 1000)
            }
            1 -> {
                copy.w = genetic.bound(gene.w + random.nextInt(maxDelta) - halfDelta, 5, context.width)
                copy.h = genetic.bound(gene.h + random.nextInt(maxDelta) - halfDelta, 5, context.height)
            }
            2 -> copy.shape = genetic.newShape()
            3 -> {
                copy.color = Color(
                        genetic.bound(gene.color.red + random.nextInt(maxColorDelta) - halfMaxColorDelta, 0, 255),
                        genetic.bound(gene.color.green + random.nextInt(maxColorDelta) - halfMaxColorDelta, 0, 255),
                        genetic.bound(gene.color.blue + random.nextInt(maxColorDelta) - halfMaxColorDelta, 0, 255),
                        genetic.bound(gene.color.alpha + random.nextInt(maxColorDelta) - halfMaxColorDelta, 0, 255))
            }

        }
        return copy
    }

}