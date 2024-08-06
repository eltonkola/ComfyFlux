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

public val Ikona.Image: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon() : ImageVector {
    return ImageVector.Builder(
                name = "network_intelligence_history",
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
                        moveTo(8.333f, 36.583f)
                        quadToRelative(-2.041f, 0f, -3.479f, -1.437f)
                        quadToRelative(-1.437f, -1.438f, -1.437f, -3.479f)
                        verticalLineTo(8.333f)
                        quadToRelative(0f, -2.041f, 1.437f, -3.5f)
                        quadToRelative(1.438f, -1.458f, 3.479f, -1.458f)
                        horizontalLineToRelative(23.334f)
                        quadToRelative(2.041f, 0f, 3.5f, 1.458f)
                        quadToRelative(1.458f, 1.459f, 1.458f, 3.5f)
                        verticalLineToRelative(23.334f)
                        quadToRelative(0f, 2.041f, -1.458f, 3.479f)
                        quadToRelative(-1.459f, 1.437f, -3.5f, 1.437f)
                        close()
                        moveToRelative(0f, -2.625f)
                        horizontalLineToRelative(23.334f)
                        quadToRelative(0.958f, 0f, 1.625f, -0.666f)
                        quadToRelative(0.666f, -0.667f, 0.666f, -1.625f)
                        verticalLineTo(8.333f)
                        quadToRelative(0f, -0.958f, -0.666f, -1.625f)
                        quadToRelative(-0.667f, -0.666f, -1.625f, -0.666f)
                        horizontalLineTo(8.333f)
                        quadToRelative(-0.958f, 0f, -1.625f, 0.666f)
                        quadToRelative(-0.666f, 0.667f, -0.666f, 1.625f)
                        verticalLineToRelative(23.334f)
                        quadToRelative(0f, 0.958f, 0.666f, 1.625f)
                        quadToRelative(0.667f, 0.666f, 1.625f, 0.666f)
                        close()
                        moveToRelative(2.5f, -4.333f)
                        lineToRelative(5.834f, -5.792f)
                        lineToRelative(3.041f, 2.959f)
                        lineToRelative(3.625f, -4.584f)
                        lineToRelative(5.959f, 7.417f)
                        close()
                        moveToRelative(2.5f, -13.333f)
                        quadToRelative(-1.25f, 0f, -2.104f, -0.875f)
                        quadToRelative(-0.854f, -0.875f, -0.854f, -2.084f)
                        quadToRelative(0f, -1.25f, 0.875f, -2.125f)
                        reflectiveQuadToRelative(2.083f, -0.875f)
                        quadToRelative(1.25f, 0f, 2.125f, 0.875f)
                        reflectiveQuadToRelative(0.875f, 2.125f)
                        quadToRelative(0f, 1.25f, -0.875f, 2.105f)
                        quadToRelative(-0.875f, 0.854f, -2.125f, 0.854f)
                        close()
                    }

            }.build()

}