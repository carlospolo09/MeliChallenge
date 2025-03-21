package com.carlospolo.melichallenge.utils

import com.carlospolo.melichallenge.utils.Constants.CONDITION_NEW_ENGLISH
import com.carlospolo.melichallenge.utils.Constants.CONDITION_NEW_SPANISH
import com.carlospolo.melichallenge.utils.Constants.CONDITION_UNKNOWN_SPANISH
import com.carlospolo.melichallenge.utils.Constants.CONDITION_USED_ENGLISH
import com.carlospolo.melichallenge.utils.Constants.CONDITION_USED_SPANISH
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ToArgentinePesosTest(
    private val amount: Double,
    private val expected: String
) {

    @Test
    fun `toArgentinePesos should format correctly`() {
        assertThat(amount.toArgentinePesos().replace("\u00A0", " ")).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> = listOf(
            arrayOf(1000.0, "$ 1.000,00"),
            arrayOf(999.99, "$ 999,99"),
            arrayOf(0.0, "$ 0,00")
        )
    }
}