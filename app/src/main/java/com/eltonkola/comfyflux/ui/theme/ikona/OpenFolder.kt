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

val Ikona.OpenFolder: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon(): ImageVector {
    return ImageVector.Builder(
        name = "folder_open",
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
            moveTo(6.25f, 33.125f)
            quadToRelative(-1.083f, 0f, -1.854f, -0.792f)
            quadToRelative(-0.771f, -0.791f, -0.771f, -1.875f)
            verticalLineTo(9.667f)
            quadToRelative(0f, -1.084f, 0.771f, -1.854f)
            quadToRelative(0.771f, -0.771f, 1.854f, -0.771f)
            horizontalLineToRelative(10.042f)
            quadToRelative(0.541f, 0f, 1.041f, 0.208f)
            quadToRelative(0.5f, 0.208f, 0.834f, 0.583f)
            lineToRelative(1.875f, 1.834f)
            horizontalLineTo(33.75f)
            quadToRelative(1.083f, 0f, 1.854f, 0.791f)
            quadToRelative(0.771f, 0.792f, 0.771f, 1.834f)
            horizontalLineTo(18.917f)
            lineTo(16.25f, 9.667f)
            horizontalLineToRelative(-10f)
            verticalLineTo(30.25f)
            lineToRelative(3.542f, -13.375f)
            quadToRelative(0.25f, -0.875f, 0.979f, -1.396f)
            quadToRelative(0.729f, -0.521f, 1.604f, -0.521f)
            horizontalLineToRelative(23.25f)
            quadToRelative(1.292f, 0f, 2.104f, 1.021f)
            quadToRelative(0.813f, 1.021f, 0.438f, 2.271f)
            lineToRelative(-3.459f, 12.833f)
            quadToRelative(-0.291f, 1f, -1f, 1.521f)
            quadToRelative(-0.708f, 0.521f, -1.75f, 0.521f)
            close()
            moveToRelative(2.708f, -2.667f)
            horizontalLineToRelative(23.167f)
            lineToRelative(3.417f, -12.875f)
            horizontalLineTo(12.333f)
            close()
            moveToRelative(0f, 0f)
            lineToRelative(3.375f, -12.875f)
            lineToRelative(-3.375f, 12.875f)
            close()
            moveToRelative(-2.708f, -15.5f)
            verticalLineTo(9.667f)
            verticalLineToRelative(5.291f)
            close()
        }
    }.build()

}