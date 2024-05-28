import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import country.data.Country
import country.data.CountryRepository
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
    LazyColumn {
        items(uiState) { country -> CountrySelector(country) }
    }
}

@Composable
@Preview
internal fun CountrySelector(item: Country) {
    Row(
        Modifier.background(
            color = MaterialTheme.colors.surface,
            shape = RoundedCornerShape(8.dp)
        )
    ) {
        Text(text = item.name)
    }
}

internal class CountrySelectorViewModel(
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<List<Country>> = MutableStateFlow(emptyList())
    val uiState: StateFlow<List<Country>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.emit(countryRepository.getCountriesWithNames())
        }
    }

}
