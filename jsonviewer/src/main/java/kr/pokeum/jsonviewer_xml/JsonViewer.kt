package kr.pokeum.jsonviewer_xml

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import kr.pokeum.jsonviewer_xml.adapter.JsonViewerAdapter
import kr.pokeum.jsonviewer_xml.databinding.LayoutJsonViewerBinding

class JsonViewer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutJsonViewerBinding = LayoutJsonViewerBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    var jsonRecyclerView: RecyclerView
        private set

    init {
        jsonRecyclerView = binding.jsonRecyclerView
    }

    fun setAdapter(adapter: JsonViewerAdapter) { jsonRecyclerView.adapter = adapter }
}