package com.kennycason.genetic.draw.gene.mutate

import com.kennycason.genetic.draw.gene.Individual
import com.kennycason.genetic.draw.gene.shape.Shape

/**
 * Created by kenny on 5/30/16.
 */
interface Mutator {
    fun mutate(gene: Shape, probability: Float): Shape

    fun mutate(individual: Individual, probability: Float): Individual {
        val mutated = mutableListOf<Shape>()
        individual.dna.forEach { gene ->
            mutated.add(mutate(gene, probability))
        }
        return Individual(mutated.sortedBy { mutated -> mutated.z }, Double.MAX_VALUE)
    }
}