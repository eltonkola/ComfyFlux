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

public val Ikona.Seed: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon() : ImageVector {
    return ImageVector.Builder(
                name = "numbers",
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
                    moveTo(14.333f, 26.625f)
                    lineToRelative(-1.416f, 5.708f)
                    quadToRelative(-0.125f, 0.417f, -0.459f, 0.688f)
                    quadToRelative(-0.333f, 0.271f, -0.75f, 0.271f)
                    quadToRelative(-0.625f, 0f, -1.02f, -0.48f)
                    quadToRelative(-0.396f, -0.479f, -0.23f, -1.104f)
                    lineToRelative(1.25f, -5.083f)
                    horizontalLineTo(6.75f)
                    quadToRelative(-0.667f, 0f, -1.062f, -0.5f)
                    quadToRelative(-0.396f, -0.5f, -0.23f, -1.125f)
                    quadToRelative(0.084f, -0.458f, 0.438f, -0.729f)
                    quadTo(6.25f, 24f, 6.75f, 24f)
                    horizontalLineToRelative(5.625f)
                    lineToRelative(2f, -8f)
                    horizontalLineTo(9.417f)
                    quadToRelative(-0.667f, 0f, -1.063f, -0.5f)
                    quadToRelative(-0.396f, -0.5f, -0.229f, -1.125f)
                    quadToRelative(0.125f, -0.417f, 0.458f, -0.708f)
                    quadToRelative(0.334f, -0.292f, 0.834f, -0.292f)
                    horizontalLineToRelative(5.625f)
                    lineTo(16.5f, 7.667f)
                    quadToRelative(0.083f, -0.417f, 0.417f, -0.688f)
                    quadToRelative(0.333f, -0.271f, 0.791f, -0.271f)
                    quadToRelative(0.625f, 0f, 1f, 0.48f)
                    quadToRelative(0.375f, 0.479f, 0.209f, 1.104f)
                    lineToRelative(-1.25f, 5.083f)
                    horizontalLineToRelative(8f)
                    lineToRelative(1.416f, -5.708f)
                    quadToRelative(0.125f, -0.417f, 0.459f, -0.688f)
                    quadToRelative(0.333f, -0.271f, 0.75f, -0.271f)
                    quadToRelative(0.625f, 0f, 1.02f, 0.48f)
                    quadToRelative(0.396f, 0.479f, 0.23f, 1.104f)
                    lineToRelative(-1.25f, 5.083f)
                    horizontalLineToRelative(4.958f)
                    quadToRelative(0.667f, 0f, 1.062f, 0.5f)
                    quadToRelative(0.396f, 0.5f, 0.23f, 1.125f)
                    quadToRelative(-0.084f, 0.458f, -0.438f, 0.729f)
                    quadToRelative(-0.354f, 0.271f, -0.854f, 0.271f)
                    horizontalLineToRelative(-5.625f)
                    lineToRelative(-2f, 8f)
                    horizontalLineToRelative(4.958f)
                    quadToRelative(0.667f, 0f, 1.063f, 0.5f)
                    quadToRelative(0.396f, 0.5f, 0.229f, 1.125f)
                    quadToRelative(-0.083f, 0.417f, -0.437f, 0.708f)
                    quadToRelative(-0.355f, 0.292f, -0.855f, 0.292f)
                    horizontalLineToRelative(-5.625f)
                    lineToRelative(-1.416f, 5.708f)
                    quadToRelative(-0.125f, 0.417f, -0.459f, 0.688f)
                    quadToRelative(-0.333f, 0.271f, -0.791f, 0.271f)
                    quadToRelative(-0.625f, 0f, -1f, -0.48f)
                    quadToRelative(-0.375f, -0.479f, -0.209f, -1.104f)
                    lineToRelative(1.25f, -5.083f)
                    close()
                    moveTo(15f, 24f)
                    horizontalLineToRelative(8f)
                    lineToRelative(2f, -8f)
                    horizontalLineToRelative(-8f)
                    close()
                }
            }.build()
}