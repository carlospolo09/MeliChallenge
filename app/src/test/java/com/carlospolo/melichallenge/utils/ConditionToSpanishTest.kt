package com.carlospolo.melichallenge.utils

import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ConditionToSpanishTest(
    private val input: String,
    private val expected: String
) {

    @Test
    fun `conditionToSpanish should return correct translation`() {
        Assertions.assertThat(input.conditionToSpanish()).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<String>> = listOf(
            arrayOf(Constants.CONDITION_NEW_ENGLISH, Constants.CONDITION_NEW_SPANISH),
            arrayOf(Constants.CONDITION_USED_ENGLISH, Constants.CONDITION_USED_SPANISH),
            arrayOf("refurbished", Constants.CONDITION_UNKNOWN_SPANISH),
            arrayOf("", Constants.CONDITION_UNKNOWN_SPANISH)
        )
    }
}