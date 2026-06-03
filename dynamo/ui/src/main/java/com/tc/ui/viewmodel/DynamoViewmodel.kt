package com.tc.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tc.domain.Response
import com.tc.domain.models.Form
import com.tc.domain.usecases.GetAllFormsUSeCase
import com.tc.domain.usecases.GetFormByIdUseCase
import com.tc.ui.UiIntent
import com.tc.ui.UiModel
import com.tc.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DynamoViewmodel @Inject constructor(
    private val getAllFormsUSeCase: GetAllFormsUSeCase,
    private val getFormByIdUseCase: GetFormByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Nothing)
    val uiState = _uiState.asStateFlow()

    fun process(intent: UiIntent) {
        when (intent) {
            is UiIntent.LoadForm -> {
                viewModelScope.launch {
                    getFormByIdUseCase(intent.id).collect { response ->
                        when(response){
                            is Response.Failure -> {
                                _uiState.update {
                                    UiState.Error(response.msg)
                                }
                            }
                            Response.Loading -> {
                                _uiState.update { UiState.Loading }
                            }
                            is Response.Success<*> -> {
                                val body = response.data as Form
                                _uiState.update {
                                    UiState.Success(
                                        UiModel(body)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            UiIntent.SubmitForm -> {

            }

            is UiIntent.UpdateFormData -> {

            }
        }
    }

}