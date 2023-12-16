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
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-4 border px-4 a-color">
                <form method="POST" action="">
                    <h3>Редактировать Заказ</h3>
                    <div class="mb-3">
                        <br>
                        <label for="OrderId"
                               class="col-sm-3 col-form-label">Номер</label>
                        <div class="col-sm-6">
                            <input type="text" name="OrderId" readonly
                                   class="form-control" id="OrderId" value="${orderEdit.getId()}"/>
                        </div>
                        <label for="customer"
                                    class="col-sm-3 col-form-label">Покупатель</label>
                        <div class="col-sm-6">
                            <input type="text" name="customer"
                                   class="form-control" id="customer" value="${orderEdit.getCustomer()}"/>
                        </div>
                        <label for="employee"
                               class="col-sm-3 col-form-label">Сотрудник</label>
                        <div class="col-sm-6">
                            <input type="text" name="employee"
                                   class="form-control" id="employee" value="${orderEdit.getEmployee()}"/>
                        </div>
                        <label for="orderDate"
                               class="col-sm-3 col-form-label">Дата заказа</label>
                        <div class="col-sm-6">
                            <input type="text" name="orderDate"
                                   class="form-control" id="orderDate" value="${orderEdit.getOrderDate()}"/>
                        </div>
                        <label for="shipDate"
                               class="col-sm-3 col-form-label">Дата продажи</label>
                        <div class="col-sm-6">
                            <input type="text" name="shipDate"
                                   class="form-control" id="shipDate" value="${orderEdit.getShipDate()}"/>
                        </div>
                        <label for="shipName"
                               class="col-sm-3 col-form-label">Название магазина</label>
                        <div class="col-sm-6">
                            <input type="text" name="shipName"
                                   class="form-control" id="shipName" value="${orderEdit.getShipName()}"/>
                        </div>
                        <label for="shipAddress"
                               class="col-sm-3 col-form-label">Адрес магазина</label>
                        <div class="col-sm-6">
                            <input type="text" name="shipAddress"
                                   class="form-control" id="shipAddress" value="${orderEdit.getShipAddress()}"/>
                        </div>
                    </div>
                    <p>
                        <br> <br> <br>
                        <button type="submit"
                                class="btn btn-primary">Добавить</button>
                        <a href='<c:url value="/orders" />' role="button" class="btn btn-secondary">Отменить</a>
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