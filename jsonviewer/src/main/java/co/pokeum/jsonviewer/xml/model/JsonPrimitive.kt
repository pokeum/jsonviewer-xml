package co.pokeum.jsonviewer.xml.model

import android.os.Parcel

class JsonPrimitive private constructor(
    key: String
) : JsonElement(key) {

    lateinit var value: Any
        private set

    constructor(key: String, value: Number) : this(key) { this.value = value }
    constructor(key: String, value: String) : this(key) { this.value = value }
    constructor(key: String, value: Boolean): this(key) { this.value = value }

    constructor(inParcel: Parcel) : this(key = inParcel.readString()!!) {
        when (inParcel.readInt()) {
            PRIMITIVE_NUMBER -> { this.value = inParcel.readValue(Number::class.java.classLoader)!! }
            PRIMITIVE_STRING -> { this.value = inParcel.readValue(String::class.java.classLoader)!! }
            PRIMITIVE_BOOLEAN -> { this.value = inParcel.readValue(Boolean::class.java.classLoader)!! }
        }
    }

    override fun describeContents(): Int { return 0 }

    override fun writeToParcel(outParcel: Parcel, flags: Int) {
        outParcel.writeInt(JSON_PRIMITIVE)
        outParcel.writeString(key)
        when (value) {
            is Number -> outParcel.writeInt(PRIMITIVE_NUMBER)
            is String -> outParcel.writeInt(PRIMITIVE_STRING)
            is Boolean -> outParcel.writeInt(PRIMITIVE_BOOLEAN)
        }
        outParcel.writeValue(value)
    }

    override fun toString(): String {
        return when (value) {
            is String -> "\"$value\""
            else -> "$value"
        }
    }

    companion object {

        private const val PRIMITIVE_NUMBER = 1
        private const val PRIMITIVE_STRING = 2
        private const val PRIMITIVE_BOOLEAN = 3
    }
}