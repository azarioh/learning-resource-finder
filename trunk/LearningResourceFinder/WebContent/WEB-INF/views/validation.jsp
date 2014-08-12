<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %> 
<html>
<body>
<div class="container">

<c:choose>
    <c:when test="${result eq 'VALID_AND_LOGGED' }">
        <p>Merci pour votre inscription. Votre compte utilisateur a �t� valid� avec succ�s. Vous pouvez � pr�sent l'utiliser.</p>
        <p>Vous venez d'�tre connect� automatiquement.</p>
    </c:when>
    <c:when test="${result eq 'VALID_BUT_NOT_LOGGED' }">
        <p>Merci pour votre inscription. Votre compte utilisateur a �t� valid� avec succ�s. Vous pouvez � pr�sent l'utiliser.</p>
        <p>Cependant, il ne nous a pas �t� possible de vous connecter automatiquement et nous vous invitons � le faire manuellement via le menu "Connexion" dans le coin sup�rieur droit de la page</p>
    </c:when>
    <c:when test="${result eq 'ALREADY_VALIDATED' }">
        Votre compte utilisateur avait d�j� �t� valid� dans le pass�. Vous pouvez vous connecter manuellement via le menu "Connexion" dans le coin sup�rieur droit de la page.</a>
    </c:when>
    <c:when test="${result eq 'INVALID_CODE' }">
        <p>Votre compte n'a <strong>PAS</strong> �t� valid�. Vous avez transmis le code suivant via l'URL: '${code}'</p>
        <p>Veuillez utiliser le lien fourni dans votre e-mail d'inscription (en cliquant sur le lien ou en le copiant en entier dans la barre d'adresse de votre navigateur).</p>
    </c:when>
</c:choose>
</div>
</body>
</html>