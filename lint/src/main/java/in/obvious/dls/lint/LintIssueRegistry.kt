package `in`.obvious.dls.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

@Suppress("UnstableApiUsage")
class LintIssueRegistry : IssueRegistry() {
  override val api: Int
    get() = CURRENT_API

  override val minApi: Int
    get() = 1

  override val issues: List<Issue>
    get() = emptyList()
}
