package kr.pokeum.jsonviewer_xml.util

import android.os.Build
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.text.Spanned
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import kr.pokeum.jsonviewer_xml.R

internal fun fromHtml(htmlText: String): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(htmlText, FROM_HTML_MODE_LEGACY)
    } else { Html.fromHtml(htmlText) }
}

internal fun keyValueHtmlGenerator(
    key: String,
    value: String,
    splitter: String,
    view: View, /* Support dark mode */
): String {
    return textColorHtmlGenerator(key, ContextCompat.getColor(view.context, R.color.jv_key_color)) +
            textColorHtmlGenerator(splitter, ContextCompat.getColor(view.context, R.color.jv_splitter_color)) +
            textColorHtmlGenerator(value, ContextCompat.getColor(view.context, R.color.jv_value_color))
}

/**
 * e.g. <font color='#FF0000'>This text is red</font>
 */
private fun textColorHtmlGenerator(
    text: String,
    @ColorInt color: Int
): String {
    return "<font color='${hexToHtml(color)}'>${text}</font>"
}

private fun hexToHtml(@ColorInt hex: Int) = String.format("#%06X", 0xFFFFFF and hex)