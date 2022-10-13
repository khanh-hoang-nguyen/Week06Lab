<%-- 
    Document   : register.jsp
    Created on : 10-Oct-2022, 9:28:57 PM
    Author     : Khanh Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <form action="ShoppingList" method="post">
            Username: <input type="text" name="username" >
            <input type="hidden" name="action" value="register">
            <br>
            <input type="submit" value="Register">
        </form>
        <p>${message}</p>
    </body>
</html>
