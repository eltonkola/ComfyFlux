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

public val Ikona.Cancel: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon() : ImageVector {
        return ImageVector.Builder(
                name = "cancel",
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
                    moveTo(12.958f, 27.042f)
                    quadToRelative(0.375f, 0.375f, 0.917f, 0.375f)
                    reflectiveQuadToRelative(0.917f, -0.375f)
                    lineTo(20f, 21.833f)
                    lineToRelative(5.25f, 5.25f)
                    quadToRelative(0.333f, 0.334f, 0.875f, 0.313f)
                    quadToRelative(0.542f, -0.021f, 0.917f, -0.354f)
                    quadToRelative(0.375f, -0.375f, 0.375f, -0.917f)
                    reflectiveQuadToRelative(-0.375f, -0.917f)
                    lineTo(21.833f, 20f)
                    lineToRelative(5.25f, -5.25f)
                    quadToRelative(0.334f, -0.333f, 0.313f, -0.875f)
                    quadToRelative(-0.021f, -0.542f, -0.354f, -0.917f)
                    quadToRelative(-0.375f, -0.375f, -0.917f, -0.375f)
                    reflectiveQuadToRelative(-0.917f, 0.375f)
                    lineTo(20f, 18.167f)
                    lineToRelative(-5.25f, -5.25f)
                    quadToRelative(-0.333f, -0.334f, -0.875f, -0.313f)
                    quadToRelative(-0.542f, 0.021f, -0.917f, 0.354f)
                    quadToRelative(-0.375f, 0.375f, -0.375f, 0.917f)
                    reflectiveQuadToRelative(0.375f, 0.917f)
                    lineTo(18.167f, 20f)
                    lineToRelative(-5.25f, 5.25f)
                    quadToRelative(-0.334f, 0.333f, -0.313f, 0.875f)
                    quadToRelative(0.021f, 0.542f, 0.354f, 0.917f)
                    close()
                    moveTo(20f, 36.375f)
                    quadToRelative(-3.458f, 0f, -6.458f, -1.25f)
                    reflectiveQuadToRelative(-5.209f, -3.458f)
                    quadToRelative(-2.208f, -2.209f, -3.458f, -5.209f)
                    quadToRelative(-1.25f, -3f, -1.25f, -6.458f)
                    reflectiveQuadToRelative(1.25f, -6.437f)
                    quadToRelative(1.25f, -2.98f, 3.458f, -5.188f)
                    quadToRelative(2.209f, -2.208f, 5.209f, -3.479f)
                    quadToRelative(3f, -1.271f, 6.458f, -1.271f)
                    reflectiveQuadToRelative(6.438f, 1.271f)
                    quadToRelative(2.979f, 1.271f, 5.187f, 3.479f)
                    reflectiveQuadToRelative(3.479f, 5.188f)
                    quadToRelative(1.271f, 2.979f, 1.271f, 6.437f)
                    reflectiveQuadToRelative(-1.271f, 6.458f)
                    quadToRelative(-1.271f, 3f, -3.479f, 5.209f)
                    quadToRelative(-2.208f, 2.208f, -5.187f, 3.458f)
                    quadToRelative(-2.98f, 1.25f, -6.438f, 1.25f)
                    close()
                    moveTo(20f, 20f)
                    close()
                    moveToRelative(0f, 13.75f)
                    quadToRelative(5.667f, 0f, 9.708f, -4.042f)
                    quadTo(33.75f, 25.667f, 33.75f, 20f)
                    reflectiveQuadToRelative(-4.042f, -9.708f)
                    quadTo(25.667f, 6.25f, 20f, 6.25f)
                    reflectiveQuadToRelative(-9.708f, 4.042f)
                    quadTo(6.25f, 14.333f, 6.25f, 20f)
                    reflectiveQuadToRelative(4.042f, 9.708f)
                    quadTo(14.333f, 33.75f, 20f, 33.75f)
                    close()
                }
            }.build()

}