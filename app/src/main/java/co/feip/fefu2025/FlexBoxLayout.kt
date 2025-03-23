package co.feip.fefu2025

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import kotlin.math.max

class FlexBoxLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val availableWidth = MeasureSpec.getSize(widthMeasureSpec)
        var curLeft = marginLeft + paddingLeft
        var curTop = marginTop + paddingTop
        var maxLeft = 0
        var curMaxHeight = 0
        var childState = 0

        for (i in 0..<childCount) {
            val child = getChildAt(i)
            if (child.isGone) continue
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            if (curLeft + child.measuredWidth > availableWidth - marginLeft - marginRight - paddingLeft - paddingRight) {
                maxLeft = max(maxLeft, curLeft)
                curLeft = marginLeft + paddingLeft
                curTop += curMaxHeight
                curMaxHeight = 0
            }
            curLeft += child.measuredWidth
            curMaxHeight = max(curMaxHeight, child.measuredHeight)
            childState = combineMeasuredStates(childState, child.measuredState)
        }
        maxLeft = max(maxLeft, curLeft)
        curTop += curMaxHeight + marginBottom + paddingBottom

        maxLeft = max(maxLeft, suggestedMinimumWidth)
        curTop = max(curTop, suggestedMinimumHeight)

        setMeasuredDimension(
            resolveSizeAndState(maxLeft, widthMeasureSpec, childState), resolveSizeAndState(
                curTop, heightMeasureSpec, childState shl MEASURED_HEIGHT_STATE_SHIFT
            )
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var curLeft = marginLeft + paddingLeft
        var curTop = marginTop + paddingTop
        var curMaxHeight = 0

        for (i in 0..<childCount) {
            val child = getChildAt(i)
            if (child.isGone) continue
            if (curLeft + child.measuredWidth > measuredWidth - marginLeft - marginRight - paddingLeft - paddingRight) {
                curLeft = marginLeft + paddingLeft
                curTop += curMaxHeight
                curMaxHeight = 0
            }
            child.layout(
                curLeft, curTop, curLeft + child.measuredWidth, curTop + child.measuredHeight
            )
            curLeft += child.measuredWidth
            curMaxHeight = max(curMaxHeight, child.measuredHeight)
        }
    }
}