<%--
  Created by IntelliJ IDEA.
  User: 18400
  Date: 2020/3/11
  Time: 16:14
 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <script src="./js/jquery-3.3.1.js"></script>
    <script>

            $.ajax({
                    url: "book/findByAll",//向本地的json文件发送请求
                    type: "POST",
                    dataType: "json",
                    success: function (data) {
                        $(data).each(
                            function (i, values) {
                                $("#list").append(
                                    "<tr><td>"+values.id+"</td>"
                                    + "<td>" + values.bookName + "</td>"
                                    + "<td>" + values.bookPrice + "</td>"
                                    + "<td>" + values.bookAuthor + "</td>"
                                    + "<td>" + values.bookType + "</td>"
                                    + "<td>" + values.bookPublish + "</td>"
                                    + "<td>" + '<input  value="删除" type="button" id= "delete" name="删除" class="del_book" >'+"</td></tr>"


                            )

                            })


                        $(function (){

                            $("#list").on("click","tr",function(){

                                var book_id = $(this).children().eq(0).html();
                                alert("确认删除吗？");
                                $.ajax({
                                    url:"book/deleteBook",
                                    type:"POST",
                                    dataType: "json",
                                    data:{"id":book_id},
                                    success:function () {
                                        alert("删除成功！");
                                        $(this).parent().parent().remove();
                                        window.location.href="/index.jsp";
                                    }

                                })

                            })
                        })

                    }



                })







    function check () {
        $("#list").empty();
        var bookType = $("#bookType").val();

        $.ajax({
                 url: "book/findByType",//向本地的json文件发送请求
                 type: "POST",
                 data:{"bookType":bookType},
                 dataType:"json",
                 success: function (data) {
                     $(data).each(
                         function (i, values) {
                             $("#list").append(
                                 "<tr><td>"+values.id+"</td>"
                                 +"<td>"+values.bookName+"</td>"
                                 +"<td>"+values.bookPrice+"</td>"
                                 +"<td>"+values.bookAuthor+"</td>"
                                 +"<td>"+values.bookType+"</td>"
                                 +"<td>"+values.bookPublish+"</td>"
                                 +"<td>" + '<input  value="删除" type="button" id= "delete" name="删除" class="del_book" >'+"</td></tr>"

                             );

                         })
                 }
        });
    }

    function add() {

        $.ajax({
           success:function () {
                window.location.href="/add.jsp";
           }
        });
    }


</script>

    <style type="text/css">
        .selected {
            background: #FF6500;
            color: #fff;
        }

        #ll
        {
            background-color: #CCE8EB;

            text-align: center;


        }

        table

        {

            border-collapse: collapse;

            margin: 0 auto;

            text-align: center;

        }

        table td, table th

        {

            border: 1px solid #cad9ea;

            color: #666;

            height: 30px;

        }

        table thead th

        {

            background-color: #CCE8EB;

            width: 100px;

        }

        table tr:nth-child(odd)

        {

            background: #fff;

        }

        table tr:nth-child(even)

        {

            background: #F5FAFA;

        }

    </style>

</head>
<body>
<div id="ll">
<div >

    书本类型：<input type="text" name="bookType" id="bookType" placeholder="请输入类型："></td>
         <input type="submit" value="查询" onclick="check()">
         <input type="button" value="添加" onclick="add()">

</div>

<div class="list">
    <table class="table">
        <thead>
        <tr>
            <th id="">编号</th>
            <th>书名</th>
            <th>价格</th>
            <th>作者</th>
            <th>类型</th>
            <th>出版社</th>
            <th>删除</th>
        </tr>
        </thead>
        <tbody id="list"></tbody>
    </table>
    </div>

</div>
</body>
</html>
