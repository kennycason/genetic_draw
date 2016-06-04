package com.kennycason.genetic.draw

import com.kennycason.genetic.draw.fitness.ImageDifference
import org.junit.Ignore
import org.junit.Test
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import kotlin.test.assertEquals

/**
 * Created by kenny on 5/23/16.
 */
class ImageCompareTest {
    val fitnessFunction = ImageDifference(1)

    @Test
    fun rgbLogic() {
        val rgb = 0xFFFFFF
        val rgb2 = 0x000000
        assertEquals(255, Math.abs((rgb and 0xFF) - (rgb2 and 0xFF)))
        assertEquals(255, Math.abs(((rgb shr 8) and 0xFF) - ((rgb2 shr 8) and 0xFF)))
        assertEquals(255, Math.abs(((rgb shr 16) and 0xFF) - ((rgb2 shr 16) and 0xFF)))
    }

    @Ignore
    fun different() {
        val bi: BufferedImage = ImageIO.read(Thread.currentThread().contextClassLoader.getResource("bulbasaur_medium.bmp"))
        val bi2: BufferedImage = ImageIO.read(Thread.currentThread().contextClassLoader.getResource("bulbasaur_1px.bmp"))
        assertEquals(255.0 * 3, fitnessFunction.compare(bi, bi2))
    }

    @Test
    fun same() {
        val bi: BufferedImage = ImageIO.read(Thread.currentThread().contextClassLoader.getResource("bulbasaur_medium.bmp"))
        val bi2: BufferedImage = ImageIO.read(Thread.currentThread().contextClassLoader.getResource("bulbasaur_medium.bmp"))
        assertEquals(0.0, fitnessFunction.compare(bi, bi2))
    }
}