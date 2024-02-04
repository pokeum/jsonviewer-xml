package kr.pokeum.jsonviewer_xml.model

import android.os.Parcel
import java.lang.StringBuilder

class JsonArray(
    key: String,
    val elements: MutableList<JsonElement>
) : JsonElement(key) {

    private var expanded = false

    fun isExpanded(): Boolean {
        return expanded
    }

    internal fun setExpanded(value: Boolean) {
        expanded = value
    }

    constructor(inParcel: Parcel) : this(
        key = inParcel.readString()!!,
        elements = inParcel.createTypedArrayList(JsonElement.CREATOR)
            ?.mapNotNull { it }
            ?.toMutableList()
            ?: mutableListOf()
    ) {
        this.expanded = inParcel.readByte() != 0.toByte()
    }

    override fun describeContents(): Int { return 0 }

    override fun writeToParcel(outParcel: Parcel, flags: Int) {
        outParcel.writeInt(JSON_ARRAY)
        outParcel.writeString(key)
        outParcel.writeTypedList(elements)
        outParcel.writeByte(if (expanded) 1 else 0)
    }

    override fun toString(): String {
        val jsonString = StringBuilder()
        elements.joinTo(jsonString, prefix = SQUARE_BRACKET_BEGIN, postfix = SQUARE_BRACKET_END)
        return jsonString.toString()
    }

    companion object {
        private const val SQUARE_BRACKET_BEGIN = "["
        private const val SQUARE_BRACKET_END = "]"
    }
}