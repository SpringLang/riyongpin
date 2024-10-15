<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
    <script
            src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <script type="text/javascript">
        $(function () {
            var id = $("#top_id").val();

            if (id == "" || id == null) {
                $("#shouye").addClass("current");
            } else {
                $("#" + id).addClass("current");
            }
        })

        function logout() {
            if (confirm('您确定要退出系统吗？')) {
                window.location.href = "${pageContext.request.contextPath}/user/logout.do";
            }

        }

        function checkLogin() {
            if ('${currentUser.userName}' == '') {
                alert("请先登录！");
            } else {
                window.location.href = "${pageContext.request.contextPath}/shopping/list.do";
            }
        }
    </script>
</head>
<body>

<div id="logo">
    <img src="${pageContext.request.contextPath}/images/logoimg.png" width="95" height="60"/>
</div>
<div class="help">
    <c:choose>
        <c:when test="${not empty currentUser }">
            <a href="${pageContext.request.contextPath}/user/userCenter.do">${currentUser.userName }</a>
            <a href="javascript:logout()">注销</a>
            <a href="${pageContext.request.contextPath}/register.jsp">注册</a>
            <%--				<a href="${pageContext.request.contextPath}/comment/list.do">留言</a>--%>
        </c:when>
        <c:otherwise>
<%--            <a href="javascript:checkLogin()" class="shopping">购物车</a>--%>
            <a href="${pageContext.request.contextPath}/login.jsp">登录</a>
            <a href="${pageContext.request.contextPath}/register.jsp">注册</a>
            <%--				<a href="${pageContext.request.contextPath}/comment/list.do">留言</a>--%>
        </c:otherwise>
    </c:choose>

    <form action="${pageContext.request.contextPath}/product/list.do" method="post">
        <input type="text" id="txtSearch" name="name" onkeyup=""
               autocomplete="off" value="${name }"/> <input
            type="submit" id="cmdSearch" value="搜索"/><br/>
        <div id="suggest" style="width: 200px"></div>
    </form>
</div>

<input type="hidden" id="top_id" value="${bigTypeId}">
<div class="navbar">
    <ul class="clearfix">
        <li id="shouye"><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
        <c:choose>
            <c:when test="${not empty currentUser }">
                <li><a href="${pageContext.request.contextPath}/shopping/list.do"
                       class="shopping">购物车(${shoppingCart.shoppingCartItems==null?0:shoppingCart.shoppingCartItems.size() }件商品)</a>
                </li>
            </c:when>
            <c:otherwise>
                <li><a href="javascript:checkLogin()" class="shopping">购物车</a></li>
            </c:otherwise>
        </c:choose>
        <li><a href="${pageContext.request.contextPath}/comment/list.do">留言</a></li>
        <%--				<c:forEach var="bigType" items="${bigTypeList }">--%>
        <%--					<li id="${bigType.id}" >--%>
        <%--						<a href="${pageContext.request.contextPath}/product/list.do?bigTypeId=${bigType.id }">${bigType.name }</a>--%>
        <%--					</li>--%>
        <%--				</c:forEach>--%>
    </ul>
</div>
</body>
</html>