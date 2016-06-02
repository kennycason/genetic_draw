package com.kennycason.genetic.draw.gene.mutate

import com.kennycason.genetic.draw.Context
import com.kennycason.genetic.draw.gene.*
import java.awt.Color
import java.util.*

/**
 * Created by kenny on 5/30/16.
 *
 * This method tends to not perform well. Introduces too much change.
 */
class PolygonNewGeneMutator(val context: Context) : Mutator {
    val random = Random()
    val genetic = PolygonGenetic(context)
    
    override fun mutate(gene: Gene, probability: Float): Gene {
        return genetic.newGene()
    }

}