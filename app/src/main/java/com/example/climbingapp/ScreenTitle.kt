package com.example.climbingapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.climbingapp.ui.theme.ClimbingAppTheme

@Composable
fun ScreenTitle(title: Int, modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                MaterialTheme.shapes.small
            )
            .border(
                1.dp,
                MaterialTheme.colorScheme.primary,
                MaterialTheme.shapes.small
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview
fun ScreenTitlePreview() {
    ClimbingAppTheme {
        ScreenTitle(title = R.string.app_name)
    }
}