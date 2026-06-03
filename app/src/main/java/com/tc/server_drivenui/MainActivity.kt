package com.tc.server_drivenui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tc.server_drivenui.ui.theme.ServerdrivenUiTheme
import com.tc.ui.viewmodel.DynamoViewmodel
import com.tc.ui.views.DynamoScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ServerdrivenUiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val dynamoViewmodel by viewModels<DynamoViewmodel>()
                    DynamoScreen(
                        modifier = Modifier.padding(innerPadding),
                        dynamoViewmodel
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ServerdrivenUiTheme {
        Greeting("Android")
    }
}