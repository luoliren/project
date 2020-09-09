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




            var url=window.location.href;
            var book_id=GetParam(url,"id");


            $.ajax({
                url:"book/transfer",
                type:"POST",
                dataType: "json",
                data:{"id":book_id},
                success:function (data) {



                    var bookName = data.bookName;
                    var bookType = data.bookType;
                    var bookPrice= data.bookPrice;
                    var bookAuthor = data.bookAuthor;
                    var booKPublish = data.bookPublish;
                    var Id = book_id;

                    $('#bookName').val(bookName);
                    $('#bookPrice').val(bookPrice);
                    $('#bookType').val(bookType);
                    $('#bookAuthor').val(bookAuthor);
                    $('#bookPublish').val(booKPublish);
                    $('#Id').val(Id);

                }
        })



        function GetParam(url, id) {
            url = url+ "";
            regstr = "/(\\?|\\&)" + id + "=([^\\&]+)/";
            reg = eval(regstr);
            //eval可以将 regstr字符串转换为 正则表达式
            result = url.match(reg);
            if (result && result[2]) {
                return result[2];
            }
        }


        function check() {

            var bookName = $("#bookName").val();//获取表单的输入值;
            var bookPrice = $("#bookPrice").val();//获取表单的输入值;
            var bookType = $("#bookType").val();//获取表单的输入值;
            var bookAuthor = $("#bookAuthor").val();//获取表单的输入值;
            var bookPublish = $("#bookPublish").val();//获取表单的输入值;

            $.ajax({
                url: "book/updateBook",
                type: "POST",
                dataType: "json",
                data: {"id": book_id,"bookName":bookName,"bookPrice":bookPrice,"bookType":bookType
                ,bookAuthor:bookAuthor,bookPublish:bookPublish},
                success: function (data) {

                            alert("确认修改吗？")
                            window.location.href="/add.jsp";


                }
            })
        }

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

<div class="rg_center">
    <div class="rg_form">
        <!--定义表单 -->
        <form onsubmit="return false" action="##" method="post" id="enroll">
            <table >
                <input id="bookid" type="text" hidden>
                <tr>

                    <th class="th_left"> <label for="Id">编号</label></th>
                    <td class="td_right"><input type="text" name="bookId" id="Id" disabled="disabled"></td>


                </tr>

                <tr>

                    <th class="th_left"><label for="bookName">书籍名称</label></th>
                    <td class="td_right"><input type="text" class="bookName" name="bookName" id="bookName" >
                    </td>


                </tr>
                <tr>

                    <th class="th_left"> <label for="bookPrice">价格</label></th>
                    <td class="td_right"><input type="text" name="bookPrice" id="bookPrice"></td>


                </tr>
                <tr>

                    <th class="th_left"><label for="bookAuthor">作者</label></th>
                    <td class="td_right"><input type="text" name="bookAuthor" id="bookAuthor" ></td>

                </tr>
                <tr>

                    <th class="th_left"><label for="bookType">类型</label></th>
                    <td class="td_right"><input type="text" name="bookType" id="bookType" ></td>

                </tr>
                <tr>

                    <th class="th_left"><label for="bookPublish">出版社</label></th>
                    <td class="td_right"><input type="text" name="bookPublish" id="bookPublish" ></td>

                </tr>

                <tr><th colspan="2"><input type="submit" id="btn_sub" value="修改" align="center" onclick="check()"></th></tr>
            </table>
        </form>
    </div>
</div>


</body>
</html>

