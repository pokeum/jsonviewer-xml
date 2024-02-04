package kr.pokeum.jsonviewer_xml.model

import android.os.Parcel
import android.os.Parcelable

abstract class JsonElement(
    val key: String
) : Parcelable {

    companion object {

        const val JSON_OBJECT = 1
        const val JSON_ARRAY = 2
        const val JSON_PRIMITIVE = 3
        const val JSON_NULL = 4

        @JvmField
        val CREATOR: Parcelable.Creator<JsonElement> = object : Parcelable.Creator<JsonElement> {

            override fun createFromParcel(inParcel: Parcel): JsonElement {
                return when(inParcel.readInt()) {
                    JSON_OBJECT -> JsonObject(inParcel)
                    JSON_ARRAY -> JsonArray(inParcel)
                    JSON_PRIMITIVE -> JsonPrimitive(inParcel)
                    JSON_NULL -> JsonNull(inParcel)
                    else -> throw IllegalArgumentException("Invalid json type")
                }
            }

            override fun newArray(size: Int): Array<JsonElement> { return newArray(size) }
        }
    }
}