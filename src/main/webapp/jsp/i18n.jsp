<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="locale" scope="session" value="${not empty sessionScope.locale ? sessionScope.locale : 'en_US'}"/>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="/i18n/common" var="common" scope="session"/>
<fmt:setBundle basename="/i18n/particular" var="particular" scope="session"/>
