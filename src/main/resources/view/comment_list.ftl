<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <@title "评论"/>
    </head>
    <body>
        <@nav/>
        <div class = "container-fluid">
            <form method="post" action="/comment" class="form-horizontal query">
                <div class="row form-row">
                    <div class="control-group">
                        <label class="col-md-1 control-label" for="platform">平台</label>
                        <div class="col-md-2 span">
                            <#--<input type=+text" class="form-control" id="platform" name="entity.webId" value="${(resultPage.entity.webId)!}">-->
                            <#assign platforms=["携程", "艺龙", "美团", "去哪儿", "阿里旅行", "大众点评"]>
                            <select name="entity.webId" id="platform" class="form-control">
                                <option value="">平台选择</option>
                                <#list platforms as platform>
                                    <#if (resultPage.entity.webId)?? && resultPage.entity.webId==platform_index + 1>
                                        <option value="${platform_index + 1}" selected>${platform}</option>
                                    <#else>
                                        <option value="${platform_index + 1}">${platform}</option>
                                    </#if>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="col-md-1 col-md-offset-1 control-label" for="phone">电话</label>
                        <div class="col-md-2 span">
                            <input type="text" class="form-control" id="phone" name="entity.phone" value="${(resultPage.entity.phone)!}">
                        </div>
                    </div>
                    <div class="control-group">
                        <label  class="col-md-1 col-md-offset-1 control-label" for="date">日期</label>
                        <div class="col-md-2 span">
                        <#--value="${(resultPage.entity.date)!}"-->
                            <input type="date" class="form-control" id="date" name="entity.date" value="<#if (resultPage.entity.date)??>${(resultPage.entity.date)?date?string('yyyy-MM-dd')}</#if>">
                        </div>
                    </div>
<#--
                    <div class="control-group">
                        <label  class="col-md-1 col-md-offset-1 control-label" for="hotelId">酒店ID</label>
                        <div class="col-md-2 span">
                            <input type="text" class="form-control" id="hotelId" name="entity.hotelId" value="${(resultPage.entity.hotelId)!}">
                        </div>
                    </div>
-->
                </div>
<#--
                <div class="row form-row">
                    <div class="control-group">
                        <label  class="col-md-1 control-label" for="score">评分</label>
                        <div class="col-md-2 span">
                            <input type="text" class="form-control" id="score" name="entity.score" value="${(resultPage.entity.score)!}">
                        </div>
                    </div>
                </div>
-->
                <div class="row fold">
                    <span>
                        <i class="glyphicon glyphicon-chevron-up"></i>
                    </span>
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary search">查 询</button>
                    <button type="reset" class="btn btn-default reset">重 置</button>
                </div>

                <table class="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <#--<th>编号</th>-->
                            <th>平台</th>
                            <th>酒店名</th>
                            <th>电话</th>
                            <#--<th>平均评分</th>-->
                            <#--<th>用户</th>-->
                            <th>评论时间</th>
                            <th>评分</th>
                            <th>评论内容</th>
                            <th>评论分析</th>

                        <#--
                            <th>酒店回复</th>
                            <th>情感倾向</th>
                        -->
                        </tr>
                    </thead>
                    <tbody>
                        <#list resultPage.result as comment>
                        <tr>
                            <#--<td class="col-ls-1"> ${comment.id}</td>-->
                            <td class="col-md-1"> ${comment.platformName}
                            <td class="col-md-2"> ${comment.hotelName}
                            <td class="col-md-1"> ${comment.phone}
                            <#--<td class="col-md-1"> ${comment.hotelScore}-->
                            <#--<td class="col-md-1"> ${comment.userName}-->
                            <td class="col-md-1"> ${comment.date}</td>
                            <td class="col-md-1"> ${comment.score}</td>


                            <td class="left col-md-3 comment">
                                <#list comment.sentimentAnalysisList as sentimentAnalysis>
                                    <span class="sentiment${sentimentAnalysis.sentimentValue}">${sentimentAnalysis.content}</span>
                                </#list>
                            </td>


                            <td class="innerTable">
                                <table class="table table-bordered">
    <#--                                <tr>
                                        <td colspan="3" class="left">
                                            <#list comment.sentimentAnalysisList as sentimentAnalysis>
                                                <span class="sentiment${sentimentAnalysis.sentimentValue}">${sentimentAnalysis.content}</span>
                                            </#list>
                                        </td>
                                    </tr>-->
                                <#list comment.sentimentAnalysisList as sentimentAnalysis>
                                    <tr>
                                        <td><span class="sentiment${sentimentAnalysis.sentimentValue}">${sentimentAnalysis.content}</span></td>
                                        <td class="col-sm-2">
                                            <#--${sentimentAnalysis.targetName!sentimentAnalysis.target!""}-->
                                            <#list sentimentAnalysis.targetNames as targetNames>${targetNames} </#list>
                                        </td>
                                        <td class="col-sm-2">${sentimentAnalysis.sentimentValue}</td>
                                </#list>
                                </table>
                            </td>
                        <#--${comment.XML} ${comment.content!""}-->
                        <#--
                            <td> ${comment.resonse!""}</td>
                            <td> ${comment.emotionStatus}</td>
                        -->
                        </tr>
     <#--                   <tr>
                            <td colspan="4">
                                <#list comment.sentimentAnalysisList as sentimentAnalysis>
                                    <span class="sentiment${sentimentAnalysis.sentimentValue}">${sentimentAnalysis.content}</span>
                                </#list>
                            </td>
                        </tr>-->
                        </#list>
                    </tbody>
                </table>
                <@pagination/>
            </form>
        </div>
    </body>
</html>
