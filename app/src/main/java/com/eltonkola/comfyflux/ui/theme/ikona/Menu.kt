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

public val Ikona.Menu: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon() : ImageVector {
        return ImageVector.Builder(
                name = "menu_open",
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
                    moveTo(6.542f, 29.833f)
                    quadToRelative(-0.542f, 0f, -0.938f, -0.395f)
                    quadToRelative(-0.396f, -0.396f, -0.396f, -0.938f)
                    quadToRelative(0f, -0.542f, 0.396f, -0.917f)
                    reflectiveQuadToRelative(0.938f, -0.375f)
                    horizontalLineTo(25.25f)
                    quadToRelative(0.542f, 0f, 0.917f, 0.375f)
                    reflectiveQuadToRelative(0.375f, 0.917f)
                    quadToRelative(0f, 0.583f, -0.375f, 0.958f)
                    reflectiveQuadToRelative(-0.917f, 0.375f)
                    close()
                    moveToRelative(0f, -8.583f)
                    quadToRelative(-0.542f, 0f, -0.938f, -0.396f)
                    quadToRelative(-0.396f, -0.396f, -0.396f, -0.937f)
                    quadToRelative(0f, -0.542f, 0.396f, -0.917f)
                    reflectiveQuadToRelative(0.938f, -0.375f)
                    horizontalLineToRelative(13.75f)
                    quadToRelative(0.541f, 0f, 0.937f, 0.375f)
                    reflectiveQuadToRelative(0.396f, 0.917f)
                    quadToRelative(0f, 0.583f, -0.396f, 0.958f)
                    reflectiveQuadToRelative(-0.937f, 0.375f)
                    close()
                    moveToRelative(0f, -8.458f)
                    quadToRelative(-0.542f, 0f, -0.938f, -0.375f)
                    quadToRelative(-0.396f, -0.375f, -0.396f, -0.959f)
                    quadToRelative(0f, -0.541f, 0.396f, -0.916f)
                    reflectiveQuadToRelative(0.938f, -0.375f)
                    horizontalLineTo(25.25f)
                    quadToRelative(0.542f, 0f, 0.917f, 0.396f)
                    quadToRelative(0.375f, 0.395f, 0.375f, 0.937f)
                    reflectiveQuadToRelative(-0.375f, 0.917f)
                    quadToRelative(-0.375f, 0.375f, -0.917f, 0.375f)
                    close()
                    moveToRelative(22.25f, 7.166f)
                    lineToRelative(5.083f, 5.084f)
                    quadToRelative(0.417f, 0.416f, 0.396f, 0.937f)
                    quadToRelative(-0.021f, 0.521f, -0.396f, 0.896f)
                    reflectiveQuadToRelative(-0.937f, 0.375f)
                    quadToRelative(-0.563f, 0f, -0.938f, -0.375f)
                    lineToRelative(-6.042f, -6f)
                    quadToRelative(-0.375f, -0.375f, -0.375f, -0.896f)
                    reflectiveQuadToRelative(0.375f, -0.937f)
                    lineToRelative(6.042f, -6f)
                    quadToRelative(0.375f, -0.375f, 0.938f, -0.375f)
                    quadToRelative(0.562f, 0f, 0.937f, 0.375f)
                    reflectiveQuadToRelative(0.375f, 0.937f)
                    quadToRelative(0f, 0.563f, -0.375f, 0.938f)
                    close()
                }
            }.build()


}