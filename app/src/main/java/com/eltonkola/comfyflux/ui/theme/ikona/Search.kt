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

val Ikona.Search: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon(): ImageVector {
    return ImageVector.Builder(
        name = "manage_search",
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
            moveTo(4.958f, 14.458f)
            quadToRelative(-0.583f, 0f, -0.958f, -0.375f)
            reflectiveQuadToRelative(-0.375f, -0.916f)
            quadToRelative(0f, -0.584f, 0.375f, -0.959f)
            reflectiveQuadToRelative(0.958f, -0.375f)
            horizontalLineTo(10.5f)
            quadToRelative(0.542f, 0f, 0.938f, 0.396f)
            quadToRelative(0.395f, 0.396f, 0.395f, 0.938f)
            quadToRelative(0f, 0.541f, -0.395f, 0.916f)
            quadToRelative(-0.396f, 0.375f, -0.938f, 0.375f)
            close()
            moveToRelative(0f, 8.5f)
            quadToRelative(-0.583f, 0f, -0.958f, -0.375f)
            reflectiveQuadToRelative(-0.375f, -0.916f)
            quadToRelative(0f, -0.584f, 0.375f, -0.959f)
            reflectiveQuadToRelative(0.958f, -0.375f)
            horizontalLineTo(10.5f)
            quadToRelative(0.542f, 0f, 0.938f, 0.396f)
            quadToRelative(0.395f, 0.396f, 0.395f, 0.938f)
            quadToRelative(0f, 0.541f, -0.395f, 0.916f)
            quadToRelative(-0.396f, 0.375f, -0.938f, 0.375f)
            close()
            moveToRelative(28.667f, 7.625f)
            lineTo(28.042f, 25f)
            quadToRelative(-1.042f, 0.792f, -2.25f, 1.167f)
            quadToRelative(-1.209f, 0.375f, -2.5f, 0.375f)
            quadToRelative(-3.375f, 0f, -5.75f, -2.375f)
            reflectiveQuadToRelative(-2.375f, -5.75f)
            quadToRelative(0f, -3.375f, 2.375f, -5.75f)
            reflectiveQuadToRelative(5.75f, -2.375f)
            quadToRelative(3.375f, 0f, 5.77f, 2.375f)
            quadToRelative(2.396f, 2.375f, 2.396f, 5.75f)
            quadToRelative(0f, 1.291f, -0.396f, 2.5f)
            quadToRelative(-0.395f, 1.208f, -1.187f, 2.208f)
            lineToRelative(5.583f, 5.625f)
            quadToRelative(0.375f, 0.375f, 0.396f, 0.896f)
            quadToRelative(0.021f, 0.521f, -0.396f, 0.937f)
            quadToRelative(-0.375f, 0.375f, -0.916f, 0.375f)
            quadToRelative(-0.542f, 0f, -0.917f, -0.375f)
            close()
            moveToRelative(-10.333f, -6.666f)
            quadToRelative(2.291f, 0f, 3.896f, -1.605f)
            quadToRelative(1.604f, -1.604f, 1.604f, -3.895f)
            quadToRelative(0f, -2.292f, -1.604f, -3.896f)
            quadToRelative(-1.605f, -1.604f, -3.896f, -1.604f)
            quadToRelative(-2.292f, 0f, -3.896f, 1.604f)
            reflectiveQuadToRelative(-1.604f, 3.896f)
            quadToRelative(0f, 2.291f, 1.604f, 3.895f)
            quadToRelative(1.604f, 1.605f, 3.896f, 1.605f)
            close()
            moveTo(4.958f, 31.5f)
            quadToRelative(-0.583f, 0f, -0.958f, -0.396f)
            reflectiveQuadToRelative(-0.375f, -0.937f)
            quadToRelative(0f, -0.542f, 0.375f, -0.917f)
            reflectiveQuadToRelative(0.958f, -0.375f)
            horizontalLineTo(18.75f)
            quadToRelative(0.542f, 0f, 0.938f, 0.375f)
            quadToRelative(0.395f, 0.375f, 0.395f, 0.917f)
            quadToRelative(0f, 0.583f, -0.395f, 0.958f)
            quadToRelative(-0.396f, 0.375f, -0.938f, 0.375f)
            close()
        }
    }.build()

}