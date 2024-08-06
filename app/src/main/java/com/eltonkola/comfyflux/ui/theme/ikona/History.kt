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

public val Ikona.History: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon() : ImageVector {
        return ImageVector.Builder(
                name = "view_list",
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
                    moveTo(5.25f, 28.792f)
                    verticalLineTo(11.208f)
                    quadToRelative(0f, -1.083f, 0.771f, -1.854f)
                    quadToRelative(0.771f, -0.771f, 1.854f, -0.771f)
                    horizontalLineToRelative(24.25f)
                    quadToRelative(1.083f, 0f, 1.854f, 0.771f)
                    quadToRelative(0.771f, 0.771f, 0.771f, 1.854f)
                    verticalLineToRelative(17.584f)
                    quadToRelative(0f, 1.083f, -0.771f, 1.875f)
                    quadToRelative(-0.771f, 0.791f, -1.854f, 0.791f)
                    horizontalLineTo(7.875f)
                    quadToRelative(-1.083f, 0f, -1.854f, -0.791f)
                    quadToRelative(-0.771f, -0.792f, -0.771f, -1.875f)
                    close()
                    moveToRelative(2.625f, -13.459f)
                    horizontalLineToRelative(4.083f)
                    verticalLineToRelative(-4.125f)
                    horizontalLineTo(7.875f)
                    verticalLineToRelative(4.125f)
                    close()
                    moveToRelative(6.75f, 0f)
                    horizontalLineToRelative(17.5f)
                    verticalLineToRelative(-4.125f)
                    horizontalLineToRelative(-17.5f)
                    verticalLineToRelative(4.125f)
                    close()
                    moveToRelative(0f, 6.709f)
                    horizontalLineToRelative(17.5f)
                    verticalLineToRelative(-4.084f)
                    horizontalLineToRelative(-17.5f)
                    verticalLineToRelative(4.084f)
                    close()
                    moveToRelative(0f, 6.75f)
                    horizontalLineToRelative(17.5f)
                    verticalLineToRelative(-4.084f)
                    horizontalLineToRelative(-17.5f)
                    verticalLineToRelative(4.084f)
                    close()
                    moveToRelative(-6.75f, 0f)
                    horizontalLineToRelative(4.083f)
                    verticalLineToRelative(-4.084f)
                    horizontalLineTo(7.875f)
                    verticalLineToRelative(4.084f)
                    close()
                    moveToRelative(0f, -6.75f)
                    horizontalLineToRelative(4.083f)
                    verticalLineToRelative(-4.084f)
                    horizontalLineTo(7.875f)
                    verticalLineToRelative(4.084f)
                    close()
                }
            }.build()


}