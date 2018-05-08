<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <@title "评论分析"/>
    </head>
    <body>
        <@nav></@nav>
        <div class = "container-fluid">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>编号</th>
                        <th>评论ID</th>
                        <#--<th>电话</th>-->
                        <th>分析目标</th>
                        <th>评论内容</th>
                        <#--<th>评分</th>-->
                    </tr>
                </thead>
                <tbody>
                    <#list resultPage.result as commentAnalysis>
                    <tr>
                        <td class="col-md-1"> ${commentAnalysis.id}
                        <td class="col-md-1"> ${commentAnalysis.commentId}
                        <#--<td> ${commentAnalysis.phone}-->
                        <td class="col-md-2"> ${commentAnalysis.analysisTargetName!""}</td>
                        <td> <span class="sentiment${commentAnalysis.score}" title="评分:${commentAnalysis.score}">${commentAnalysis.content}</span></td>
                        <#--<td> ${commentAnalysis.score}-->
                    </tr>
                    </#list>
                </tbody>
            </table>
            <@pagination></@pagination>
        </div>
    </body>
</html>
