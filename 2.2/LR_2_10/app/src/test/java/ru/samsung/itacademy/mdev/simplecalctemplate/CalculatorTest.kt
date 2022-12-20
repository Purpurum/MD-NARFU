package ru.samsung.itacademy.mdev.simplecalctemplate

import org.jetbrains.annotations.Contract
import org.junit.Test
import org.junit.Assert
import org.junit.Before
import org.junit.After

class CalculatorTest {
    private var calculator: Calculator? = null
    @Before
    fun setUp() {
        calculator = Calculator()
    }

    @Test
    fun addition() {
        Assert.assertEquals(3, calculator!!.add(1, 2).toLong())
    }

    @Test
    fun substracion() {
        Assert.assertEquals(-1, calculator!!.subtract(1, 2).toLong())
    }

    @Test
    fun multiplication() {
        Assert.assertEquals(2, calculator!!.multiply(1, 2).toLong())
    }
    @Test
    fun division() {
        Assert.assertEquals(0, calculator!!.divide(1, 2).toLong())
    }
    @Test
    fun divisionbyzero() {
        Assert.assertEquals(0, calculator!!.divide(1, 0).toLong())
    }

    @After
    fun tearDown() {
        calculator = null
    }
}