package com.kennycason.genetic.draw.gene.shape

import java.awt.Color
import java.awt.Graphics

/**
 * Created by kenny on 6/4/16.
 */
class Rectangle(override var color: IntArray,
                var x: Int,
                var y: Int,
                override var z: Int,
                var w: Int,
                var h: Int) : Shape {
    override val type: ShapeType = ShapeType.RECTANGLE

    override fun draw(g: Graphics) {
        g.color = Color(color.get(0), color.get(1), color.get(2), color.get(3))
        g.fillRect(x, y, w, h)
    }

    override fun copy(): Rectangle {
        return Rectangle(
                color = color.copyOf(),
                x = x.toInt(),
                y = y.toInt(),
                z = z.toInt(),
                w = w.toInt(),
                h = h.toInt()
        )
    }
}