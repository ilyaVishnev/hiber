<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 10.09.2018
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List</title>
    <style>
        DIV.table {
            display: table;
        }

        FORM.tr, DIV.tr {
            display: table-row;
        }

        SPAN.td {
            display: table-cell;
        }

        #made {
            padding-top: 2%;
        }
    </style>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            $.ajax({
                method: 'POST',
                dataType: 'json',
                url: '/list',
                success: function (data) {
                    var cars = data.array;
                    var content = " <div class=\"tr\">\n" +
                        "       <span class=\"td\">\n" +
                        "          Модель\n" +
                        "    </span>\n" +
                        "        <span class=\"td\">\n" +
                        "          Цена\n" +
                        "    </span>\n" +
                        "        <span class=\"td\">\n" +
                        "          Статус\n" +
                        "    </span>\n" +
                        "        <span class=\"td\">\n" +
                        "          Фото\n" +
                        "    </span>\n" +
                        "    </div>";
                    cars.forEach(function (car, index, cars) {
                        content += "<form action=\"${pageContext.servletContext.contextPath}/desc\" method=\"POST\" class=\"tr\">" +
                            "<span class=\"td\"><input type=\"text\" name=\"model\" value=\"" + car.model + "\" disabled /></span>" +
                            "<span class=\"td\"><input type=\"text\" name=\"price\" value=\"" + car.price + "\" disabled /></span>" +
                            "<span class=\"td\"><input type=\"text\" name=\"status\" value=\"" + car.status + "\" disabled /></span>" +
                            "<span class=\"td\"><input type=\"image\" src=\"" + car.photo + "\" width=\"30%\" \n" +
                            "   height=\"25%\"  alt=\"Submit Form\" /></span>" + "<input type=\"hidden\" name=\"car_id\" value=\"" +
                            car.id + "\"/></form>";
                    })
                    $('#table').empty().html(content);
                }
            })
        })
    </script>
</head>
<body>
<div class="table" id="table">
</div>
</br>
<form action="${pageContext.servletContext.contextPath}/create" method="get">
    <p><input type="submit" value="Добавить авто" name="add" class="btn btn-default"></p>
</form>
</body>
</html>
