package co.feip.fefu2025.presentation.details.utils

import android.content.Context
import android.content.res.Resources

fun isDrawableResourceValid(context: Context, resId: Int): Boolean {
    return try {
        context.resources.getDrawable(resId, null)
        true
    } catch (e: Resources.NotFoundException) {
        false
    }
}