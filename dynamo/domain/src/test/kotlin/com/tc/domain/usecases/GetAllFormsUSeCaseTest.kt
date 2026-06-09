package com.tc.domain.usecases

import com.tc.domain.Calculator
import com.tc.domain.Response
import com.tc.domain.models.Form
import com.tc.domain.repo.DynamoRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetAllFormsUSeCaseTest {

    lateinit var fakeRepository: DynamoRepository
    lateinit var getAllFormsUSeCase: GetAllFormsUSeCase

    val mockRepository = mockk<Calculator>()

    @Before
    fun setUp() {


//        fakeRepository = object : DynamoRepository{
//            private val fakeList = mutableListOf<Form>()
//            private val drafts = mutableListOf<Form>()
//            override suspend fun getAllForms(): Flow<Response> {
//                return flow {
//
//                }
//            }
//
//            override suspend fun getForm(id: String): Flow<Response> {
//                return flow {
//
//                }
//            }
//
//            override suspend fun submitForm(form: Form) {
//                fakeList.add(form)
//            }
//
//            override suspend fun saveAsDraft(form: Form) {
//                drafts.add(form)
//            }
//
//        }

        every { mockRepository.add(5,5) }.returns(10)

    }

    @After
    fun tearDown() {
        TODO("Not yet implemented")
    }

    @Test
    fun addTest() {
        assertEquals(mockRepository.add(5,5), 10)
    }

}