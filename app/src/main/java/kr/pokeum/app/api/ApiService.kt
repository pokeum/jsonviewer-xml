package kr.pokeum.app.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.net.URL

fun performNetworkRequest(url: String, callback: (String) -> Unit) {
    GlobalScope.launch(Dispatchers.IO) {
        try {
            if (isValidUrl(url)) {
                val response = makeRequest(url)
                withContext(Dispatchers.Main) { callback(response) }
            }
        } catch (_: IOException) { /* do nothing */ }
    }
}

/**
 * Make the actual network request
 */
@Throws(IOException::class)
private fun makeRequest(url: String): String {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url(url)
        .build()

    client.newCall(request).execute().use { response ->
        if (response.isSuccessful) {
            return response.body?.string() ?:
                ""  // empty response
        } else {
            throw IOException("Unexpected response code: ${response.code}")
        }
    }
}

private fun isValidUrl(url: String): Boolean {
    return try {
        URL(url)
        true
    } catch (e: Exception) { false }
}