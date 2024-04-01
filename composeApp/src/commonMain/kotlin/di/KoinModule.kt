package di

import com.lindenlabs.shared.data.AppRepository
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

private val utilityModule = module {
    factory { getDispatcherProvider() }
//    single { DogifyDatabase(createDriver("dogify.db")) }
}

private val apiModule = module {
    factory { AppRepository() }
}


private val usecaseModule = module {
//    factory { GetBreedsUseCase() }
//    factory { FetchBreedsUseCase() }
//    factory { ToggleFavouriteStateUseCase() }
}

private val sharedModules = listOf(usecaseModule, apiModule, utilityModule)

fun initKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    modules(sharedModules)
    appDeclaration()
}

fun initKoin() = initKoin { }