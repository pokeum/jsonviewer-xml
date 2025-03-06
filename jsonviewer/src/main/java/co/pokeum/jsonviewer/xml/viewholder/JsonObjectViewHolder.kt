package co.pokeum.jsonviewer.xml.viewholder

import android.annotation.SuppressLint
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.RecyclerView
import co.pokeum.jsonviewer.xml.adapter.BaseJsonViewerAdapter
import co.pokeum.jsonviewer.xml.adapter.JsonViewerAdapter
import co.pokeum.jsonviewer.xml.databinding.ItemJsonObjectBinding
import co.pokeum.jsonviewer.xml.model.JsonObject

internal class JsonObjectViewHolder(
    containerView: View,
    recycledViewPool: RecyclerView.RecycledViewPool
) : RecyclerView.ViewHolder(containerView) {

    private val binding: ItemJsonObjectBinding = ItemJsonObjectBinding.bind(containerView)
    private val childAdapter: JsonViewerAdapter
    private var target: JsonObject? = null

    init {
        binding.headerLayout.setOnClickListener {
            target?.let { target ->
                target.setExpanded(!target.isExpanded())
                expandableUI(target.isExpanded())
            }
        }
        childAdapter = JsonViewerAdapter()
        binding.childRecyclerView.adapter = childAdapter
        binding.childRecyclerView.setRecycledViewPool(recycledViewPool)
    }

    @SuppressLint("SetTextI18n")
    fun bind(jsonObject: JsonObject) {
        target = jsonObject
        expandableUI(jsonObject.isExpanded())
        binding.keyLabel.text = "\"${jsonObject.key}\""
        childAdapter.setElements(jsonObject.elements)

        setStyle()
    }

    private fun expandableUI(isExpanded: Boolean) {
        val rotation = if (isExpanded) 90f else 0f
        binding.arrowImage.rotation = rotation
        binding.expandableLayout.visibility = if (isExpanded) VISIBLE else GONE
    }

    private fun setStyle() {
        // COLOR
        binding.arrowImage.setColorFilter(BaseJsonViewerAdapter.ARROW_COLOR.getColor(binding.root))
        binding.keyDescriptionLabel.setTextColor(BaseJsonViewerAdapter.BRACKET_COLOR.getColor(binding.root))
        binding.keyLabel.setTextColor(BaseJsonViewerAdapter.KEY_COLOR.getColor(binding.root))
        binding.divider.setBackgroundColor(BaseJsonViewerAdapter.DIVIDER_COLOR.getColor(binding.root))
    }
}