<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<body>

<h2>Check your status</h2>
<hr>


<style>
.error {color:red}
</style>

<form:form action="statusResponse" modelAttribute="student" >
Email:
<form:input path="email" />
<form:errors path="email" cssClass="error"/>
<br>
<input type="submit" value="Submit" />
</form:form>
<br>
</body>
</html>