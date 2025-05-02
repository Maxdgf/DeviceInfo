package com.example.deviceinfo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.deviceinfo.Modules.DeviceInfo
import com.example.deviceinfo.ui.theme.DeviceInfoTheme
import androidx.compose.ui.unit.dp

private const val appName = "Device Info\uD83D\uDCF1"

private val deviceInfo: DeviceInfo = DeviceInfo()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeviceInfoTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    GreetingPreview()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = name,
        modifier = Modifier.fillMaxWidth(),
        fontStyle = FontStyle.Italic,
        color = Color.Green,
        textAlign = TextAlign.Center
    )
}

@Composable
fun SystemData(systemData: String, memoryData: String) {
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column {
            Text(
                text = systemData,
                fontWeight = FontWeight.Bold,
                color = Color.Green,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = memoryData,
                fontWeight = FontWeight.Bold,
                color = Color.Green,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@SuppressLint("VisibleForTests")
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val context = LocalContext.current

    DeviceInfoTheme {
        Column {
            Greeting(appName)

            Spacer(modifier = Modifier.height(20.dp))

            SystemData("System data:\n${deviceInfo.getPrimarySystemData(context)}",
                "Memory data:\n${deviceInfo.getMemoryData(context)}"
            )
        }
    }
}