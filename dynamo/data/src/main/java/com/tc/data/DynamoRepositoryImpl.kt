package com.tc.data

import android.util.Log
import com.tc.data.db.FormDao
import com.tc.data.network.DomainMapper
import com.tc.data.network.DynamoApi
import com.tc.domain.Response
import com.tc.domain.models.Form
import com.tc.domain.repo.DynamoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

inline suspend fun < reified T : DomainMapper > processResponse(
    response: retrofit2.Response<T>,
    flow: FlowCollector<Response>,
    cache : ( T ) -> Unit
) {
    when {
        response.isSuccessful -> {
            val body = response.body()
            body?.let {
                flow.emit(Response.Success(it.toDomain() ) )
                cache(it)
            }
            if (body == null) {
                flow.emit(Response.Failure("Null response from server"))
            }
        }

        else -> {
            flow.emit(Response.Failure(response.message() ) )
        }
    }
}

class DynamoRepositoryImpl @Inject constructor(
    private val api: DynamoApi,
    private val formDao: FormDao
) : DynamoRepository {
    override suspend fun getAllForms(): Flow<Response> {
        return flow {

            emit(Response.Loading)

            val formsResponse = api.getAllForms()

            processResponse(formsResponse, this){
                // save to db
                formDao.insertAll(it.forms.map { it.toEntity() } )
            }
        }
    }

    override suspend fun getForm(id: String): Flow<Response> {
        try {
            fetchFormFromServer(id).collect {

            }
        }
        catch (e : Exception){
            Log.d("NET_DBG", e.message.toString())
        }
        return formDao.getFormById(id).map {
            Response.Success(it.toDomain())
        }
    }

     suspend fun fetchFormFromServer(id: String): Flow<Response> {
         return flow{
            val response = api.getForm(id)

            Log.d("NET_DBG", response.toString())

            processResponse( response, this ){
                // save to db
                formDao.insert(it.toEntity() )
            }
        }
    }

    override suspend fun submitForm(form: Form) {

    }

    override suspend fun saveAsDraft(form: Form) {

    }
}