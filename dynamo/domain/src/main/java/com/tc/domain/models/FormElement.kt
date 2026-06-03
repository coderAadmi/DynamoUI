package com.tc.domain.models


data class FormElement(
    val id : String,
    val title : String,
    val type : Type,
    val imgUrl : String? = null,
    val options : List<String>? = null
){
    enum class Type {
        TEXT, INPUT, IMG, CHECKBOX, RADIOS
    }
}
