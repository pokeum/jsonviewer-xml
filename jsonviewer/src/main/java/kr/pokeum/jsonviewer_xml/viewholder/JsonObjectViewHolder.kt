package kr.pokeum.jsonviewer_xml.viewholder

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.RecyclerView
import kr.pokeum.jsonviewer_xml.adapter.JsonViewerAdapter
import kr.pokeum.jsonviewer_xml.databinding.ItemJsonObjectBinding
import kr.pokeum.jsonviewer_xml.model.JsonObject

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

    fun bind(jsonObject: JsonObject) {
        target = jsonObject
        expandableUI(jsonObject.isExpanded())
        binding.keyLabel.text = "\"${jsonObject.key}\""
        childAdapter.setElements(jsonObject.elements)
    }

    private fun expandableUI(isExpanded: Boolean) {
        val rotation = if (isExpanded) 90f else 0f
        binding.arrowImage.rotation = rotation
        binding.expandableLayout.visibility = if (isExpanded) VISIBLE else GONE
    }
}