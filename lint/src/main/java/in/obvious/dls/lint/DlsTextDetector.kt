@file:Suppress("UnstableApiUsage")

package `in`.obvious.dls.lint

import com.android.SdkConstants.ANDROID_URI
import com.android.SdkConstants.ATTR_FONT_FAMILY
import com.android.SdkConstants.ATTR_STYLE
import com.android.SdkConstants.ATTR_TEXT_APPEARANCE
import com.android.SdkConstants.ATTR_TEXT_COLOR
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.LayoutDetector
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.TextFormat
import com.android.tools.lint.detector.api.XmlContext
import org.w3c.dom.Element

class DlsTextDetector : LayoutDetector() {
  override fun getApplicableElements(): Collection<String> {
    return listOf(DLS_TEXT_VIEW)
  }

  override fun visitElement(context: XmlContext, element: Element) {
    if (element.hasAttributeNS(ANDROID_URI, ATTR_TEXT_APPEARANCE)) {
      reportIssue(context, element, IncorrectTextStyleWithDlsTextView)
    }

    if (element.hasAttributeNS(ANDROID_URI, ATTR_TEXT_COLOR)) {
      reportIssue(context, element, IncorrectTextColorWithDlsTextView)
    }

    if (element.hasAttributeNS(ANDROID_URI, ATTR_FONT_FAMILY)) {
      reportIssue(context, element, IncorrectFontFamilyWithDlsTextView)
    }

    if (element.hasAttribute(ATTR_STYLE)) {
      reportIssue(context, element, IncorrectStyleAttrWithDlsTextView)
    }
  }

  private fun reportIssue(context: XmlContext, element: Element, issue: Issue) {
    context.report(
        issue = issue,
        location = context.getLocation(element),
        message = issue.getExplanation(TextFormat.TEXT)
    )
  }

  companion object {

    /**
     * Fully Qualified name of the DlsTextView class.
     */
    const val DLS_TEXT_VIEW = "in.obvious.dls.typography.DlsTextView"

    val IncorrectTextStyleWithDlsTextView = Issue.create(
        id = "IncorrectTextStyleWithDlsTextView",
        briefDescription = "DlsTextView doesn't support android:textAppearance",
        explanation = "android:textAppearance is not supported. Use app:dlsTextStyle to assign the text styles provided by the design system",
        category = Category.CORRECTNESS,
        severity = Severity.ERROR,
        implementation = Implementation(DlsTextDetector::class.java, Scope.RESOURCE_FILE_SCOPE),
        androidSpecific = true
    )

    val IncorrectTextColorWithDlsTextView = Issue.create(
        id = "IncorrectTextColorWithDlsTextView",
        briefDescription = "DlsTextView doesn't support android:textColor",
        explanation = "android:textColor is not supported. Use app:dlsTextColor to assign the colors provided by the design system",
        category = Category.CORRECTNESS,
        severity = Severity.ERROR,
        implementation = Implementation(DlsTextDetector::class.java, Scope.RESOURCE_FILE_SCOPE),
        androidSpecific = true
    )

    val IncorrectFontFamilyWithDlsTextView = Issue.create(
        id = "IncorrectFontFamilyWithDlsTextView",
        briefDescription = "DlsTextView doesn't support android:fontFamily",
        explanation = "android:fontFamily is not supported. Use app:dlsTextStyle to assign the text styles provided by the design system",
        category = Category.CORRECTNESS,
        severity = Severity.ERROR,
        implementation = Implementation(DlsTextDetector::class.java, Scope.RESOURCE_FILE_SCOPE),
        androidSpecific = true
    )

    val IncorrectStyleAttrWithDlsTextView = Issue.create(
        id = "IncorrectStyleAttrWithDlsTextView",
        briefDescription = "DlsTextView doesn't support style attribute",
        explanation = "Use of style attribute is not supported. Use app:dlsTextStyle and app:dlsTextColor",
        category = Category.CORRECTNESS,
        severity = Severity.ERROR,
        implementation = Implementation(DlsTextDetector::class.java, Scope.RESOURCE_FILE_SCOPE),
        androidSpecific = true
    )
  }
}
