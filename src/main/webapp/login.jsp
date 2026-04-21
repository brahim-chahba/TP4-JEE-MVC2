<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
</head>
<body>

	<div class="login-box">
		<h2>Connexion</h2>

		<!-- Message d'erreur si login échoué -->
		<c:if test="${erreur != null}">
			<div class="erreur">${erreur}</div>
		</c:if>

		<form action="login" method="post">
			<label>Login :</label> 
			<input type="text" name="login"
				placeholder="Entrez votre login" required /> 
				<label>Mot de passe :</label> 
				<input type="password" name="password"
				placeholder="Entrez votre mot de passe" required /> 
				<input type="submit" value="Se connecter" />
		</form>
	</div>

</body>
</html>