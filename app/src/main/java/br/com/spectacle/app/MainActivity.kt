package br.com.spectacle.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import br.com.spectacle.app.ui.theme.SpectacleTheme
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import logcat.logcat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpectacleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            val auth = FirebaseAuth.getInstance()
            val result = auth.signInWithEmailAndPassword(
                "lucas-gabriel-2000@hotmail.com",
                "1DB4A0041876241916FF8B935A46B680DE655E06456C77C1D2970688EA2838B9"
            ).await()

            val token = result.user?.getIdToken(true)?.await()?.token
            logcat { "TokenId: $token" }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SpectacleTheme {
        Greeting("Android")
    }
}