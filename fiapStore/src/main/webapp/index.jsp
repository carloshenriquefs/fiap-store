<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>FiapStore</title>
    <meta name="viewport" content="with=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <div class="mt-5 ms-5 me-5">
        <div class="card mb-3">
            <div class="card-header">
                LISTA DE PRODUTOS
            </div>
            <div class="card-body">
                <h5 class="card-title">Gestão de produtos eficiente</h5>
                <p class="card-text">Mantenha os dados dos seus produtos sempre atualizados e acessiveis</p>
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Nome</th>
                        <th class="text-end">Quantidade</th>
                        <th class="text-end">Valor</th>
                        <th class="text-center">Data de fabricação</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>Mouse wireless</th>
                        <th class="text-end">100</th>
                        <th class="text-end">30.99</th>
                        <th class="text-center">15-09-2024</th>
                    </tr>
                    <tr>
                        <th>Monitor LED 23P</th>
                        <th class="text-end">16</th>
                        <th class="text-end">700.00</th>
                        <th class="text-center">21-11-2023</th>
                    </tr>
                    <tr>
                        <th>Impressora HP</th>
                        <th class="text-end">6</th>
                        <th class="text-end">768.99</th>
                        <th class="text-center">09-04-2024</th>
                    </tr>
                    </tbody>
                </table>
                <a href="cadastro-produto.jsp" class="btn btn-primary">Adicionar produto</a>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
<script src="resources/js/bootstrap.bundle.js"></script>
</body>
</html>