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

public val Ikona.Create: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon() : ImageVector {

        return ImageVector.Builder(
                name = "magic_button",
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
                    moveTo(16.333f, 30.375f)
                    lineToRelative(-3.875f, -8.542f)
                    lineToRelative(-8.583f, -3.875f)
                    lineToRelative(8.583f, -3.916f)
                    lineTo(16.333f, 5.5f)
                    lineToRelative(3.875f, 8.542f)
                    lineToRelative(8.584f, 3.916f)
                    lineToRelative(-8.584f, 3.875f)
                    close()
                    moveTo(29.875f, 34.5f)
                    lineToRelative(-1.917f, -4.292f)
                    lineToRelative(-4.291f, -1.916f)
                    lineToRelative(4.291f, -1.959f)
                    lineToRelative(1.917f, -4.291f)
                    lineToRelative(2f, 4.291f)
                    lineToRelative(4.25f, 1.959f)
                    lineToRelative(-4.25f, 1.916f)
                    close()
                }
            }.build()

}