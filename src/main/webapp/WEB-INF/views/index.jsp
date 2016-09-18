<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Management companies</title>
    <%@include file="parts/links.jsp"%>

</head>
<body>
<div class="blog-masthead">
    <div class="container">
        <nav class="blog-nav">
            <a class="navbar-brand" href="/">Management companies</a>
        </nav>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-7">
            <h3>Companies Tree</h3>
            <c:if test = "${!empty companies}">

                <c:forEach items = "${companies}" var = "company">
                    <c:if test="${empty company.parentCompany}">
                        <div id="treeDiv1">
                            <li>
                                <h3><a href="/edit/${company.id}">${company.name}</a> |
                                <font color="green">${company.earnings} $</font></h3>
                            </li>
                            <ul>
                                <c:forEach var="child" items="${company.childCompanies}">
                                    <template:nodeTree node="${child}"/>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                </c:forEach>

            </c:if>
            <c:if test="${empty companies}">
                <br> <h3>There are no companies</h3>
            </c:if>
        </div>

        <div class="col-md-5">
            <h3>Add/update company</h3>
            <sf:form method="post" modelAttribute="dto" id="addForm">
                <div class="form-group">
                    <label>Name<font color="red">*</font></label>
                    <input type="text" value="${currentCompany.name}" class="form-control" name="name" required> <br>
                </div>

                <div class="form-group">
                    <label>Estimated earnings<font color="red">*</font></label>
                    <input type="number" value="${currentCompany.earnings}" class="form-control" name="earnings" required> <br>
                </div>

                <div class="form-group" id="divid">
                    <label>Parent company</label>
                    <c:if test="${!empty companies}">
                        <sf:select id="selectorId" path="parent_id" cssClass="selectpicker">
                            <c:forEach items="${companies}" var="company">
                                <option
                                        <c:if test="${company.id eq currentCompany.parentCompany.id}">selected="selected"</c:if>
                                        value="${company.id}">${company.name}
                                </option>
                            </c:forEach>
                        </sf:select> <br>
                        <input
                            <c:if test="${!empty currentCompany.name}">
                                <c:if test="${empty currentCompany.parentCompany.id}">checked="true"</c:if>
                            </c:if>
                                type="checkbox" id="chId" name="checked">
                        <label>Main company</label>
                    </c:if>
                    <c:if test="${empty companies}">
                        <br> <h3>There are no companies</h3>
                    </c:if> <br>
                </div>

                <c:if test="${!empty currentCompany.name}">
                    <button type="submit" style="width: 50%;" class="btn btn-success btn-lg">Update</button>
                    <a href="/delete/${currentCompany.id}" style="width: 40%;" class="btn btn-danger btn-lg">Delete</a>
                </c:if>
                <c:if test="${empty currentCompany.name}">
                    <button type="submit" style="width: 50%;" class="btn btn-success btn-lg">Add</button>
                </c:if>

            </sf:form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $('#addForm').one('submit', function() {
        $(this).find('button[type="submit"]').attr('disabled','disabled');
    });
</script>

</body>
</html>
