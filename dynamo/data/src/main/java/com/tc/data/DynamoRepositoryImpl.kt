package com.tc.data

import com.tc.domain.Response
import com.tc.domain.models.Form
import com.tc.domain.models.FormElement
import com.tc.domain.repo.DynamoRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkApi{
    fun getAllForms() : List<Form> {
        return listOf(
            Form("1", "Form #1", listOf()),
            Form("2", "Form #2", listOf()),
            Form("3", "Form #3", listOf())
        )
    }
}

class DynamoRepositoryImpl @Inject constructor (private val api  :  NetworkApi) : DynamoRepository {
    override suspend fun getAllForms(): Flow<Response> {
        return flow {
            emit(Response.Loading)

            delay(3000)

            emit(
                Response.Success<List<Form>>(
                    api.getAllForms()
                )
            )
        }
    }

    override suspend fun getForm(id: String): Flow<Response> {
        return flow {
            emit(Response.Loading)

            delay(3000)

            emit(
                Response.Success<Form>(
                    Form(
                        "1", "Form #1",
                        listOf(
                            FormElement("1","First element", FormElement.Type.TEXT),
                            FormElement("2","second element", FormElement.Type.INPUT),
                            FormElement("3","3rd element", FormElement.Type.IMG)
                        )
                    )
                )

            )
        }
    }

    override suspend fun submitForm(form: Form) {

    }

    override suspend fun saveAsDraft(form: Form) {

    }
}