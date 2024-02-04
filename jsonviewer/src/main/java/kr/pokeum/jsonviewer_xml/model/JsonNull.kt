package kr.pokeum.jsonviewer_xml.model

import android.os.Parcel

class JsonNull(
    key: String
) : JsonElement(key) {

    constructor(inParcel: Parcel) : this(key = inParcel.readString()!!)

    override fun describeContents(): Int { return 0 }

    override fun writeToParcel(outParcel: Parcel, flags: Int) {
        outParcel.writeInt(JSON_NULL)
        outParcel.writeString(key)
    }

    override fun toString(): String { return NULL }

    companion object {
        private const val NULL = "null"
    }
}