$(function () {
    initATarget();
    commonCmdBind();
    commonGridClickBind();
});

//主菜单AJAX加载到主区域
function initATarget() {
    $('#mainMenu').find("a[uri]").each(function () {
        $(this).unbind('click').click(function (event) {
            var tabs = $("#tabs");
            var $this = $(this);
            var uri = $this.attr('uri');
            var title = $this.context.innerText;
            var tab = tabs.tabs("getTab", title);
            if (tab) {
                tabs.tabs("select", title);
            } else {
                tabs.tabs('add', {
                    title: title,
                    href: uri,
                    closable: true,
                    bodyCls: "content"
                });
            }
        });
    });
}

//延迟2秒展示后台主页
setTimeout(function () {
    $('#loading').fadeOut(200);
}, 2000);

//通用tableGroup按钮点击事件监听
function commonCmdBind() {
    $('body').on('click', ".tableGroup a[data-cmd]", function () {
        var clickTarget = $(this);
        if (clickTarget.hasClass('l-btn-disabled')) return;
        var cmd = $(clickTarget).data('cmd');
        var grid = $(clickTarget).closest('.tableGroup').find('.easyui-datagrid');
        var width = clickTarget.attr('width');
        var height = clickTarget.attr('height');
        var title = clickTarget.attr('title');
        var msg = clickTarget.attr('msg');
        var error = clickTarget.attr('error');
        var params = {};
        if (width) {
            width.replace('px', '');
            params.width = width;
        }
        if (height) {
            height.replace('px', '');
            params.height = height;
        }
        if (title) {
            params.title = title;
        }
        if (msg) {
            params.msg = msg;
        }
        if (error) {
            params.error = error;
        }
        if ($(clickTarget).attr('onclick') && '' != $(clickTarget).attr('onclick')) {
        } else {
            if (window[cmd]) {
                window[cmd](clickTarget);
            } else if (commonCmd[cmd]) {
                commonCmd[cmd](grid, clickTarget, params);
            } else {
                console.error("[" + cmd + "]命令无处理函数,请实现" + cmd + "(clickTarget)方法，或绑定onclick处理方法");
            }
        }


    });
}

//通用tableGroup工具栏按钮控制
function commonGridClickBind() {
    $('body').on('click', ".datagrid", function () {
        var clickTarget = $(this);
        var grid = clickTarget.closest('.tableGroup').find('.easyui-datagrid');
        try {
            if (grid) {
                var options = grid.datagrid('options');
                if (options) {
                    var row = grid.datagrid('getSelected');
                    var toolbarDiv = $(options.toolbar);
                    if (row) {//选中
                        $(toolbarDiv).find('[mustsel]').linkbutton('enable');
                    } else {//未选中
                        $(toolbarDiv).find('[mustsel]').linkbutton('disable');
                    }
                }
            }
        } catch (e) {
        }
    });
}

//通用表格命令处理函数
var commonCmd = {
    add: function (grid, clickTarget, params) {
        var editWindow = $('<div></div>');
        var urlPrefix = MXF.getPrefix(grid);
        editWindow.appendTo('body');
        var _width = params.width || 600;
        var _height = params.height || 450;
        var _title = params.title || 'add'
        editWindow.window({
            title: _title,
            width: _width,
            height: _height,
            modal: true,
            closed: false,
            border: false,
            href: urlPrefix + '/edit',
            onLoad: function (data) {
                try {
                    var retJson = $.parseJSON(data);
                    MXF.ajaxFormDone(retJson);
                    editWindow.window('destroy');
                    return;
                } catch (e) {
                }
                var $form = editWindow.find('form');
                $form.data('grid', grid);
                $form.data('window', editWindow);
                editWindow.data('grid', grid);

                var queryParams = $(grid).datagrid('options').queryParams;
                $form.form('load', queryParams);

            },
            onClose: function () {
                editWindow.window('destroy');
            }
        });
    },
    edit: function (grid, clickTarget, params) {
        var row = grid.datagrid('getSelected');
        var _error = params.error || 'Please select at least one data ?';
        if (row == null) {
            MXF.error(_error);
            return;
        }

        var isRemote = false;
        if ('true' == $(clickTarget).attr('remote') || true == $(clickTarget).attr('remote')) {
            isRemote = true;
        }

        var editWindow = $('<div></div>');
        var urlPrefix = MXF.getPrefix(grid);
        editWindow.appendTo('body');
        var _width = params.width || 600;
        var _height = params.height || 450;
        var _title = params.title || 'edit'
        editWindow.window({
            title: _title,
            width: _width,
            height: _height,
            modal: true,
            closed: false,
            border: false,
            href: urlPrefix + '/edit?id=' + row.id,
            onLoad: function (data) {
                try {
                    var retJson = $.parseJSON(data);
                    MXF.ajaxFormDone(retJson);
                    editWindow.window('destroy');
                    return;
                } catch (e) {
                }

                var $form = editWindow.find('form');
                $form.data('grid', grid);
                $form.data('window', editWindow);
                editWindow.data('grid', grid);

                //$form.form('clear');
                var selRow = grid.datagrid('getSelected');

                var queryParams = $(grid).datagrid('options').queryParams;
                if (isRemote) {
                    $.post(urlPrefix + '/detail?id=' + selRow.id, {}, function (data) {
                        var formData = $.extend({}, queryParams, MXF.jsonEval(data));
                        formData = MXF.transferComplexProperty(formData);
                        $form.form('load', formData);
                    });
                } else {
                    var formData = $.extend({}, queryParams, selRow);
                    formData = MXF.transferComplexProperty(formData);
                    console.debug(formData);
                    $form.form('load', formData);
                }
            },
            onClose: function () {
                editWindow.window('destroy');
            }
        });
    },
    search: function (grid) {
        var searchForm = $(grid).closest('.tableGroup').find('.searchForm').find('form');
        var params = MXF.getParamObj(searchForm);
        grid.datagrid('load', params);
    },
    resetSearch: function (grid) {
        var searchForm = $(grid).closest('.tableGroup').find('.searchForm').find('form');
        $(searchForm).form('clear');
        grid.datagrid('load', {});
    },
    del: function (grid, clickTarget, params) {
        var rows = grid.datagrid('getSelections');
        var _msg = params.msg || 'Confirm delete';
        var _error = params.error || 'Please select at least one data';
        var ids = '';
        for (var i = 0; i < rows.length; i++) {
            ids += ',' + rows[i].id;
        }
        if (ids.length > 1) ids = ids.substring(1);
        if ('' != ids) {
            MXF.confirm(_msg + '?', function () {
                MXF.ajaxing(true);
                var urlPrefix = MXF.getPrefix(grid);
                $.post(urlPrefix + '/delete', {id: ids}, function (data) {
                    MXF.ajaxing(false);
                    MXF.ajaxFormDone(data);
                    if (data.success) {
                        $(grid).datagrid('reload');
                    }
                });
            });
        } else {
            MXF.error(_error);
        }
    },
    show: function (grid, clickTarget, params) {
        var row = grid.datagrid('getSelected');
        if (row == null) {
            MXF.error('请至少选中一条数据');
            return;
        }
        var urlPrefix = MXF.getPrefix(grid);
        var showWindow = $('<div></div>');
        showWindow.appendTo('body');
        var _width = params.width || 600;
        var _height = params.height || 450;
        showWindow.window({
            title: '查看详情',
            width: _width,
            height: _height,
            href: urlPrefix + '/show',
            queryParams: {id: row.id},
            onLoad: function (data) {
                try {
                    var retJson = $.parseJSON(data);
                    MXF.ajaxFormDone(retJson);
                    editWindow.window('destroy');
                    return;
                } catch (e) {
                }
            },
            onClose: function () {
                showWindow.window('destroy');
            }
        });
    }
};


//自定义工具方法
var MXF = {};
MXF.getPrefix = function (grid) {
    var gridOptions = grid.datagrid('options');
    var url = gridOptions.url;
    var endIndex = url.lastIndexOf('/');
    var urlPrefix = url.substring(0, endIndex);
    return urlPrefix;
}

//将指定的form表单变为ajax提交方式，然后提交表单
MXF.ajaxForm = function (obj, callbackFn, beforeSubmit) {
    var $form = $(obj);
    if (!$form.is('form')) {
        $form = $(obj).closest('form');
    }
    if (!$form.is('form')) return;
    $form.form('submit', {
        url: $form.attr('action'),
        onSubmit: function (param) {
            var isValid = $(this).form('enableValidation').form('validate');
            if (isValid) {
                MXF.ajaxing(true);
            }
            if ($.isFunction(beforeSubmit)) {
                beforeSubmit(param);
            }
            return isValid;	// 返回false终止表单提交
        },
        success: function (text) {
            var data = JSON.parse(text);
            MXF.ajaxing(false);
            MXF.ajaxFormDone(data);
            if (data.success) {
                console.log($form);
                var formWindow = $form.data('window');
                if (formWindow) {//关闭窗口
                    var formGrid = $form.data('grid');
                    if (formGrid) {//刷新表格
                        formGrid.datagrid('reload');
                    }
                    formWindow.window('close');
                }
                if (callbackFn) {
                    callbackFn($form, data);
                }
            } else {
                MXF.error(data.message + data.info);
            }
        }
    });
}

//清空表单
MXF.clearForm = function (obj) {
    var $form = $(obj);
    if (!$form.is('form')) {
        $form = $(obj).closest('form');
    }
    if (!$form.is('form')) return;
    $form.form('clear');
}

//获取当前窗口的尺寸 W和H
MXF.window = function () {
    return {W: $(window).width(), H: $(window).height()};
};

MXF.centerPosition = function (w, h) {
    return {X: (MXF.window().W - w) / 2, Y: (MXF.window().H - h) / 2};
};

MXF.getTabContentHeight = function () {
    var contentHeight = $(document.body).height();
    contentHeight = contentHeight - 89;
    $('.tab-wrap').css('height', contentHeight);
}

//弹出提示框
MXF.alert = function (msg, isSuccess) {
    var gPane = $('#globalErrMsgPane');
    if (isSuccess == "true" || isSuccess == true) {
        gPane = $('#globalMsgPane');
    }
    if (null == msg || '' == msg) msg = '未知信息';
    gPane.find('.globalContent').html(msg);
    gPane.css('margin', '-' + (gPane.height() / 2) + 'px 0 0 -' + (gPane.width() / 2) + 'px');
    gPane.stop().fadeIn(100).delay(2000).fadeOut(200);
};
//确认对话框
MXF.confirm = function (msg, fn, cfn) {
    var gPane = $('#globalConfirmPane');
    gPane.find('.globalContent').html(msg);
    gPane.css('margin', '-' + (gPane.height() / 2) + 'px 0 0 -' + (gPane.width() / 2) + 'px');
    gPane.stop().fadeIn(100).delay(10000).fadeOut(200);
    gPane.find('.btn-confirm').unbind('click').click(function () {
        gPane.hide();
        if (null != fn) {
            fn();
        }
    });
    gPane.find('.btn-cancel').unbind('click').click(function () {
        gPane.hide();
        if (null != cfn) {
            cfn();
        }
    });
};
//提示信息
MXF.info = function (msg) {
    MXF.alert(msg, true);
};
//错误信息
MXF.error = function (msg) {
    MXF.alert(msg, false);
};
//ajax请求遮罩 和 完成时取消遮罩
MXF.ajaxing = function (isShow, msg) {
    var gPane = $('#globalAjaxing');
    var gContent = gPane.find('.globalContent');
    if (isShow) {
        if (null != msg || '' != msg) {
            gContent.html(msg);
        } else {
            gContent.html('加载中...');
        }
        var p = MXF.centerPosition(gPane.width(), gPane.height());

        var modalWrapper = $('<div id="bgModalWrapper" class="dialogModalWraper"></div>');
        $('body').append(modalWrapper);
        modalWrapper.css('z-index', MXF.MAXINDEX++).css('height', MXF.window().H + 'px');
        modalWrapper.show();
        gPane.css('z-index', MXF.MAXINDEX++).css('left', p.X + 'px').css('top', p.Y + 'px');
        gPane.show();
    } else {
        setTimeout(function () {
            gPane.hide();
            $('#bgModalWrapper').remove();
        }, 200);
    }
};

MXF.jsonEval = function (data) {
    try {
        if ($.type(data) == 'string')
            return eval('(' + data + ')');
        else
            return data;
    } catch (e) {
        return {};
    }
};
//统一提示后台返回的信息
MXF.ajaxFormDone = function (data) {
    try {
        var jData = MXF.jsonEval(data);
        if (jData.success || jData.success == 'false' || jData.success == false) {
            MXF.alert(MXF.formatAjaxMsg(jData), jData.success);
        } else {
            var msg = '处理失败，返回数据格式解析错误';
            var msgTag = $(data).find('#globalErrMsg');
            var extmsgTag = $(data).find('#globalErrExtMsg');
            var extmsg = '';
            if (null != msgTag) msg = $(msgTag).html();
            if (null != extmsgTag) extmsg = $(extmsgTag).html();
            if (extmsg && extmsg.length > 0) {
                msg += '<br>' + extmsg;
            }

            MXF.error(msg);
            return false;
        }
        if (jData.success == "true" || jData.success == true) return true;
        else return false;
    } catch (e) {
        MXF.error('返回数据格式解析错误');
        return false;
    }
}

MXF.formatAjaxMsg = function (data) {
    var __retMsg = "未知信息";
    if (typeof (data.success) != 'undefined') {
        __retMsg = data.message;
        var __extmsg = data.info;
        if (__extmsg && __extmsg != '') __retMsg += '<br>' + __extmsg;
    }
    return __retMsg;
}

//将指定的form中的参数转换为json对象格式
MXF.getParamObj = function ($form) {
    var params = {};
    var paramsArr = $($form).serializeArray();
    $.each(paramsArr, function (index, object) {
        params[object.name] = object.value;
    });
    return params;
}

MXF.commonFormatter = function (value, row, index) {
    if (null == value) {
        return '';
    }
    return value.name || value.text || value.title;
}

//将对象的复杂属性转变为普通属性
//例如： formData.a.b=1 => formData['a.b']=1
MXF.transferComplexProperty = function (formData) {
    var retData = $.extend({}, formData);
    for (var key in formData) {
        var propObj = formData[key];
        if (null != propObj && 'object' == (typeof propObj)) {
            for (var pkey in propObj) {
                var propObjValue = propObj[pkey];
                if (propObjValue != null) {
                    var complexKey = key + '.' + pkey;
                    retData[complexKey] = propObjValue;
                }
            }
        }
    }

    for (var key in retData) {
        if (retData[key] == null) {
            delete retData[key];
        }
    }
    return retData;
}

Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    };
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};

MXF.dateTimeFormatter = function (val, row) {
    var now = new Date(val);
    return now.format("yyyy-MM-dd hh:mm:ss");
};

MXF.openDialog = function (el, title, url, callback, width, height) {
    var editWindow = $('<div id="' + el + '"></div>');
    editWindow.appendTo('body');
    var _width = width || 600;
    var _height = height || 450;
    editWindow.window({
        title: title,
        width: _width,
        height: _height,
        modal: true,
        closed: false,
        border: false,
        href: url,
        onLoad: callback,
        onClose: function () {
            editWindow.window('destroy');
        }
    });
}

