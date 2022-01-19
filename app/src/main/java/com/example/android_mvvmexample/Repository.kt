package com.example.android_mvvmexample

import com.example.android_mvvmexample.network.ApiService

class Repository(private val apiService: ApiService) {
   suspend fun getCharacters(pages: String) = apiService.fechCharacters(pages)
}