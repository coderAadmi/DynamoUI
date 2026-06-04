package com.tc.data.network

import kotlinx.serialization.Serializable

@Serializable
data class FormResponseDTO(val forms : List<FormDTO>) : DomainMapper{
    override fun toDomain() = forms.map { it.toDomain() }
}
