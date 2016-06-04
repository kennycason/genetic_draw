package com.kennycason.genetic.draw.gene.shape

import java.awt.Color
import java.awt.Graphics

/**
 * Created by kenny on 6/4/16.
 */
class Polygon(override var color: IntArray,
              var x: IntArray,
              var y: IntArray,
              override var z: Int) : Shape {
    override val type: ShapeType = ShapeType.POLYGON

    override fun draw(g: Graphics) {
        g.color = Color(color.get(0), color.get(1), color.get(2), color.get(3))
        g.fillPolygon(x, y, x.size)
    }

    override fun copy(): Polygon {
        return Polygon(
                color = color.copyOf(),
                x = x.copyOf(),
                y = y.copyOf(),
                z = z.toInt()
        )
    }
}