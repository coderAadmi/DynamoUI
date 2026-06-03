package com.tc.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tc.domain.models.FormElement
import com.tc.ui.R
import com.tc.ui.UiIntent
import com.tc.ui.UiState
import com.tc.ui.viewmodel.DynamoViewmodel

fun x(){

}
@Composable
fun DynamoScreen(
    modifier: Modifier = Modifier,
    dynamoVm: DynamoViewmodel
) {

    val uiState by dynamoVm.uiState.collectAsStateWithLifecycle()


    LaunchedEffect(Unit ) {
        dynamoVm.process(UiIntent.LoadForm("1"))
    }

    Scaffold(modifier.fillMaxSize()) { innerPadding ->

        Column(Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            when (val state = uiState) {
                is UiState.Error -> {
                    Text(state.msg)
                }

                UiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.size(48.dp))
                }

                UiState.Nothing -> {
                    Text("Waittttttttt")

                }

                is UiState.Success -> {
                    LazyColumn(Modifier.fillMaxSize()) {

                        items(state.uiModel.form.formBody) { element ->
                            when (element.type) {
                                FormElement.Type.TEXT -> {
                                    Text(element.title)
                                }

                                FormElement.Type.INPUT -> {
                                    var inState by remember { mutableStateOf("") }
                                    TextField(value = inState, onValueChange = { inState = it })
                                }

                                FormElement.Type.IMG -> {
                                    Icon(
                                        modifier = Modifier.size(48.dp),
                                        imageVector = Icons.Default.PlayArrow,
                                        contentDescription = "Demo"
                                    )
                                }

                                FormElement.Type.CHECKBOX -> {

                                }

                                FormElement.Type.RADIOS -> {

                                }
                            }
                        }
                    }

                }
            }
        }


    }

}