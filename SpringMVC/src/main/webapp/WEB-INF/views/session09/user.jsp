<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<style type="text/css">
			.element-margin {
				margin-right:2px;
				margin-left:5px;
			}
		</style>
		<script type="text/javascript">
			function deleteUser(userId) {
			    const url = '${pageContext.request.contextPath}/mvc/session09/user/js/' + userId;
			    
			    fetch(url, {
			        method: 'DELETE'
			    })
			    .then(response => {
			        if(response.ok) {
			            // 操作成功，更新前端或重定向
			            window.location.href = '${pageContext.request.contextPath}/mvc/session09/user/';
			        } else {
			            // 处理错误情况
			            console.error('Delete request failed');
			        }
			    })
			    .catch(error => {
			        console.error('Error:', error);
			    });
			}

		</script>
	</head>
	<body style="padding: 15px">
		<table>
			<tr>
				<!-- User Form -->
				<td valign="top" style="padding: 5px">
					<sp:form class="pure-form"
						method="POST"
						modelAttribute="user"
						action="${ pageContext.request.contextPath }/mvc/session09/user/${ user.id }">
					
						<fieldset>
							<legend>User Form</legend>
							<!-- user 表單元素 -->
							姓名: <sp:input path="name" type="text" /><p />
							年齡: <sp:input path="age" type="number" /><p />
							生日: <sp:input path="birth" type="date" /><p />
							學歷: <sp:select path="educationId" items="${ educations }" itemValue="id" itemLabel="name" /><p />
							性別: <sp:radiobuttons path="sexId" items="${ sexs }"  itemValue="id" itemLabel="name" cssClass="element-margin" /><p />
							興趣: <sp:checkboxes path="interestIds" items="${ interests }"  itemValue="id" itemLabel="name" cssClass="element-margin" /><p />
							履歷: <sp:textarea path="resume" /><p />
							<!-- 所有錯誤資訊 -->
							<sp:errors path="*" cssClass="error" /><p />
							<!-- 自訂隱藏表單元素 -->
							<input type="hidden" name="_method" value="${ _method }"><p />
							<button type="submit" class="pure-button pure-button-primary">
								${ buttonName }
							</button>	
						</fieldset>
					
					</sp:form>
					<!-- 
					${ _method } <p />
					${ buttonName } <p />
					${ educations } <p />
					${ interests } <p />
					${ sexs } <p />
					-->
				</td>
				<!-- User List -->
				<td valign="top" style="padding: 5px">
					
				</td>
			</tr>
		</table>
	</body>
</html>