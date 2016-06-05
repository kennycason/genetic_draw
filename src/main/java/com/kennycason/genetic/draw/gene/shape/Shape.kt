package com.kennycason.genetic.draw.gene.shape

import com.kennycason.genetic.draw.Context
import java.awt.Color
import java.awt.Graphics

/**
 * Created by kenny on 6/4/16.
 */
interface Shape {
    val z: Int // z-index
    val type: ShapeType
    var color: IntArray // RGBA
    fun draw(g: Graphics, context: Context)
    fun copy(): Shape
}