package kr.pokeum.jsonviewer_xml.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kr.pokeum.jsonviewer_xml.adapter.BaseJsonViewerAdapter
import kr.pokeum.jsonviewer_xml.databinding.ItemJsonNullBinding
import kr.pokeum.jsonviewer_xml.model.JsonNull
import kr.pokeum.jsonviewer_xml.util.fromHtml
import kr.pokeum.jsonviewer_xml.util.keyValueHtmlGenerator

internal class JsonNullViewHolder(
    containerView: View
) : RecyclerView.ViewHolder(containerView) {

    private val binding: ItemJsonNullBinding = ItemJsonNullBinding.bind(containerView)

    fun bind(jsonNull: JsonNull) {
        binding.keyLabel.text = fromHtml(
            keyValueHtmlGenerator(
                key = "\"${jsonNull.key}\"",
                value = NULL,
                splitter = SPLITTER,
                view = binding.root
            )
        )

        setStyle()
    }

    private fun setStyle() {
        // COLOR
        binding.keyDescriptionLabel.setTextColor(BaseJsonViewerAdapter.TYPE_COLOR.getColor(binding.root))
    }

    companion object {
        private const val SPLITTER = " : "
        private const val NULL = "NULL"
    }
}