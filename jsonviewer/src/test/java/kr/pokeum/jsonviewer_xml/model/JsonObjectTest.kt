package kr.pokeum.jsonviewer_xml.model

import kr.pokeum.jsonviewer_xml.JsonParser
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test

class JsonObjectTest {

    private val jsonParser = JsonParser.Builder().build()

    @Test
    fun `convert to string`() {

        val jsonString = "{\"name\":\"John\", \"age\":30, \"car\":null}"
        val jsonElement = jsonParser.parse(jsonString)

        val jsonObject = JSONObject(jsonElement.toString())
        assertEquals("John", jsonObject.getString("name"))
        assertEquals(30, jsonObject.getInt("age"))
        assertJSONObjectNull(jsonObject, "car")
    }

    private fun assertJSONObjectNull(jsonObject: JSONObject, key: String) {
        val orgJsonNullQualifiedName = "org.json.JSONObject.Null"
        val jsonNull = jsonObject.get(key)
        assertTrue(jsonNull::class.qualifiedName == orgJsonNullQualifiedName)
    }
}