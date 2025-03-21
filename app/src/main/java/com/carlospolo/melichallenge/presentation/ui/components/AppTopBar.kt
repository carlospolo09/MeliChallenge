package com.carlospolo.melichallenge.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.carlospolo.melichallenge.R
import com.carlospolo.melichallenge.ui.theme.MercadoLibreBlue

/**
 * A composable function that displays a centered top app bar with an optional title and back button.
 *
 * @param title The title text displayed in the app bar. Defaults to `null` (no title).
 * @param onBack Callback invoked when the back button is clicked. If `null`, the back button is not displayed.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(title: String? = null, onBack: (() -> Unit)? = null) {
    CenterAlignedTopAppBar(
        title = { title?.let { Text(title) } },
        navigationIcon = {
            onBack?.let {
                IconButton(onClick = it) {
                    Icon(Icons.Default.ArrowBack, contentDescription = stringResource(id = R.string.top_bar_component_description_back_icon))
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MercadoLibreBlue
        )
    )
}
