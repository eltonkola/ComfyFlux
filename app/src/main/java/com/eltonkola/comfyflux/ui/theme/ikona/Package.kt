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

public val Ikona.Package: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon() : ImageVector {
    return ImageVector.Builder(
                name = "package",
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
                    moveTo(16.042f, 16.958f)
                    lineToRelative(3.083f, -1.5f)
                    quadToRelative(0.417f, -0.208f, 0.875f, -0.208f)
                    reflectiveQuadToRelative(0.875f, 0.208f)
                    lineToRelative(3.083f, 1.5f)
                    verticalLineTo(7.875f)
                    horizontalLineToRelative(-7.916f)
                    close()
                    moveTo(13.333f, 28.25f)
                    quadToRelative(-0.666f, 0f, -1.125f, -0.458f)
                    quadToRelative(-0.458f, -0.459f, -0.458f, -1.125f)
                    quadToRelative(0f, -0.667f, 0.458f, -1.146f)
                    quadToRelative(0.459f, -0.479f, 1.125f, -0.479f)
                    horizontalLineToRelative(5f)
                    quadToRelative(0.667f, 0f, 1.146f, 0.479f)
                    quadToRelative(0.479f, 0.479f, 0.479f, 1.146f)
                    quadToRelative(0f, 0.666f, -0.479f, 1.125f)
                    quadToRelative(-0.479f, 0.458f, -1.146f, 0.458f)
                    close()
                    moveToRelative(-5.458f, 6.5f)
                    quadToRelative(-1.042f, 0f, -1.833f, -0.792f)
                    quadToRelative(-0.792f, -0.791f, -0.792f, -1.833f)
                    verticalLineTo(7.875f)
                    quadToRelative(0f, -1.042f, 0.792f, -1.833f)
                    quadToRelative(0.791f, -0.792f, 1.833f, -0.792f)
                    horizontalLineToRelative(24.25f)
                    quadToRelative(1.042f, 0f, 1.833f, 0.792f)
                    quadToRelative(0.792f, 0.791f, 0.792f, 1.833f)
                    verticalLineToRelative(24.25f)
                    quadToRelative(0f, 1.042f, -0.792f, 1.833f)
                    quadToRelative(-0.791f, 0.792f, -1.833f, 0.792f)
                    close()
                    moveToRelative(0f, -26.875f)
                    verticalLineToRelative(24.25f)
                    verticalLineToRelative(-24.25f)
                    close()
                    moveToRelative(0f, 24.25f)
                    horizontalLineToRelative(24.25f)
                    verticalLineTo(7.875f)
                    horizontalLineToRelative(-5.5f)
                    verticalLineToRelative(11.208f)
                    quadToRelative(0f, 0.792f, -0.625f, 1.167f)
                    reflectiveQuadToRelative(-1.292f, 0.042f)
                    lineTo(20f, 17.958f)
                    lineToRelative(-4.708f, 2.334f)
                    quadToRelative(-0.667f, 0.333f, -1.271f, -0.042f)
                    quadToRelative(-0.604f, -0.375f, -0.604f, -1.167f)
                    verticalLineTo(7.875f)
                    horizontalLineTo(7.875f)
                    verticalLineToRelative(24.25f)
                    close()
                }
            }.build()

}