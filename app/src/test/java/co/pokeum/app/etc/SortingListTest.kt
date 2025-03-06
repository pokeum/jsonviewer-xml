package co.pokeum.app.etc

import org.junit.Test
import org.junit.Assert.*

class SortingListTest {

    @Test
    fun sortedTest() {
        val expected = listOf(2, 4, 10, 50, 77, 100)
        val list = listOf(10, 100, 50, 2, 77, 4)

        val result = list.sorted()
        assertEquals(expected, result)
    }

    @Test
    fun sortTest() {
        val expected = listOf(2, 4, 10, 50, 77, 100)
        val list = mutableListOf(10, 100, 50, 2, 77, 4)

        list.sort()
        assertEquals(expected, list)
    }

    @Test
    fun alphabetOrderSortedTest() {
        val expected = listOf("apple", "banana", "kiwi", "orange", "watermelon")
        val list = listOf("apple", "banana", "kiwi", "orange", "watermelon")

        val result = list.sorted()
        assertEquals(expected, result)
    }

    @Test
    fun lengthSortedWithTest() {
        val expected = listOf("kiwi", "apple", "banana", "orange", "watermelon")
        val list = listOf("apple", "banana", "kiwi", "orange", "watermelon")

        val comparator: Comparator<String> = compareBy { it.length }
        val result = list.sortedWith(comparator)
        assertEquals(expected, result)
    }

    @Test
    fun test() {
        val list = listOf(1, 0)
        val result = list.sortedWith(compareBy { it == 1 })
        println(result)
    }
}