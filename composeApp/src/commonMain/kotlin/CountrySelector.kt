import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import country.data.CountryRepository
import country.domain.model.Country
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.mp.KoinPlatform.getKoin

val koin = getKoin()

@Composable
@Preview
internal fun CountrySelector(
    viewModel: CountrySelectorViewModel = viewModel { CountrySelectorViewModel(koin.get()) },
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Choose the language you want to learn",
                style = MaterialTheme.typography.h5
            )
        }
        LazyColumn(Modifier.padding(horizontal = 16.dp)) {
            items(uiState) { country -> CountrySelector(country) }
        }
    }
}

@Composable
@Preview
internal fun CountrySelector(item: Country) {
    Row(
        Modifier.background(
            color = MaterialTheme.colors.surface,
            shape = RoundedCornerShape(2.dp)
        )
            .clickable { }
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

internal class CountrySelectorViewModel(
    private val countryRepository: CountryRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<List<Country>> = MutableStateFlow(emptyList())
    val uiState: StateFlow<List<Country>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.emit(countryRepository.getLanguagesList())
        }
    }

}
