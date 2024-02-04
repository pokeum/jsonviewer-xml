package kr.pokeum.app

import android.content.Context
import android.net.Uri
import kr.pokeum.app.util.readAssetsFile
import kr.pokeum.app.util.readCacheFile
import kr.pokeum.app.util.readFileFromUri
import kr.pokeum.jsonviewer_xml.JsonParser
import kr.pokeum.jsonviewer_xml.model.JsonElement
import kr.pokeum.jsonviewer_xml.model.JsonObject
import org.json.JSONException

class JsonGenerator(
    private val context: Context
) {
    private val jsonParser = JsonParser
        .Builder()
        .setComparator(compareBy { it !is JsonObject })
        .build()

    @Throws(JSONException::class)
    fun generateFromString(jsonString: String): JsonElement? {
        return try {
            jsonParser.parse(jsonString)
        }
        // Raise a JSONException if it is not a JSONObject or JSONArray.
        catch (e: JSONException) { throw e }
    }

    @Throws(JSONException::class)
    fun generateFromCacheFile(filename: String): JsonElement? {

        return try {
            jsonParser.parse(String(context.readCacheFile(filename)))
        }
        // Raise a JSONException if it is not a JSONObject or JSONArray.
        catch (e: JSONException) { throw e }
        catch (_: Exception) { null }
    }

    @Throws(JSONException::class)
    fun generateFromAssetsFile(filename: String): JsonElement? {

        return try {
            jsonParser.parse(context.readAssetsFile(filename))
        }
        // Raise a JSONException if it is not a JSONObject or JSONArray.
        catch (e: JSONException) { throw e }
        catch (_: Exception) { null }
    }

    @Throws(JSONException::class)
    fun generateFromUri(uri: Uri): JsonElement? {

        return try {
            jsonParser.parse(context.readFileFromUri(uri)!!)
        }
        // Raise a JSONException if it is not a JSONObject or JSONArray.
        catch (e: JSONException) { throw e }
        catch (_: Exception) { null }
    }
}