package com.funapp.retroui

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform