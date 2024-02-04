package kr.pokeum.jsonviewer_xml

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.pokeum.jsonviewer_xml.adapter.JsonViewerAdapter

class JsonRecyclerView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {

    private var text: String

    init {
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.JsonRecyclerView, 0, 0
        )
        try {
            text = typedArray.getString(R.styleable.JsonRecyclerView_text) ?: DEFAULT_TEXT
        } finally {
            typedArray.recycle()
        }
        layoutManager = LinearLayoutManager(context)
        setText(text)
    }

    fun setText(text: String) {
        this.text = text

        val jsonParser = JsonParser.Builder().build()
        adapter = JsonViewerAdapter(try {
            jsonParser.parse(text)
        } catch (_: Throwable) {
            jsonParser.parse(DEFAULT_TEXT)
        })
    }

    companion object {
        private const val DEFAULT_TEXT = "{}"
    }
}