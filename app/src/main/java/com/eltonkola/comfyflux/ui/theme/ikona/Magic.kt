package com.eltonkola.comfyflux.ui.theme.ikona

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.eltonkola.comfyflux.ui.theme.Ikona

private var _icon: ImageVector? = null

val Ikona.Magic: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon(): ImageVector {
    return ImageVector.Builder(
        name = "generating_tokens",
        defaultWidth = 40.0.dp,
        defaultHeight = 40.0.dp,
        viewportWidth = 40.0f,
        viewportHeight = 40.0f
    ).apply {
        path(
            fill = SolidColor(Color.Black),
            fillAlpha = 1f,
            stroke = null,
            strokeAlpha = 1f,
            strokeLineWidth = 1.0f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1f,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(15.042f, 33.25f)
            quadToRelative(-5.542f, 0f, -9.396f, -3.854f)
            quadTo(1.792f, 25.542f, 1.792f, 20f)
            quadToRelative(0f, -5.542f, 3.875f, -9.417f)
            reflectiveQuadToRelative(9.375f, -3.875f)
            quadToRelative(5.541f, 0f, 9.416f, 3.875f)
            reflectiveQuadTo(28.333f, 20f)
            quadToRelative(0f, 5.542f, -3.875f, 9.396f)
            quadToRelative(-3.875f, 3.854f, -9.416f, 3.854f)
            close()
            moveToRelative(0f, -2.625f)
            quadToRelative(4.416f, 0f, 7.541f, -3.104f)
            reflectiveQuadTo(25.708f, 20f)
            quadToRelative(0f, -4.417f, -3.125f, -7.521f)
            reflectiveQuadToRelative(-7.541f, -3.104f)
            quadToRelative(-4.417f, 0f, -7.521f, 3.104f)
            reflectiveQuadTo(4.417f, 20f)
            quadToRelative(0f, 4.417f, 3.104f, 7.521f)
            reflectiveQuadToRelative(7.521f, 3.104f)
            close()
            moveToRelative(0f, -5.167f)
            quadToRelative(0.583f, 0f, 0.958f, -0.375f)
            reflectiveQuadToRelative(0.375f, -0.916f)
            verticalLineToRelative(-7.042f)
            horizontalLineToRelative(2.417f)
            quadToRelative(0.375f, 0f, 0.646f, -0.25f)
            quadToRelative(0.27f, -0.25f, 0.27f, -0.625f)
            reflectiveQuadToRelative(-0.27f, -0.646f)
            quadToRelative(-0.271f, -0.271f, -0.646f, -0.271f)
            horizontalLineToRelative(-7.5f)
            quadToRelative(-0.375f, 0f, -0.625f, 0.271f)
            reflectiveQuadToRelative(-0.25f, 0.646f)
            quadToRelative(0f, 0.375f, 0.25f, 0.625f)
            reflectiveQuadToRelative(0.625f, 0.25f)
            horizontalLineToRelative(2.458f)
            verticalLineToRelative(7.042f)
            quadToRelative(0f, 0.541f, 0.375f, 0.916f)
            reflectiveQuadToRelative(0.917f, 0.375f)
            close()
            moveToRelative(16.75f, -13.333f)
            lineToRelative(-1.209f, -2.667f)
            lineToRelative(-2.666f, -1.166f)
            quadToRelative(-0.417f, -0.167f, -0.417f, -0.625f)
            quadToRelative(0f, -0.459f, 0.417f, -0.625f)
            lineToRelative(2.666f, -1.167f)
            lineToRelative(1.209f, -2.667f)
            quadToRelative(0.166f, -0.416f, 0.604f, -0.416f)
            quadToRelative(0.437f, 0f, 0.604f, 0.416f)
            lineToRelative(1.208f, 2.667f)
            lineToRelative(2.667f, 1.167f)
            quadToRelative(0.375f, 0.166f, 0.375f, 0.625f)
            quadToRelative(0f, 0.458f, -0.375f, 0.625f)
            lineToRelative(-2.667f, 1.166f)
            lineTo(33f, 12.125f)
            quadToRelative(-0.167f, 0.417f, -0.604f, 0.417f)
            quadToRelative(-0.438f, 0f, -0.604f, -0.417f)
            close()
            moveToRelative(0f, 24.667f)
            lineToRelative(-1.209f, -2.667f)
            lineToRelative(-2.666f, -1.167f)
            quadToRelative(-0.417f, -0.166f, -0.417f, -0.625f)
            quadToRelative(0f, -0.458f, 0.417f, -0.625f)
            lineToRelative(2.666f, -1.166f)
            lineToRelative(1.209f, -2.667f)
            quadToRelative(0.166f, -0.417f, 0.604f, -0.417f)
            quadToRelative(0.437f, 0f, 0.604f, 0.417f)
            lineToRelative(1.208f, 2.667f)
            lineToRelative(2.667f, 1.166f)
            quadToRelative(0.375f, 0.167f, 0.375f, 0.625f)
            quadToRelative(0f, 0.459f, -0.375f, 0.625f)
            lineToRelative(-2.667f, 1.167f)
            lineTo(33f, 36.792f)
            quadToRelative(-0.167f, 0.416f, -0.604f, 0.416f)
            quadToRelative(-0.438f, 0f, -0.604f, -0.416f)
            close()
            moveTo(15.042f, 20f)
            close()
        }
    }.build()


}