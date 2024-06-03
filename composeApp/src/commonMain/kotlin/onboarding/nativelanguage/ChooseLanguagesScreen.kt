package onboarding.nativelanguage

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import country.domain.model.Country
import koin
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ChooseLanguagesScreen(
    chooseLanguageViewModel: ChooseLanguageViewModel = viewModel { ChooseLanguageViewModel(koin.get()) },
) {
    val uiState by chooseLanguageViewModel.countries.collectAsState()
    val confirmLanguage by chooseLanguageViewModel.confirmLanguage.collectAsState()
    val animatedAlpha by animateDpAsState(
        if (confirmLanguage is ChooseLanguagesScreen.ConfirmLanguage) {
            10.dp
        } else {
            0.dp
        }
    )

    var showAlert by remember { mutableStateOf(false) }

    Text(text = "What is the native language do you speak?")

    Scaffold(topBar = {
        TopAppBar(title = { Text("What is the native language do you speak?") })
    }) {
        Box {
            if (showAlert) {
                AlertDialog(
                    onDismissRequest = {
                        showAlert = false
                    },
                    title = {
                        Text(text = "Dialog Title Will Show Here")
                    },
                    text = {
                        Text("Here is a description text of the dialog")
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                showAlert = false
                            }) {
                            Text("Confirm Button")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                showAlert = false
                            }) {
                            Text("Dismiss Button")
                        }
                    }
                )
            }
        }
    }
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp).blur(animatedAlpha),
        userScrollEnabled = confirmLanguage is ChooseLanguagesScreen.None
    ) {
        items(uiState) { country ->
            CountrySelector(country) {
                if (confirmLanguage is ChooseLanguagesScreen.None) {
                    chooseLanguageViewModel.selectNativeLanguage(it)
                }
            }
        }
    }

    Crossfade(confirmLanguage) {
        when (it) {
            is ChooseLanguagesScreen.ConfirmLanguage -> {
                ConfirmLanguageScreen(
                    country = it.country,
                    yes = {
                        showAlert = true
                    },
                    no = { chooseLanguageViewModel.discardSelection() }
                )
            }

            ChooseLanguagesScreen.None -> Unit
        }
    }

}

@Composable
@Preview
internal fun ConfirmLanguageScreen(country: Country, yes: () -> Unit, no: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colors.surface.copy(0.5f))
            .padding(horizontal = 32.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Do you speak ${country.nativeName}?",
            style = MaterialTheme.typography.h3
        )
        Row(Modifier.padding(vertical = 16.dp)) {
            OutlinedButton(
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colors.primary
                ),
                onClick = yes
            ) {
                Text(text = "Yes")
            }
            Spacer(Modifier.width(16.dp))
            OutlinedButton(
                onClick = no
            ) {
                Text(text = "No")
            }
        }
    }
}

@Composable
@Preview
internal fun CountrySelector(item: Country, onSelected: (Country) -> Unit) {
    Row(
        Modifier.background(
            color = MaterialTheme.colors.surface,
            shape = RoundedCornerShape(2.dp)
        )
            .clickable { onSelected(item) }
            .padding(8.dp)
    ) {
        Text(
            text = item.id.uppercase(),
            style = MaterialTheme.typography.h6
        )

        Box(
            Modifier
                .padding(horizontal = 8.dp)
                .background(color = MaterialTheme.colors.onBackground.copy(alpha = 0.1f))
                .width(1.dp)
                .height(24.dp)
        )

        Text(
            modifier = Modifier.weight(1f),
            text = item.name, style = MaterialTheme.typography.body1
        )
        Spacer(Modifier.width(8.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = item.nativeName, style = MaterialTheme.typography.body2
        )
    }
}
