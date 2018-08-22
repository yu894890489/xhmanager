<html>

<head>
	<script type="text/javascript" src="../static/js/jquery-1.9.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" pageEncodeing="utf-8">  
<title>Insert title here</title>  
<script type="text/javascript">  
    function ajaxTest(){  
        $.ajax({  
        data:"name="+$("#name").val(),  
        type:"GET",  
        dataType: 'json',  
        url:"comm/login.do",  
        error:function(data){ 
            alert("出错了！！:"+data.msg);  
        },  
        success:function(data){  
        	console.log(data);
            alert("success:"+data.msg);  
            $("#result").html(data.msg) ;  
        }  
        });  
    }  
</script>  
</head>  
<body>  
    <input type="text" name="name" id="name"/>  
    <input type="submit" value="登录" onclick="ajaxTest();"/>  
    <div id="result"></div>  
</body>  
</html>
