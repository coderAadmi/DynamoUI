package com.tc.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun CounterScreen(modifier: Modifier = Modifier){
    var count by remember {  mutableStateOf(0) }

    Column(
        Modifier
        .fillMaxSize()
        .testTag("MAIN_COL")
    ) {
        Text(count.toString() ,
            modifier = Modifier.semantics{
            contentDescription = "COUNT_TXT "
        }
        )

        Button({
            count ++
        }, modifier = Modifier.testTag("BTN_INC")) {
            Text("Increment")
        }
    }

}