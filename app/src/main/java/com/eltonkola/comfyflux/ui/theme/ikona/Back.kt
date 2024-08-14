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

val Ikona.Back: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon(): ImageVector {
    return ImageVector.Builder(
        name = "arrow_back_ios",
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
            moveTo(15.375f, 35.208f)
            lineTo(1.083f, 20.917f)
            quadToRelative(-0.208f, -0.209f, -0.291f, -0.438f)
            quadTo(0.708f, 20.25f, 0.708f, 20f)
            quadToRelative(0f, -0.25f, 0.084f, -0.479f)
            quadToRelative(0.083f, -0.229f, 0.291f, -0.438f)
            lineTo(15.375f, 4.792f)
            quadToRelative(0.5f, -0.5f, 1.208f, -0.5f)
            quadToRelative(0.709f, 0f, 1.209f, 0.5f)
            quadToRelative(0.5f, 0.5f, 0.5f, 1.208f)
            reflectiveQuadToRelative(-0.5f, 1.25f)
            lineTo(5.042f, 20f)
            lineToRelative(12.75f, 12.792f)
            quadToRelative(0.5f, 0.5f, 0.5f, 1.208f)
            reflectiveQuadToRelative(-0.5f, 1.167f)
            quadToRelative(-0.5f, 0.541f, -1.209f, 0.541f)
            quadToRelative(-0.708f, 0f, -1.208f, -0.5f)
            close()
        }
    }.build()

}