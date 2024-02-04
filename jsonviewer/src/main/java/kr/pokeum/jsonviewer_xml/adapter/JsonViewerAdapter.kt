package kr.pokeum.jsonviewer_xml.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.pokeum.jsonviewer_xml.databinding.*
import kr.pokeum.jsonviewer_xml.model.*
import kr.pokeum.jsonviewer_xml.model.JsonElement.Companion.JSON_ARRAY
import kr.pokeum.jsonviewer_xml.model.JsonElement.Companion.JSON_NULL
import kr.pokeum.jsonviewer_xml.model.JsonElement.Companion.JSON_OBJECT
import kr.pokeum.jsonviewer_xml.model.JsonElement.Companion.JSON_PRIMITIVE
import kr.pokeum.jsonviewer_xml.viewholder.JsonArrayViewHolder
import kr.pokeum.jsonviewer_xml.viewholder.JsonNullViewHolder
import kr.pokeum.jsonviewer_xml.viewholder.JsonObjectViewHolder
import kr.pokeum.jsonviewer_xml.viewholder.JsonPrimitiveViewHolder

@SuppressLint("NotifyDataSetChanged")
class JsonViewerAdapter(
    jsonElement: JsonElement? = null,
    recyclerViewPool: RecyclerView.RecycledViewPool? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val elements: MutableList<JsonElement>
    private val recyclerViewPool: RecyclerView.RecycledViewPool

    init {
        elements = when (jsonElement) {
            is JsonObject       -> { jsonElement.elements }
            is JsonArray        -> { jsonElement.elements }
            is JsonPrimitive    -> { mutableListOf(jsonElement) }
            is JsonNull         -> { mutableListOf(jsonElement) }
            else                -> { mutableListOf() }
        }
        this.recyclerViewPool = recyclerViewPool ?: RecyclerView.RecycledViewPool()
    }

    fun setElements(elements: List<JsonElement>) {
        this.elements.clear()
        this.elements.addAll(elements)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            JSON_OBJECT -> {
                val binding = ItemJsonObjectBinding.inflate(inflater, parent, false)
                return JsonObjectViewHolder(
                    containerView = binding.root,
                    recycledViewPool = recyclerViewPool
                )
            }
            JSON_ARRAY -> {
                val binding = ItemJsonArrayBinding.inflate(inflater, parent, false)
                return JsonArrayViewHolder(
                    containerView = binding.root,
                    recycledViewPool = recyclerViewPool
                )
            }
            JSON_PRIMITIVE -> {
                val binding = ItemJsonPrimitiveBinding.inflate(inflater, parent, false)
                return JsonPrimitiveViewHolder(containerView = binding.root)
            }
            else -> {   // JSON_NULL
                val binding = ItemJsonNullBinding.inflate(inflater, parent, false)
                return JsonNullViewHolder(containerView = binding.root)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val jsonElement = elements[position]
        when (holder) {
            is JsonObjectViewHolder     -> holder.bind(jsonElement as JsonObject)
            is JsonArrayViewHolder      -> holder.bind(jsonElement as JsonArray)
            is JsonPrimitiveViewHolder  -> holder.bind(jsonElement as JsonPrimitive)
            is JsonNullViewHolder       -> holder.bind(jsonElement as JsonNull)
        }
    }

    override fun getItemCount(): Int = elements.size

    override fun getItemViewType(position: Int): Int = when (elements[position]) {
        is JsonObject           -> JSON_OBJECT
        is JsonArray            -> JSON_ARRAY
        is JsonPrimitive        -> JSON_PRIMITIVE
        else                    -> JSON_NULL
    }
}