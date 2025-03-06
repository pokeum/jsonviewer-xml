package co.pokeum.jsonviewer.xml.model

import android.os.Parcel
import java.lang.StringBuilder

class JsonArray(
    key: String,
    val elements: MutableList<JsonElement>
) : JsonElement(key), Expandable {

    override var expanded = false

    override fun expandAll() {
        expanded = true
        for (element in elements) {
            if (element is Expandable) {
                element.expandAll()
            }
        }
    }

    override fun collapseAll() {
        expanded = false
        for (element in elements) {
            if (element is Expandable) {
                element.collapseAll()
            }
        }
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