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

public val Ikona.Server: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon() : ImageVector {
        return ImageVector.Builder(
                name = "api",
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
                    moveTo(16.458f, 12.208f)
                    lineToRelative(-3.291f, -3.291f)
                    lineToRelative(5f, -5f)
                    quadToRelative(0.375f, -0.417f, 0.854f, -0.584f)
                    quadToRelative(0.479f, -0.166f, 0.979f, -0.166f)
                    reflectiveQuadToRelative(0.979f, 0.166f)
                    quadToRelative(0.479f, 0.167f, 0.854f, 0.584f)
                    lineToRelative(5f, 5f)
                    lineToRelative(-3.291f, 3.291f)
                    lineTo(20f, 8.667f)
                    close()
                    moveTo(8.917f, 26.833f)
                    lineToRelative(-5f, -5f)
                    quadToRelative(-0.417f, -0.375f, -0.584f, -0.854f)
                    quadToRelative(-0.166f, -0.479f, -0.166f, -0.979f)
                    reflectiveQuadToRelative(0.166f, -0.979f)
                    quadToRelative(0.167f, -0.479f, 0.584f, -0.854f)
                    lineToRelative(5f, -5f)
                    lineToRelative(3.291f, 3.291f)
                    lineTo(8.667f, 20f)
                    lineToRelative(3.541f, 3.542f)
                    close()
                    moveToRelative(22.166f, 0f)
                    lineToRelative(-3.291f, -3.291f)
                    lineTo(31.333f, 20f)
                    lineToRelative(-3.541f, -3.542f)
                    lineToRelative(3.291f, -3.291f)
                    lineToRelative(5f, 5f)
                    quadToRelative(0.417f, 0.375f, 0.584f, 0.854f)
                    quadToRelative(0.166f, 0.479f, 0.166f, 0.979f)
                    reflectiveQuadToRelative(-0.166f, 0.979f)
                    quadToRelative(-0.167f, 0.479f, -0.584f, 0.854f)
                    close()
                    moveTo(20f, 36.833f)
                    quadToRelative(-0.5f, 0f, -0.979f, -0.166f)
                    quadToRelative(-0.479f, -0.167f, -0.854f, -0.584f)
                    lineToRelative(-5f, -5f)
                    lineToRelative(3.291f, -3.291f)
                    lineTo(20f, 31.333f)
                    lineToRelative(3.542f, -3.541f)
                    lineToRelative(3.291f, 3.291f)
                    lineToRelative(-5f, 5f)
                    quadToRelative(-0.375f, 0.417f, -0.854f, 0.584f)
                    quadToRelative(-0.479f, 0.166f, -0.979f, 0.166f)
                    close()
                    moveToRelative(0f, -13.75f)
                    quadToRelative(-1.25f, 0f, -2.188f, -0.895f)
                    quadToRelative(-0.937f, -0.896f, -0.937f, -2.188f)
                    quadToRelative(0f, -1.292f, 0.937f, -2.208f)
                    quadToRelative(0.938f, -0.917f, 2.188f, -0.917f)
                    quadToRelative(1.292f, 0f, 2.188f, 0.917f)
                    quadToRelative(0.895f, 0.916f, 0.937f, 2.208f)
                    quadToRelative(-0.042f, 1.292f, -0.937f, 2.188f)
                    quadToRelative(-0.896f, 0.895f, -2.188f, 0.895f)
                    close()
                }
            }.build()
}