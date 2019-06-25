package com.example.josegarcia.todaymeal.repository

import androidx.lifecycle.LiveData

interface Repository<T> {
    fun fetchData()
    val data: LiveData<List<T>>
    val isDownloading: LiveData<Boolean>
    val hasConnection: LiveData<Boolean>
}