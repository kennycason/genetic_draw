package com.kennycason.genetic.draw.gene.selection

import com.kennycason.genetic.draw.gene.Individual
import java.util.*

/**
 * Created by kenny on 5/31/16.
 */
class TournamentSelector(val tournamentSize: Int) : Selector {
    val random = Random()

    override fun select(population: List<Individual>): Individual {
        val tournament = mutableListOf<Individual>()
        (1..tournamentSize).forEach {
            tournament.add(population.get(random.nextInt(population.size)))
        }
        return tournament.sortedBy { individual -> individual.fitness }
                .first()
    }
}