package com.example.lintrule;


import com.android.tools.lint.client.api.UElementHandler;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.psi.PsiMethod;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UCallExpression;

import java.util.Arrays;
import java.util.List;

public class LogDetector extends Detector implements Detector.UastScanner  {
    public static final Issue ISSUE = Issue.create(
            "LogUse", // 问题id
            "避免直接使用android.util.Log", // 问题简要描述
            "避免直接使用android.util.log,请使用xxx 代替", // 问题解释，解决方案
            Category.SECURITY,// 问题所属类型-安全
            5, // 问题优先级 0 - 10
            Severity.ERROR, // 问题等级
            new Implementation(LogDetector.class, Scope.JAVA_FILE_SCOPE)
    );

    @Nullable
    @Override
    public UElementHandler createUastHandler(@NotNull JavaContext context) {
        return super.createUastHandler(context);
    }

    /**
     * 获取方法的筛选条件
     * @return
     */
    @Override
    public List<String> getApplicableMethodNames() {
        return Arrays.asList("v", "d", "i", "w", "e");
    }

    /**
     * 搜索到方法的回调
     * @param context
     * @param node
     * @param method
     */
    @Override
    public void visitMethod(@NotNull JavaContext context, @NotNull UCallExpression node, @NotNull PsiMethod method) {
        if (context.getEvaluator().isMemberInClass(method, "android.util.Log")) {
            context.report(ISSUE, node, context.getLocation(node), "请勿直接调用Log，应该使用统一Log工具类"); // 问题上报
        }
    }
}
