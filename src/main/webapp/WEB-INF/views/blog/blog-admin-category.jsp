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
				if(response.success == "success") {
					alert("<" + name + "> " + "카테고리를 삭제했습니다.");
					clickObj.parent().parent().remove();
				}
			},
			error: function(error){
				console.error("error:" + error);
			}
		});
	});
	
	
	$("#cat-input").click(function(){
		var clickObj = $(this);
		var categoryInfor = $("#CreateCategoryForm").serialize();
		console.log("---클릭 이벤트---");
		console.log("#CreateCategoryForm의 내부 값: " + categoryInfor);
		//var name = clickObj.parent().parent().parent().parent().val("name"); 왜 이렇게는 안받아질까..?
		
		// ajax 통신
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/${authUser.id }/admin/category/write",
			type: "POST",
			dataType: "json",
			data: categoryInfor,
			success: function(response){
				if(response != null) {
					alert("카테고리를 추가했습니다.");
					
					//테이블추가
					//console.log(response.NewCategory);  >> 조사.
				    createCategoryTable(response.NewCategory);
				}
			},
			error: function(error){
				console.error("error:" + error);
			}
		});	
	});
	function createCategoryTable(response) {
		  $(".admin-cat-body").append(
			   	"<tr>" +
		        "<td>" + "${fn:length(AdminCategoryList) + 1 }" + "</td>" +
		        "<td>" + response.name + "</td>" +
		        "<td>" + response.postCount + "</td>"+
		        "<td>" + response.description + "</td>" +
		        "<td>" +
		        "<img src='${pageContext.servletContext.contextPath}/assets/images/delete.jpg' + name='" + response.name + 
			    "' id='" + response.no + "'>" +	
		        "</td>" +
		        "</tr>");
		  }	
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
		      		<thead>
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>
		      		</tr>      			
		      		</thead>
		      		<tbody class="admin-cat-body">
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
		      		</tbody>
				</table>
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      		<form id="CreateCategoryForm" method="post">
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
								<!-- 왜 type="summit"으로 하면 에러가 날까??나중에 확인-->
								<td><input type="button" id ="cat-input" value="카테고리 추가" ></td>
							</tr>
						</table> 
		      		</form>   		      		
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/include/blog-footer.jsp"/>
	</div>
</body>
</html>