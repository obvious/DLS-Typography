package `in`.obvious.dls.typography

import `in`.obvious.dls.typography.TextColor.TEXT_PRIMARY
import `in`.obvious.dls.typography.TextStyle.HEADER4
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.use
import androidx.core.widget.TextViewCompat
import com.google.android.material.textview.MaterialTextView
import kotlin.DeprecationLevel.HIDDEN

class DlsTextView(
    context: Context,
    attrs: AttributeSet? = null
) : MaterialTextView(context, attrs) {
  init {
    val defaultTextStyleAttrIndex = TextStyle.VALUES.indexOf(HEADER4)
    val defaultTextColorAttrIndex = TextColor.VALUES.indexOf(TEXT_PRIMARY)

    context.theme.obtainStyledAttributes(attrs, R.styleable.DLSTextView, 0, 0).use { ta ->
      val textStyleAttrIndex = ta.getInt(R.styleable.DLSTextView_dlsTextStyle, defaultTextStyleAttrIndex)
      setTextStyle(TextStyle.VALUES[textStyleAttrIndex])

      val textColorAttrIndex = ta.getInt(R.styleable.DLSTextView_dlsTextColor, defaultTextColorAttrIndex)
      setTextColor(TextColor.VALUES[textColorAttrIndex])
    }
  }

  fun setTextStyle(textStyle: TextStyle) {
    val resId = obtainAttributeResId(textStyle.typographyAttr)
    TextViewCompat.setTextAppearance(this, resId)
  }

  fun setTextColor(textColor: TextColor) {
    val resId = obtainAttributeResId(textColor.colorAttr)
    super.setTextColor(ContextCompat.getColor(context, resId))
  }

  @Deprecated(message = "Not supported by Design System", level = HIDDEN)
  override fun setTextColor(color: Int) {
    super.setTextColor(color)
  }

  @Deprecated(message = "Not supported by Design System", level = HIDDEN)
  override fun setTextColor(colors: ColorStateList?) {
    super.setTextColor(colors)
  }

  @Deprecated(message = "Not supported by Design System", level = HIDDEN)
  override fun setTextAppearance(resId: Int) {
    super.setTextAppearance(resId)
  }

  /**
   * For given [attribute], extracts the resource value for it from current Theme.
   */
  private fun obtainAttributeResId(@AttrRes attribute: Int): Int {
    return context.theme.obtainStyledAttributes(intArrayOf(attribute)).use { ta ->
      ta.getResourceId(0, 0)
    }
  }
}
