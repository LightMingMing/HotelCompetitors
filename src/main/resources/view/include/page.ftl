<#macro pagination>
<#if (resultPage.totalResults>resultPage.pageSize)>
<nav>
    <ul class="pagination">
        <#if resultPage.first>
            <li class="disabled"><a title="第一页"><i class="glyphicon glyphicon-fast-backward"></i></a></li>
            <li class="disabled"><a title="前一页"><i class="glyphicon glyphicon-step-backward"></i></a></li>
        <#else>
            <li>
                <a title="第一页" class="first-page">
                <#--<a title="第一页" href="${resultPage.renderUrl(1)}">-->
                    <i class="glyphicon glyphicon-fast-backward"></i>
                </a>
            </li>
            <li>
                <#--<a title="前一页" href="${resultPage.renderUrl(resultPage.previousPage)}">-->
                <a title="前一页" class="previous-page">
                    <i class="glyphicon glyphicon-step-backward"></i>
                </a>
            </li>
        </#if>

        <#if resultPage.last>
            <li class="disabled"><a title="下一页"><i class="glyphicon glyphicon-step-forward"></i></a></li>
            <li class="disabled"><a title="最后一页"><i class="glyphicon glyphicon-fast-forward"></i></a></li>
        <#else>
            <li>
                <a title="下一页" class="next-page">
                <#--<a title="下一页" href="${resultPage.renderUrl(resultPage.nextPage)}">-->
                    <i class="glyphicon glyphicon-step-forward"></i>
                </a>
            </li>

            <li>
                <a title="最后一页"  class="last-page">
                <#--<a title="最后一页"  href="${resultPage.renderUrl(resultPage.totalPage)}">-->
                    <i class="glyphicon glyphicon-fast-forward"></i>
                </a>
            </li>
        </#if>

        <li class="pageNo">
            <span class="input-append">
                <input type="text" name="pageNo" value="${resultPage.pageNo?string("#")}" class="inputPage" title="当前页"/><span class="totalPage" title="总页数">${resultPage.totalPage?string("#")}</span>
            </span>
        </li>
        <li class="visible-desktop">
            <#assign pages=[5, 10, 15, 30, 50]>
            <select name="pageSize" class="pageSize" title="页大小">
                <#list pages as page>
                    <option value="${page}" <#if page==resultPage.pageSize>selected</#if>>${page}</option>
                </#list>
            </select>
        </li>
    </ul>
</nav>
</#if>
</#macro>