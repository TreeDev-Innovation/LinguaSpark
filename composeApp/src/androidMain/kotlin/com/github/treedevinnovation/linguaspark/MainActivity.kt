package com.github.treedevinnovation.linguaspark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import di.startDI
import onboarding.nativelanguage.ChooseLanguagesScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startDI()
        setContent { ChooseLanguagesScreen() }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {


}