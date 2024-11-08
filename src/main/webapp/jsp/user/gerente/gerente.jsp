<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    session = request.getSession(false);
    if (session == null || !"Gerente".equals(session.getAttribute("usuario"))) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="pt-BR">
    <%@ include file="/jsp/globais/header.jsp" %>
    <%@ include file="/jsp/globais/nav.jsp" %>

    <head>
        <title>Página do Gerente</title>
    </head>

    <div>
        <h2>Bem-vindo, Gerente!</h2>
        <p>Email: <%= session.getAttribute("email") %></p>
        <a href="logout.jsp">Sair</a>
    </div>
</html>
