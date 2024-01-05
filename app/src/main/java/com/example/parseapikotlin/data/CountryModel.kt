package com.example.parseapikotlin.data


data class CountryModel(
    val name: NameInfo?,
    val flags: Flags?,
    val capital: List<String>?,
    val region: String?
)

data class NameInfo(
    val common: String?,
    val official: String?,
    val nativeName: Map<String, Map<String, String>>?
    // Add other properties as needed
)

data class Flags(
    val png: String?,
    val svg: String?
    // Add other properties as needed
)