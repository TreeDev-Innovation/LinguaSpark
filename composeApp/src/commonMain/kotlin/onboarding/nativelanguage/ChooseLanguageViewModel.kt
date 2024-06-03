package onboarding.nativelanguage

import androidx.lifecycle.ViewModel
import country.data.CountryRepository
import country.domain.model.Country
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChooseLanguageViewModel(
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val _countries: MutableStateFlow<List<Country>> = MutableStateFlow(emptyList())
    val countries: StateFlow<List<Country>> = _countries.asStateFlow()

    private val _confirmLanguage: MutableStateFlow<ChooseLanguagesScreen> =
        MutableStateFlow(ChooseLanguagesScreen.None)
    val confirmLanguage: StateFlow<ChooseLanguagesScreen> = _confirmLanguage.asStateFlow()

    init {
        viewModelScope.launch {
            _countries.emit(countryRepository.getLanguagesList())
        }
    }

    fun selectNativeLanguage(country: Country) {
        viewModelScope.launch {
            _confirmLanguage.emit(ChooseLanguagesScreen.ConfirmLanguage(country))
        }
    }

    fun discardSelection() {
        viewModelScope.launch {
            _confirmLanguage.emit(ChooseLanguagesScreen.None)
        }
    }
}

sealed class ChooseLanguagesScreen {
    object None : ChooseLanguagesScreen()
    data class ConfirmLanguage(val country: Country) : ChooseLanguagesScreen()
}
