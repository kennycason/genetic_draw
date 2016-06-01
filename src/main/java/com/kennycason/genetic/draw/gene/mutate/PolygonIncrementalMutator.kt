package com.kennycason.genetic.draw.gene.mutate

import com.kennycason.genetic.draw.Context
import com.kennycason.genetic.draw.gene.*
import java.awt.Color
import java.util.*

/**
 * Created by kenny on 5/30/16.
 */
class PolygonIncrementalMutator(val context: Context) : Mutator {
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

        val mutationType = random.nextInt(10)
        when (mutationType) {
            0 -> copy.x = genetic.bound(gene.x + random.nextInt(maxDelta) - halfDelta, 0, context.width - gene.w )
            1 -> copy.y = genetic.bound(gene.y + random.nextInt(maxDelta) - halfDelta, 0, context.height - gene.h)
            2 -> copy.z = genetic.bound(gene.z + random.nextInt(maxDelta) - halfDelta, 0, 1000)
            3 -> copy.w = genetic.bound(gene.w + random.nextInt(maxDelta) - halfDelta, 5, context.width)
            4 -> copy.h = genetic.bound(gene.h + random.nextInt(maxDelta) - halfDelta, 5, context.height)
            5 -> copy.shape = genetic.newShape()
            6 -> copy.color = Color(
                    //random.nextInt(256),
                    genetic.bound(gene.color.red + random.nextInt(maxColorDelta) - halfMaxColorDelta, 0, 255),
                    gene.color.green,
                    gene.color.blue,
                    gene.color.alpha)
            7-> copy.color = Color(
                    gene.color.red,
                    //random.nextInt(256),
                    genetic.bound(gene.color.green + random.nextInt(maxColorDelta) - halfMaxColorDelta, 0, 255),
                    gene.color.blue,
                    gene.color.alpha)
            8 -> copy.color = Color(
                    gene.color.red,
                    gene.color.green,
                    //random.nextInt(256),
                    genetic.bound(gene.color.blue + random.nextInt(maxColorDelta) - halfMaxColorDelta, 0, 255),
                    gene.color.alpha)
            9 -> copy.color = Color(
                    gene.color.red,
                    gene.color.green,
                    gene.color.blue,
                    //random.nextInt(256))
                    genetic.bound(gene.color.alpha + random.nextInt(maxColorDelta) - halfMaxColorDelta, 0, 255))
        }
        return copy
    }

}