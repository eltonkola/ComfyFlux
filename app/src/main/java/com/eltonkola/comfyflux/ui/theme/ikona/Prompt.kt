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

val Ikona.Promp: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon(): ImageVector {
    return ImageVector.Builder(
        name = "key_visualizer",
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
            moveTo(5.083f, 34.917f)
            verticalLineToRelative(-2.625f)
            horizontalLineToRelative(4.875f)
            verticalLineToRelative(2.625f)
            close()
            moveToRelative(0f, -6.959f)
            verticalLineToRelative(-2.625f)
            horizontalLineToRelative(13.209f)
            verticalLineToRelative(2.625f)
            close()
            moveToRelative(0f, -6.666f)
            verticalLineToRelative(-2.625f)
            horizontalLineToRelative(29.875f)
            verticalLineToRelative(2.625f)
            close()
            moveToRelative(0f, -6.667f)
            verticalLineTo(12f)
            horizontalLineToRelative(13.209f)
            verticalLineToRelative(2.625f)
            close()
            moveToRelative(0f, -6.917f)
            verticalLineTo(5.042f)
            horizontalLineToRelative(4.875f)
            verticalLineToRelative(2.666f)
            close()
            moveToRelative(8.334f, 27.209f)
            verticalLineToRelative(-2.625f)
            horizontalLineToRelative(4.875f)
            verticalLineToRelative(2.625f)
            close()
            moveToRelative(0f, -27.209f)
            verticalLineTo(5.042f)
            horizontalLineToRelative(4.875f)
            verticalLineToRelative(2.666f)
            close()
            moveToRelative(8.333f, 13.584f)
            verticalLineToRelative(-2.625f)
            horizontalLineToRelative(10.417f)
            verticalLineToRelative(2.625f)
            close()
            moveToRelative(0f, -6.917f)
            verticalLineToRelative(-2.667f)
            horizontalLineToRelative(7.625f)
            verticalLineToRelative(2.667f)
            close()
            moveToRelative(0.125f, 13.875f)
            verticalLineToRelative(-2.625f)
            horizontalLineToRelative(7.5f)
            verticalLineToRelative(2.625f)
            close()
            moveToRelative(0f, -6.917f)
            verticalLineToRelative(-2.666f)
            horizontalLineToRelative(10.292f)
            verticalLineToRelative(2.666f)
            close()
            moveToRelative(8.208f, 13.584f)
            verticalLineToRelative(-2.625f)
            horizontalLineToRelative(4.875f)
            verticalLineToRelative(2.625f)
            close()
            moveToRelative(0f, -27.209f)
            verticalLineTo(5.042f)
            horizontalLineToRelative(4.875f)
            verticalLineToRelative(2.666f)
            close()
        }
    }.build()

}