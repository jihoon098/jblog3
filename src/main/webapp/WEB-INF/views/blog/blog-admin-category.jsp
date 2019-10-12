<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<script>
$(function(){
	$(document).on("click", ".admin-cat img", function(){		
		var clickObj = $(this);
		var no = clickObj.attr("id");
		var name = clickObj.attr("name");
		
		// ajax 통신
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/${authUser.id }/admin/category/delete",
			type: "POST",
			dataType: "json",
			data: {
				"no" : no,
				"blogId" : "${authUser.id }"
			},
			success: function(response){
				if(response == "true") {
					alert(name + "카테고리를 삭제했습니다.");
					clickObj.parent().parent().remove();
				}
			},
			error: function(error){
				console.error("error:" + error);
			}
		});
		
	});
});
</script>

</head>
<body>
	<div id="container">
	
		<c:import url="/WEB-INF/views/include/blog-header.jsp"/>
		<div id="wrapper">
			<div id="content" class="full-screen">
				
				<c:import url="/WEB-INF/views/include/admin-menu.jsp"/>

		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<c:forEach items="${AdminCategoryList }" var="category" varStatus='status' >
		      			<tr>
						<td>${status.count }</td>
						<td>${category.name }</td>
						<td>${category.postCount }</td>
						<td>${category.description }</td>
						<td>
							<img src="${pageContext.request.contextPath}/assets/images/delete.jpg" name="${category.name }" id="${category.no }"/>
						</td>
						</tr>
		      		</c:forEach>
				</table>
				
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      		<form action="${pageContext.request.contextPath }/${blogVo.id }/admin/category/write" method="post">
				      	<table id="admin-cat-add">
							<tr>
								<td class="t">카테고리명</td>
								<td><input type="text" name="name"></td>
							</tr>
							<tr>
								<td class="t">설명</td>
								<td><input type="text" name="description"></td>
							</tr>
							<tr>
								<td class="s">&nbsp;</td>
								<td><input type="submit" value="카테고리 추가"></td>
							</tr>
						</table> 
		      		</form>   		      		
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/include/blog-footer.jsp"/>
	</div>
</body>
</html>