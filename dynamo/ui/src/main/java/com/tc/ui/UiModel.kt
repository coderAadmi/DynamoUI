package com.tc.ui

import com.tc.domain.models.Form

data class UiModel(
    val form : Form
)

sealed class UiState{
    data object Nothing : UiState()
    data object Loading : UiState()
    data class Success( val uiModel: UiModel ) : UiState()
    data class Error(val msg : String) : UiState()
}


