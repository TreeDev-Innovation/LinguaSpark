package di

import country.data.CountryRepository
import country.data.LanguageDataSource
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun startDI() {
    startKoin {
        modules(appModule)
    }
}

val appModule: Module = module {
    single { LanguageDataSource() }
    single { CountryRepository(get()) }
}