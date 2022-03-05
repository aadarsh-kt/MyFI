package com.example.financetracker.ui.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financetracker.R

/*
Generic component used by income and expenditure screens.
 */

@Composable
fun <T> DetailsScreen(
    detailsName: String,
    detailsData: List<T>,
    colors: (T) -> Color,
    amounts: (T) -> Double,
    onBackPressed: () -> Unit,
    onAddPressed: () -> Unit,
    detailsCard: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xffFAF6F6),
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable(
                            onClick = onBackPressed
                        )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "back",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(10.dp)
                    )
                }
                Text(
                    text = detailsName,
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(20.dp)
                        .weight(1f)
                )
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xffFAF6F6),
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable(
                            onClick = onAddPressed
                        )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = "back",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(10.dp)
                    )
                }

            }
        }
    ) { innerPadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            //extract proportions and colors from the income or expenditure
            val amountsProportions = detailsData.extractProportions { amounts(it) }
            val circleColors = detailsData.map { colors(it) }

            AnimatedCircle(
                proportions = amountsProportions,
                colors = circleColors,
                modifier = Modifier
                    .padding(16.dp)
                    .height(300.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(40.dp))

            detailsCard()
        }
    }
}