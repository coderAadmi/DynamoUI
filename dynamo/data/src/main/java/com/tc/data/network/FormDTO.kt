package com.tc.data.network

import FormElementEntity
import FormEntity
import com.tc.domain.models.Form
import com.tc.domain.models.FormElement
import kotlinx.serialization.Serializable


interface DomainMapper{
    fun toDomain() : Any
}

interface EntityMapper{
    fun toEntity() : Any
}

@Serializable
data class FormDTO(
    val formId: String,
    val title: String,
    val elements: List<FormElementDTO>
) : DomainMapper, EntityMapper {
    override fun  toDomain()
          = Form(formId, title, elements.map { it.toDomain() } )

    override fun toEntity() = FormEntity(formId, title, elements.map { it.toEntity() })
}

@Serializable
data class FormElementDTO(
    val id: String,
    val title: String,
    val type: String,
    val imgUrl: String? = null,
    val options: List<String>? = null
) : DomainMapper , EntityMapper {
    override fun toDomain() = FormElement(
        id =  id,
        title  = title,
        type = when (type) {
            "text" -> FormElement.Type.TEXT
            "input" -> FormElement.Type.INPUT
            "image" -> FormElement.Type.IMG
            "radioGroup" -> FormElement.Type.RADIOS
            "checkBox" -> FormElement.Type.CHECKBOX
            else -> FormElement.Type.TEXT
        },
        imgUrl = imgUrl,
        options = options
    )

    override fun toEntity() = FormElementEntity(
        id =  id,
        title  = title,
        type = type ,
        imgUrl = imgUrl,
        options = options
    )

}




