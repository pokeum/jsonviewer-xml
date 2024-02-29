package kr.pokeum.jsonviewer_xml.util

import android.content.res.Configuration
import android.view.View
import androidx.annotation.ColorInt

class JVColor(
    @ColorInt private val default: Int,
    @ColorInt private val night: Int
) {
    constructor(@ColorInt default: Int) : this(default, default)

    internal fun getColor(view: View) = when (view.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES -> night
        else -> default
    }
}