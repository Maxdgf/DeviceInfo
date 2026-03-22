package com.example.deviceinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.deviceinfo.utils.DeviceInfo
import com.example.deviceinfo.ui.theme.DeviceInfoTheme
import androidx.compose.ui.unit.dp
import com.example.deviceinfo.utils.ClipboardManager

private const val appName = "Device Info\uD83D\uDCF1"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeviceInfoTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun AppNameTitle(
    modifier: Modifier = Modifier,
    name: String
) {
    Text(
        text = name,
        modifier = modifier,
        fontStyle = FontStyle.Italic,
        color = Color.Green,
        textAlign = TextAlign.Center
    )
}

@Composable
fun SystemData(
    modifier: Modifier = Modifier,
    systemData: String,
    memoryData: String,
    onCopyData: () -> Unit
) {
    Box(modifier = modifier) {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            // system data text
            Text(
                text = systemData,
                fontWeight = FontWeight.Bold,
                color = Color.Green,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            // memory data text
            Text(
                text = memoryData,
                fontWeight = FontWeight.Bold,
                color = Color.Green,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            // copy data button
            Button(
                onClick = { onCopyData() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "copy data")
            }
        }
    }
}

@Composable
fun MainContent() {
    val context = LocalContext.current

    val deviceInfo = remember { DeviceInfo(context) }
    val clipboardManager = remember { ClipboardManager(context) }

    Box(modifier = Modifier.fillMaxSize()) {
        AppNameTitle(
            name = appName,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        val systemDataText = "System data:\n${deviceInfo.getPrimarySystemData()}"
        val memoryDataText = "Memory data:\n${deviceInfo.getMemoryData()}"

        SystemData(
            modifier = Modifier.align(Alignment.Center),
            systemData = systemDataText,
            memoryData = memoryDataText,
            onCopyData = {
                clipboardManager.setTextToClipboard(
                    "My device data:\n\n" +
                    systemDataText +
                    memoryDataText
                )
            }
        )
    }
}