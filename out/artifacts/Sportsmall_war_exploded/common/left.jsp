<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style>
        .product-container {
            position: relative; /* 必要的定位上下文 */
            height: 62px; /* 设置你的高度 */
            line-height: 1.5; /* 根据你的文本设置合适的行高 */
            overflow: hidden; /* 隐藏超出容器的内容 */
        }

        .product-link {
            position: absolute; /* 相对于父元素定位 */
            width: 100%; /* 填满父元素宽度 */
            height: 85%; /* 填满父元素高度 */
            display: -webkit-box; /* 旧版本的WebKit浏览器（Safari, iOS） */
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 3; /* 控制显示的行数 */
            overflow: hidden;
            text-overflow: ellipsis; /* 这个在这里不会起作用，但保持一致性 */
            word-wrap: break-word; /* 防止长单词撑破容器 */
        }

        /* 添加省略号样式 */
        .product-container::after {
            content: "...";
            position: absolute;
            bottom: 0;
            right: 0;
            padding: 0 10px 10px; /* 调整省略号的位置和间距 */
            background: linear-gradient(to right, rgba(255, 255, 255, 0), rgba(255, 255, 255, 1) 50%); /* 渐变背景，使省略号与背景色融合 */
            pointer-events: none; /* 确保省略号不可点击 */
        }
    </style>
</head>
<body>
<div class="box">
    <h2>商品分类</h2>
    <dl>
        <c:forEach items="${bigTypeList }" var="bigType">
            <dt>${bigType.name }</dt>
            <c:forEach items="${bigType.smallTypeList }" var="smallType">
                <dd>
                    <a href="${pageContext.request.contextPath}/product/list.do?smallTypeId=${smallType.id }">${smallType.name }</a>
                </dd>
            </c:forEach>
        </c:forEach>
    </dl>
</div>

<div class="spacer"></div>
<div class="last-view">
    <h2>最近浏览</h2>
    <dl class="clearfix">
        <c:forEach var="p" items="${currentBrowse }">
            <dt>
                <img src="${pageContext.request.contextPath}/${p.proPic }" class="imgs"
                     style="height: 50px; width: 50px;">
            </dt>
            <dd>
                <div class="product-container">
                    <a class="product-link" href="${pageContext.request.contextPath}/product/showProduct/${p.id}.do"
                       target="_blank">${p.name }</a>
                </div>
            </dd>
        </c:forEach>
    </dl>
</div>
</body>
</html>