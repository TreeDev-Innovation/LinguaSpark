package com.github.treedevinnovation.linguaspark

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import di.startDI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startDI()
        setContent { App() }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}