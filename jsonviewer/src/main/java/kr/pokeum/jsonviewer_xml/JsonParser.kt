package kr.pokeum.jsonviewer_xml

// https://www.rfc-editor.org/rfc/rfc7159.html

import kr.pokeum.jsonviewer_xml.model.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class JsonParser private constructor(
    private val comparator: Comparator<JsonElement>?
) {

    @Throws(JSONException::class)
    fun parse(jsonString: String): JsonElement {
        return try {
            parse("", JSONObject(jsonString))
        } catch (e: JSONException) {
            parse("", JSONArray(jsonString))
        }
        // Raise a JSONException if it is not a JSONObject or JSONArray.
    }

    private fun parse(key: String, value: Any): JsonElement {
        when (value) {
            is JSONObject -> {
                val elements = mutableListOf<JsonElement>()
                for (childKey in value.keys()) {
                    elements.add(
                        parse(
                            key = childKey,
                            value = value[childKey]
                        )
                    )
                }
                // An object is an unordered collection of zero or more name/value pairs,
                // where a name is a string and a value is a string, number, boolean, null, object, or array.
                comparator?.let { elements.sortWith(it) }
                return JsonObject(key, elements)
            }
            is JSONArray -> {
                val elements = mutableListOf<JsonElement>()
                for (index in 0 until value.length()) {
                    val child = value[index]
                    elements.add(
                        parse(
                            key = index.toString(),
                            value = child
                        )
                    )
                }
                // An array is an ordered sequence of zero or more values.
                return JsonArray(key, elements)
            }
            is String -> {
                return JsonPrimitive(key, value)
            }
            is Number -> {
                return JsonPrimitive(key, value)
            }
            is Boolean -> {
                return JsonPrimitive(key, value)
            }
            else -> {
                return JsonNull(key)
            }
        }
    }

    class Builder {
        private var comparator: Comparator<JsonElement>? = null

        fun setComparator(comparator: Comparator<JsonElement>): Builder {
            this.comparator = comparator
            return this
        }

        fun build(): JsonParser {
            return JsonParser(comparator = comparator)
        }
    }
}