package com.github.novotnyr.presentr

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(val login: String) {
    override fun toString() = login
}
