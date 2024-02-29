package kr.pokeum.jsonviewer_xml.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kr.pokeum.jsonviewer_xml.adapter.BaseJsonViewerAdapter
import kr.pokeum.jsonviewer_xml.databinding.ItemJsonPrimitiveBinding
import kr.pokeum.jsonviewer_xml.model.JsonPrimitive
import kr.pokeum.jsonviewer_xml.util.fromHtml
import kr.pokeum.jsonviewer_xml.util.keyValueHtmlGenerator

internal class JsonPrimitiveViewHolder(
    containerView: View
) : RecyclerView.ViewHolder(containerView) {

    private val binding: ItemJsonPrimitiveBinding = ItemJsonPrimitiveBinding.bind(containerView)
    private var target: JsonPrimitive? = null

    fun bind(jsonPrimitive: JsonPrimitive) {
        target = jsonPrimitive
        val value: String = when (jsonPrimitive.value) {
            is Number -> {
                binding.keyDescriptionLabel.text = NUMBER_KEY_DESCRIPTION
                jsonPrimitive.value.toString()
            }
            is String -> {
                binding.keyDescriptionLabel.text = STRING_KEY_DESCRIPTION
                "\"${jsonPrimitive.value}\""
            }
            else -> {   // is Boolean
                binding.keyDescriptionLabel.text = BOOLEAN_KEY_DESCRIPTION
                jsonPrimitive.value.toString()
            }
        }
        binding.keyLabel.text = fromHtml(
            keyValueHtmlGenerator(
                key = "\"${jsonPrimitive.key}\"",
                value = value,
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
        private const val NUMBER_KEY_DESCRIPTION = "123"
        private const val STRING_KEY_DESCRIPTION = "ABC"
        private const val BOOLEAN_KEY_DESCRIPTION = "T/F"
        private const val SPLITTER = " : "
    }
}