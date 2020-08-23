<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<body>

<h2> Student registration page</h2>
<hr>

<style>
.error {color:red}
</style>

<form:form action="confirmation" modelAttribute="student" >
Email:
<form:input path="email" />
<form:errors path="email" cssClass="error"/>
<br>
Name:
<form:input path="name" />
<form:errors path="name" cssClass="error"/>
<br>
Last name:
<form:input path="lastName" />
<form:errors path="lastName" cssClass="error"/>
<br>
Birth Date dd/mm/yyyy :
<form:input path="birthDate" />
<form:errors path="birthDate" cssClass="error"/>
<br>


<input type="submit" value="Submit" />
</form:form>
</body>
</html>