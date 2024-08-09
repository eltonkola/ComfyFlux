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

public val Ikona.Stop: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon() : ImageVector {
   return ImageVector.Builder(
                name = "stop_circle",
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
                    quadToRelative(5.833f, 0f, 9.792f, -3.958f)
                    quadTo(33.75f, 25.833f, 33.75f, 20f)
                    reflectiveQuadToRelative(-3.958f, -9.792f)
                    quadTo(25.833f, 6.25f, 20f, 6.25f)
                    reflectiveQuadToRelative(-9.792f, 3.958f)
                    quadTo(6.25f, 14.167f, 6.25f, 20f)
                    reflectiveQuadToRelative(3.958f, 9.792f)
                    quadTo(14.167f, 33.75f, 20f, 33.75f)
                    close()
                    moveToRelative(-5f, -7.458f)
                    horizontalLineToRelative(10.042f)
                    quadToRelative(0.541f, 0f, 0.916f, -0.354f)
                    quadToRelative(0.375f, -0.355f, 0.375f, -0.938f)
                    verticalLineTo(14.958f)
                    quadToRelative(0f, -0.541f, -0.375f, -0.916f)
                    reflectiveQuadToRelative(-0.916f, -0.375f)
                    horizontalLineTo(15f)
                    quadToRelative(-0.583f, 0f, -0.938f, 0.375f)
                    quadToRelative(-0.354f, 0.375f, -0.354f, 0.916f)
                    verticalLineTo(25f)
                    quadToRelative(0f, 0.583f, 0.354f, 0.938f)
                    quadToRelative(0.355f, 0.354f, 0.938f, 0.354f)
                    close()
                }
            }.build()

}