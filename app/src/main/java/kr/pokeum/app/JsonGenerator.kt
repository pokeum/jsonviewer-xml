package kr.pokeum.app

import android.content.Context
import android.net.Uri
import android.widget.Toast
import kr.pokeum.app.util.readAssetsFile
import kr.pokeum.app.util.readCacheFile
import kr.pokeum.app.util.readFileFromUri
import kr.pokeum.app.util.writeCacheFile
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

    /**
     * Generate JSON Element from String
     */
    @Throws(JSONException::class)
    fun fromString(jsonString: String): JsonElement? = try {
        jsonParser.parse(jsonString)
    }
    // Raise a JSONException if it is not a JSONObject or JSONArray.
    catch (e: JSONException) { throw e }
    catch (_: Exception) { null }

    /**
     * Generate JSON Element from an app's assets file
     */
    @Throws(JSONException::class)
    fun fromAssetsFile(filename: String): JsonElement? = try {
        jsonParser.parse(context.readAssetsFile(filename))
    }
    // Raise a JSONException if it is not a JSONObject or JSONArray.
    catch (e: JSONException) { throw e }
    catch (_: Exception) { null }

    /**
     * Generate JSON Element from the given Content URI
     */
    @Throws(JSONException::class)
    fun fromContentUri(uri: Uri): JsonElement? = try {
        jsonParser.parse(context.readFileFromUri(uri)!!)
    }
    // Raise a JSONException if it is not a JSONObject or JSONArray.
    catch (e: JSONException) { throw e }
    catch (_: Exception) { null }

    /**
     * Get Cached JSON Element
     */
    @Throws(JSONException::class)
    fun getCacheData(): JsonElement? = try {
        jsonParser.parse(String(context.readCacheFile(CACHE_JSON_FILE)))
    }
    // Raise a JSONException if it is not a JSONObject or JSONArray.
    catch (e: JSONException) { throw e }
    catch (_: Exception) { null }

    /**
     * Save JSON Element to Cache
     */
    fun saveToCache(loadFunc: JsonGenerator.() -> JsonElement?): Boolean {
        try {
            // write to cache file
            loadFunc.invoke(this)?.let {
                context.writeCacheFile(it.toString().toByteArray(), CACHE_JSON_FILE)
            }
        } catch (_: JSONException) {
            Toast.makeText(context, "Input is not a JSONObject or JSONArray", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    companion object {
        private const val CACHE_JSON_FILE = "cache.json"
    }
}