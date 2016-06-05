package com.kennycason.genetic.draw

import com.kennycason.genetic.draw.gene.mutate.Mutator
import com.kennycason.genetic.draw.gene.shape.ShapeType
import com.kennycason.genetic.draw.probability.Probability
import com.kennycason.genetic.draw.probability.StaticProbability

/**
 * Created by kenny on 5/30/16.
 */
data class Context(val width: Int,
                   val height: Int,
                   val geneCount: Int = 128,
                   val populationCount: Int = 20,
                   val mutationProbability: Probability = StaticProbability(0.01f),
                   val allowedShapes: Array<ShapeType> = arrayOf(ShapeType.ELLIPSE, ShapeType.RECTANGLE),
                   val maxPolygonSize: Int = 5,
                   val pixelSize: Int = 8,
                   val useAlpha: Boolean = true)