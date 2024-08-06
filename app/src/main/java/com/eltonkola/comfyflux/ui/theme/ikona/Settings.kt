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

public val Ikona.Settings: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon() : ImageVector {
        return ImageVector.Builder(
                name = "settings",
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
                    moveTo(22.792f, 36.375f)
                    horizontalLineToRelative(-5.584f)
                    quadToRelative(-0.5f, 0f, -0.875f, -0.313f)
                    quadToRelative(-0.375f, -0.312f, -0.416f, -0.77f)
                    lineToRelative(-0.625f, -4.084f)
                    quadToRelative(-0.375f, -0.125f, -1.167f, -0.583f)
                    quadToRelative(-0.792f, -0.458f, -1.708f, -1.042f)
                    lineToRelative(-3.75f, 1.667f)
                    quadToRelative(-0.5f, 0.208f, -0.979f, 0.042f)
                    quadToRelative(-0.48f, -0.167f, -0.73f, -0.625f)
                    lineTo(4.167f, 25.75f)
                    quadToRelative(-0.25f, -0.417f, -0.125f, -0.917f)
                    reflectiveQuadToRelative(0.5f, -0.791f)
                    lineTo(8f, 21.5f)
                    quadToRelative(-0.083f, -0.333f, -0.104f, -0.708f)
                    quadToRelative(-0.021f, -0.375f, -0.021f, -0.792f)
                    quadToRelative(0f, -0.333f, 0.021f, -0.75f)
                    reflectiveQuadTo(8f, 18.417f)
                    lineToRelative(-3.458f, -2.5f)
                    quadToRelative(-0.375f, -0.292f, -0.521f, -0.771f)
                    quadToRelative(-0.146f, -0.479f, 0.146f, -0.938f)
                    lineToRelative(2.791f, -4.916f)
                    quadToRelative(0.292f, -0.459f, 0.771f, -0.604f)
                    quadToRelative(0.479f, -0.146f, 0.938f, 0.062f)
                    lineToRelative(3.791f, 1.708f)
                    quadTo(13f, 10f, 13.771f, 9.542f)
                    quadToRelative(0.771f, -0.459f, 1.521f, -0.709f)
                    lineToRelative(0.625f, -4.166f)
                    quadToRelative(0.041f, -0.459f, 0.416f, -0.771f)
                    quadToRelative(0.375f, -0.313f, 0.875f, -0.313f)
                    horizontalLineToRelative(5.584f)
                    quadToRelative(0.5f, 0f, 0.875f, 0.313f)
                    quadToRelative(0.375f, 0.312f, 0.416f, 0.771f)
                    lineToRelative(0.625f, 4.125f)
                    quadToRelative(0.709f, 0.25f, 1.48f, 0.687f)
                    quadToRelative(0.77f, 0.438f, 1.354f, 0.979f)
                    lineToRelative(3.833f, -1.708f)
                    quadToRelative(0.458f, -0.208f, 0.917f, -0.042f)
                    quadToRelative(0.458f, 0.167f, 0.75f, 0.584f)
                    lineToRelative(2.791f, 4.916f)
                    quadToRelative(0.292f, 0.417f, 0.167f, 0.917f)
                    quadToRelative(-0.125f, 0.5f, -0.542f, 0.792f)
                    lineToRelative(-3.5f, 2.5f)
                    quadToRelative(0.042f, 0.375f, 0.063f, 0.771f)
                    quadToRelative(0.021f, 0.395f, 0.021f, 0.812f)
                    quadToRelative(0f, 0.417f, -0.021f, 0.812f)
                    quadToRelative(-0.021f, 0.396f, -0.063f, 0.73f)
                    lineToRelative(3.459f, 2.5f)
                    quadToRelative(0.416f, 0.291f, 0.541f, 0.791f)
                    quadToRelative(0.125f, 0.5f, -0.125f, 0.917f)
                    lineTo(33f, 30.708f)
                    quadToRelative(-0.25f, 0.459f, -0.729f, 0.604f)
                    quadToRelative(-0.479f, 0.146f, -0.938f, -0.062f)
                    lineToRelative(-3.791f, -1.708f)
                    quadToRelative(-0.542f, 0.458f, -1.271f, 0.896f)
                    quadToRelative(-0.729f, 0.437f, -1.563f, 0.77f)
                    lineToRelative(-0.625f, 4.084f)
                    quadToRelative(-0.041f, 0.458f, -0.416f, 0.77f)
                    quadToRelative(-0.375f, 0.313f, -0.875f, 0.313f)
                    close()
                    moveToRelative(-2.834f, -10.958f)
                    quadToRelative(2.25f, 0f, 3.834f, -1.584f)
                    quadTo(25.375f, 22.25f, 25.375f, 20f)
                    reflectiveQuadToRelative(-1.583f, -3.833f)
                    quadToRelative(-1.584f, -1.584f, -3.834f, -1.584f)
                    reflectiveQuadToRelative(-3.833f, 1.584f)
                    quadTo(14.542f, 17.75f, 14.542f, 20f)
                    reflectiveQuadToRelative(1.583f, 3.833f)
                    quadToRelative(1.583f, 1.584f, 3.833f, 1.584f)
                    close()
                    moveToRelative(0f, -2.625f)
                    quadToRelative(-1.166f, 0f, -1.979f, -0.813f)
                    quadToRelative(-0.812f, -0.812f, -0.812f, -1.979f)
                    reflectiveQuadToRelative(0.812f, -1.979f)
                    quadToRelative(0.813f, -0.813f, 1.979f, -0.813f)
                    quadToRelative(1.167f, 0f, 1.98f, 0.813f)
                    quadToRelative(0.812f, 0.812f, 0.812f, 1.979f)
                    reflectiveQuadToRelative(-0.812f, 1.979f)
                    quadToRelative(-0.813f, 0.813f, -1.98f, 0.813f)
                    close()
                    moveTo(20f, 19.958f)
                    close()
                    moveTo(18.25f, 33.75f)
                    horizontalLineToRelative(3.5f)
                    lineToRelative(0.583f, -4.583f)
                    quadToRelative(1.334f, -0.375f, 2.479f, -1.021f)
                    quadToRelative(1.146f, -0.646f, 2.105f, -1.646f)
                    lineToRelative(4.333f, 1.875f)
                    lineToRelative(1.625f, -2.917f)
                    lineToRelative(-3.833f, -2.791f)
                    quadToRelative(0.166f, -0.667f, 0.27f, -1.334f)
                    quadToRelative(0.105f, -0.666f, 0.105f, -1.333f)
                    quadToRelative(0f, -0.708f, -0.084f, -1.333f)
                    quadToRelative(-0.083f, -0.625f, -0.291f, -1.334f)
                    lineToRelative(3.833f, -2.833f)
                    lineToRelative(-1.583f, -2.917f)
                    lineToRelative(-4.375f, 1.875f)
                    quadTo(26f, 12.5f, 24.833f, 11.792f)
                    quadToRelative(-1.166f, -0.709f, -2.5f, -0.959f)
                    lineToRelative(-0.541f, -4.625f)
                    horizontalLineTo(18.25f)
                    lineToRelative(-0.583f, 4.625f)
                    quadToRelative(-1.417f, 0.334f, -2.521f, 0.959f)
                    quadToRelative(-1.104f, 0.625f, -2.104f, 1.666f)
                    lineTo(8.75f, 11.583f)
                    lineTo(7.083f, 14.5f)
                    lineToRelative(3.792f, 2.792f)
                    quadToRelative(-0.167f, 0.708f, -0.271f, 1.354f)
                    quadToRelative(-0.104f, 0.646f, -0.104f, 1.312f)
                    quadToRelative(0f, 0.709f, 0.104f, 1.354f)
                    quadToRelative(0.104f, 0.646f, 0.271f, 1.355f)
                    lineToRelative(-3.792f, 2.791f)
                    lineToRelative(1.667f, 2.917f)
                    lineToRelative(4.292f, -1.833f)
                    quadToRelative(1f, 1f, 2.146f, 1.646f)
                    quadToRelative(1.145f, 0.645f, 2.479f, 0.979f)
                    close()
                }
            }.build()


}