package kr.pokeum.jsonviewer_xml.viewholder

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kr.pokeum.jsonviewer_xml.adapter.BaseJsonViewerAdapter
import kr.pokeum.jsonviewer_xml.adapter.JsonViewerAdapter
import kr.pokeum.jsonviewer_xml.databinding.ItemJsonArrayBinding
import kr.pokeum.jsonviewer_xml.model.JsonArray

internal class JsonArrayViewHolder(
    containerView: View,
    recycledViewPool: RecyclerView.RecycledViewPool
) : RecyclerView.ViewHolder(containerView) {

    private val binding: ItemJsonArrayBinding = ItemJsonArrayBinding.bind(containerView)
    private val childAdapter: JsonViewerAdapter
    private var target: JsonArray? = null

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
    fun bind(jsonArray: JsonArray) {
        target = jsonArray
        expandableUI(jsonArray.isExpanded())
        binding.keyLabel.text = "\"${jsonArray.key}\""
        childAdapter.setElements(jsonArray.elements)

        setStyle()
    }

    private fun expandableUI(isExpanded: Boolean) {
        val rotation = if (isExpanded) 90f else 0f
        binding.arrowImage.rotation = rotation
        binding.expandableLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE
    }

    private fun setStyle() {
        // COLOR
        binding.arrowImage.setColorFilter(BaseJsonViewerAdapter.ARROW_COLOR.getColor(binding.root))
        binding.keyDescriptionLabel.setTextColor(BaseJsonViewerAdapter.BRACKET_COLOR.getColor(binding.root))
        binding.keyLabel.setTextColor(BaseJsonViewerAdapter.KEY_COLOR.getColor(binding.root))
        binding.divider.setBackgroundColor(BaseJsonViewerAdapter.DIVIDER_COLOR.getColor(binding.root))
    }
}