<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Товары</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Product</title>
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
                <h3>Список Товаров:</h3>
                <table class="table">
                    <thead>
                    <th scope="col">Код</th>
                    <th scope="col">Категория</th>
                    <th scope="col">Название товара</th>
                    <th scope="col">Цена за ед.</th>
                    <th scope="col">Кол-во в упаковке</th>
                    <th scope="col">Кол-во в заказе</th>
                    </thead>
                    <tbody>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>${product.getId()}</td>
                            <td>${product.category.getCategoryName()}</td>
                            <td>${product.getProductName()}</td>
                            <td>${product.getUnitPrice()}</td>
                            <td>${product.getUnitInStock()}</td>
                            <td>${product.getUnitInOrder()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-4 border px-4 a-color">
                <form method="POST" action="">
                    <h3>Редактировать товар:</h3>
                    <br>
                    <div class="mb-3 row">
                        <label for="idProduct"
                               class="col-sm-3 col-form-label">Номер</label>
                        <div class="col-sm-7">
                            <input type="text" readonly class="form-control"
                                   id="idProduct" name="idProduct" value="${productEdit.getId()}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputCategory"
                               class="col-sm-3 col-form-label">Категория</label>
                        <div class="col-sm-7">
                            <select name="categoryField" class="form-control">
                                <option value="${productEdit.category}">${productEdit.category.getCategoryName()}</option>
                                <c:forEach var="cat" items="${categories}">
                                    <option value="${cat}">
                                        <c:out value="${cat.getCategoryName()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="productName"
                               class="col-sm-3 col-form-label">Название товара</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control"
                                   id="productName" name="productName" value="${productEdit.getProductName()}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="unitPrice"
                               class="col-sm-3 col-form-label">Цена за ед.</label>
                        <div class="col-sm-7">
                            <input type="text"
                                   class="form-control" id="unitPrice"
                                   name="unitPrice" value="${productEdit.getUnitPrice()}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="unitInStock"
                               class="col-sm-3 col-form-label">Кол-во в упаковке</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control"
                                   id="unitInStock" name="unitInStock" value="${productEdit.getUnitInStock()}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="unitInOrder"
                               class="col-sm-3 col-form-label">Кол-во в заказе</label>
                        <div class="col-sm-7">
                            <input type="text"
                                   class="form-control" id="unitInOrder"
                                   name="unitInOrder" value="${productEdit.getUnitInOrder()}"/>
                        </div>
                    </div>
                    <p> <br>
                        <button type="submit"
                                class="btn btn-primary">Добавить</button>
                        <a href='<c:url value="/products" />' role="button" class="btn btn-secondary">Отменить</a>
                    </p>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="/views/footer.jsp" />
</div>
</body>
</html>