package com.tc.domain.repo

import com.tc.domain.Response
import com.tc.domain.models.Form
import kotlinx.coroutines.flow.Flow

interface DynamoRepository  {

    suspend fun getAllForms() : Flow<Response>

    suspend fun getForm(id : String) : Flow<Response>

    suspend fun submitForm(form: Form)

    suspend fun saveAsDraft(form: Form)
}