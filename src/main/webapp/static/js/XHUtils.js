/**
 * @Time : 2017-07-07
 * @author : yq
 * @updateTime : 20170907
 * @TODO : 添加生产和开发模式
 *   引用到第三方js时请做检查和写在本文的最后，减少不必要的错误
 * @第三方：jquery art-template watch.js  bootstrap-table bootstrapValidator(nghuuphuoc版)
 * @常量：常量（session常量，url,前后台公共字段，字典关键字，常用日期格式，缓存数据，常用常量，特殊关键字）
 * @函数功能：1.公共功能：eval,日期格式化（添加在Date里可直接用），多行文本处理，非空检测，collapse（类手风琴），cookie的处理
 *            2.常用功能：form数据操作（打包和注入），ajax，字典方法
 *            3.封装第三方功能：jq的ajax，bootstrap-table(create和load),art-template的模板方法，watch（通过对cache数据的监听完成复杂功能），
 *                              扩展art-template的helper，form检验（基于bootstrapValidator）
 *            4.杂项功能：
 * @兼容性:ES3及以上，IE8及以上（cache功能暂未对8测试），其他浏览器请自行测试发现
 *
 *  注意：1.鉴于过多的注释和空格之类的请在生产模式下压缩
 *        2.本文大量用了jq的extend，如果不使用jq请自行添加扩展
 *  
 */
(function($) {
  //全局系统对象
  window['XHUtils'] = {};

  /**session常量*/
  XHUtils.SESSION_NAME = "xhSession";
  XHUtils.ThIRTY = 30;

  /**url*/
  XHUtils.ROOTURL = '';
  XHUtils.COMMURL = XHUtils.ROOTURL + '/comm/';

  /**前后台公共字段*/
  XHUtils.COM_DATA = "data";
  XHUtils.JSON = "json";
  XHUtils.flag = 'valid';

  /**字典关键字*/
  XHUtils.DICT_DATA_KEY = 'key';
  XHUtils.DICT_DATA_VALUE = 'value';
  
  /**常用日期格式*/
  XHUtils.FORMAT = "yyyy-MM-dd";

  /**缓存数据*/
  XHUtils.cache = '';

  /**常用常量*/
  XHUtils.SUBMIT = '提交';
  XHUtils.SAVE = '保存';
  XHUtils.EDIT = '编辑';
  XHUtils.ADD = "新增";
  XHUtils.DEL = '删除';
  /**特殊关键字*/
  XHUtils.xh = "xh";


  /**
   * 直接从结果集（ResultBean)里得到JSON数据
   * @param  {[type]} data [description]
   * @param  {[type]} flag [true为String类型，false为Object]
   * @return {[type]}      [description]
   */
  XHUtils.getDataByResult = function(data,flag){
    if(flag)return data[XHUtils.JSON];
    return JSON.parse(data[XHUtils.JSON]);
  }

  /**
   * 代替js原生的eval（如果是IE10及以下，请使用这个）
   * @param  {Function} fn [description]
   * @return {[type]}      [description]
   */
  XHUtils.eval = function(fn){
    var Fn = Function;  //一个变量指向Function，防止有些前端编译工具报错
    return new Fn('return ' + fn)();
  }

  /**
   * 注入form
   * @param {[type]} data   [description]
   * @param {[type]} formId [在该form下寻找域,防止多个form里重复的字段（如果真实存在请改正，不建议一个html里存在多个同名ID）]
   */
  XHUtils.setFormData = function(data,formId){
    var parentNode = document;
    if(formId){
      parentNode = $('#'+formId);
    }
    if(typeof(data)==="string"){
      data = JSON.parse(data);
    }
    if(data instanceof Array){
      for(var j = 0, length2 = data.length; j < length2; j++){
        for(obj in data[j]){
          $('#'+obj,parentNode).val(data[j][obj]);
        }
      }
    }
    if(data instanceof Object){
      if(data[XHUtils.JSON]){
        data = JSON.parse(data[XHUtils.JSON]);
      }
      for(obj in data){
        $('#'+obj,parentNode).val(data[obj]);
      }
    }
  }

  /**
   * 多行文本的兼容解决
   * @param  {Function} fn [description]
   * @return {[type]}      [description]
   */
  XHUtils.heredoc = function(fn){
     return fn.toString().split('\n').slice(1,-1).join('\n') + '\n';
  }

  /**
   * hereDoc方法的扩展，用作多行文本js
   * @param  {Function} fn  [文本（必须用Function包起来）]
   * @param  {[type]}   obj （可选） [K-V形式的Object,key为其要替换的字符，value为其值]
   * @return {[type]}       [description]
   */
  XHUtils.hereJs = function(fn,obj){
    var html = "<script>"+XHUtils.heredoc(fn)+"<\/script>";
    if(XHUtils.isEmpty(obj))return html;
    for(a in obj){
      var str = '%'+a+'%';
      html = html.replace(new RegExp(str,'g'),obj[a]);
    }
    return html;
  }
  /**
   * select的options的配置 TODO:扩展到所有可用的dict里
   * @param  {[type]} config [description]
   * @param  {[type]} name   [description]
   * @return {[type]}        [description]
   */
  XHUtils.getDictForSelect = function(config, name) {
    if(typeof(config) === 'string') { //针对直接传key的时候的处理

    }
    if(config instanceof Object) {
      var obj = $.extend({}, config);

      var o = {};
      o[XHUtils.DICT_DATA_KEY] = config.data;
      obj = $.extend(obj, {
        data: JSON.stringify(o),
        callBackFun: function(data) {
          var html = "";
          var list = JSON.parse(data[XHUtils.JSON]);
          for(var i = 0; i < list.length; i++) {
            html += '<option value="' + list[i][XHUtils.DICT_DATA_KEY] + '">' + list[i][XHUtils.DICT_DATA_VALUE] + '</option>';
          }
          XHUtils.cache='';
          XHUtils.cache = '$(\'#' + name + '\').html(\'' + html + '\')';

        }
      });
      XHUtils.ajax(obj);
    }
  }

  /**
   * 格式化日期
   * @param  {[type]} str [description]
   * @param  {[type]} fm  [description]
   * @return {[type]}     [description]
   */
  XHUtils.formatterDate = function(str, fm) {
    var date = new Date(str);
    if(fm) {
      return date.Format(fm);
    }
    return date.Format(XHUtils.FORMAT);
  }

  /**
   * 打包，包含disable属性的表单
   */

  XHUtils.convertAllJson = function(form) {
    var v = null;
    v = {};
    var dis = $(':disabled',"#" + form);
    dis.removeAttr('disabled');
    var o = $("#" + form).serializeArray();
    dis.attr('disabled','');
    for(var i in o) {
      if(!o[i].name) {
        //忽略length属性
        continue;
      }
      if(typeof(v[o[i].name]) == 'undefined')
        //v[o[i].name] = o[i].value;
        //对password加密
        /*if ("password" == o[i].name || "newPwd" == o[i].name || "enterPwd" == o[i].name) {
            v[o[i].name] = hex_md5(o[i].value);
        }
        else {*/
        v[o[i].name] = o[i].value;
      // }
      else
        v[o[i].name] += "," + o[i].value;
    }
    return JSON.stringify(v);
  };

  /**
   * 打包form表单数据并将json数据转换为String
   */
  XHUtils.convertStringJson = function(form) {
    var v = null;
    v = {};
    var o = $("#" + form).serializeArray();
    for(var i in o) {
      if(!o[i].name) {
        //忽略length属性
        continue;
      }
      if(typeof(v[o[i].name]) == 'undefined')
        //v[o[i].name] = o[i].value;
        //对password加密
        /*if ("password" == o[i].name || "newPwd" == o[i].name || "enterPwd" == o[i].name
                 || "oldPassword" == o[i].name
                 || "newPassword" == o[i].name) {
            v[o[i].name] = hex_md5(o[i].value);
        }
        else {*/
        v[o[i].name] = o[i].value;
      // }
      else
        v[o[i].name] += "," + o[i].value;
    }
    return JSON.stringify(v);
  };
  /**
   * 获取SeSSIOn
   * @return {[type]} [description]
   */
  XHUtils.getSession = function() {
    return getCookie(XHUtils.SESSION_NAME)
  }
  /**
   * 设置Session
   * @param {[type]} sessionId [description]
   * @param {[type]} minu      [description]
   */
  XHUtils.setSession = function(sessionId, minu) {
    if(XHUtils.getSession() === sessionId) {
      return;
    }
    setCookie(XHUtils.SESSION_NAME, sessionId, minu);
  }
  /**
   * 删除Session
   * @return {[type]} [description]
   */
  XHUtils.delSession = function() {
    delCookie(XHUtils.SESSION_NAME);
  }
  /**
   * 公共数据，先加Session，后续不断扩展
   * @return {[type]} [description]
   */
  XHUtils.getCommData = function() {
    var data = {
      session: XHUtils.isEmpty(XHUtils.getSession()) ? "" : XHUtils.getSession()
    };
    return data;
  }
  /**
   * 判断是否为空
   */
  XHUtils.isEmpty = function(value, allowEmptyString) {
    return(value === null) || (value === undefined) || (!allowEmptyString ? value === '' : false) || ($.isArray(value) && value.length === 0);
  };
  /**
   * 共同ajax调用
   * @param config 是一个自定义对象属性有
   * config.data:提交参数信息
   * config.url:请交路径
   * config.callBackFun:回调函数
   * config.async : true|false 默认为false，是同步模式，true 表示异步加载
   */
  XHUtils.ajax = function(config) {
    var btn = config.btn;
    if(config.url) {
      config.url += (config.url.indexOf("?") != -1 ? "&" : "?") + "_dc=" + $.now();
    }
    //对于所有非登陆前的ajax 简单的拦截
    if(XHUtils.isEmpty(XHUtils.getSession())) {
      if(!(config.url.indexOf("login") > 0)) {
        window.top.location.href = XHUtils.ROOTURL + "/page/login/login.html";
        return;
      }
    }
    config.data = setCommData(config.url, config.data);
    $.ajax($.extend({
      async: config.async || false,
      cache: false,
      type: "POST",
      dataType: "json",
      contentType: "application/json",
      timeout: 300000,
      url: "",
      data: {},
      success: function(data) {
        //如果失败显示失败信息
        if(!XHUtils.isEmpty(data) && data.resultType == 6) {
          delCookie();
          window.top.location.href = XHUtils.ROOTURL + "/login.html";
        }
        if(data.resultType == 2) {
          alert(data.msg);
          return;
        }
        if(data.map && data.map[XHUtils.SESSION_NAME]) {
          console.log(data.map[XHUtils.SESSION_NAME]);
          XHUtils.setSession(data.map[XHUtils.SESSION_NAME]);
        }
        var fn = function(data) {};
        if(config.callBackFun) { //如果有回调函数
          fn = function(rs) {
            config.callBackFun(rs);
          };
        }
        fn(data);
      },
      error: function(r, m, d) { // 请求失败处理函数
        if(config.errorCallback && config.errorCallback !== null) {
          config.errorCallback(r, m, d);
        } else {
          XHUtils.ajaxError(r, m, d);
        }
      }
    }, config));
  };

  /**
   * Ajax 调用失败时的处理
   * @param r
   * @param m
   * @param d
   */
  XHUtils.ajaxError = function(r, m, d) {
    if(r.readyState == 4 && r.status == 200) {
      alert("向服务器发送请求失败，失败的原因是:<br>" + d.message);
    } else {
      alert("向服务器发送请求无响应！");
    }
  };
  /**
   * bootstrapTable更新数据
   */
  XHUtils.load = function(id,data){
    if(typeof(data)==='string'){
      data = JSON.parse(data);
    }
	  if(data instanceof Object){
      if(data[XHUtils.JSON]){
        data = JSON.parse(data[XHUtils.JSON]);
      }

      if(!data instanceof Array){
        data = [data];
      }
	  }
	  

	  $('#' + id).bootstrapTable('load',data);
  }
  
  /**
   * 
   * @param id
   * @param config
   */
  XHUtils.createTable = function(id, config) {
    if(arguments.length!==2){
      throw "请检查createTable方法或传入ID和cofig";
    }
    var options = {
      //url: 'bootJson.json',         //请求后台的URL（*）
      dataType: 'json',
      method: 'get', //请求方式（*）
      // toolbar: '#'+toolbarId,                //工具按钮用哪个容器
      striped: true, //是否显示行间隔色
      cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
      pagination: true, //是否显示分页（*）
      sortable: true, //是否启用排序
      sortOrder: "asc", //排序方式
      // queryParams: oTableInit.queryParams,//传递参数（*）
      sidePagination: "client", //分页方式：client客户端分页，server服务端分页（*）
      pageNumber: 1, //初始化加载第一页，默认第一页
      pageSize: 10, //每页的记录行数（*）
      pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
      search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
      strictSearch: true,
      showColumns: false, //是否显示所有的列
      showRefresh: false, //是否显示刷新按钮
      minimumCountColumns: 2, //最少允许的列数
      clickToSelect: true, //是否启用点击选中行
      //height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
      //uniqueId: "id", //每一行的唯一标识，一般为主键列
      showToggle: false, //是否显示详细视图和列表视图的切换按钮
      cardView: false, //是否显示详细视图
      detailView: false, //是否显示父子表
    };
    $('#' + id).bootstrapTable($.extend(options, config));
  }

  /**  以下的XHUtils的公共方法都有调用外部的js  放在最后防止有错，而影响其他公共方法 */
  /**
   * [template description]
   * @param  {[type]}   id  [要渲染的Dom ID]
   * @param  {[type]}   obj [元数据格式为｛tempName:'',data｝]
   * @param  {Function} fn  [回调函数]
   * @param  {[type]}   pa  [回调函数参数]
   */
  XHUtils.template = function(id, obj, fn, pa) {
    if(!template) {
      alert("请检查art-template是否引入或是否在XHUtils.js前引入！");
      return;
    }
    var html = "";
    if(obj instanceof Array) {
      for(var i = 0; i < obj.length; i++) {
        html += template(obj[i].tempName, obj[i].data);
      }
    } else {
      html += template(obj.tempName, obj.data);
    }

    $('#' + id).html(html);
    var callBackFun = function(pa) {};
    if(fn) { //如果有回调函数
      callBackFun = function(rs) {
        fn(rs);
      };
    }
    callBackFun(pa);
  }

  /**
   * 对cache的监听  未测试IE8，同时未对document监听，可能出错
   * @param  {[type]} WatchJS.watch [description]
   * @return {[type]}               [description]
   */
  if(WatchJS) {
    WatchJS.watch(XHUtils, 'cache', function(prop, action, newvalue, oldvalue) {
      console.log(oldvalue);
      XHUtils.eval(newvalue);
    });
  }

  /**
   * 为了增加art-template的helper的方法
   * @param  {[type]} obj [description]
   * @return {[type]}     [description]
   */
  XHUtils.objTostr = function(obj) {
    return JSON.stringify(obj);
  }

  /**
   * 添加公共数据(目前只有SessionId)
   * @param {[type]} url  [description]
   * @param {[type]} data [description]
   */
  var setCommData = function(url, data) {
    if(url.indexOf('login') > 0) {
      return data;
    }
    if(!data) {
      data = JSON.stringify({});
    }
    var cookie = XHUtils.getSession() == null ? "" : XHUtils.getSession();
    var commData = {};
    var nameObj = {};
    nameObj[XHUtils.SESSION_NAME] = cookie;
    commData[XHUtils.COM_DATA] = nameObj;
    if(typeof(data) == "string")
      return JSON.stringify($.extend(JSON.parse(data), commData));
    return $.extend(data, commData);
  }
  /**
   * 获取Cookie
   * @param  {[type]} name [description]
   * @return {[type]}      [description]
   */
  var getCookie = function(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if(arr = document.cookie.match(reg))
      return unescape(arr[2]);
    else
      return null;
  }
  /**
   * 默认设置时间为30分钟
   * @param {[type]} name  [description]
   * @param {[type]} value [description]
   * @param {[type]} minu  [description]
   */
  var setCookie = function(name, value, minu) {
    //var Days = 30;
    if(!minu) minu = XHUtils.ThIRTY;
    var exp = new Date();
    exp.setTime(exp.getTime() + minu * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";path=/;expires=" + exp.toGMTString();
  }

  var delCookie = function delCookie(name) {
    if(!name) name = XHUtils.SESSION_NAME;
      document.cookie = name + "=" + "" + ";path=/;expires=" + -1;
  }

  /**
   * collapse 定义收缩事件
   * 当加collapse=“id”时该点击该元素会收费ID为id的元素
   */
  $(document).on('click.collapse', ['collapse="*"'], function(e) {
    $this = $('#' + $(e.target).attr('collapse'));
    if($this.css('display') === 'none') {
      $this.show();
    } else {
      $this.hide();
    }
  });

  // 对Date的扩展，将 Date 转化为指定格式的String   
  // 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
  // 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
  // 例子：   
  // (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
  // (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
  Date.prototype.Format = function(fmt) {
    var o = {
      "M+": this.getMonth() + 1, //月份   
      "d+": this.getDate(), //日   
      "h+": this.getHours(), //小时   
      "m+": this.getMinutes(), //分   
      "s+": this.getSeconds(), //秒   
      "q+": Math.floor((this.getMonth() + 3) / 3), //季度   
      "S": this.getMilliseconds() //毫秒   
    };
    if(/(y+)/.test(fmt))
      fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for(var k in o)
      if(new RegExp("(" + k + ")").test(fmt))
        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
  }

  /**
   * 扩展art-template函数
   * @param  {[type]} ) {                 return Math;    } [description]
   * @return {[type]}   [description]
   */
  if(template) {
    template.helper('Math', Math);
    template.helper('XHUtils', XHUtils);
  }
  


  /**
   * 页面头的公共信息加载，可以删除
   * @param  {[type]} $('#loginUserName').length [description]
   * @return {[type]}                            [description]
   */
  if($('#loginUserName').length){
		XHUtils.ajax({
		  	url:XHUtils.COMMURL+"getUserById.do",
		  	callBackFun:function(data){
					$('#loginUserName').html(JSON.parse(data[XHUtils.JSON]).userName)
				}
		  })
	}

  /**
   * [valid 基于bootstrapValidator的检验]
   * @param  {[type]} id     [form的id]
   * @param  {[type]} config [校验配置]
   * @return {[type]}        [bootstrapValidator]
   */
  XHUtils.valid = function(id,config){
    config = config===undefined?{}:config;
    var option = {//默认配置可重写
        /**
          *  指定不验证的情况
          *  值可设置为以下三种类型：
          *  1、String  ':disabled, :hidden, :not(:visible)'
          *  2、Array  默认值  [':disabled', ':hidden', ':not(:visible)']
          *  3、带回调函数  
              [':disabled', ':hidden', function($field, validator) {
                  // $field 当前验证字段dom节点
                  // validator 验证实例对象 
                  // 可以再次自定义不要验证的规则
                  // 必须要return，return true or false; 
                  return !$field.is(':visible');
              }]
          */
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        /**
        * 指定验证后验证字段的提示字体图标。（默认是bootstrap风格）
        * Bootstrap 版本 >= 3.1.0
        * 也可以使用任何自定义风格，只要引入好相关的字体文件即可
        * 默认样式 
            .form-horizontal .has-feedback .form-control-feedback {
                top: 0;
                right: 15px;
            }
        * 自定义该样式覆盖默认样式
            .form-horizontal .has-feedback .form-control-feedback {
                top: 0;
                right: -15px;
            }
            .form-horizontal .has-feedback .input-group .form-control-feedback {
                top: 0;
                right: -30px;
            }
        */
        feedbackIcons: {
          valid: 'icon-ok',
          invalid: 'icon-remove',
          validating: 'icon-refresh'
        },
        /**
         * 生效规则（三选一）
         * enabled 字段值有变化就触发验证
         * disabled,submitted 当点击提交时验证并展示错误信息
         */
        live: 'enabled',
        /**
         * 为每个字段指定通用错误提示语
         */
        message: '这个是不通过检验规则的！！！',
        /**
         * 指定提交的按钮，例如：'.submitBtn' '#submitBtn'
         * 当表单验证不通过时，该按钮为disabled
         */
        //submitButtons: '#btn',
        /**
          * submitHandler: function(validator, form, submitButton) {
          *   //validator: 表单验证实例对象
          *   //form  jq对象  指定表单对象
          *   //submitButton  jq对象  指定提交按钮的对象
          * }
          * 在ajax提交表单时很实用
          *   submitHandler: function(validator, form, submitButton) {
                  // 实用ajax提交表单
                  $.post(form.attr('action'), form.serialize(), function(result) {
                      // .自定义回调逻辑
                  }, 'json');
               }
          * 
          */
        submitHandler: null,
        /**
         * 为每个字段设置统一触发验证方式（也可在fields中为每个字段单独定义），默认是live配置的方式，数据改变就改变
         * 也可以指定一个或多个（多个空格隔开） 'focus blur keyup'
         */
        trigger: 'blur',
        /**
         * Number类型  为每个字段设置统一的开始验证情况，当输入字符大于等于设置的数值后才实时触发验证
         */
        threshold: 2
    };
   var str = JSON.stringify(config.fields);
            /**如果fields里有remote配置项则对其改造*/
            var remoteNum = 0;
            for(var i =0;i<Object.keys(config.fields).length;i++){
              if(Object.keys(config.fields)[i]==="remote")remoteNum++;
            }
            if(str.match(/"remote"/g)&&str.match(/"remote"/g).length-remoteNum){
              var ajaxOption = {
                type: "POST",
              dataType: "json",
              timeout: 300000
              };
              for (obj in config.fields) {
                if(config.fields[obj].validators.remote===undefined)continue;
                if(config.fields[obj].validators.remote.data!==undefined){
                  ajaxOption['data'] = setCommData(config.fields[obj].validators.remote.url,config.fields[obj].validators.remote.data);
                }
                $.extend(config.fields[obj].validators.remote,ajaxOption);
              }
            }
    $.extend(option,config);
    $('#'+id).bootstrapValidator(option);
    return $('#'+id).data('bootstrapValidator');
  }

  /**
   * 启动校验
   * @param  {[type]} param [id或者bootstrapValidator]
   * @return {[type]}       [bootstrapValidator]
   */
  XHUtils.validate = function(param){
    if(param instanceof Object)return param.validate();
    if(typeof(param)==="string")return $('#'+param).bootstrapValidator('validate').data("bootstrapValidator");
  }

  /**
   * 检测是否通过校验
   * @param  {[type]}  param [id或者bootstrapValidator]
   * @return {Boolean}       [boolean]
   */
  XHUtils.isValid = function(param){
    return XHUtils.validate(param).isValid();
  }
})(jQuery);