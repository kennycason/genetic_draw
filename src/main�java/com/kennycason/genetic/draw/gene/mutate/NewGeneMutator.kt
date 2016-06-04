package com.kennycason.genetic.draw.gene.mutate

import com.kennycason.genetic.draw.Context
import com.kennycason.genetic.draw.gene.*
import com.kennycason.genetic.draw.gene.shape.Shape
import java.util.*

/**
 * Created by kenny on 5/30/16.
 *
 * This method tends to not perform well. Introduces too much change.
 */
class NewGeneMutator(val context: Context) : Mutator {
    val random = Random()
    val genetic = Genetic(context)
    
    override fun mutate(gene: Shape, probability: Float): Shape {
        return genetic.newShape()
    }

}