package com.example.lintrule;

import com.android.tools.lint.detector.api.ApiKt;
import com.android.tools.lint.detector.api.Issue;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class IssueRegistry extends com.android.tools.lint.client.api.IssueRegistry {

    /**
     * 返回问题列表
     * @return
     */
    @NotNull
    @Override
    public List<Issue> getIssues() {
        return new ArrayList<Issue>() {{
            add(LogDetector.ISSUE);
        }};
    }

    @Override
    public int getApi() {
        return ApiKt.CURRENT_API;
    }
}
