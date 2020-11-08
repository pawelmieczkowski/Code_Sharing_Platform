<html>
<head>
    <title>Latest</title>
    <link rel="stylesheet" href="/css/code.css">
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>

    <#list model["codeSnippets"] as codeSnippet>
        <span id="load_date">
               ${codeSnippet.date}
        </span>
        <pre id="code_snippet">
            <code>
                ${codeSnippet.code}
            </code>
        </pre>
    </#list>
</body>
</html>