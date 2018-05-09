<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <@title "竞争对手分析"/>
    <link href="/static/css/competitor.css" type="text/css" rel="stylesheet"/>
    <script src="/static/js/highcharts.js"></script>
</head>
<body>
    <@nav/>
    <#if source??>
        <div class="compare-container">
            <div class="compare-inner row">
                <div class="compare-select col-md-3">
<#--
                    <select class="form-control">
                        <#list hotelList as hotel>
                            <option value="${hotel.phone}">${hotel.name}</option>
                        </#list>
                    </select>
-->
                </div>
                <div class="compare-list col-md-6">
                    <a href="#" class="btn compare-item" id="platformComment">平台评论数量</a>
                    <a href="#" class="btn compare-item-1" id="commentSentiment">评论情感分布</a>
                    <a href="#" class="btn compare-item-2" id="commentTargetSentiment">评论目标情感分布</a>
                </div>
            </div>
            <div id="container" style="min-width:400px;max-width:1200px;height:400px">
                <script>
                    var phoneList = [];
                    phoneList.push('${source.phone}');
                        <#list hotelList as hotel>
                            phoneList.push('${hotel.phone}');
                        </#list>
                    var returnData;
                    $.ajax({
                        type:'post',
                        data:JSON.stringify(phoneList),
                        url: '/competitor/platformCommentStatistics',
                        contentType: 'application/json',
                        success: function(data) {
                            returnData = data;

                            var chart = Highcharts.chart('container',{
                                chart: {
                                    type: 'column'
                                },
                                title: {
                                    text: '各平台评论数量'
                                },
                                subtitle: {
                                    text: '${source.name}竞争分析'
                                },
                                xAxis: {
                                    categories: returnData.categories,
                                    crosshair: true
                                },
                                yAxis: {
                                    min: 0,
                                    title: {
                                        text: '评论数量 (条)'
                                    }
                                },
                                tooltip: {
                                    // head + 每个 point + footer 拼接成完整的 table
                                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                                    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                                    '<td style="padding:0"><b>{point.y} 条</b></td></tr>',
                                    footerFormat: '</table>',
                                    shared: true,
                                    useHTML: true
                                },
                                plotOptions: {
                                    column: {
                                        borderWidth: 0
                                    }
                                },
                                series: returnData.series
                            });
                        },
                        error: function(xhr) {
                            alert(xhr.status + " " + xhr.statusText)
                        }
                    });

                </script>
            </div>
        </div>
        <div class="map_side">
            <div class="side_inner" style="height: 680px;">
                <div class="side_source">
                    <p class="source_name">${source.name}</p>
                </div>
                <div class="side_list" style="height: 650px;">
                    <div class="side_list_box">
                    <#list hotelList as hotel>
                        <div class="side_list_item">
                            <div class="list_item_cont">
                                <span class="icon_num">${hotel_index + 1}</span>
                                <p class="item_title">
                                    ${hotel.name}
                                </p>
                                <p class="item_address">${hotel.address}</p>
                                <p class="item_c"><span class="item_comment_number">${commentNumberList[hotel_index]}</span>条点评</p>
                            </div>
                        </div>
                    </#list>
                    </div>
                </div>
            </div>
        </div>
    <#else>
        无相关酒店
    </#if>
</body>
</html>
