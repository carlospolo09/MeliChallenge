package com.carlospolo.melichallenge.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carlospolo.melichallenge.R
import com.carlospolo.melichallenge.ui.theme.MercadoLibreYellow

/**
 * A composable function that displays a full-screen error message with retry and back options.
 *
 * @param message The error message to be displayed. Defaults to a localized string resource.
 * @param onRetry Callback invoked when the retry button is clicked.
 * @param onBack Callback invoked when the back button is clicked.
 */
@Composable
fun FullScreenError(
    message: String = stringResource(id = R.string.error_screen_lbl_title),
    onRetry: () -> Unit,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = message,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onRetry,
                    colors = ButtonDefaults.buttonColors(containerColor = MercadoLibreYellow)
                ) {
                    Text(
                        text = stringResource(id = R.string.error_screen_btn_retry),
                        color = Color.Black
                    )
                }
                OutlinedButton(
                    onClick = onBack,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = stringResource(id = R.string.error_screen_btn_back))
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewFullScreenError() {
    FullScreenError(
        onRetry = {},
        onBack = {}
    )
}