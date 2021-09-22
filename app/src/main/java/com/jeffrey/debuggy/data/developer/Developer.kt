package com.jeffrey.debuggy.data.developer

import com.jeffrey.debuggy.data.social.Social

data class Developer(
    val username: String,
    val status: String,
    val avatarUrl: String,
    val socials: List<Social>
)