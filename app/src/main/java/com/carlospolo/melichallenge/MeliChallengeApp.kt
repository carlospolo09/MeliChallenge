package com.carlospolo.melichallenge

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Main application class for the MeliChallengeApp.
 * This class initializes the necessary dependencies for the application.
 *
 * - Annotated with `@HiltAndroidApp` to enable Hilt for dependency injection.
 * - Uses `Timber` for logging in debug mode.
 */
@HiltAndroidApp
class MeliChallengeApp : Application() {

    /**
     * Called when the application is starting.
     * Initializes Timber for logging.
     */
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}