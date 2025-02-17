package kr.pokeum.app.util

import android.content.Context
import android.net.Uri
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Reads the contents of a file from assets
 */
@Throws(IOException::class)
fun Context.readAssetsFile(filename: String): String {
    val inputStream: InputStream = assets.open(filename)
    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
    val stringBuilder = StringBuilder()
    var line: String?
    while (bufferedReader.readLine().also { line = it } != null) {
        stringBuilder.append(line)
    }
    inputStream.close()
    return stringBuilder.toString()
}

/**
 * Reads the contents of a file from the given Content URI
 */
@Throws(IOException::class)
fun Context.readFileFromUri(uri: Uri): String? {
    val inputStream = contentResolver.openInputStream(uri)
    return inputStream?.let { istream ->
        val reader = BufferedReader(InputStreamReader(istream))
        val stringBuilder = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
        istream.close()
        stringBuilder.toString()
    }
}

@Throws(IOException::class)
fun Context.readCacheFile(filename: String): ByteArray {
    val fileToReadFrom = File(cacheDir, filename)
    val size = fileToReadFrom.length().toInt()
    val bytes = ByteArray(size)
    val tmpBuff = ByteArray(size)
    val fis = FileInputStream(fileToReadFrom)
    try {
        var read = fis.read(bytes, 0, size)
        if (read < size) {
            var remain = size - read
            while (remain > 0) {
                read = fis.read(tmpBuff, 0, remain)
                System.arraycopy(tmpBuff, 0, bytes, size - remain, read)
                remain -= read
            }
        }
    }
    catch (e: IOException) { throw e }
    finally { fis.close() }
    return bytes
}

fun Context.writeCacheFile(bytesToWrite: ByteArray, filename: String) {
    val fileToWriteIn = File(cacheDir, filename)
    try {
        if (!fileToWriteIn.exists()) {
            fileToWriteIn.createNewFile()
        }
        val fos = FileOutputStream(fileToWriteIn)
        fos.write(bytesToWrite)
        fos.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

@ColorInt
fun Context.getColorInt(@ColorRes resourceId: Int): Int =
    ContextCompat.getColor(this, resourceId)