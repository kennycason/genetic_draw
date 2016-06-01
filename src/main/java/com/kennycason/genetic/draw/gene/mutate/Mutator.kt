package com.kennycason.genetic.draw.gene.mutate

import com.kennycason.genetic.draw.gene.Gene
import com.kennycason.genetic.draw.gene.Individual

/**
 * Created by kenny on 5/30/16.
 */
interface Mutator {
    fun mutate(gene: Gene, probability: Float): Gene

    fun mutate(individual: Individual, probability: Float): Individual {
        val mutated = mutableListOf<Gene>()
        individual.dna.forEach { gene ->
            mutated.add(mutate(gene, probability))
        }
        return Individual(mutated.sortedBy { gene -> gene.z }, Double.MAX_VALUE)
    }
}