package com.carlospolo.melichallenge.utils

import com.carlospolo.melichallenge.utils.Constants.CONDITION_NEW_ENGLISH
import com.carlospolo.melichallenge.utils.Constants.CONDITION_NEW_SPANISH
import com.carlospolo.melichallenge.utils.Constants.CONDITION_UNKNOWN_SPANISH
import com.carlospolo.melichallenge.utils.Constants.CONDITION_USED_ENGLISH
import com.carlospolo.melichallenge.utils.Constants.CONDITION_USED_SPANISH
import com.carlospolo.melichallenge.utils.Constants.COUNTRY_AR
import com.carlospolo.melichallenge.utils.Constants.LANGUAGE_ES
import com.carlospolo.melichallenge.utils.Constants.SPACE
import com.carlospolo.melichallenge.utils.Constants.SPACE_PATTERN
import java.text.NumberFormat
import java.util.Locale

/**
 * Extension functions for formatting currency values.
 */

/**
 * Converts a [Double] value to an Argentine Pesos currency format.
 *
 * @return A formatted currency string in Argentine Pesos.
 */
fun Double.toArgentinePesos(): String {
    val format = NumberFormat.getCurrencyInstance(Locale(LANGUAGE_ES, COUNTRY_AR))
    return format.format(this)
}

/**
 * Extension functions for manipulating strings.
 */

/**
 * Removes leading and trailing spaces and replaces multiple spaces with a single space.
 *
 * @return A cleaned-up string with normalized spaces.
 */
fun String.cleanSpaces(): String {
    return this.trim().replace(Regex(SPACE_PATTERN), SPACE)
}

/**
 * Translates product condition descriptions from English to Spanish.
 *
 * @return The corresponding condition in Spanish.
 */
fun String.conditionToSpanish(): String {
    return when (this) {
        CONDITION_NEW_ENGLISH -> CONDITION_NEW_SPANISH
        CONDITION_USED_ENGLISH -> CONDITION_USED_SPANISH
        else -> CONDITION_UNKNOWN_SPANISH
    }
}