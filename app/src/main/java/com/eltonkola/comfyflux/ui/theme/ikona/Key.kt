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

val Ikona.Key: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon(): ImageVector {
    return ImageVector.Builder(
        name = "key",
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
            moveTo(11.75f, 22.875f)
            quadToRelative(-1.167f, 0f, -2.021f, -0.854f)
            quadToRelative(-0.854f, -0.854f, -0.854f, -2.021f)
            quadToRelative(0f, -1.167f, 0.854f, -2.021f)
            quadToRelative(0.854f, -0.854f, 2.021f, -0.854f)
            quadToRelative(1.167f, 0f, 2.021f, 0.854f)
            quadToRelative(0.854f, 0.854f, 0.854f, 2.021f)
            quadToRelative(0f, 1.167f, -0.854f, 2.021f)
            quadToRelative(-0.854f, 0.854f, -2.021f, 0.854f)
            close()
            moveToRelative(0f, 6.917f)
            quadToRelative(-4.083f, 0f, -6.938f, -2.854f)
            quadTo(1.958f, 24.083f, 1.958f, 20f)
            reflectiveQuadToRelative(2.854f, -6.937f)
            quadToRelative(2.855f, -2.855f, 6.938f, -2.855f)
            quadToRelative(2.917f, 0f, 5.125f, 1.375f)
            reflectiveQuadToRelative(3.5f, 4.042f)
            horizontalLineToRelative(14.292f)
            quadToRelative(0.208f, 0f, 0.458f, 0.104f)
            reflectiveQuadToRelative(0.417f, 0.313f)
            lineToRelative(3.333f, 3.291f)
            quadToRelative(0.208f, 0.209f, 0.292f, 0.438f)
            quadToRelative(0.083f, 0.229f, 0.083f, 0.521f)
            quadToRelative(0f, 0.25f, -0.104f, 0.479f)
            quadToRelative(-0.104f, 0.229f, -0.313f, 0.437f)
            lineToRelative(-5.166f, 4.875f)
            quadToRelative(-0.167f, 0.167f, -0.375f, 0.25f)
            quadToRelative(-0.209f, 0.084f, -0.459f, 0.084f)
            quadToRelative(-0.208f, 0.041f, -0.416f, -0.021f)
            quadToRelative(-0.209f, -0.063f, -0.417f, -0.188f)
            lineToRelative(-2.708f, -2f)
            lineToRelative(-2.75f, 2.042f)
            quadToRelative(-0.209f, 0.167f, -0.396f, 0.208f)
            quadToRelative(-0.188f, 0.042f, -0.396f, 0.042f)
            quadToRelative(-0.208f, 0f, -0.417f, -0.062f)
            quadToRelative(-0.208f, -0.063f, -0.375f, -0.188f)
            lineTo(22.5f, 24.375f)
            horizontalLineToRelative(-2.125f)
            quadToRelative(-1.125f, 2.417f, -3.333f, 3.917f)
            quadToRelative(-2.209f, 1.5f, -5.292f, 1.5f)
            close()
            moveToRelative(0f, -2.625f)
            quadToRelative(2.375f, 0f, 4.312f, -1.521f)
            quadToRelative(1.938f, -1.521f, 2.521f, -3.938f)
            horizontalLineToRelative(4.834f)
            lineToRelative(2.333f, 1.834f)
            quadToRelative(-0.042f, 0f, 0f, 0f)
            horizontalLineToRelative(0.021f)
            horizontalLineToRelative(-0.021f)
            lineToRelative(3.583f, -2.584f)
            lineToRelative(3.292f, 2.459f)
            horizontalLineToRelative(-0.021f)
            horizontalLineToRelative(0.021f)
            lineToRelative(3.417f, -3.209f)
            quadToRelative(-0.042f, 0f, -0.021f, 0.021f)
            reflectiveQuadToRelative(0.021f, -0.021f)
            lineToRelative(-1.959f, -1.916f)
            verticalLineToRelative(-0.021f)
            verticalLineToRelative(0.021f)
            horizontalLineToRelative(-15.5f)
            quadToRelative(-0.541f, -2.292f, -2.458f, -3.875f)
            quadToRelative(-1.917f, -1.584f, -4.375f, -1.584f)
            quadToRelative(-3f, 0f, -5.083f, 2.084f)
            quadTo(4.583f, 17f, 4.583f, 20f)
            reflectiveQuadToRelative(2.084f, 5.083f)
            quadToRelative(2.083f, 2.084f, 5.083f, 2.084f)
            close()
        }
    }.build()


}