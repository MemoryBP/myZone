$(function () {
    var page=$(
        '<div id="prizeConfig" class="modal fade" tabindex="-1">\
        <div class="modal-dialog" style="width: 80%;">\
        <div class="modal-content">\
        <div class="modal-body">\
        <button class="close" data-dismiss="modal"><span>&times;</span></button>\
    </div>\
    <div class="modal-title">\
        <h1 class="text-center">奖品模板配置</h1>\
        </div>\
        <div class="modal-body">\
        <table class="table">\
        <thead class="bg-primary">\
        <th>编号</th>\
        <th>代号</th>\
        <th>奖品名称</th>\
        <th>是否启用</th>\
        <th>备注</th>\
        <th>总量</th>\
        <th>剩余</th>\
        <th>概率</th>\
        <th>开始日期</th>\
        <th>结束日期</th>\
        <th>创建日期</th>\
        <th>更新日期</th>\
        </thead>\
        <tbody id="prizeList">\
        </tbody>\
        </table>\
        </div>\
        </div>\
        </div>\
        </div>'
    );
    $('body').append(page)
})