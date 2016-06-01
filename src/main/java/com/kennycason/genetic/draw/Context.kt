package com.kennycason.genetic.draw

import com.kennycason.genetic.draw.gene.mutate.Mutator
import com.kennycason.genetic.draw.probability.Probability

/**
 * Created by kenny on 5/30/16.
 */
data class Context(val width: Int,
                   val height: Int,
                   val geneCount: Int,
                   val populationCount: Int,
                   val mutationProbability: Probability,
                   val pixelSize: Int)