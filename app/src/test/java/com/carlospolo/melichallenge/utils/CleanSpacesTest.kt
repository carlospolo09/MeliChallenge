package com.carlospolo.melichallenge.utils

import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class CleanSpacesTest(
    private val input: String,
    private val expected: String
) {

    @Test
    fun `cleanSpaces should normalize spaces correctly`() {
        Assertions.assertThat(input.cleanSpaces()).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<String>> = listOf(
            arrayOf("  hello world  ", "hello world"),
            arrayOf("multiple     spaces", "multiple spaces"),
            arrayOf("noSpaces", "noSpaces"),
            arrayOf("   ", "") // Caso borde: solo espacios
        )
    }
}