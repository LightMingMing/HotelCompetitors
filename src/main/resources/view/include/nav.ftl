<#macro nav>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">HOTEL</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <#if (resultPage.controllerName)??>
                    <li class=""><a href="/">首页</a></li>
                    <li class="<#if resultPage.controllerName=='hotel'>active</#if>"><a href="../hotel">酒店列表</a></li>
                    <li class="<#if resultPage.controllerName=='comment'>active</#if>"><a href="../comment">酒店评论</a></li>
                    <#--<li class=""><a href="../competitor">竞争对手</a></li>-->
                    <#--<li class="<#if resultPage.controllerName=='commentAnalysis'>active</#if>"><a href="../commentAnalysis">评论分析</a></li>-->
                <#--<#elseif (controllerName)??>
                    <li class=""><a href="/">首页</a></li>
                    <li class=""><a href="../hotel">酒店列表</a></li>
                    <li class=""><a href="../comment">酒店评论</a></li>
                    <li class="active"><a href="../competitor">竞争对手</a></li>-->
                <#else>
                    <li class="active"><a href="/">首页</a></li>
                    <li class=""><a href="../hotel">酒店列表</a></li>
                    <li class=""><a href="../comment">酒店评论</a></li>
                    <#--<li class=""><a href="../competitor">竞争对手</a></li>-->
                    <#--<li class=""><a href="../commentAnalysis">评论分析</a></li>-->
                </#if>
            </ul>
            <form class="navbar-form navbar-left" action="../competitor" method="get">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="竞争对手查询" <#if phone??>value="${phone}"</#if> name="phone">
                </div>
                <button type="submit" class="btn"> 查&nbsp;询 </button>
            </form>
            <#--<button type="button" class="navbar-right btn btn-default navbar-btn" style="margin-right: 1em;"><a href="#"></a></button>-->
        </div>

    </div>
</nav>
</#macro>