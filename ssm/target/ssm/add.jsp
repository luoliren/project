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
        function search() {
            $.ajax({
                success:function () {
                    window.location.href="/index.jsp";
                }
            });
        }

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
                            + "<td>" + '<input  value="更新" type="button" id= "update" name="更新" class="update_book" >'+"</td></tr>"


                        )

                    })



                $(function (){

                    $("#list").on("click","tr",function(){

                        var book_id = $(this).children().eq(0).html();
                       // alert(book_id);
                        $.ajax({
                            url:"book/transfer",
                            type:"POST",
                            dataType: "json",
                            data:{"id":book_id},
                            success:function (data) {
                                var id = data.id;
                                var url="/update.jsp"+"?id="+id;
                                window.location.href=url;

                               // alert(url);


                            }
                        })

                    })
                })

            }


        })




        function check() {
           var bookName = $("#bookName").val();//获取表单的输入值;
           var bookPrice = $("#bookPrice").val();//获取表单的输入值;
           var bookAuthor = $("#bookAuthor").val();//获取表单的输入值;
           var bookType = $("#bookType").val();//获取表单的输入值;
           var bookPublish = $("#bookPublish").val();//获取表单的输入值;
           $("#list").empty();
           alert("添加成功！");
           $.ajax({
               url: "book/saveBook",//向本地的json文件发送请求
               type: "POST",
               dataType: "json",
               data:{"bookName":bookName,"bookPrice":bookPrice,"bookAuthor":bookAuthor
                        ,"bookType":bookType,"bookPublish":bookPublish},
               success: function (data) {

                   $(data).each(
                       function (i, values) {
                           $("#list").append(
                               "<tr><td>" + values.id + "</td>"
                               + "<td>" + values.bookName + "</td>"
                               + "<td>" + values.bookPrice + "</td>"
                               + "<td>" + values.bookAuthor + "</td>"
                               + "<td>" + values.bookType + "</td>"
                               + "<td>" + values.bookPublish + "</td></tr>"
                           );

                       })
               }
           })



        };


    </script>

    <style type="text/css">
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
  <div>
<form method="post">
     书名： <input type="text" name="bookName" id="bookName" placeholder="请输入书名">
     价格： <input type="text" name="bookPrice" id="bookPrice" placeholder="请输入价格">
     作者： <input type="text" name="bookAuthor" id="bookAuthor" placeholder="请输入作者">
     类型： <input type="text" name="bookType" id="bookType" placeholder="请输入类型">
     出版社： <input type="text" name="bookPublish" id="bookPublish" placeholder="请输入出版社">
      <input type="submit" value="添加" onclick="check()">
    <input type="button" value="搜索" onclick="search()">
</form>
      <input type="text" id="AjaxData" hidden>

  </div>
    <div>
        <table class="table">
            <thead>
            <tr>
                <th>编号</th>
                <th>书名</th>
                <th>价格</th>
                <th>作者</th>
                <th>类型</th>
                <th>出版社</th>
                <th>更新</th>

            </tr>
            </thead>
            <tbody id="list"></tbody>
        </table>

    </div>
</div>



</body>
</html>
