package kr.pokeum.jsonviewer_xml

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.pokeum.jsonviewer_xml.adapter.JsonViewerAdapter
import kr.pokeum.jsonviewer_xml.util.JsonViewerColor

@Suppress("DEPRECATION")
class JsonRecyclerView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {

    private var text: String

    private var keyColor: Int
    private var valueColor: Int
    private var splitterColor: Int
    private var typeColor: Int
    private var arrowColor: Int
    private var bracketColor: Int
    private var dividerColor: Int

    init {
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.JsonRecyclerView, 0, 0
        )
        try {
            text = typedArray.getString(R.styleable.JsonRecyclerView_text) ?: DEFAULT_TEXT

            keyColor = typedArray.getColor(R.styleable.JsonRecyclerView_keyColor, resources.getColor(R.color.jv_key_color))
            valueColor = typedArray.getColor(R.styleable.JsonRecyclerView_valueColor, resources.getColor(R.color.jv_value_color))
            splitterColor = typedArray.getColor(R.styleable.JsonRecyclerView_splitterColor, resources.getColor(R.color.jv_splitter_color))
            typeColor = typedArray.getColor(R.styleable.JsonRecyclerView_typeColor, resources.getColor(R.color.jv_type_color))
            arrowColor = typedArray.getColor(R.styleable.JsonRecyclerView_arrowColor, resources.getColor(R.color.jv_arrow_color))
            bracketColor = typedArray.getColor(R.styleable.JsonRecyclerView_bracketColor, resources.getColor(R.color.jv_bracket_color))
            dividerColor = typedArray.getColor(R.styleable.JsonRecyclerView_dividerColor, resources.getColor(R.color.jv_divider_color))
        } finally {
            typedArray.recycle()
        }
        layoutManager = LinearLayoutManager(context)
        setText(text)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun setText(text: String) {
        this.text = text

        val jsonParser = JsonParser.Builder().build()
        adapter = JsonViewerAdapter(try {
            jsonParser.parse(text)
        } catch (_: Throwable) {
            jsonParser.parse(DEFAULT_TEXT)
        }).apply {
            setKeyColor(JsonViewerColor(keyColor))
            setValueColor(JsonViewerColor(valueColor))
            setSplitterColor(JsonViewerColor(splitterColor))
            setTypeColor(JsonViewerColor(typeColor))
            setArrowColor(JsonViewerColor(arrowColor))
            setBracketColor(JsonViewerColor(bracketColor))
            setDividerColor(JsonViewerColor(dividerColor))
        }
    }

    companion object {
        private const val DEFAULT_TEXT = "{}"
    }
}