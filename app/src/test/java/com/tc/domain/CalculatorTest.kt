package com.tc.domain

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class CalculatorTest {

    /**
     * 3 A A A -> arrange -> act -> Assert
     *
     */

    lateinit var calc : Calculator

    val listUnderTest = mutableListOf<Int>()


    @Before
    fun setUp() {
        calc = Calculator()

        repeat(10000000){
            listUnderTest.add(Random.nextInt(1, 9) )
        }
    }

    @Test
    fun add_min_and_min(){
        val result = calc.add(-1, -1)
        assertEquals(-2, result)
    }

    @Test
    fun add_0_to_the_number(){
        val x = 5L + Int.MAX_VALUE
//        assertEquals(x, Int.MAX_VALUE + 5)
    }

    @Test
    fun `testing binary search for overflow indexing`(){
        assertEquals(true, listUnderTest.contains(8))
        assertEquals(true, calc.bs(listUnderTest, 8))
    }

    @After
    fun tearDown() {
        //calc = null
    }

}