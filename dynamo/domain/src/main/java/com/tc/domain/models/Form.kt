package com.tc.domain.models


data class Form(
    val id : String,
    val title : String,
    val formBody : List<FormElement>
)
