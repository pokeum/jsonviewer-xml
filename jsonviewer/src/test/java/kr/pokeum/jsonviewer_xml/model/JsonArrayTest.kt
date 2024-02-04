package kr.pokeum.jsonviewer_xml.model

import kr.pokeum.jsonviewer_xml.JsonParser
import org.junit.Assert
import org.junit.Test

class JsonArrayTest {

    private val jsonParser = JsonParser.Builder().build()

    @Test
    fun `convert to string`() {

        var jsonString = "[\"Basketball\", \"Soccer\", \"Rugby\", \"Baseball\", \"Billiards\", \"Golf\"]"
        var jsonElement = jsonParser.parse(jsonString)
        Assert.assertEquals(jsonString, jsonElement.toString())

        jsonString = "[[\"a\", \"b\", \"c\"], [\"a\", \"b\", 1, 2.0], [1, true, false, null]]"
        jsonElement = jsonParser.parse(jsonString)
        Assert.assertEquals(jsonString, jsonElement.toString())
    }
}