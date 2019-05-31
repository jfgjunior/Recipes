package com.example.josegarcia.todaymeal.model

import android.os.Parcel
import android.os.Parcelable

class Step : Parcelable {
    val id: Int
    val shortDescription: String
    val description: String
    val videoURL: String
    private val thumbnailURL: String

    val number: Int
        get() = id + 1

    constructor(
        id: Int,
        shortDescription: String,
        longDescription: String,
        videoUrl: String,
        thumbnailURL: String
    ) {
        this.id = id
        this.shortDescription = shortDescription
        this.description = longDescription
        this.videoURL = videoUrl
        this.thumbnailURL = thumbnailURL
    }

    protected constructor(`in`: Parcel) {
        id = `in`.readInt()
        shortDescription = `in`.readString()
        description = `in`.readString()
        videoURL = `in`.readString()
        thumbnailURL = `in`.readString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Step) {
            return false
        }
        return id == other.id &&
                shortDescription == other.shortDescription &&
                description == other.description &&
                videoURL == other.videoURL &&
                thumbnailURL == other.thumbnailURL
    }

    override fun hashCode() = id

    override fun describeContents() = 0

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(id)
        parcel.writeString(shortDescription)
        parcel.writeString(description)
        parcel.writeString(videoURL)
        parcel.writeString(thumbnailURL)
    }

    companion object CREATOR : Parcelable.Creator<Step> {
        override fun createFromParcel(parcel: Parcel) = Step(parcel)

        override fun newArray(size: Int): Array<Step?> = arrayOfNulls(size)
    }
}
