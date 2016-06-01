package com.kennycason.genetic.draw.gene

import com.kennycason.genetic.draw.Context
import com.kennycason.genetic.draw.gene.Gene
import com.kennycason.genetic.draw.gene.Shape
import java.awt.Color
import java.awt.Graphics
import java.util.*

/**
 * Created by kenny on 5/23/16.
 */
class PolygonGenetic(val context: Context) {
    val random = Random()
    val genetic = Genetic(context)

    fun newIndividual(): Individual {
        val genes = mutableListOf<Gene>()
        (1..context.geneCount).forEach {
            genes.add(newGene())
        }
        return Individual(genes.sortedBy { gene -> gene.z }, Double.MAX_VALUE)
    }

    fun newPopulation(): List<Individual> {
        var population = mutableListOf<Individual>()
        (1..context.populationCount).forEach {
            population.add(newIndividual())
        }
        return population
    }

    fun newGene(): Gene {
        val x = random.nextInt(context.width)
        val y = random.nextInt(context.height)
        val z = random.nextInt(1000)
        val w = genetic.bound(random.nextInt(context.width / 4), 2, context.width - x)
        val h = genetic.bound(random.nextInt(context.height / 4), 2, context.height - y)
        val shape = genetic.newShape()
        val color = genetic.newColor()
        return Gene(x, y, z, w, h, shape, color)
    }

    fun expressDna(g: Graphics, individual: Individual) {
        g.color = Color.BLACK
        g.clearRect(0, 0, context.width, context.height)
        individual.dna.forEach { gene ->
            when (gene.shape) {
                Shape.CIRCLE -> { drawCircle(g, gene) }
                Shape.RECTANGLE -> { drawRectangle(g, gene) }
            }
        }
    }

    private fun drawRectangle(g: Graphics, gene: Gene) {
        g.color = gene.color
        g.fillRect(gene.x, gene.y, gene.w, gene.h)
    }

    private fun drawCircle(g: Graphics, gene: Gene) {
        g.color = gene.color
        g.fillOval(gene.x, gene.y, gene.w / 2, gene.h / 2)
    }

}