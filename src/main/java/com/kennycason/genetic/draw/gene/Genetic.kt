package com.kennycason.genetic.draw.gene

import com.kennycason.genetic.draw.Context
import com.kennycason.genetic.draw.gene.shape.*
import java.awt.Color
import java.awt.Graphics
import java.util.*

/**
 * Created by kenny on 5/23/16.
 */
class Genetic(val context: Context) {
    val random = Random()

    fun newIndividual(): Individual {
        val genes = mutableListOf<Shape>()
        (1..context.geneCount).forEach {
            genes.add(newShape())
        }
        return Individual(genes, Double.MAX_VALUE)
    }

    fun newPopulation(): List<Individual> {
        var population = mutableListOf<Individual>()
        (1..context.populationCount).forEach {
            population.add(newIndividual())
        }
        return population
    }

    fun expressDna(g: Graphics, individual: Individual) {
        g.color = Color.BLACK
        g.clearRect(0, 0, context.width, context.height)
        individual.dna.forEach { gene ->
            gene.draw(g, context)
        }
    }

    fun copy(individual: Individual) : Individual {
        val copied = mutableListOf<Shape>()
        individual.dna.forEach { gene ->
            copied.add(gene.copy())
        }
        return Individual(copied, individual.fitness)
    }

    fun bound(value: Int, min: Int, max: Int): Int {
        if (value < min) { return min; }
        if (value > max) { return max; }
        return value
    }

    fun newColor(): IntArray {
        val color = IntArray(4)
        color.set(0, random.nextInt(256))
        color.set(1, random.nextInt(256))
        color.set(2, random.nextInt(256))
        color.set(3, random.nextInt(256))
        return color
    }

    fun newShape(): Shape {
        val shapeIndex = random.nextInt(context.allowedShapes.size)
        when (context.allowedShapes.get(shapeIndex)) {
            ShapeType.RECTANGLE -> return newRectangle()
            ShapeType.ELLIPSE -> return newEllipse()
            ShapeType.POLYGON -> return newPolygon()
            ShapeType.PIXEL -> return newPixel()
            ShapeType.CIRCLE -> return newCircle()
        }
    }

    private fun newPolygon(): Polygon {
        val numberPoints = random.nextInt(context.maxPolygonSize - 2) + 3
        val x = IntArray(numberPoints)
        val y = IntArray(numberPoints)
        (0..numberPoints-1).forEach { i ->
            x.set(i, random.nextInt(context.width))
            y.set(i, random.nextInt(context.height))
        }
        val z = random.nextInt(1000)
        val color = newColor()
        return Polygon(color, x, y, z)
    }

    private fun newEllipse(): Ellipse {
        val x = random.nextInt(context.width)
        val y = random.nextInt(context.height)
        val z = random.nextInt(1000)
        val w = bound(random.nextInt(context.width / 4), 2, context.width - x)
        val h = bound(random.nextInt(context.height / 4), 2, context.height - y)
        val color = newColor()
        return Ellipse(color, x, y, z, w, h)
    }

    private fun newCircle(): Circle {
        val x = random.nextInt(context.width)
        val y = random.nextInt(context.height)
        val z = random.nextInt(1000)
        val r = bound(random.nextInt(context.width / 4), 2, (context.width + context.height) / 2)
        val color = newColor()
        return Circle(color, x, y, z, r)
    }

    fun newPixel(): Pixel {
        val x = random.nextInt(context.width / context.pixelSize) * context.pixelSize
        val y = random.nextInt(context.height / context.pixelSize) * context.pixelSize
        val z = random.nextInt(1000)
        val color = newColor()
        return Pixel(color, x, y, z, context.pixelSize)
    }

    private fun newRectangle(): Rectangle {
        val x = random.nextInt(context.width)
        val y = random.nextInt(context.height)
        val w = bound(random.nextInt(context.width / 4), 2, context.width - x)
        val h = bound(random.nextInt(context.height / 4), 2, context.height - y)
        val z = random.nextInt(1000)
        val color = newColor()
        return Rectangle(color, x, y, z, w, h)
    }

}