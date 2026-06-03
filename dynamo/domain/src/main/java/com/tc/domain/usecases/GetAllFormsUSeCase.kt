package com.tc.domain.usecases

import com.tc.domain.repo.DynamoRepository
import javax.inject.Inject

class GetAllFormsUSeCase @Inject constructor(private val repository: DynamoRepository) {
    suspend operator fun invoke() = repository.getAllForms()
}