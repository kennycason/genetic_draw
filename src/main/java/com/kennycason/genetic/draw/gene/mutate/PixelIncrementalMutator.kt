package com.kennycason.genetic.draw.gene.mutate

import com.kennycason.genetic.draw.Context
import com.kennycason.genetic.draw.gene.*
import java.awt.Color
import java.util.*

/**
 * Created by kenny on 5/30/16.
 */
class PixelIncrementalMutator(val context: Context) : Mutator {
    val random = Random()
    val genetic = Genetic(context)
    
    override fun mutate(gene: Gene, probability: Float): Gene {
        val copy = genetic.copy(gene)
        // don't mutate
        if (random.nextDouble() > probability) { return copy }

        // for moving/size
        val maxWidth = context.width / context.pixelSize
        val maxHeight = context.height / context.pixelSize
        // for color
        val maxColorDelta = 100
        val halfMaxColorDelta = maxColorDelta / 2

        val mutationType = random.nextInt(7)
        when (mutationType) {
            0 -> copy.x = genetic.bound(gene.x + random.nextInt(maxWidth / 2) - maxWidth / 4 , 0, maxWidth)
            1 -> copy.y = genetic.bound(gene.y + random.nextInt(maxHeight / 2) - maxHeight / 4, 0, maxHeight)
            2 -> copy.z = genetic.bound(gene.z + random.nextInt(10) - 5, 0, 1000)
            3 -> copy.color = Color(
                    //random.nextInt(256),
                    genetic.bound(gene.color.red + random.nextInt(maxColorDelta) - halfMaxColorDelta, 0, 255),
                    gene.color.green,
                    gene.color.blue,
                    gene.color.alpha)
            4-> copy.color = Color(
                    gene.color.red,
                    genetic.bound(gene.color.green + random.nextInt(maxColorDelta) - halfMaxColorDelta, 0, 255),
                    //random.nextInt(256),
                    gene.color.blue,
                    gene.color.alpha)
            5 -> copy.color = Color(
                    gene.color.red,
                    gene.color.green,
                    genetic.bound(gene.color.blue + random.nextInt(maxColorDelta) - halfMaxColorDelta, 0, 255),
                    //random.nextInt(256),
                    gene.color.alpha)
            6 -> copy.color = Color(
                    gene.color.red,
                    gene.color.green,
                    gene.color.blue,
                    //random.nextInt(256))
                    genetic.bound(gene.color.alpha + random.nextInt(maxColorDelta) - halfMaxColorDelta, 0, 255))
        }
        return copy
    }

}