<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Категории</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Category</title>
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
                <h3>Список Категорий:</h3>
                <table class="table">
                    <thead>
                    <th scope="col">Код</th>
                    <th scope="col">Название категории</th>
                    <th scope="col">Описание</th>
                    </thead>
                    <tbody>
                    <c:forEach var="cat" items="${categories}">
                        <tr>
                            <td>${cat.getId()}</td>
                            <td>${cat.getCategoryName()}</td>
                            <td>${cat.getDiscription()}</td>
                            <td width="20"><a href="#" role="button"
                                              class="btn btn-outline-primary">
                                <img alt="Редактировать"
                                     src="img/edit.png"></a></td>
                            <td width="20"><a href="#" role="button"
                                              class="btn btn-outline-primary">
                                <img alt="Удалить"
                                     src="img/delete.png"></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-4 border px-4 a-color">
                <form method="POST" action="">
                    <h3>Редактировать категорию</h3>
                    <div class="mb-3">
                        <br>
                        <label for="idcategory"
                               class="col-sm-3 col-form-label">Номер</label>
                        <div class="col-sm-6">
                            <input type="text" name="idcategory"
                                   class="form-control" readonly id="idcategory" value="${categoryEdit.getId()}"/>
                        </div>
                        <label for="categoryName"
                                    class="col-sm-3 col-form-label">Название категории</label>
                        <div class="col-sm-6">
                            <input type="text" name="categoryName"
                                   class="form-control" id="categoryName" value="${categoryEdit.getCategoryName()}"/>
                        </div>
                        <label for="discription"
                               class="col-sm-3 col-form-label">Описание категории</label>
                        <div class="col-sm-6">
                            <input type="text" name="discription"
                                   class="form-control" id="discription" value="${categoryEdit.getDiscription()}"/>
                        </div>
                    </div>
                    <p>
                        <br> <br> <br>
                        <button type="submit"
                                class="btn btn-primary">Добавить</button>
                        <a href='<c:url value="/category" />' role="button" class="btn btn-secondary">Отменить</a>
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