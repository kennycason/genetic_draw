package com.kennycason.genetic.draw.gene

import java.awt.Color

/**
 * Created by kenny on 5/23/16.
 */
data class Gene(var x: Int,
                var y: Int,
                var z: Int,
                var w: Int,
                var h: Int,
                var shape: Shape,
                var color: Color)