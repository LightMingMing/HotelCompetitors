<#macro title title="首页">
    <meta charset="utf-8">
    <title>${title}</title>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="/static/css/base.css" rel="stylesheet">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/bootstrap.js"></script>
    <script>

        $(document).ready(function(){
            var form = $("form.query");
            var inputPage = $(".pageNo .inputPage");
            inputPage.change(function (){ form.submit()});

            $("select.pageSize").change(function() {submitForm(1)});
            $("a.first-page").click(function(){ submitForm(1)});
            $("a.previous-page").click(function() { submitForm(parseInt(inputPage.val())-1)});
            $("a.next-page").click(function() { submitForm(parseInt(inputPage.val())+1)});
            $("a.last-page").click(function() { submitForm($("span.totalPage").text())});
            $("button.search").click(function() {submitForm(1)});

            var submitForm = function(pageNo) { inputPage.val(pageNo); form.submit()};

        })
    </script>
</#macro>