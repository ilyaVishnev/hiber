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
            align-content: right;
            width: 74%
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
            var today;
            var photo;
            var id_brand;
            $.ajax({
                method: 'GET',
                dataType: 'json',
                url: '/filterFlist',
                success: function (data) {
                    var content = '<option value="-1" selected>Выберите марку</option>';
                    var array = data.array;
                    today = data.today;
                    id_brand = data.id_brand;
                    photo = data.havePhoto;
                    if (today == 'on') {
                        document.getElementById("today").checked = true;
                    }
                    if (photo == 'on') {
                        document.getElementById("photo").checked = true;
                    }
                    array.forEach(function (brand, index, array) {
                        if (id_brand == brand.id) {
                            content += '<option value=\"' + brand.id + '\" selected>' + brand.brand + '</option>';
                        } else {
                            content += '<option value=\"' + brand.id + '\">' + brand.brand + '</option>';
                        }
                    });
                    $('#brands').empty().html(content);
                }
            })
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
                    var date = new Date();
                    var compareDate = date.getFullYear() + " " + date.getMonth() + " " + date.getDate();
                    cars.forEach(function (car, index, cars) {
                        var filter = true;
                        if (today == 'on') {
                            filter = compareDate == car.date && filter;
                        }
                        if (photo == 'on') {
                            filter = car.photo != "" && filter;
                        }
                        if (id_brand != "-1") {
                            filter = id_brand == car.brand_id && filter;
                        }
                        if (filter) {
                            content += "<form action=\"${pageContext.servletContext.contextPath}/desc\" method=\"POST\" class=\"tr\">" +
                                "<span class=\"td\"><input type=\"text\" name=\"model\" value=\"" + car.model + "\" disabled /></span>" +
                                "<span class=\"td\"><input type=\"text\" name=\"price\" value=\"" + car.price + "\" disabled /></span>" +
                                "<span class=\"td\"><input type=\"text\" name=\"status\" value=\"" + car.status + "\" disabled /></span>" +
                                "<span class=\"td\"><input type=\"image\" src=\"" + car.photo + "\" width=\"30%\" \n" +
                                "   height=\"25%\"  alt=\"photo\" /></span>" + "<input type=\"hidden\" name=\"car_id\" value=\"" +
                                car.id + "\"/></form>";
                        }
                    })
                    $('#table').empty().html(content);
                }
            })
        })
    </script>
</head>
<body>
<div style="float: left; margin-top: 200px;margin-right: 10px">
    <form action="${PageContext.servletContext.contextPath}/filterFlist" method="post">
        <input type="checkbox" name="photo" id="photo" onchange="submit()">only photo<br>
        <input type="checkbox" name="today" id="today" onchange="submit()">only today<br>
        <select id="brands" name="brands" onchange="submit()"></select>
    </form>
</div>
<div class="table" id="table">
</div>
</br>
<form action="${pageContext.servletContext.contextPath}/create" method="get">
    <p><input type="submit" value="Добавить авто" name="add" class="btn btn-default"></p>
</form>
</body>
</html>
