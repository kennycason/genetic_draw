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
class Genetic(val context: Context) {
    val random = Random()

    fun copy(individual: Individual) : Individual {
        val copied = mutableListOf<Gene>()
        individual.dna.forEach { gene ->
            copied.add(copy(gene))
        }
        return Individual(copied, individual.fitness)
    }

    fun copy(gene: Gene) : Gene {
        // return gene
        return Gene(x = gene.x.toInt(),
                y = gene.y.toInt(),
                z = gene.z.toInt(),
                w = gene.w.toInt(),
                h = gene.h.toInt(),
                shape = gene.shape,
                color = Color(
                        gene.color.red,
                        gene.color.green,
                        gene.color.blue,
                        gene.color.alpha))
    }

    fun bound(value: Int, min: Int, max: Int): Int {
        if (value < min) { return min; }
        if (value > max) { return max; }
        return value
    }

    fun newColor(): Color {
        return Color(
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)     // alpha
        )
    }

    fun newShape(): Shape {
        if (random.nextBoolean()) {
            return Shape.CIRCLE
        }
        return Shape.RECTANGLE
    }

}