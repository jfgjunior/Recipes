package com.example.josegarcia.todaymeal.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Recipe : Parcelable {
    var id: Int
        private set
    val servings: Int
    val name: String
    val ingredients: List<Ingredient>
    val steps: List<Step>
    val image: String
    val score: Int

    constructor(
        id: Int, name: String, ingredients: List<Ingredient>, steps: List<Step>,
        servings: Int, image: String, score: Int
    ) {
        this.id = id
        this.name = name
        this.ingredients = ingredients
        this.steps = steps
        this.servings = servings
        this.image = image
        this.score = score
    }

    private constructor(`in`: Parcel) {
        id = `in`.readInt()
        name = `in`.readString() ?: ""
        ingredients = `in`.createTypedArrayList(Ingredient)
        steps = `in`.createTypedArrayList(Step.CREATOR)
        servings = `in`.readInt()
        image = `in`.readString() ?: ""
        score = `in`.readInt()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Recipe) return false
        return id == other.id &&
                servings == other.servings &&
                name == other.name &&
                ingredients == other.ingredients &&
                steps == other.steps &&
                image == other.image
    }

    override fun hashCode(): Int {
        return Objects.hash(id, name, ingredients, steps, servings, image)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeTypedList(ingredients)
        parcel.writeTypedList(steps)
        parcel.writeInt(servings)
        parcel.writeString(image)
        parcel.writeInt(score)
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel) = Recipe(parcel)

        override fun newArray(size: Int): Array<Recipe?> = arrayOfNulls(size)
    }
}

