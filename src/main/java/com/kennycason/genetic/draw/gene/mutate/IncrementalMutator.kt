package com.kennycason.genetic.draw.gene.mutate

import com.kennycason.genetic.draw.Context
import com.kennycason.genetic.draw.gene.*
import com.kennycason.genetic.draw.gene.shape.*
import java.awt.Color
import java.util.*

/**
 * Created by kenny on 5/30/16.
 */
class IncrementalMutator(val context: Context) : Mutator {
    val random = Random()
    val genetic = Genetic(context)

    // for moving/size
    val maxDelta = ((context.width + context.height) / 2) / 2
    val halfDelta = maxDelta / 2 + 2
    // for color
    val maxColorDelta = 100
    val halfMaxColorDelta = maxColorDelta / 2

    // for moving/size pixels
    val maxPixelWidth = context.width / context.pixelSize
    val maxPixelHeight = context.height / context.pixelSize

    override fun mutate(gene: Shape, probability: Float): Shape {
        // don't mutate
        if (random.nextDouble() > probability) { return gene }

        when (gene.type) {
            ShapeType.RECTANGLE -> return mutateRectangle(gene as Rectangle)
            ShapeType.ELLIPSE -> return mutateEllipse(gene as Ellipse)
            ShapeType.POLYGON -> return mutatePolygon(gene as Polygon)
            ShapeType.PIXEL -> return mutatePixel(gene as Pixel)
            ShapeType.CIRCLE -> return mutateCircle(gene as Circle)
        }
    }

    private fun mutatePixel(gene: Pixel): Pixel {
        val mutationType = random.nextInt(7)
        when (mutationType) {
            0 -> gene.x = genetic.bound((gene.x / context.pixelSize) + random.nextInt(maxPixelWidth / 2) - maxPixelWidth / 4 , 0, maxPixelWidth) * context.pixelSize
            1 -> gene.y = genetic.bound((gene.y / context.pixelSize) + random.nextInt(maxPixelHeight / 2) - maxPixelHeight / 4, 0, maxPixelHeight) * context.pixelSize
            2 -> gene.z = random.nextInt(1000)
            3 -> mutateColor(gene, 0) // red
            4 -> mutateColor(gene, 1) // green
            5 -> mutateColor(gene, 2) // blue
            6 -> mutateColor(gene, 3) // alpha
        }
        return gene
    }

    private fun mutatePolygon(gene: Polygon): Polygon {
        if (random.nextBoolean()) {
            val colorMutation = random.nextInt(4)
            when (colorMutation) {
                0 -> mutateColor(gene, 0) // red
                1 -> mutateColor(gene, 1) // green
                2 -> mutateColor(gene, 2) // blue
                3 -> mutateColor(gene, 3) // alpha
            }
        }
        else { // mutate position
            if (random.nextBoolean()) {
                val position = random.nextInt(gene.x.size)
                gene.x.set(position, genetic.bound(gene.x.get(position) + random.nextInt(maxDelta) - halfDelta, 0, context.width))
                gene.y.set(position, genetic.bound(gene.y.get(position) + random.nextInt(maxDelta) - halfDelta, 0, context.height))
            } else {
                gene.z = random.nextInt(1000)
            }
        }
        return gene
    }

    private fun mutateRectangle(gene: Rectangle): Rectangle {
        val mutationType = random.nextInt(9)
        when (mutationType) {
            0 -> gene.x = genetic.bound(gene.x + random.nextInt(maxDelta) - halfDelta, 0, context.width - gene.w)
            1 -> gene.y = genetic.bound(gene.y + random.nextInt(maxDelta) - halfDelta, 0, context.height - gene.h)
            2 -> gene.z = random.nextInt(1000)
            3 -> gene.w = genetic.bound(gene.w + random.nextInt(maxDelta) - halfDelta, 5, context.width)
            4 -> gene.h = genetic.bound(gene.h + random.nextInt(maxDelta) - halfDelta, 5, context.height)
            5 -> mutateColor(gene, 0) // red
            6 -> mutateColor(gene, 1) // green
            7 -> mutateColor(gene, 2) // blue
            8 -> mutateColor(gene, 3) // alpha
        }
        return gene
    }

    private fun mutateEllipse(gene: Ellipse): Ellipse {
        val mutationType = random.nextInt(9)
        when (mutationType) {
            0 -> gene.x = genetic.bound(gene.x + random.nextInt(maxDelta) - halfDelta, 0, context.width - gene.w)
            1 -> gene.y = genetic.bound(gene.y + random.nextInt(maxDelta) - halfDelta, 0, context.height - gene.h)
            2 -> gene.z = random.nextInt(1000)
            3 -> gene.w = genetic.bound(gene.w + random.nextInt(maxDelta) - halfDelta, 5, context.width)
            4 -> gene.h = genetic.bound(gene.h + random.nextInt(maxDelta) - halfDelta, 5, context.height)
            5 -> mutateColor(gene, 0) // red
            6 -> mutateColor(gene, 1) // green
            7 -> mutateColor(gene, 2) // blue
            8 -> mutateColor(gene, 3) // alpha
        }
        return gene
    }

    private fun mutateCircle(gene: Circle): Circle {
        val mutationType = random.nextInt(8)
        when (mutationType) {
            0 -> gene.x = genetic.bound(gene.x + random.nextInt(maxDelta) - halfDelta, 0, context.width - gene.r)
            1 -> gene.y = genetic.bound(gene.y + random.nextInt(maxDelta) - halfDelta, 0, context.height - gene.r)
            2 -> gene.z = random.nextInt(1000)
            3 -> gene.r = genetic.bound(gene.r + random.nextInt(maxDelta) - halfDelta, 5, context.width)
            4 -> mutateColor(gene, 0) // red
            5 -> mutateColor(gene, 1) // green
            6 -> mutateColor(gene, 2) // blue
            7 -> mutateColor(gene, 3) // alpha
        }
        return gene
    }

    private fun mutateColor(gene: Shape, i: Int) {
        gene.color.set(i, genetic.bound(gene.color.get(i) + random.nextInt(maxColorDelta) - halfMaxColorDelta, 0, 255))
    }

}