<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	</head>
	<body style="padding: 15px">
		<table>
			<tr>
				<!-- User Form -->
				<td valign="top" style="padding: 5px">
					<sp:form class="pure-form"
						method="POST"
						modelAttribute="user"
						action="${ pageContext.request.contextPath }/mvc/session09/user/">
					
						<fieldset>
							<legend>User Form</legend>
							<!-- user 表單元素 -->
							姓名: <sp:input path="name" type="text" /><p />
							年齡: <sp:input path="age" type="number" /><p />
							生日: <sp:input path="birth" type="date" /><p />
							學歷: <sp:select path="education" items="${ educations }" itemLabel="name" /><p />
							性別: <sp:radiobuttons path="sex" items="${ sexs }" itemLabel="name" /><p />
							興趣: <sp:checkboxes path="interests" items="${ interests }" itemLabel="name" /><p />
							履歷: <sp:textarea path="resume" /><p />
							<!-- 自訂隱藏表單元素 -->
							<input type="hidden" name="_method" value="${ _method }"><p />
							<button type="submit" class="pure-button pure-button-primary">
								${ buttonName }
							</button>	
						</fieldset>
					
					</sp:form>
					${ _method } <p />
					${ buttonName } <p />
					${ educations } <p />
					${ interests } <p />
					${ sexs } <p />
				</td>
				<!-- User List -->
				<td valign="top" style="padding: 5px">
					${ (users == null) ? "users 尚無資料" : users }
				</td>
			</tr>
		</table>
	</body>
</html>