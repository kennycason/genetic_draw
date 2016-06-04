package com.kennycason.genetic.draw

/**
 * Created by kenny on 5/23/16.
 */

import com.kennycason.genetic.draw.gene.*
import com.kennycason.genetic.draw.gene.mutate.PixelIncrementalMutator
import com.kennycason.genetic.draw.gene.selection.StochasticSelector
import com.kennycason.genetic.draw.fitness.ImageDifference
import com.kennycason.genetic.draw.gene.mutate.PolygonIncrementalMutator
import com.kennycason.genetic.draw.gene.mutate.PolygonLargeIncrementalMutator
import com.kennycason.genetic.draw.gene.mutate.PolygonNewGeneMutator
import com.kennycason.genetic.draw.probability.DynamicRangeProbability
import com.kennycason.genetic.draw.probability.StaticProbability
import com.sun.javafx.iio.ImageStorage
import java.awt.Color
import java.awt.Graphics
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import javax.imageio.ImageIO
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants

fun main(args: Array<String>) {
    PopulationBasedGeneticDraw().run()
}

class PopulationBasedGeneticDraw {
    val random = Random()
    val fileName = "sm-logo.png"
    val target = ImageIO.read(Thread.currentThread().contextClassLoader.getResource(fileName))
    val context = Context(
            width = target.width,
            height = target.height,
            geneCount = 1000,
            populationCount = 30,
            mutationProbability = DynamicRangeProbability(0.001f, 0.01f),
            pixelSize = 8)
    //val mutator = PixelIncrementalMutator(context)
    //val genetic = PixelGenetic(context)
    //val mutator = PolygonIncrementalMutator(context)
    //val mutator = PolygonNewGeneMutator(context)
    val mutator = PolygonLargeIncrementalMutator(context)
    val genetic = PolygonGenetic(context)

    val crossOver = CrossOver()
    val fitnessFunction = ImageDifference(2)
    val selector = StochasticSelector()

    val canvas: BufferedImage = BufferedImage(context.width, context.height, BufferedImage.TYPE_INT_ARGB)
    val canvasGraphics = canvas.graphics
    val mostFitCanvas: BufferedImage = BufferedImage(context.width, context.height, BufferedImage.TYPE_INT_ARGB)
    val mostFitCanvasGraphics = mostFitCanvas.graphics

    val saveOutput = false
    val saveOutputFrequency = 25

    fun run() {
        val frame = JFrame()
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
        frame.setSize(target.width, target.height)
        frame.setVisible(true)

        var i = 0
        var population = genetic.newPopulation()
        val panel = object: JPanel() {
            override fun paintComponent(g: Graphics) {
                super.paintComponent(g)
                genetic.expressDna(mostFitCanvasGraphics, population.first())
                g.drawImage(mostFitCanvas, 0, 0, context.width, context.height, this)

                if (saveOutput && (i % saveOutputFrequency == 0)) {
                    ImageIO.write(mostFitCanvas, "png", File("/tmp/evolved_$i.png"))
                }
            }
        };
        frame.add(panel)
        panel.revalidate()

        do {
            population = evaluateFitness(canvasGraphics, population)
            println("${i}, ${population.first().fitness}")
            panel.repaint()
            population = buildNextGeneration(population)
            i++
        } while (population.first().fitness > 0)
        ImageIO.write(mostFitCanvas, "png", File("/tmp/evolved.png"))
    }

    private fun buildNextGeneration(population: List<Individual>): List<Individual> {
        val nextGeneration = mutableListOf<Individual>()

        if (context.populationCount == 1) {
            throw RuntimeException("Population must be > 1")
        }
        // elitism
        nextGeneration.add(population.first())

        while (nextGeneration.size < population.size) {
            val one = selector.select(population)
            val two = selector.select(population)
            nextGeneration.add(crossOver.perform(Pair(one, two), mutator, context.mutationProbability.next()))
        }

        return nextGeneration
    }

    private fun evaluateFitness(g: Graphics, population: List<Individual>): List<Individual> {
        population.forEach { individual ->
            genetic.expressDna(g, individual) // expresses to canvas, reuse
            individual.fitness = fitnessFunction.compare(canvas, target)
        }
        return population.sortedBy { individual -> individual.fitness }
    }

}