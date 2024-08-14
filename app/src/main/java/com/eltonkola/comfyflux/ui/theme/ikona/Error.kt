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

val Ikona.Error: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon(): ImageVector {
    return ImageVector.Builder(
        name = "error",
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
            moveTo(20.125f, 21.875f)
            quadToRelative(0.542f, 0f, 0.917f, -0.396f)
            reflectiveQuadToRelative(0.375f, -0.937f)
            verticalLineToRelative(-7.667f)
            quadToRelative(0f, -0.5f, -0.396f, -0.875f)
            reflectiveQuadToRelative(-0.938f, -0.375f)
            quadToRelative(-0.541f, 0f, -0.916f, 0.375f)
            reflectiveQuadToRelative(-0.375f, 0.917f)
            verticalLineToRelative(7.666f)
            quadToRelative(0f, 0.542f, 0.396f, 0.917f)
            quadToRelative(0.395f, 0.375f, 0.937f, 0.375f)
            close()
            moveTo(20f, 28.208f)
            quadToRelative(0.625f, 0f, 1.021f, -0.416f)
            quadToRelative(0.396f, -0.417f, 0.396f, -1f)
            quadToRelative(0f, -0.625f, -0.396f, -1.021f)
            quadToRelative(-0.396f, -0.396f, -1.021f, -0.396f)
            quadToRelative(-0.625f, 0f, -1.021f, 0.396f)
            quadToRelative(-0.396f, 0.396f, -0.396f, 1.021f)
            quadToRelative(0f, 0.583f, 0.396f, 1f)
            quadToRelative(0.396f, 0.416f, 1.021f, 0.416f)
            close()
            moveToRelative(0f, 8.167f)
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