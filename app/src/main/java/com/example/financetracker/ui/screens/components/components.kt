package com.example.financetracker.ui.screens.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//Individual cards
@Composable
fun DetailsCard(
    detailsName: String,
    amount: Double,
    color: Color
) {

    Surface(
        shape = RoundedCornerShape(20.dp),
        color = Color(0xffFCF9FF),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Canvas(
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterVertically)
            ) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                drawCircle(
                    color = color,
                    center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                    radius = size.minDimension / 2
                )
            }
            Spacer(Modifier.width(20.dp))
            Text(
                text = detailsName,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = "Rs $amount",
                style = MaterialTheme.typography.body2
            )
        }
    }
}