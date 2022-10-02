package com.erbeandroid.petfinder.core.common.dispatcher

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val petFinderDispatcher: PetFinderDispatcher)

enum class PetFinderDispatcher {
    IO,
    Default
}