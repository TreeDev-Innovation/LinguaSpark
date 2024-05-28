package country.data

import country.domain.model.Country

class CountryRepository(private val languageDataSource: LanguageDataSource) {
    suspend fun getLanguagesList(): List<Country> {
        return languageDataSource.getListOfLanguages().map {
            Country(
                id = it.key,
                name = it.value.name,
                nativeName = it.value.nativeName
            )
        }
    }

}
