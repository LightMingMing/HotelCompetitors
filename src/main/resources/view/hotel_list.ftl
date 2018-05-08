<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <@title "酒店列表"/>
    </head>

    <body>
        <@nav/>
        <div class = "container-fluid">
            <form action="/hotel" method="post" class="form-horizontal query">
                <div class="row form-row">
                    <div class="control-group">
                        <label  class="col-md-1 control-label" for="province">城市</label>
                        <div class="col-md-1 span" style="padding-right:5px;">
                            <#--<input type="text" class="form-control" id="province" name="entity.provinceCode" value="${(resultPage.entity.provinceCode)!}">-->
                            <select class="form-control" id="province" name="entity.provinceCode">
                            </select>
                        </div>
                        <div class="col-md-1 span" style="padding-left:5px;">
                            <#--<input type="text" class="form-control" id="city" name="entity.cityCode" value="${(resultPage.entity.cityCode)!}">-->
                            <select class="form-control" id="city" name="entity.cityCode">
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="col-md-1 col-md-offset-1 control-label" for="phone">电话</label>
                        <div class="col-md-2 span">
                            <input type="text" class="form-control" id="phone" name="entity.phone" value="${(resultPage.entity.phone)!}">
                        </div>
                    </div>
<#--
                    <div class="control-group">
                        <label class="col-md-1 control-label" for="hotelCode">代码</label>
                        <div class="col-md-2 span">
                            <input type="text" class="form-control" id="hotelCode" name="entity.code" value="${(resultPage.entity.code)!}">
                        </div>
                    </div>

                    <div class="control-group">
                        <label  class="col-md-1 col-md-offset-1 control-label" for="mobile">手机</label>
                        <div class="col-md-2 span">
                            <input type="text" class="form-control" id="mobile" name="entity.mobile" value="${(resultPage.entity.mobile)!}">
                        </div>
                    </div>
                -->
                </div>

<#--
                <div class="row form-row">
                    <div class="control-group">
                        <label  class="col-md-1 control-label" for="province">城市</label>
                        <div class="col-md-1 span" style="padding-right:5px;">
                            &lt;#&ndash;<input type="text" class="form-control" id="province" name="entity.provinceCode" value="${(resultPage.entity.provinceCode)!}">&ndash;&gt;
                            <select class="form-control" id="province" name="entity.provinceCode">
                            </select>
                        </div>
                        <div class="col-md-1 span" style="padding-left:5px;">
                            &lt;#&ndash;<input type="text" class="form-control" id="city" name="entity.cityCode" value="${(resultPage.entity.cityCode)!}">&ndash;&gt;
                            <select class="form-control" id="city" name="entity.cityCode">
                            </select>
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
                        <th>编号</th>
                        <th>代码</th>
                        <th>名称</th>
                        <th>电话</th>
                        <#--<th>手机</th>-->
                        <th>地址</th>
                        <th>经度</th>
                        <th>纬度</th>
                    </tr>
                </thead>
                <tbody>
                    <#list resultPage.result as hotel>
                    <tr>
                        <td> ${hotel.id}
                        <td> ${hotel.code}
                        <td> ${hotel.name}
                        <td> ${hotel.phone!""}
                        <#--<td> ${hotel.mobile!""}-->
                        <td> ${hotel.address!""}
                        <td> ${hotel.longitude}
                        <td> ${hotel.latitude}
                    </tr>
                </#list>
                </tbody>
            </table>
            <@pagination/>
            </form>
        </div>
    </body>
</html>
