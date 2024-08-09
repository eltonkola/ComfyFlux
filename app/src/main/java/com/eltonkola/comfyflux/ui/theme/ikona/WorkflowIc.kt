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

public val Ikona.WorkflowIc: ImageVector
    get() {
        if (_icon != null) {
            return _icon!!
        }
        _icon = buildIcon()
        return _icon!!
    }

private fun buildIcon() : ImageVector {
        return ImageVector.Builder(
                name = "flowsheet",
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
                    moveTo(6.042f, 21.042f)
                    verticalLineToRelative(9.416f)
                    verticalLineTo(9.542f)
                    verticalLineToRelative(11.5f)
                    close()
                    moveToRelative(17f, 14.583f)
                    quadToRelative(0.875f, 0f, 1.458f, -0.583f)
                    quadToRelative(0.583f, -0.584f, 0.583f, -1.417f)
                    quadToRelative(0f, -0.875f, -0.583f, -1.458f)
                    quadToRelative(-0.583f, -0.584f, -1.458f, -0.584f)
                    quadToRelative(-0.834f, 0f, -1.417f, 0.584f)
                    quadToRelative(-0.583f, 0.583f, -0.583f, 1.458f)
                    quadToRelative(0f, 0.833f, 0.583f, 1.417f)
                    quadToRelative(0.583f, 0.583f, 1.417f, 0.583f)
                    close()
                    moveToRelative(10.583f, -17.25f)
                    quadToRelative(0.833f, 0f, 1.417f, -0.583f)
                    quadToRelative(0.583f, -0.584f, 0.583f, -1.417f)
                    quadToRelative(0f, -0.833f, -0.583f, -1.417f)
                    quadToRelative(-0.584f, -0.583f, -1.417f, -0.583f)
                    quadToRelative(-0.833f, 0f, -1.417f, 0.583f)
                    quadToRelative(-0.583f, 0.584f, -0.583f, 1.417f)
                    quadToRelative(0f, 0.833f, 0.583f, 1.417f)
                    quadToRelative(0.584f, 0.583f, 1.417f, 0.583f)
                    close()
                    moveToRelative(-16.417f, -0.625f)
                    quadToRelative(0.584f, 0f, 0.959f, -0.396f)
                    reflectiveQuadToRelative(0.375f, -0.937f)
                    quadToRelative(0f, -0.542f, -0.375f, -0.917f)
                    reflectiveQuadToRelative(-0.959f, -0.375f)
                    horizontalLineToRelative(-5.75f)
                    quadToRelative(-0.541f, 0f, -0.937f, 0.375f)
                    reflectiveQuadToRelative(-0.396f, 0.917f)
                    quadToRelative(0f, 0.583f, 0.396f, 0.958f)
                    reflectiveQuadToRelative(0.937f, 0.375f)
                    close()
                    moveToRelative(0f, 7.125f)
                    quadToRelative(0.584f, 0f, 0.959f, -0.375f)
                    reflectiveQuadToRelative(0.375f, -0.917f)
                    quadToRelative(0f, -0.583f, -0.375f, -0.958f)
                    reflectiveQuadToRelative(-0.959f, -0.375f)
                    horizontalLineToRelative(-5.75f)
                    quadToRelative(-0.541f, 0f, -0.937f, 0.396f)
                    reflectiveQuadToRelative(-0.396f, 0.937f)
                    quadToRelative(0f, 0.542f, 0.396f, 0.917f)
                    reflectiveQuadToRelative(0.937f, 0.375f)
                    close()
                    moveTo(6.042f, 33.083f)
                    quadToRelative(-1.084f, 0f, -1.875f, -0.791f)
                    quadToRelative(-0.792f, -0.792f, -0.792f, -1.834f)
                    verticalLineTo(9.542f)
                    quadToRelative(0f, -1.042f, 0.792f, -1.834f)
                    quadToRelative(0.791f, -0.791f, 1.875f, -0.791f)
                    horizontalLineToRelative(27.916f)
                    quadToRelative(1.084f, 0f, 1.875f, 0.791f)
                    quadToRelative(0.792f, 0.792f, 0.792f, 1.834f)
                    horizontalLineTo(6.042f)
                    verticalLineToRelative(20.916f)
                    horizontalLineToRelative(9.75f)
                    verticalLineToRelative(2.625f)
                    close()
                    moveToRelative(17f, 5.167f)
                    quadToRelative(-1.917f, 0f, -3.271f, -1.354f)
                    quadToRelative(-1.354f, -1.354f, -1.354f, -3.313f)
                    quadToRelative(0f, -1.541f, 0.937f, -2.771f)
                    quadToRelative(0.938f, -1.229f, 2.396f, -1.687f)
                    verticalLineTo(25f)
                    quadToRelative(0f, -0.542f, 0.375f, -0.938f)
                    quadToRelative(0.375f, -0.395f, 0.917f, -0.395f)
                    horizontalLineToRelative(9.25f)
                    verticalLineToRelative(-2.834f)
                    quadToRelative(-1.459f, -0.416f, -2.396f, -1.625f)
                    quadToRelative(-0.938f, -1.208f, -0.938f, -2.833f)
                    quadToRelative(0f, -1.917f, 1.354f, -3.292f)
                    quadToRelative(1.355f, -1.375f, 3.313f, -1.375f)
                    quadToRelative(1.917f, 0f, 3.292f, 1.375f)
                    quadToRelative(1.375f, 1.375f, 1.375f, 3.334f)
                    quadToRelative(0f, 1.541f, -0.959f, 2.771f)
                    quadToRelative(-0.958f, 1.229f, -2.375f, 1.645f)
                    verticalLineTo(25f)
                    quadToRelative(0f, 0.542f, -0.396f, 0.917f)
                    quadToRelative(-0.395f, 0.375f, -0.937f, 0.375f)
                    horizontalLineToRelative(-9.25f)
                    verticalLineToRelative(2.833f)
                    quadToRelative(1.458f, 0.458f, 2.396f, 1.667f)
                    quadToRelative(0.937f, 1.208f, 0.937f, 2.833f)
                    quadToRelative(0f, 1.917f, -1.354f, 3.271f)
                    quadTo(25f, 38.25f, 23.042f, 38.25f)
                    close()
                }
            }.build()



}