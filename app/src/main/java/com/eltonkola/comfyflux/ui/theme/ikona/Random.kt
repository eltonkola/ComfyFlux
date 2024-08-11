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

public val Ikona.Random: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon() : ImageVector {
        return  ImageVector.Builder(
                name = "shuffle",
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
                    moveTo(16.125f, 17.917f)
                    lineTo(7.458f, 9.25f)
                    quadToRelative(-0.416f, -0.375f, -0.416f, -0.938f)
                    quadToRelative(0f, -0.562f, 0.416f, -0.937f)
                    quadTo(7.833f, 7f, 8.396f, 7f)
                    quadToRelative(0.562f, 0f, 0.937f, 0.375f)
                    lineTo(18f, 16.042f)
                    close()
                    moveToRelative(9.25f, 15.625f)
                    quadToRelative(-0.583f, 0f, -0.958f, -0.396f)
                    reflectiveQuadToRelative(-0.375f, -0.938f)
                    quadToRelative(0f, -0.541f, 0.375f, -0.937f)
                    reflectiveQuadToRelative(0.958f, -0.396f)
                    horizontalLineToRelative(3.667f)
                    lineToRelative(-7f, -6.958f)
                    lineToRelative(1.875f, -1.875f)
                    lineToRelative(7.041f, 7f)
                    verticalLineToRelative(-3.75f)
                    quadToRelative(0f, -0.542f, 0.375f, -0.938f)
                    quadToRelative(0.375f, -0.396f, 0.959f, -0.396f)
                    quadToRelative(0.541f, 0f, 0.916f, 0.396f)
                    reflectiveQuadToRelative(0.375f, 0.938f)
                    verticalLineToRelative(6.916f)
                    quadToRelative(0f, 0.584f, -0.375f, 0.959f)
                    reflectiveQuadToRelative(-0.916f, 0.375f)
                    close()
                    moveToRelative(-17.958f, -0.959f)
                    quadToRelative(-0.375f, -0.375f, -0.375f, -0.916f)
                    quadToRelative(0f, -0.542f, 0.375f, -0.959f)
                    lineTo(29.083f, 9.042f)
                    horizontalLineToRelative(-3.708f)
                    quadToRelative(-0.583f, 0f, -0.958f, -0.375f)
                    reflectiveQuadToRelative(-0.375f, -0.959f)
                    quadToRelative(0f, -0.541f, 0.375f, -0.916f)
                    reflectiveQuadToRelative(0.958f, -0.375f)
                    horizontalLineToRelative(6.917f)
                    quadToRelative(0.541f, 0f, 0.916f, 0.375f)
                    reflectiveQuadToRelative(0.375f, 0.916f)
                    verticalLineToRelative(6.917f)
                    quadToRelative(0f, 0.583f, -0.395f, 0.958f)
                    quadToRelative(-0.396f, 0.375f, -0.938f, 0.375f)
                    quadToRelative(-0.542f, 0f, -0.917f, -0.375f)
                    reflectiveQuadToRelative(-0.375f, -0.958f)
                    verticalLineToRelative(-3.667f)
                    lineTo(9.292f, 32.583f)
                    quadToRelative(-0.375f, 0.375f, -0.917f, 0.375f)
                    reflectiveQuadToRelative(-0.958f, -0.375f)
                    close()
                }
            }.build()


}