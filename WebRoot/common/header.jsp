<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
    pageContext.setAttribute("basePath", request.getContextPath()+"/") ;
%>
    <script type="text/javascript" src="${basePath}js/jquery/jquery-1.8.0.js"></script>
    <link href="${basePath}css/skin1.css" rel="stylesheet" type="text/css" />