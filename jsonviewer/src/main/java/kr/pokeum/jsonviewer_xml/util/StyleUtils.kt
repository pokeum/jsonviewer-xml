package kr.pokeum.jsonviewer_xml.util

import android.content.res.Configuration
import android.view.View
import androidx.annotation.ColorInt

data class JsonViewerColor(
    @ColorInt private val color: Int,
    @ColorInt private val darkModeColor: Int? = null
) {
    internal fun getColor(view: View) =
        if (isDarkMode(view)) { darkModeColor ?: color } else { color }

    private fun isDarkMode(view: View): Boolean =
        when (view.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
        }
}