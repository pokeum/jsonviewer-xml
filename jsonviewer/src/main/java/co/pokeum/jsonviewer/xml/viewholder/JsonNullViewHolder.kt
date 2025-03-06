package co.pokeum.jsonviewer.xml.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import co.pokeum.jsonviewer.xml.adapter.BaseJsonViewerAdapter
import co.pokeum.jsonviewer.xml.databinding.ItemJsonNullBinding
import co.pokeum.jsonviewer.xml.model.JsonNull
import co.pokeum.jsonviewer.xml.util.fromHtml
import co.pokeum.jsonviewer.xml.util.keyValueHtmlGenerator

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