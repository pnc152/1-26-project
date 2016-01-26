<%@page import="org.springframework.http.HttpRequest"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="mybatis.BoardManager"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.List" %>
<%@page import="com.hta.board.repository.BoardDto" %>
<%@page import = "java.io.PrintWriter" %>

<%@include file="/header.jsp"%>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>메인</title>

<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/foundation.min.css" type="text/css">
<link rel="stylesheet" href="css/superfish.css">
<link rel="stylesheet" href="css/style.css" type="text/css">
<link rel="stylesheet" href="js/slider/flexslider.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="js/slider/testimonialslider.css"
	type="text/css" media="screen">
<link
	href='http://fonts.googleapis.com/css?family=PT+Sans+Caption|Open+Sans'
	rel='stylesheet' type='text/css'>
<script src="js/vendor/custom.modernizr.js"></script>
<script>
	Modernizr.load({
		// test if browser understands media queries
		test : Modernizr.mq('only all'),
		// if not load ie8-grid
		nope : 'css/ie8-grid-foundation-4.css'
	});
</script>
<!--[if lt IE 9]>
<link rel="stylesheet" href="css/ie-fixes.css">
<![endif]-->

<style>
	html,body{width: 100%;height: 100%}
html{background-image: linear-gradient(-45deg, slategrey, purple, teal);}
body{background-image: linear-gradient(0deg, transparent 0%, transparent 9px, rgba(255, 255, 255, 0.2) 9px, rgba(255, 255, 255, 0.2) 10px, transparent 10px, transparent 19px, rgba(255, 255, 255, 0.1) 19px, rgba(255, 255, 255, 0.1) 20px, transparent 20px, transparent 29px, rgba(255, 255, 255, 0.1) 29px, rgba(255, 255, 255, 0.1) 30px, transparent 30px, transparent 39px, rgba(255, 255, 255, 0.1) 39px, rgba(255, 255, 255, 0.1) 40px, transparent 40px, transparent 49px, rgba(255, 255, 255, 0.1) 49px, rgba(255, 255, 255, 0.1) 50px), linear-gradient(-90deg, transparent 0%, transparent 9px, rgba(255, 255, 255, 0.2) 9px, rgba(255, 255, 255, 0.2) 10px, transparent 10px, transparent 19px, rgba(255, 255, 255, 0.1) 19px, rgba(255, 255, 255, 0.1) 20px, transparent 20px, transparent 29px, rgba(255, 255, 255, 0.1) 29px, rgba(255, 255, 255, 0.1) 30px, transparent 30px, transparent 39px, rgba(255, 255, 255, 0.1) 39px, rgba(255, 255, 255, 0.1) 40px, transparent 40px, transparent 49px, rgba(255, 255, 255, 0.1) 49px, rgba(255, 255, 255, 0.1) 50px);background-size: 100px 100px;
}
	</style>
</head>
<body>
<div class="slideshow">
  <div class="row">
    <div class="large-12 twelve columns">
      <section class="slider">
        <div class="flexslider">
          <ul class="slides">
            <li style="width: 400px; height: 300px;"><img src="images/qnet.jpg" ></a></li>
            <li style="width: 400px; height: 300px;"><img src="images/sh1.jpg" ></li>
            <li style="width: 400px; height: 300px;"><img src="images/sh2.jpg" ></li>
          </ul>
        </div>
      </section>
    </div>
  </div>
</div>
</body>
<!-- <table border="1"> -->
<!-- <tr> -->
<!-- 	<tr><td>번호</td><td>제목</td><td>아이디</td> -->
<!-- 	<td>날짜</td><td>조회수</td> -->
<!-- </tr> -->
<%-- <c:forEach var="dto" items="${list}"> --%>
<!-- 	<tr> -->
<%-- 		<td>${dto.b_num}</td> --%>
<%-- 		<td><a href="read.board1?b_seq=${dto.b_num}">${dto.b_title}</a></td> --%>
<%-- 		<td>${dto.b_writer}</td> --%>
<%-- 		<td>${dto.b_date}</td> --%>
<%-- 		<td>${dto.b_count}</td> --%>
<!-- 	</tr> -->
<%-- </c:forEach> --%>
<!-- </table> -->

<%@include file="/footer.jsp" %>


<script>
	(function(){
		if("${bool}"==true||"${bool}"==""){
		//alert("한번 실행해봅시다!");
		
		location.href = "list.board1?bool="+true;
		}
	})();
	
	
</script>


  























