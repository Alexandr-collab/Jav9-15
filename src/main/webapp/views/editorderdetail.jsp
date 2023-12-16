<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Детали заказов</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Order Detail</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <!-- jQuery -->
    <script defer src="js/jquery.min.js"></script>
    <!-- Bootstrap JS + Popper JS -->
    <script defer src="js/bootstrap.min.js"></script>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.mi
n.js"></script>
</head>
<body>
<div class="container-fluid back-container">
    <jsp:include page="/views/header.jsp" />
    <div class="container-fluid back-container">
        <div class="row justify-content-start ">
            <div class="col-8 border bg-light px-4">
                <h3>Детали заказов:</h3>
                <table class="table">
                    <thead>
                    <th scope="col">Код</th>
                    <th scope="col">Товар</th>
                    <th scope="col">Заказ</th>
                    <th scope="col">Цена заказа</th>
                    <th scope="col">Скидка</th>
                    </thead>
                    <tbody>
                    <c:forEach var="ord" items="${orderDetails}">
                        <tr>
                            <td>${ord.getId()}</td>
                            <td>${ord.getProduct().getProductName()}</td>
                            <td>${ord.getOrders().getCustomer()}</td>
                            <td>${ord.getUnitPrice()}</td>
                            <td>${ord.getDiscount()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-4 border px-4 a-color">
                <form method="POST" action="">
                    <h3>Редактировать детали заказов:</h3>
                    <br>
                    <div class="mb-3 row">
                        <label for="idOD"
                               class="col-sm-3 col-form-label">Цена заказа</label>
                        <div class="col-sm-7">
                            <input type="text"
                                   class="form-control" readonly id="idOD"
                                   name="idOD" value="${odEdit.getId()}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputCategory"
                               class="col-sm-3 col-form-label">Товар</label>
                        <div class="col-sm-7">
                            <select name="productField" class="form-control">
                                <option value="${odEdit.product}">${odEdit.product.getProductName()}</option>
                                <c:forEach var="prod" items="${products}">
                                    <option value="${prod}">
                                        <c:out value="${prod.getProductName()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputCategory"
                               class="col-sm-3 col-form-label">Заказ</label>
                        <div class="col-sm-7">
                            <select name="orderField" class="form-control">
                                <option value="${odEdit.orders}">${odEdit.orders.getCustomer()}</option>
                                <c:forEach var="ord" items="${orders}">
                                    <option value="${ord}">
                                        <c:out value="${ord.getCustomer()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="unitPrice"
                               class="col-sm-3 col-form-label">Цена заказа</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control"
                                   id="unitPrice" name="unitPrice"  value="${odEdit.getUnitPrice()}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="discount"
                               class="col-sm-3 col-form-label">Скидка</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control"
                                   id="discount" name="discount"  value="${odEdit.getDiscount()}"/>
                        </div>
                    </div>
                    <p> <br>
                        <button type="submit"
                                class="btn btn-primary">Добавить</button>
                        <a href='<c:url value="/orderdetail" />' role="button" class="btn btn-secondary">Отменить</a>
                    </p>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="/views/footer.jsp" />
</div>
</body>
</html>