package com.example.financetracker.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financetracker.R
import com.example.financetracker.ui.screens.components.AnimatedCircle

@Composable
fun HomeScreen(
    onItemClick: (String) -> Unit,
    totalSavings: String
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 16.dp, end = 16.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = "menu",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                )

                Text(
                    text = "MyFi",
                    fontFamily = FontFamily(
                        Font(R.font.carter_one_regular)
                    ),
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)

                )
            }
        }
    ) {

        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .verticalScroll(rememberScrollState()),
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp)),
                color = Color(0xffFCF9FF)
            ) {
                Column {
                    Text(
                        text = "Stats",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .offset(x = 10.dp, y = 8.dp)
                    )
                    AnimatedCircle(
                        proportions = listOf(0.1f, 0.9f),
                        colors = listOf(Color(0xff3DF444), Color(0xff718FF8)),
                        modifier = Modifier
                            .padding(16.dp)
                            .height(300.dp)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            CurrentSavingsCard(totalSavings)

            Spacer(modifier = Modifier.height(10.dp))

            Column {
                HomeCardItem(
                    "Income",
                    R.mipmap.ic_income_foreground,
                    onItemClick = onItemClick
                )
                Spacer(Modifier.height(10.dp))
                HomeCardItem(
                    "Expenditure",
                    R.mipmap.ic_expenditure_foreground,
                    onItemClick = onItemClick
                )
            }


        }
    }
}

@Composable
fun CurrentSavingsCard(
    totalSavings: String
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp)),
        color = Color(0xffFCF9FF)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)

        ) {
            Text(
                text = "Current Savings",
                style = FinanceBoldType
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = totalSavings,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5
            )
        }
    }
}

@Composable
fun HomeCardItem(
    itemName: String,
    imagePath: Int,
    onItemClick: (String) -> Unit
) {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .clickable(
                    onClick = { onItemClick(itemName) }
                ),
            color = Color(0xffFCF9FF)
        ) {
            Row {

                Image(
                    painter = painterResource(id = imagePath),
                    contentDescription = "homeScreenItemIcon"
                )
                Spacer(Modifier.width(20.dp))
                Text(
                    text = itemName,
                    style = FinanceRegularType,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = "next",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(40.dp)
                        .padding(10.dp)
                )
            }
        }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        {},
        totalSavings = "5000"
    )
}

@Preview
@Composable
fun HomeCardItemPreview() {
    HomeCardItem(
        itemName = "Expenditure",
        imagePath = R.mipmap.ic_expenditure_foreground,
        onItemClick = {})
}

val FinanceRegularType = TextStyle(
    fontFamily = FontFamily(Font(R.font.epilogue_regular)),
    fontSize = 20.sp
)

val FinanceBoldType = TextStyle(
    fontFamily = FontFamily(Font(R.font.epilogue_bold)),
    fontSize = 20.sp
)

