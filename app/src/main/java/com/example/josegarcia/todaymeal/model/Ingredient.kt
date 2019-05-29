package com.example.josegarcia.todaymeal.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

class Ingredient : Parcelable {
    val quantity: Float
    val measure: String
    @SerializedName("ingredient")
    val name: String

    val length: Int
        get() = quantity.toString().length + name.length

    constructor(quantity: Int, measure: String, name: String) {
        this.quantity = quantity.toFloat()
        this.measure = measure
        this.name = name
    }

    constructor(`in`: Parcel) {
        quantity = `in`.readFloat()
        measure = `in`.readString()
        name = `in`.readString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Ingredient) {
            return false
        }
        return quantity == other.quantity &&
                measure == other.measure &&
                name == other.name
    }

    override fun hashCode() = Objects.hash(quantity, measure, name)

    override fun describeContents() = 0

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeFloat(quantity)
        parcel.writeString(measure)
        parcel.writeString(name)
    }

    companion object CREATOR : Parcelable.Creator<Ingredient> {
        override fun createFromParcel(parcel: Parcel): Ingredient {
            return Ingredient(parcel)
        }

        override fun newArray(size: Int): Array<Ingredient?> {
            return arrayOfNulls(size)
        }
    }
}
