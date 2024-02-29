package kr.pokeum.jsonviewer_xml.adapter

import androidx.recyclerview.widget.RecyclerView
import kr.pokeum.jsonviewer_xml.util.JVColor

abstract class BaseJsonViewerAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>()
{
//    abstract fun expandAll()
//    abstract fun collapseAll()

    fun setKeyColor(color: JVColor) { KEY_COLOR = color }
    fun setValueColor(color: JVColor) { VALUE_COLOR = color }
    fun setSplitterColor(color: JVColor) { SPLITTER_COLOR = color }
    fun setTypeColor(color: JVColor) { TYPE_COLOR = color }
    fun setArrowColor(color: JVColor) { ARROW_COLOR = color }
    fun setBracketColor(color: JVColor) { BRACKET_COLOR = color }
    fun setDividerColor(color: JVColor) { DIVIDER_COLOR = color }

    companion object {
        internal var KEY_COLOR          = JVColor(0xFF000000.toInt(), 0xFFFFFFFF.toInt())
        internal var VALUE_COLOR        = JVColor(0xFF888888.toInt())
        internal var SPLITTER_COLOR     = JVColor(0xFF000000.toInt(), 0xFFFFFFFF.toInt())
        internal var TYPE_COLOR         = JVColor(0xFF2196F3.toInt())
        internal var ARROW_COLOR        = JVColor(0xFFF44336.toInt())
        internal var BRACKET_COLOR      = JVColor(0xFF4CAF50.toInt())
        internal var DIVIDER_COLOR      = JVColor(0x1E000000.toInt(), 0x1EFFFFFF.toInt())
    }
}

