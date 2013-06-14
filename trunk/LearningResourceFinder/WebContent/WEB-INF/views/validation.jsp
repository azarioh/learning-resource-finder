<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %> 
<c:choose>
    <c:when test="${result eq 'VALID_AND_LOGGED' }">
        Votre compte utilisateur a �t� valid� avec succ�s. Vous pouvez � pr�sent l'utiliser. Vous venez d'�tre connect� automatiquement.
    </c:when>
    <c:when test="${result eq 'VALID_BUT_NOT_LOGGED' }">
        Votre compte utilisateur a �t� valid� avec succ�s. Vous pouvez � pr�sent l'utiliser. Cependant, il ne nous a pas �t� possible de vous connecter automatiquement et nous vous invitons � le faire manuellement XXXXXXX  LINK LOGIN
    </c:when>
    <c:when test="${result eq 'ALREADY_VALIDATED' }">
        Votre compte utilisateur avait d�j� �t� valid� dans le pass�. Vous pouvez vous connecter manuellement <a href ="login">ici</a>
        
    </c:when>
    <c:when test="${result eq 'INVALID_CODE' }">
        Votre compte n'a <span class="error">PAS</span> �t� valid�. Vous avez transmis le code suivant via l'URL: '${code}'<br/>
        Veuillez utiliser le lien fournit dans votre e-mail d'inscription (en cliquant sur le lien ou en le copiant en entier dans la barre d'adresse de votre navigateur).
    </c:when>
</c:choose>