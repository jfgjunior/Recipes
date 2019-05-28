package com.example.josegarcia.todaymeal.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Recipe : Parcelable {
    var id: Int
        private set
    private var servings: Int = 0
    val name: String
    val ingredients: List<Ingredient>
    val steps: List<Step>
    val image: String

    constructor(
        id: Int, name: String, ingredients: List<Ingredient>, steps: List<Step>,
        servings: Int, image: String
    ) {
        this.id = id
        this.name = name
        this.ingredients = ingredients
        this.steps = steps
        this.servings = servings
        this.image = image
    }

    protected constructor(`in`: Parcel) {
        id = `in`.readInt()
        name = `in`.readString()
        ingredients = `in`.createTypedArrayList(Ingredient)
        steps = `in`.createTypedArrayList(Step.CREATOR)
        servings = `in`.readInt()
        image = `in`.readString()
    }

    fun getId(): Float {
        return id.toFloat()
    }

    fun getServings(): Float {
        return servings.toFloat()
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun setServings(servings: Int) {
        this.servings = servings
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj !is Recipe) return false
        val recipe = obj as Recipe?
        return getId() == recipe!!.getId() &&
                getServings() == recipe.getServings() &&
                name == recipe.name &&
                ingredients == recipe.ingredients &&
                steps == recipe.steps &&
                image == recipe.image
    }

    override fun hashCode(): Int {
        return Objects.hash(getId(), name, ingredients, steps, getServings(), image)
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
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel) = Recipe(parcel)

        override fun newArray(size: Int): Array<Recipe?> = arrayOfNulls(size)
    }
}

