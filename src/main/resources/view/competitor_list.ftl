<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <@title "竞争对手分析"/>
    <link href="/static/css/competitor.css" type="text/css" rel="stylesheet"/>
    <script src="/static/js/highcharts.js"></script>
    <script src="/static/js/charts.js"></script>
</head>
<body>
    <@nav/>
    <#if source??>
        <div class="compare-container">
            <div class="compare-inner row">
                <div class="compare-select col-md-3"></div>
                <div class="compare-list col-md-6">
                    <a href="#" class="btn compare-item" id="platformComment">平台评论数量</a>
                    <a href="#" class="btn compare-item-1" id="commentSentiment">评论情感分布</a>
                    <#--<a href="#" class="btn compare-item-2" id="commentTargetSentiment">评论目标情感分布</a>-->
                </div>
            </div>
            <div id="container" style="min-width:400px;max-width:1150px;height:400px;margin-top:10px"></div>
        </div>
        <div class="map_side">
            <div class="side_inner" style="height: 680px;">
                <div class="side_source">
                    <p class="source_name"><a class="name" title="${source.phone}">${source.name}</a></p>
                    <p class="item_phone hidden" id="source_phone">${source.phone}</p>
                </div>
                <div class="side_list" style="height: 650px;">
                    <div class="side_list_box">
                    <#list hotelList as hotel>
                        <div class="side_list_item">
                            <div class="list_item_cont">
                                <span class="icon_num">${hotel_index + 1}</span>
                                <p class="item_title">
                                    <a class="name" title="${hotel.phone}">${hotel.name}</a>
                                </p>
                                <p class="hidden item_phone">${hotel.phone}</p>
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
