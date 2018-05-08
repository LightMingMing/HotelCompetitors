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

            var provinceSelect = $("#province");
            var citySelect = $("#city");
            provinceSelect.append("<option value=''>选择省份</option>");
            citySelect.append("<option value=''>选择城市</option>");

            var provinceCode = "${(resultPage.entity.provinceCode)!""}";
            var cityCode = "${(resultPage.entity.cityCode)!""}";

            $.ajax({
                type:'get',
                url:'/region',
                success:function(data) {
                    // alert(JSON.stringify(data));
                    for (var i = 0; i < data.length; i ++) {
                        if (provinceCode === data[i].code)
                            provinceSelect.append("<option value=" + data[i].code + " selected>" + data[i].name + "</option>");
                        else
                            provinceSelect.append("<option value=" + data[i].code + ">" + data[i].name + "</option>");
                    }
                    if (provinceCode !== "")
                        loadCity(provinceCode);
                },
                error:function(xhr) {
                    alert(xhr.status + " " + xhr.statusText)
                }
            });

            provinceSelect.change(function() {
                citySelect.empty();
                citySelect.append("<option value=''>选择城市</option>");
                if (provinceSelect.val() !== "") {
                    loadCity(provinceSelect.val());
                }
            });


            var loadCity = function (provinceCode) {
                $.ajax({
                    type:'get',
                    url:'/region/'+ provinceCode,
                    success:function(data) {
                        for (var i = 0; i < data.length; i ++) {
                            var code = data[i].code;
                            var name = data[i].name;
                            if (cityCode === code) {
                                citySelect.append("<option value=" + code + " selected>" + name + "</option>");
                            } else {
                                citySelect.append("<option value=" + code + ">" + name + "</option>");
                            }

                        }
                    },
                    error:function(xhr) {
                        alert(xhr.status + " " + xhr.statusText)
                    }
                });
            };

            var submitForm = function(pageNo) { inputPage.val(pageNo); form.submit()};

        })
    </script>
</#macro>