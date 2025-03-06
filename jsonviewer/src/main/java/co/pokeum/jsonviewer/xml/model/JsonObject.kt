package co.pokeum.jsonviewer.xml.model

import android.os.Parcel
import java.lang.StringBuilder

class JsonObject(
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
        outParcel.writeInt(JSON_OBJECT)
        outParcel.writeString(key)
        outParcel.writeTypedList(elements)
        outParcel.writeByte(if (expanded) 1 else 0)
    }

    override fun toString(): String {
        val jsonString = StringBuilder()
        elements.joinTo(jsonString, prefix = CURLY_BRACKET_BEGIN, postfix = CURLY_BRACKET_END) { element ->
            "\"${element.key}\": $element"
        }
        return jsonString.toString()
    }

    companion object {
        private const val CURLY_BRACKET_BEGIN = "{"
        private const val CURLY_BRACKET_END = "}"
    }
}