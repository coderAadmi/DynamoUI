package com.tc.domain.usecases

import com.tc.domain.repo.DynamoRepository
import javax.inject.Inject

class GetFormByIdUseCase @Inject constructor(private val repository: DynamoRepository) {
    suspend operator fun invoke(id : String) = repository.getForm(id)
}