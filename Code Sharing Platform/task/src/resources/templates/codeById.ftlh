<html>
<head>
    <title>Code</title>
    <link rel="stylesheet" href="/css/code.css">
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <span id="load_date">
        ${model.codeSnippet.date}
    </span>
    <#if model.codeSnippet.time!=0>
    <span id="time_restriction">
        The code will be available for ${model.codeSnippet.time} seconds
    </span>
    </#if>
    <#if model.codeSnippet.valueRestrictionApplied==true>
    <span id="views_restriction">
        ${model.codeSnippet.views} more views allowed
    </span>
    </#if>
    <pre id="code_snippet">
        <code>
        ${model.codeSnippet.code}
        </code>
    </pre>
</body>
</html>