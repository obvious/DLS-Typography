package `in`.obvious.dls.typography

import androidx.annotation.AttrRes

enum class TextStyle(@AttrRes val typographyAttr: Int) {
  HEADER1(R.attr.textAppearanceHeader1),
  HEADER2(R.attr.textAppearanceHeader2),
  HEADER3(R.attr.textAppearanceHeader3),
  HEADER4(R.attr.textAppearanceHeader4),
  ;

  internal companion object {
    val VALUES = values()
  }
}
