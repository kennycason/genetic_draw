package com.kennycason.genetic.draw.gene.shape

import java.awt.Color
import java.awt.Graphics

/**
 * Created by kenny on 6/4/16.
 */
class Circle(override var color: IntArray,
              var x: Int,
              var y: Int,
             override var z: Int,
              var r: Int) : Shape {
    override val type: ShapeType = ShapeType.CIRCLE

    override fun draw(g: Graphics) {
        g.color = Color(color.get(0), color.get(1), color.get(2), color.get(3))
        g.fillOval(x, y, r, r)
    }

    override fun copy(): Circle {
        return Circle(
                color = color.copyOf(),
                x = x.toInt(),
                y = y.toInt(),
                z = z.toInt(),
                r = r.toInt()
        )
    }
}