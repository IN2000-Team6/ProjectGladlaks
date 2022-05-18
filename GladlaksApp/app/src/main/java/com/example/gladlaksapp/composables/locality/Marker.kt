package com.example.gladlaksapp.composables.locality

import android.graphics.Color
import android.graphics.Typeface
import android.text.TextUtils
import androidx.annotation.ColorInt
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import com.patrykandpatryk.vico.core.chart.insets.Insets
import com.patrykandpatryk.vico.core.component.OverlayingComponent
import com.patrykandpatryk.vico.core.component.marker.MarkerComponent
import com.patrykandpatryk.vico.core.component.shape.DashedShape
import com.patrykandpatryk.vico.core.component.shape.LineComponent
import com.patrykandpatryk.vico.core.component.shape.ShapeComponent
import com.patrykandpatryk.vico.core.component.shape.Shapes
import com.patrykandpatryk.vico.core.component.shape.cornered.Corner
import com.patrykandpatryk.vico.core.component.shape.cornered.MarkerCorneredShape
import com.patrykandpatryk.vico.core.component.text.textComponent
import com.patrykandpatryk.vico.core.context.MeasureContext
import com.patrykandpatryk.vico.core.dimensions.MutableDimensions
import com.patrykandpatryk.vico.core.extension.copyColor
import com.patrykandpatryk.vico.core.marker.Marker


/**
 * Returns a Marker-instance for the graph
 */
@Composable
fun marker(): Marker = with(MaterialTheme.colorScheme) {
    getMarker(
        label = onSurface.toArgb(),
        bubble = surface.toArgb(),
        indicatorInnerColor = surface.toArgb(),
        guidelineColor = onSurface.toArgb(),
    )
}


/**
 * Creates and returns a Marker-instance
 */

fun getMarker(
    @ColorInt label: Int,
    @ColorInt bubble: Int,
    @ColorInt indicatorInnerColor: Int,
    @ColorInt guidelineColor: Int
): Marker {
    val labelBackgroundShape = MarkerCorneredShape(all = Corner.FullyRounded)
    val label = textComponent {
        color = label
        ellipsize = TextUtils.TruncateAt.END
        lineCount = 1
        padding = MutableDimensions(startDp = 8f, topDp = 4f, endDp = 8f, bottomDp = 4f)
        typeface = Typeface.MONOSPACE
        background = ShapeComponent(shape = labelBackgroundShape, color = bubble)
            .setShadow(radius = 4f, dy = 2f, applyElevationOverlay = true)
    }

    val indicatorInner = ShapeComponent(shape = Shapes.pillShape, color = indicatorInnerColor)
    val indicatorCenter = ShapeComponent(shape = Shapes.pillShape, color = Color.WHITE)
    val indicatorOuter = ShapeComponent(shape = Shapes.pillShape, color = Color.WHITE)

    val indicator = OverlayingComponent(
        outer = indicatorOuter,
        innerPaddingAllDp = 10f,
        inner = OverlayingComponent(
            outer = indicatorCenter,
            inner = indicatorInner,
            innerPaddingAllDp = 5f,
        ),
    )

    val guideline = LineComponent(
        color = guidelineColor.copyColor(alpha = .2f),
        thicknessDp = 2f,
        shape = DashedShape(
            shape = Shapes.pillShape,
            dashLengthDp = 8f,
            gapLengthDp = 4f,
        )
    )

    return object : MarkerComponent(
        label = label,
        indicator = indicator,
        guideline = guideline,
    ) {
        init {
            indicatorSizeDp = 36f
            onApplyEntryColor = { entryColor ->
                indicatorOuter.color = entryColor.copyColor(alpha=32)
                with(indicatorCenter) {
                    color = entryColor
                    //setShadow(radius = 12f, color = entryColor)
                }
            }
        }
        override fun getInsets(context: MeasureContext, outInsets: Insets) = with(context) {
            outInsets.top = label.getHeight(context) + labelBackgroundShape.tickSizeDp.pixels +
                    4f.pixels * 1.3f - 2f.pixels
        }
    }
}