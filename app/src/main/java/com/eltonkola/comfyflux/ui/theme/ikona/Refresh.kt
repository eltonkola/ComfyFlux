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

public val Ikona.Refresh: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon() : ImageVector {
        return ImageVector.Builder(
                name = "refresh",
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
                    moveTo(20f, 33.083f)
                    quadToRelative(-5.458f, 0f, -9.271f, -3.812f)
                    quadTo(6.917f, 25.458f, 6.917f, 20f)
                    reflectiveQuadToRelative(3.812f, -9.271f)
                    quadToRelative(3.813f, -3.812f, 9.313f, -3.812f)
                    quadToRelative(3.25f, 0f, 5.896f, 1.354f)
                    quadToRelative(2.645f, 1.354f, 4.52f, 3.771f)
                    verticalLineTo(8.208f)
                    quadToRelative(0f, -0.541f, 0.375f, -0.916f)
                    reflectiveQuadToRelative(0.959f, -0.375f)
                    quadToRelative(0.541f, 0f, 0.937f, 0.375f)
                    reflectiveQuadToRelative(0.396f, 0.916f)
                    verticalLineToRelative(8.042f)
                    quadToRelative(0f, 0.583f, -0.375f, 0.938f)
                    quadToRelative(-0.375f, 0.354f, -0.958f, 0.354f)
                    horizontalLineToRelative(-8f)
                    quadToRelative(-0.584f, 0f, -0.959f, -0.375f)
                    reflectiveQuadToRelative(-0.375f, -0.917f)
                    quadToRelative(0f, -0.583f, 0.375f, -0.958f)
                    reflectiveQuadToRelative(0.959f, -0.375f)
                    horizontalLineToRelative(5.541f)
                    quadToRelative(-1.5f, -2.459f, -3.875f, -3.917f)
                    quadToRelative(-2.375f, -1.458f, -5.416f, -1.458f)
                    quadToRelative(-4.417f, 0f, -7.459f, 3.041f)
                    quadTo(9.542f, 15.625f, 9.542f, 20f)
                    reflectiveQuadToRelative(3.041f, 7.417f)
                    quadToRelative(3.042f, 3.041f, 7.459f, 3.041f)
                    quadToRelative(3.083f, 0f, 5.666f, -1.646f)
                    quadToRelative(2.584f, -1.645f, 3.875f, -4.437f)
                    quadToRelative(0.167f, -0.375f, 0.563f, -0.625f)
                    quadToRelative(0.396f, -0.25f, 0.771f, -0.25f)
                    quadToRelative(0.75f, 0f, 1.104f, 0.5f)
                    quadToRelative(0.354f, 0.5f, 0.104f, 1.125f)
                    quadToRelative(-1.542f, 3.625f, -4.813f, 5.792f)
                    quadToRelative(-3.27f, 2.166f, -7.312f, 2.166f)
                    close()
                }
            }.build()


}