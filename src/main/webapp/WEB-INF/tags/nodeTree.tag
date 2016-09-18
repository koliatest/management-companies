<%@tag description="display the whole nodeTree" pageEncoding="UTF-8"%>
<%@attribute name="node" type="com.kolia.model.Company" required="true" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<li>
    <h4><a href="/edit/${node.id}">${node.name}</a> | <font color="green">${node.earnings} $</font></h4>
    <c:if test="${fn:length(node.childCompanies) > 0}">
        <ul>
            <c:forEach var="child" items="${node.childCompanies}">
                <template:nodeTree node="${child}"/>
            </c:forEach>
        </ul>
    </c:if>
</li>