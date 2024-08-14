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

val Ikona.Paste: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon(): ImageVector {

    return ImageVector.Builder(
        name = "content_paste",
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
            moveTo(20f, 7.792f)
            quadToRelative(0.667f, 0f, 1.146f, -0.479f)
            quadToRelative(0.479f, -0.48f, 0.479f, -1.146f)
            quadToRelative(0f, -0.667f, -0.479f, -1.125f)
            quadToRelative(-0.479f, -0.459f, -1.104f, -0.459f)
            quadToRelative(-0.709f, 0f, -1.167f, 0.459f)
            quadToRelative(-0.458f, 0.458f, -0.458f, 1.125f)
            quadToRelative(0f, 0.666f, 0.458f, 1.146f)
            quadToRelative(0.458f, 0.479f, 1.125f, 0.479f)
            close()
            moveTo(7.917f, 34.708f)
            quadToRelative(-1.125f, 0f, -1.896f, -0.75f)
            quadToRelative(-0.771f, -0.75f, -0.771f, -1.875f)
            verticalLineTo(7.875f)
            quadToRelative(0f, -1.125f, 0.771f, -1.875f)
            reflectiveQuadToRelative(1.896f, -0.75f)
            horizontalLineToRelative(8f)
            quadToRelative(0.291f, -1.458f, 1.458f, -2.375f)
            reflectiveQuadTo(20f, 1.958f)
            quadToRelative(1.5f, 0f, 2.667f, 0.917f)
            quadToRelative(1.166f, 0.917f, 1.458f, 2.375f)
            horizontalLineToRelative(8f)
            quadToRelative(1.125f, 0f, 1.896f, 0.75f)
            quadToRelative(0.771f, 0.75f, 0.771f, 1.875f)
            verticalLineToRelative(24.208f)
            quadToRelative(0f, 1.125f, -0.771f, 1.875f)
            reflectiveQuadToRelative(-1.896f, 0.75f)
            close()
            moveToRelative(0f, -2.625f)
            horizontalLineToRelative(24.208f)
            verticalLineTo(7.875f)
            horizontalLineTo(29.5f)
            verticalLineToRelative(1.333f)
            quadToRelative(0f, 1.084f, -0.771f, 1.855f)
            quadToRelative(-0.771f, 0.77f, -1.854f, 0.77f)
            horizontalLineTo(13.167f)
            quadToRelative(-1.084f, 0f, -1.855f, -0.77f)
            quadToRelative(-0.77f, -0.771f, -0.77f, -1.855f)
            verticalLineTo(7.875f)
            horizontalLineTo(7.917f)
            verticalLineToRelative(24.208f)
            close()
        }
    }.build()

}