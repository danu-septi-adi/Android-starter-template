package com.template.app.di

import com.template.app.data.repository.SampleRepository

/**
 * Simple manual DI container.
 * Upgrade to Hilt/Koin when the project grows beyond a few dependencies.
 */
object AppContainer {

    // Lazily initialized dependencies
    val sampleRepository: SampleRepository by lazy {
        SampleRepository()
    }

    // Add more repositories / use-cases here as the app grows
}
