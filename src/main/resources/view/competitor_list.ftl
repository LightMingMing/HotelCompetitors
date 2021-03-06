<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <@title "竞争对手分析"/>
    <link href="/static/css/competitor.css" type="text/css" rel="stylesheet"/>
    <script src="/static/js/highcharts.js"></script>
    <script src="/static/js/highcharts-more.js"></script>
    <script src="/static/js/charts.js"></script>
</head>
<body>
    <@nav/>
    <#if source??>
        <div class="compare-container">
            <div class="compare-inner row">
                <div class="compare-list col-md-4 col-md-offset-2">
                    <a href="#" class="btn compare-item" id="platformComment">平台评论数量</a>
                    <a href="#" class="btn compare-item-1" id="platformScore">平台平均得分</a>
                    <a href="#" class="btn compare-item-2" id="commentSentiment">评论情感分布</a>
                    <#--<a href="#" class="btn compare-item-2" id="commentTargetSentiment">评论目标情感分布</a>-->
                </div>
                <div class="col-md-2">
                    <select class="form-control" id="compare_option">
                        <option value="1">评论目标情感分布</option>
                        <option value="2">平台得分对比</option>
                    </select>
                </div>
            </div>
            <div id="container" style="min-width:400px;max-width:1150px;height:400px;margin-top:10px"></div>
        </div>
        <div class="map_side">
            <div class="side_inner" style="height: 716px;">
                <div class="side_source">
                    <p class="source_name"><a class="name" title="${source.phone}">${source.name}</a></p>
                    <p class="item_phone hidden" id="source_phone">${source.phone}</p>
                </div>
                <div class="side_list" style="height: 630px;">
                    <div class="side_list_box" id="hotel_list">
                    <#--<#list hotelList as hotel>
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
                    </#list>-->
                    </div>
                </div>
                <nav>
                    <ul class="pager" id="simple_pager">
                    </ul>
                </nav>
            </div>
        </div>
    <#else>
        无相关酒店
    </#if>
</body>
</html>
