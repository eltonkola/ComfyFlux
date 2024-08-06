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

public val Ikona.Close: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon() : ImageVector {
        return ImageVector.Builder(
                name = "close",
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
                    moveTo(20f, 21.875f)
                    lineToRelative(-8.5f, 8.5f)
                    quadToRelative(-0.417f, 0.375f, -0.938f, 0.375f)
                    quadToRelative(-0.52f, 0f, -0.937f, -0.375f)
                    quadToRelative(-0.375f, -0.417f, -0.375f, -0.937f)
                    quadToRelative(0f, -0.521f, 0.375f, -0.938f)
                    lineToRelative(8.542f, -8.542f)
                    lineToRelative(-8.542f, -8.5f)
                    quadToRelative(-0.375f, -0.375f, -0.375f, -0.916f)
                    quadToRelative(0f, -0.542f, 0.375f, -0.917f)
                    quadToRelative(0.417f, -0.417f, 0.937f, -0.417f)
                    quadToRelative(0.521f, 0f, 0.938f, 0.417f)
                    lineToRelative(8.5f, 8.5f)
                    lineToRelative(8.5f, -8.5f)
                    quadToRelative(0.417f, -0.375f, 0.938f, -0.375f)
                    quadToRelative(0.52f, 0f, 0.937f, 0.375f)
                    quadToRelative(0.375f, 0.417f, 0.375f, 0.938f)
                    quadToRelative(0f, 0.52f, -0.375f, 0.937f)
                    lineTo(21.833f, 20f)
                    lineToRelative(8.542f, 8.542f)
                    quadToRelative(0.375f, 0.375f, 0.396f, 0.916f)
                    quadToRelative(0.021f, 0.542f, -0.396f, 0.917f)
                    quadToRelative(-0.375f, 0.375f, -0.917f, 0.375f)
                    quadToRelative(-0.541f, 0f, -0.916f, -0.375f)
                    close()
                }
            }.build()


}