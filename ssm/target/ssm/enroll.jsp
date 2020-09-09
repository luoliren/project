<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <script src="./js/jquery-3.3.1.js"></script>

    <script>
        function check() {

            var username = $("#username").val();//获取表单的输入值;
            var password = $("#password").val();//获取表单的输入值;
            var rePassword = $("#rePassword").val();//获取表单的输入值;

            $.ajax({
                url:"action/enroll",
                type:"POST",
                data:{"username":username,"password":password,"rePassword":rePassword},
                success: function(data){
                    if (data == "true"){
                        alert("注册成功！");
                        window.location.href="/info.jsp";
                    }else if (data == "false"){
                        alert("该ID太受欢迎了,请您再选一个，重新注册！");
                        window.location.href="/enroll.jsp";
                    }else if (data == "password not rePassword"){
                        alert("两次输入的密码不一致！请重新注册！")
                        window.location.href="/enroll.jsp";
                    }
                }
            })
        }
    </script>
    <style>
        *{
            margin: 0px;
            padding: 0px;
            box-sizing: border-box;
        }
        body{
            background: url("/images/1.jpg") no-repeat center;
        }
        .rg_layout{
            width: 900px;
            height: 500px;
            border: 8px solid #EEEEEE;
            background: white;
            /*    让div水平居中*/
            margin: auto;
            padding: 15px;
            margin-top: 15px;
        }
        .rg_left{
            /*border: 1px solid red;*/
            float: left;
        }
        .rg_left >p:first-child{
            color: #Ffd026;
            font-size: 20px;
            margin: 15px;
        }
        .rg_left >p:last-child{
            color: #A6A6A6;
            font-size: 20px;
        }
        .rg_center{
            /*border: 1px solid red;*/
            float: left;
            width: 450px;

        }
        .rg_right{
            /*border: 1px solid red;*/
            float: right;
        }
        .rg_right > p:first-child {
            font-size: 10px;
            margin: 15px;
        }
        .rg_right p a {
            color: pink;
        }
        .th_left{
            width: 100px;
            text-align: right;
            height: 45px;
        }
        .td_right{
            padding-left: 50px;
        }
        #username,#password,#rePassword{
            width: 251px;
            height: 32px;
            border: 1px solid #A6A6A6;
            /*    设置边框圆角*/
            border-radius: 5px;
            padding-left: 15px;
        }
        #checkcode {
            width: 110px;
        }
        #img_check{
    
            height: 32px;
            /*    垂直居中*/
            vertical-align: middle;
        }
        #btn_sub{
            width: 150px;
            height: 40px;
            background-color: #Ffd026;
            border: 1px solid #Ffd026;
        }
    </style>


</head>
<body>
<div class="rg_layout">
    <div class="rg_left">
        <p>新用户注册</p>
        <p>USER REGISTER</p>
    </div>
    <div class="rg_center">
        <div class="rg_form">
            <!--定义表单 -->
            <form onsubmit="return false" action="##" method="post" id="enroll">
                <table >
                    <tr>

                        <th class="th_left"><label for="username">用户名</label></th>
                        <td class="td_right"><input type="text" name="username" id="username" placeholder="请输入用户名">

                        </td>


                    </tr>
                    <tr>
    
                        <th class="th_left"> <label for="password">密码</label></th>
                        <td class="td_right"><input type="password" name="password" id="password" placeholder="请输入密码"></td>


                    </tr>
                    <tr>
    
                        <th class="th_left"><label for="rePassword">确认密码</label></th>
                        <td class="td_right"><input type="text" name="rePassword" id="rePassword" placeholder="请再次输入密码"></td>

                    </tr>

                    <tr><th colspan="2"><input type="submit" id="btn_sub" value="注册" align="center" onclick="check()"></th></tr>
                </table>
            </form>
        </div>
    </div>
    <div class="rg_right">
        <p>已有帐号?<a href="/info.jsp">立即登录</a></p>
    </div>





</div>






</body>
</html>