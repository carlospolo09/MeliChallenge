package com.carlospolo.melichallenge.presentation.ui.productsearch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carlospolo.melichallenge.R
import com.carlospolo.melichallenge.ui.theme.DarkGray
import com.carlospolo.melichallenge.ui.theme.MercadoLibreYellow
import com.carlospolo.melichallenge.utils.cleanSpaces

/**
 * Composable function that displays the product search screen.
 *
 * @param onSearch Callback triggered when a search is performed.
 */
@Composable
fun ProductSearchScreen(
    onSearch: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf(TextFieldValue()) }
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MercadoLibreYellow)
            .padding(16.dp)
    ) {
        // Search bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    isError = it.text.isBlank()
                                },
                modifier = Modifier.weight(1f),
                placeholder = {
                    Text(stringResource(id = R.string.search_screen_lbl_search_products))
                },
                isError = isError,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = if (isError) Color.Red else Color.Transparent,
                    unfocusedBorderColor = if (isError) Color.Red else Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    errorBorderColor = Color.Red
                )
            )
            IconButton(onClick = {
                if (searchQuery.text.isBlank()) {
                    isError = true
                } else {
                    isError = false
                    onSearch(searchQuery.text.cleanSpaces())
                }
            }) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search_screen_description_search_icon)
                )
            }
        }

        // Error message
        if (isError) {
            Text(
                text = stringResource(id = R.string.search_screen_lbl_error_message),
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Informational message
        Text(
            text = stringResource(id = R.string.search_screen_lbl_information_message),
            fontSize = 24.sp,
            color = DarkGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductSearchScreen() {
    ProductSearchScreen(onSearch = {})
}
