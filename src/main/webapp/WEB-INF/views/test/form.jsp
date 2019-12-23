<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/easyui/plugin/codemirror-5.49.2/lib/codemirror.css">
    <link rel="stylesheet" href="/easyui/plugin/codemirror-5.49.2/theme/monokai.css">
    <script type="text/javascript" src="/easyui/plugin/codemirror-5.49.2/lib/codemirror.js"></script>
    <script type="text/javascript" src="/easyui/plugin/codemirror-5.49.2/mode/javascript/javascript.js"></script>
    <script type="text/javascript" src="/easyui/plugin/codemirror-5.49.2/mode/css/css.js"></script>
    <script type="text/javascript" src="/easyui/plugin/codemirror-5.49.2/mode/htmlmixed/htmlmixed.js"></script>
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
</head>
<body>
<div>
    <div class="code-editor">
        <!--选择脚本风格代码-->
        <div class="controls">
            <span>主题：</span>
            <select id='select'>
                <option>default</option>
                <option>3024-night</option>
                <option>erlang-dark</option>
                <option selected>monokai</option>
            </select>
        </div>
    </div>
    <form action="/test/store" method="post">
        <div>
            <label for="editor">内容</label>
            <textarea id="editor" name="content" style="width: 500px; height: 450px">${content}</textarea>
        </div>
        <div>
            <button type="submit">提交</button>
        </div>
    </form>
</div>
<script>
    var editor = CodeMirror.fromTextArea($("#editor")[0], {
        styleActiveLine: true,  // 当前行背景高亮
        lineWrapping: true,  //是否强制换行
        theme: 'monokai',  // 使用monokai模版
        mode: 'text/html',
        selectionPointer: true,
        lineNumbers: false,
        matchBrackets: true,
        indentUnit: 4,
        indentWithTabs: true
    });
    //选择界面风格JS
    $('#select').change(function () {
        var theme = $('#select').val();
        editor.setOption("theme", theme);
    });
</script>
</body>
</html>
