package com.fatahrez.pin.ui.theme

import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.fatahrez.pin.R

val helveticaFonts = FontFamily(
    Font(R.font.helvetica_black_condensed, FontWeight.Black),
    Font(R.font.helvetica_ce_boldcondensed, FontWeight.Bold),
    Font(R.font.helvetica_ce_bold, FontWeight.ExtraBold),
    Font(R.font.helvetica_ce_bold_narrow, FontWeight.SemiBold),
    Font(R.font.helvetica_ce_medium, FontWeight.Medium),
    Font(R.font.helvetica_ce_medium, FontWeight.Normal),
    Font(R.font.helvetica_light_condensed, FontWeight.Light),
    Font(R.font.helvetica_light_condensed, FontWeight.ExtraLight),
    Font(R.font.helvetica_ce_condensed_oblique, FontWeight.Thin)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = helveticaFonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ) ,
    titleLarge = TextStyle(
        fontFamily = helveticaFonts,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = helveticaFonts,
        fontWeight = FontWeight.ExtraBold,
        lineHeight = 30.sp,
        letterSpacing = 0.sp
    )
    /* Other default text styles to override
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)