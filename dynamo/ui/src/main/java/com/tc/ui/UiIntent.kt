package com.tc.ui

import com.tc.domain.models.FormElement

sealed interface UiIntent {
    data class LoadForm(val id : String) : UiIntent
    data object SubmitForm  : UiIntent
    data class UpdateFormData(val formElement: FormElement) : UiIntent
}