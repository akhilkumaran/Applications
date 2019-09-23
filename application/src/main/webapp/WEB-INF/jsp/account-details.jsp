<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<%@ page isELIgnored="false" %>
<meta charset="ISO-8859-1">
<title>Banking App</title>
</head>
<body>
<h2>Please save below details for future transactions</h2>
    <table>
        <tr>
            <td>Name :</td>
            <td>${name}</td>
        </tr>
        
        <tr>
            <td>UserID :</td>
            <td>${userId}</td>
        </tr>
        
        <tr>
            <td>Account Numbers :</td>
            <td>${accountNums}</td>
        </tr>
        
    </table>
    <a href="banking-home.html">Go to Application</a>

</body>
</html>