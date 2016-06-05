package com.kennycason.genetic.draw.gene.shape

import com.kennycason.genetic.draw.Context
import java.awt.Color
import java.awt.Graphics

/**
 * Created by kenny on 6/4/16.
 */
class Ellipse(override var color: IntArray,
              var x: Int,
              var y: Int,
              override var z: Int,
              var w: Int,
              var h: Int) : Shape {
    override val type: ShapeType = ShapeType.ELLIPSE

    override fun draw(g: Graphics, context: Context) {
        if (context.useAlpha) {
            g.color = Color(color.get(0), color.get(1), color.get(2), color.get(3))
        } else {
            g.color = Color(color.get(0), color.get(1), color.get(2))
        }
        g.fillOval(x, y, w, h)
    }

    override fun copy(): Ellipse {
        return Ellipse(
                color = color.copyOf(),
                x = x,
                y = y,
                z = z,
                w = w,
                h = h
        )
    }
}