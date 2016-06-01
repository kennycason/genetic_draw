package com.kennycason.genetic.draw.gene.selection

import com.kennycason.genetic.draw.gene.Individual

/**
 * Created by kenny on 5/31/16.
 */
interface Selector {
    fun select(population: List<Individual>): Individual
}