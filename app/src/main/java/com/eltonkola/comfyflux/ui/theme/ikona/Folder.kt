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

val Ikona.Folder: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon(): ImageVector {
    return ImageVector.Builder(
        name = "folder",
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
            moveTo(6.292f, 33.125f)
            quadToRelative(-1.084f, 0f, -1.875f, -0.813f)
            quadToRelative(-0.792f, -0.812f, -0.792f, -1.854f)
            verticalLineTo(9.667f)
            quadToRelative(0f, -1.042f, 0.792f, -1.834f)
            quadToRelative(0.791f, -0.791f, 1.875f, -0.791f)
            horizontalLineToRelative(10f)
            quadToRelative(0.541f, 0f, 1.041f, 0.208f)
            quadToRelative(0.5f, 0.208f, 0.834f, 0.583f)
            lineToRelative(1.875f, 1.834f)
            horizontalLineToRelative(13.75f)
            quadToRelative(1.041f, 0f, 1.833f, 0.791f)
            quadToRelative(0.792f, 0.792f, 0.792f, 1.834f)
            verticalLineToRelative(18.166f)
            quadToRelative(0f, 1.042f, -0.792f, 1.854f)
            quadToRelative(-0.792f, 0.813f, -1.833f, 0.813f)
            close()
            moveToRelative(0f, -23.458f)
            verticalLineToRelative(20.791f)
            horizontalLineToRelative(27.5f)
            verticalLineTo(12.292f)
            horizontalLineTo(18.917f)
            lineToRelative(-2.625f, -2.625f)
            horizontalLineToRelative(-10f)
            close()
            moveToRelative(0f, 0f)
            verticalLineToRelative(20.791f)
            close()
        }
    }.build()

}