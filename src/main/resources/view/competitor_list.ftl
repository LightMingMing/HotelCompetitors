<!DOCTYPE html>
<html lang="zh-CN">
<head>
        <@title "竞争对手分析"/>
</head>

<body>
    <@nav/>
    <div class = "container-fluid">
                            <#list hotelList as hotel>
                                <p>
                                    <span> ${hotel.name}
                                    <span> ${hotel.phone!""}
                                    <#--<td> ${hotel.mobile!""}-->
                                    <span> ${hotel.address!""}
                                    <#--<span> ${hotel.longitude}-->
                                    <#--<span> ${hotel.latitude}-->
                                </p>
                            </#list>
    </div>
</body>
</html>
