package com.example.financetracker.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.financetracker.R


private val EpilogueFontFamily = FontFamily(
    Font(R.font.epilogue_light, FontWeight.Light),
    Font(R.font.epilogue_regular, FontWeight.Medium),
    Font(R.font.epilogue_bold, FontWeight.Bold)
)

private val RobotoFontFamily = FontFamily(
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_regular, FontWeight.Medium)
)
// Set of Material typography styles to start with
val Typography = Typography(
    h6 = TextStyle(
        fontFamily = EpilogueFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    body1 = TextStyle(
        fontFamily = EpilogueFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 15.sp
    ),
    body2 = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    )

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)


private val CarterOneFontFamily = FontFamily(
    Font(R.font.carter_one_regular, FontWeight.Medium),
)

