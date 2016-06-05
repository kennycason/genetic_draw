package com.kennycason.genetic.draw

/**
 * Created by kenny on 5/23/16.
 */

import com.kennycason.genetic.draw.gene.*
import com.kennycason.genetic.draw.gene.mutate.IncrementalMutator
import com.kennycason.genetic.draw.fitness.ImageDifference
import com.kennycason.genetic.draw.fitness.PriorityRegionImageDifference
import com.kennycason.genetic.draw.gene.shape.ShapeType
import com.kennycason.genetic.draw.probability.DynamicRangeProbability
import com.kennycason.genetic.draw.probability.StaticProbability
import com.sun.javafx.iio.ImageStorage
import java.awt.Color
import java.awt.Graphics
import java.awt.Rectangle
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import javax.imageio.ImageIO
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants

fun main(args: Array<String>) {
    SingleParentGeneticDraw().run()
}

class SingleParentGeneticDraw {
    val fileName = "kirby.jpg"
    val target: BufferedImage = ImageIO.read(Thread.currentThread().contextClassLoader.getResource(fileName))
    val context = Context(
            width = target.width,
            height = target.height,
            geneCount = 256,
            mutationProbability = DynamicRangeProbability(0.001f, 0.02f),
            allowedShapes = arrayOf(/*ShapeType.POLYGON,*/ /*ShapeType.RECTANGLE, ShapeType.ELLIPSE*/ ShapeType.PIXEL),
            maxPolygonSize = 3,
            pixelSize = 8,
            useAlpha = true)

    val mutator = IncrementalMutator(context)
    val genetic = Genetic(context)

    val crossOver = CrossOver()
    val fitnessFunction = ImageDifference(2)

    val canvas: BufferedImage = BufferedImage(context.width, context.height, BufferedImage.TYPE_INT_ARGB)
    val canvasGraphics = canvas.graphics
    val mostFitCanvas: BufferedImage = BufferedImage(context.width, context.height, BufferedImage.TYPE_INT_ARGB)
    val mostFitCanvasGraphics = mostFitCanvas.graphics

    val saveOutput = true
    val saveOutputFrequency = 25

    fun run() {
        val frame = JFrame()
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
        frame.setSize(context.width, context.height + 18)
        frame.setVisible(true)

        var mostFit = genetic.newIndividual()
        var mostFitScore = Double.MAX_VALUE

        var i = 0
        val panel = object: JPanel() {
            override fun paintComponent(g: Graphics) {
                super.paintComponent(g)
                genetic.expressDna(mostFitCanvasGraphics, mostFit)
                g.drawImage(mostFitCanvas, 0, 0, context.width, context.height, this)

                if (saveOutput && (i % saveOutputFrequency == 0)) {
                    ImageIO.write(mostFitCanvas, "png", File("/tmp/evolved_$i.png"))
                }
            }
        };
        frame.add(panel)
        panel.revalidate()

        do {
            val child = mutator.mutate(genetic.copy(mostFit), context.mutationProbability.next())

            // evaluate fitness
            canvasGraphics.color = Color.BLACK
            canvasGraphics.clearRect(0, 0, context.width, context.height)
            genetic.expressDna(canvasGraphics, child)
            val fitness = fitnessFunction.compare(canvas, target)

            if (fitness <= mostFitScore) {
                println("$i, $fitness")
                mostFit = child
                mostFitScore = fitness
            }
            panel.repaint() // must redraw as that's what actually draws to the canvas
            i++
        } while (mostFitScore > 0)
        ImageIO.write(mostFitCanvas, "png", File("/tmp/evolved.png"))
    }

}
