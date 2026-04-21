<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Gestion des Produits MVC2</title>
</head>
<body>

<h2>Gestion des Produits</h2>

<p>
    Bienvenue, <strong>${sessionScope.utilisateur.login}</strong> 
    (${sessionScope.utilisateur.role}) 
    | <a href="logout">Logout</a>
</p>

<hr/>

<c:if test="${sessionScope.utilisateur.role == 'ADMIN'}">

    <%-- Default action is "add" --%>
    <form action="DispatcherServlet?action=add" method="post" id="produitForm">
        <input type="hidden" name="idProduit" value="${produitEdit.idProduit}" />

        Nom: 
        <input type="text" name="nom" value="${produitEdit.nom}" required />

        Description: 
        <input type="text" name="description" value="${produitEdit.description}" required />

        Prix: 
        <input type="text" name="prix" value="${produitEdit.prix}" required />

        <input type="submit" value="Enregistrer" />
    </form>

    <%-- Si on est en mode edition, on change l'action vers "update" --%>
    <c:if test="${produitEdit != null}">
        <script>
            document.getElementById('produitForm').action = 'DispatcherServlet?action=update';
        </script>
    </c:if>

    <hr/>
</c:if>

<form action="DispatcherServlet" method="get">
    <%-- Tell the servlet to use handelList --%>
    <input type="hidden" name="action" value="list">
    ID: <input type="text" name="idProduit" />
    <input type="submit" value="Rechercher" />
</form>

<c:if test="${not empty message}">
    <p style="color: red;">${message}</p>
</c:if>

<hr/>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Description</th>
        <th>Prix</th>
        <th>Actions</th>
    </tr>

    <c:forEach var="p" items="${listeProduits}">
        <tr>
            <td>${p.idProduit}</td>
            <td>${p.nom}</td>
            <td>${p.description}</td>
            <td>${p.prix}</td>
            <td>
                <c:if test="${sessionScope.utilisateur.role == 'ADMIN'}">
                    <a href="DispatcherServlet?action=edit&idProduit=${p.idProduit}">Modifier</a> | 
                    <a href="DispatcherServlet?action=delete&idProduit=${p.idProduit}" 
                       onclick="return confirm('Voulez-vous vraiment supprimer ce produit ?');">
                        Supprimer
                    </a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>