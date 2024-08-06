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

public val Ikona.ArrowRight: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon() : ImageVector {
        return ImageVector.Builder(
                name = "arrow_forward_ios",
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
                    moveTo(11.75f, 35.125f)
                    quadToRelative(-0.5f, -0.5f, -0.5f, -1.208f)
                    quadToRelative(0f, -0.709f, 0.5f, -1.209f)
                    lineToRelative(12.792f, -12.75f)
                    lineTo(11.75f, 7.167f)
                    quadToRelative(-0.5f, -0.5f, -0.5f, -1.188f)
                    quadToRelative(0f, -0.687f, 0.5f, -1.229f)
                    quadToRelative(0.5f, -0.5f, 1.208f, -0.521f)
                    quadToRelative(0.709f, -0.021f, 1.209f, 0.521f)
                    lineToRelative(14.291f, 14.292f)
                    quadToRelative(0.209f, 0.208f, 0.313f, 0.416f)
                    quadToRelative(0.104f, 0.209f, 0.104f, 0.5f)
                    quadToRelative(0f, 0.25f, -0.104f, 0.48f)
                    quadToRelative(-0.104f, 0.229f, -0.313f, 0.437f)
                    lineTo(14.167f, 35.167f)
                    quadToRelative(-0.5f, 0.5f, -1.209f, 0.479f)
                    quadToRelative(-0.708f, -0.021f, -1.208f, -0.521f)
                    close()
                }
            }.build()

}