package co.pokeum.jsonviewer.xml.adapter

import androidx.recyclerview.widget.RecyclerView
import co.pokeum.jsonviewer.xml.util.JsonViewerColor

abstract class BaseJsonViewerAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>()
{
//    abstract fun expandAll()
//    abstract fun collapseAll()

    fun setKeyColor(color: JsonViewerColor) { KEY_COLOR = color }
    fun setValueColor(color: JsonViewerColor) { VALUE_COLOR = color }
    fun setSplitterColor(color: JsonViewerColor) { SPLITTER_COLOR = color }
    fun setTypeColor(color: JsonViewerColor) { TYPE_COLOR = color }
    fun setArrowColor(color: JsonViewerColor) { ARROW_COLOR = color }
    fun setBracketColor(color: JsonViewerColor) { BRACKET_COLOR = color }
    fun setDividerColor(color: JsonViewerColor) { DIVIDER_COLOR = color }

    companion object {
        internal var KEY_COLOR          = JsonViewerColor(0xFF000000.toInt(), 0xFFFFFFFF.toInt())
        internal var VALUE_COLOR        = JsonViewerColor(0xFF888888.toInt())
        internal var SPLITTER_COLOR     = JsonViewerColor(0xFF000000.toInt(), 0xFFFFFFFF.toInt())
        internal var TYPE_COLOR         = JsonViewerColor(0xFF2196F3.toInt())
        internal var ARROW_COLOR        = JsonViewerColor(0xFFF44336.toInt())
        internal var BRACKET_COLOR      = JsonViewerColor(0xFF4CAF50.toInt())
        internal var DIVIDER_COLOR      = JsonViewerColor(0x1E000000.toInt(), 0x1EFFFFFF.toInt())
    }
}

