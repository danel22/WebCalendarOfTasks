<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 10.10.2017
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
<h1>Календарь ведения задач</h1>
  <p>Введите ваши регистрационные данные</p>
  <form name="auth" method="post" action="/calendar/auth">
    <p>Логин <input name="login" id="login" type="text" /></p>
    <p>Пароль <input name="password" id="password" type="password"/></p>
    <p><input type="submit" name="submit" value="ВОЙТИ" class="button"/><br>
    <input type="submit" name="submit" value="Регистрация" class="register" src="registration.jsp"/>
  </form>
  </body>
</html>
