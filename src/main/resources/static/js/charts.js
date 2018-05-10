$(document).ready(function(){

    var phoneList = [];
    var itemPhones = $("p.item_phone");
    for (var i = 0; i < itemPhones.length; i ++) {
        // alert(itemPhones[i].innerText);
        phoneList.push(itemPhones[i].innerText)
    }

    var platformCommentChart = {
        chart: {
            type: 'column'
        },
        title: {
            text: '平台评论数量对比'
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: [],
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
        series: []
    };

    $("#platformComment").click(function() {
        if (platformCommentChart.series.length === 0) {
            $.ajax({
                type:'post',
                data:JSON.stringify(phoneList),
                url: '/competitor/platformCommentStatistics',
                contentType: 'application/json',
                success: function(data) {
                    platformCommentChart.series = data.series;
                    platformCommentChart.xAxis.categories = data.categories;
                    Highcharts.chart('container',platformCommentChart);
                },
                error: function(xhr) {
                    alert(xhr.status + " " + xhr.statusText)
                }
            });

        } else {
            Highcharts.chart('container', platformCommentChart)
        }
    });

    var commentSentimentChart =  {
        chart: {
            type: 'column'
        },
        title: {
            text: '评论观点情感分布'
        },
        xAxis: {
            categories: []
        },
        yAxis: {
            min: 0,
            title: {
                text: '观点总量'
            }
        },
        tooltip: {
            pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b>' +
            '({point.percentage:.0f}%)<br/>',
            //:.0f 表示保留 0 位小数，详见教程：https://www.hcharts.cn/docs/basic-labels-string-formatting
            shared: true
        },
        plotOptions: {
            column: {
                stacking: 'percent'
            }
        },
        series: []
    };

    $("#commentSentiment").click(function() {
        if (commentSentimentChart.series.length === 0) {
            $.ajax({
                type:'post',
                data:JSON.stringify(phoneList),
                url: '/competitor/commentSentimentStatistics',
                contentType: 'application/json',
                success: function(data) {
                    commentSentimentChart.xAxis.categories = data.categories;
                    commentSentimentChart.series = data.series;
                    Highcharts.chart('container',commentSentimentChart);
                },
                error: function(xhr) {
                    alert(xhr.status + " " + xhr.statusText)
                }
            });
        } else {
            Highcharts.chart('container', commentSentimentChart)
        }
    });

});