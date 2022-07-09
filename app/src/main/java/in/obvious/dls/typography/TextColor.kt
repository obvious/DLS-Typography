package `in`.obvious.dls.typography

import androidx.annotation.AttrRes

enum class TextColor(@AttrRes val colorAttr: Int) {
  TEXT_PRIMARY(R.attr.colorTextPrimary),
  TEXT_SECONDARY(R.attr.colorTextSecondary),
  TEXT_TERTIARY(R.attr.colorTextTertiary),
  TEXT_DISABLED(R.attr.colorTextDisabled),
  ;

  internal companion object {
    val VALUES = values()
  }
}
