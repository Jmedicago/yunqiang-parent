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
                console.log('[INFO] On click cur tab.');
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
        var _error = params.error || '请至少选择一条要删除的数据！';
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
        var _msg = params.msg || '确认删除';
        var _error = params.error || '请至少选择一条要删除的数据！';
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
MXF.CURRENCY = 'CNY';
MXF.getPrefix = function (grid) {
    var gridOptions = grid.datagrid('options');
    var url = gridOptions.url;
    var endIndex = url.lastIndexOf('/');
    var urlPrefix = url.substring(0, endIndex);
    return urlPrefix;
}

$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
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
                // 提交前处理参数
                /*var formData = $form.serializeObject();
                console.log('formData', formData);*/
                beforeSubmit(param);
            }
            return isValid;	// 返回false终止表单提交
        },
        success: function (text) {
            var data = JSON.parse(text);
            MXF.ajaxing(false);
            MXF.ajaxFormDone(data);
            if (data.success) {
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
                MXF.error(data.message + '，' + data.info);
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
    gPane.stop().fadeIn(100, function () {
        $('.window-mask').show();
    }).delay(2000).fadeOut(200, function () {
        $('.window-mask').hide();
    });
};
//确认对话框
MXF.confirm = function (msg, fn, cfn) {
    var gPane = $('#globalConfirmPane');
    gPane.find('.globalContent').html(msg);
    gPane.css('margin', '-' + (gPane.height() / 2) + 'px 0 0 -' + (gPane.width() / 2) + 'px');
    gPane.stop().fadeIn(100, function () {
        $('.window-mask').show();
    }).delay(5000).fadeOut(200, function () {
        $('.window-mask').hide();
        // 执行取消方法
        if (null != cfn) {
            cfn();
        }
    });
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

    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            gPane.find('.btn-confirm').triggerHandler('click');
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

MXF.showImageDialog = function (e) {
    var large = '<img width="596px" height="453px" src=' + e + '></img>';
    $("#imageLarge").html(large).animate({
        height: '30%',
        width: '30%'
    }, 1);
    $("#globalImageDialog").dialog("open").dialog("setTitle", "图片展示");
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

function formatMoney(number, places, symbol, thousand, decimal) {
    number = number || 0;
    places = !isNaN(places = Math.abs(places)) ? places : 0; // 2
    symbol = symbol !== undefined ? symbol : "MT"; //MTn
    thousand = thousand || ",";
    decimal = decimal || ".";
    var negative = number < 0 ? "-" : "",
        i = parseInt(number = Math.abs(+number || 0).toFixed(places), 10) + "",
        j = (j = i.length) > 3 ? j % 3 : 0;
    return negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand) + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2) : "") + symbol;
}

MXF.priceFormatter = function (val) {
    //return OSREC.CurrencyFormatter.format(val * 0.01, {currency: 'MZN', decimal: '.', group: ','});
    return formatMoney(val * 0.01);
}

//格式化单元格提示信息
MXF.cellTooltipFormatter = function (value) {
    if (value) {
        var title = value;
        title = value.replace(/<br>/g, "\n");
        return "<span title='" + title + "'>" + value + "</span>";
    } else {
        return "";
    }

}

MXF.priceParse = function (val) {
    return OSREC.CurrencyFormatter.parse(val, {decimal: '.'});
}

MXF.dateTimeFormatter = function (val, row) {
    if (val) {
        var now = new Date(val);
        return now.format("yyyy-MM-dd hh:mm:ss");
    }
    return '';
};

MXF.dateFormatter = function (val, row) {
    if (val) {
        var now = new Date(val);
        return now.format("yyyy-MM-dd");
    }
    return '';
};

MXF.openDialog = function (el, title, url, callback, width, height, maximized) {
    var editWindow = $('<div id="' + el + '"></div>');
    editWindow.appendTo('body');
    var _width = width || 600;
    var _height = height || 450;
    var _maximized = maximized || false;
    editWindow.window({
        title: title,
        width: _width,
        height: _height,
        modal: true,
        closed: false,
        border: false,
        maximized: _maximized,
        href: url,
        onLoad: callback,
        onClose: function () {
            editWindow.window('destroy');
        }
    });
}

MXF.initPicFileUploader = function (data) {
    $(".picFileUpload").each(function (i, e) {
        var _ele = $(e);
        _ele.siblings("div.pics").remove();
        _ele.after('\
    			<div class="pics">\
        			<ul></ul>\
        		</div>');

        // 回显图片
        if (data) {
            var imgs = data.split(",");
            for (var i in imgs) {
                if ($.trim(imgs[i]).length > 0) {
                    _ele.siblings(".pics").find("ul").append("<li style='display: inline-block;'><a style='display: block; height: 80px; width: 80px; border: 1px solid #eee; padding: 5px 5px; margin: 15px 10px 0 0;' href='" + imgs[i] + "' target='_blank'><img src='" + imgs[i] + "' width='80' height='80' /></a></li>");
                }
            }
        }

        //给“上传图片按钮”绑定click事件
        $(e).click(function () {
            var form = $(this).parentsUntil("form").parent("form");
            // 编辑器参数
            var kingEditorParams = {
                //指定上传文件参数名称
                filePostName: "uploadFile",
                //指定上传文件请求的url。
                uploadJson: '/resources/upload',
                //上传类型，分别为image、flash、media、file
                dir: "image"
            };
            //打开图片上传窗口
            KindEditor.editor(kingEditorParams).loadPlugin('multiimage', function () {
                var editor = this;
                editor.plugin.multiImageDialog({
                    clickFn: function (urlList) {
                        var imgArray = [];
                        KindEditor.each(urlList, function (i, data) {
                            imgArray.push(data.url);
                            form.find(".pics ul").append("<li style='display: inline-block;'><a style='display: block; height: 80px; width: 80px; border: 1px solid #eee; padding: 5px 5px; margin: 15px 10px 0 0;' href='" + data.url + "' target='_blank'><img src='" + data.url + "' width='80' height='80' /></a></li>");
                        });
                        form.find("[name=resources]").val(imgArray.join(","));
                        editor.hideDialog();
                    }
                });
            });
        });
    });
}

MXF.initOnePicUploader = function (data) {
    // 图片回显
    if (data) {
        var input = $(".onePicUpload").siblings("input");
        input.parent().find(".pic-are").remove();
        input.val(data);
        input.after("<a class='pic-are' style='display: block; width: 80px; height: 80px; border: 1px solid #eee; margin-top: 15px; padding: 5px;' href='" + data + "' target='_blank'><img src='" + data + "' width='80' height='80'/></a>")
    }
    $(".onePicUpload").click(function () {
        var _self = $(this);
        // 编辑器参数
        var kingEditorParams = {
            //指定上传文件参数名称
            filePostName: "uploadFile",
            //指定上传文件请求的url。
            uploadJson: '/resources/upload',
            //上传类型，分别为image、flash、media、file
            dir: "image"
        };
        KindEditor.editor(kingEditorParams).loadPlugin('image', function () {
            this.plugin.imageDialog({
                showRemote: false,
                clickFn: function (url, title, width, height, border, align) {
                    var input = _self.siblings("input");
                    input.parent().find(".pic-are").remove();
                    input.val(url);
                    input.after("<a class='pic-are' style='display: block; width: 80px; height: 80px; border: 1px solid #eee; margin-top: 15px; padding: 5px;' href='" + url + "' target='_blank'><img src='" + url + "' width='80' height='80'/></a>");
                    this.hideDialog();
                }
            });
        });
    });
}

