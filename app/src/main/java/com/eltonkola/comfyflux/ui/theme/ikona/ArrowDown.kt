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

public val Ikona.ArrowDown: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon() : ImageVector {

return ImageVector.Builder(
            name = "arrow_drop_down",
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
                moveTo(19.083f, 23.958f)
                lineTo(14.125f, 19f)
                quadToRelative(-0.625f, -0.625f, -0.271f, -1.417f)
                quadToRelative(0.354f, -0.791f, 1.188f, -0.791f)
                horizontalLineToRelative(9.916f)
                quadToRelative(0.834f, 0f, 1.188f, 0.791f)
                quadToRelative(0.354f, 0.792f, -0.271f, 1.417f)
                lineToRelative(-4.958f, 4.958f)
                quadToRelative(-0.209f, 0.209f, -0.438f, 0.313f)
                quadToRelative(-0.229f, 0.104f, -0.479f, 0.104f)
                quadToRelative(-0.25f, 0f, -0.479f, -0.104f)
                quadToRelative(-0.229f, -0.104f, -0.438f, -0.313f)
                close()
            }
        }.build()

}