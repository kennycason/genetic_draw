package com.kennycason.genetic.draw.gene

import com.kennycason.genetic.draw.gene.shape.Shape

/**
 * Created by kenny on 5/24/16.
 *
 * fitness lower = better
 */
data class Individual(val dna: List<Shape>, var fitness: Double)