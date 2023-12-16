<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Заказы</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Orders</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <!-- jQuery -->
    <script defer src="js/jquery-3.6.4.js"></script>
    <!-- Bootstrap JS + Popper JS -->
    <script defer src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid back-container">
    <jsp:include page="/views/header.jsp" />
    <div class="container-fluid back-container">
        <div class="row justify-content-start ">
            <div class="col-8 border bg-light px-4">
                <h3>Список Заказов:</h3>
                <table class="table">
                    <thead>
                    <th scope="col">Код</th>
                    <th scope="col">Покупатель</th>
                    <th scope="col">Сотрудник</th>
                    <th scope="col">Дата заказа</th>
                    <th scope="col">Дата продажи</th>
                    <th scope="col">Название магазина</th>
                    <th scope="col">Адрес магазина</th>
                    <th scope="col">Редактировать</th>
                    <th scope="col">Удалить</th>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <td>${order.getId()}</td>
                            <td>${order.getCustomer()}</td>
                            <td>${order.getEmployee()}</td>
                            <td>${order.getOrderDate()}</td>
                            <td>${order.getShipDate()}</td>
                            <td>${order.getShipName()}</td>
                            <td>${order.getShipAddress()}</td>
                            <td width="20"><a href="<c:url value="/editorder?id=${order.getId()}" />" role="button"
                                              class="btn btn-outline-primary">
                                <img alt="Редактировать"
                                     src="img/edit.png"></a></td>
                            <td width="20"><a href="<c:url value="/deleteorder?id=${order.getId()}" />" role="button"
                                              class="btn btn-outline-primary">
                                <img alt="Удалить"
                                     src="img/delete.png" onclick="return confirm('Удалить заказ с ID: ' + ${order.getId()} + '?')"></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-4 border px-4 a-color">
                <form method="POST" action="">
                    <h3>Новый Заказ</h3>
                    <div class="mb-3">
                        <br> <label for="customer"
                                    class="col-sm-3 col-form-label">Покупатель</label>
                        <div class="col-sm-6">
                            <input type="text" name="customer"
                                   class="form-control" id="customer" />
                        </div>
                        <label for="employee"
                               class="col-sm-3 col-form-label">Сотрудник</label>
                        <div class="col-sm-6">
                            <input type="text" name="employee"
                                   class="form-control" id="employee" />
                        </div>
                        <label for="orderDate"
                               class="col-sm-3 col-form-label">Дата заказа</label>
                        <div class="col-sm-6">
                            <input type="text" name="orderDate"
                                   class="form-control" id="orderDate" />
                        </div>
                        <label for="shipDate"
                               class="col-sm-3 col-form-label">Дата продажи</label>
                        <div class="col-sm-6">
                            <input type="text" name="shipDate"
                                   class="form-control" id="shipDate" />
                        </div>
                        <label for="shipName"
                               class="col-sm-3 col-form-label">Название магазина</label>
                        <div class="col-sm-6">
                            <input type="text" name="shipName"
                                   class="form-control" id="shipName" />
                        </div>
                        <label for="shipAddress"
                               class="col-sm-3 col-form-label">Адрес магазина</label>
                        <div class="col-sm-6">
                            <input type="text" name="shipAddress"
                                   class="form-control" id="shipAddress" />
                        </div>
                    </div>
                    <p>
                        <br> <br> <br>
                        <button type="submit"
                                class="btn btn-primary">Добавить</button>
                        <br>
                    </p>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="/views/footer.jsp" />
</div>
</body>
</html>