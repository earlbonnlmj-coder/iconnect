package com.example.iconnect
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.iconnect.ui.theme.IConnectTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IConnectTheme {
                InternetScreen()
            }
        }
    }
}

@Composable
fun InternetScreen(
    viewModel: PostViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Internet Demo",
        style = MaterialTheme.typography.headlineMedium
        )
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        when {
            uiState.isLoading -> {
                CircularProgressIndicator()
            }
            uiState.error != null -> {
                Text(
                    text = uiState.error ?: "Unknown error"
                )
            }
            uiState.post != null -> {
                Text(
                    text = uiState.post!!.title
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )
                Text(
                    text = uiState.post!!.body
                )
            }
        }
    }
}