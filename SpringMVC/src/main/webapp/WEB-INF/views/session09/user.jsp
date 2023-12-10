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
							學歷: <sp:select path="educationId" items="${ educations }" itemValue="id" itemLabel="name" /><p />
							性別: <sp:radiobuttons path="sexId" items="${ sexs }"  itemValue="id" itemLabel="name" /><p />
							興趣: <sp:checkboxes path="interestIds" items="${ interests }"  itemValue="id" itemLabel="name" /><p />
							履歷: <sp:textarea path="resume" /><p />
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
					<form class="pure-form">
						<fieldset>
							<legend>
								User List
								${(fn:length(users) == 0)? '無資料' : ''}
							</legend>
							<table class="pure-table pure-table-bordered">
								<thead>
									<tr>
										<th nowrap>id</th><th nowrap>姓名</th><th nowrap>年齡</th><th nowrap>生日</th><th nowrap>學歷</th>
										<th nowrap>性別</th><th nowrap>興趣</th><th nowrap>履歷</th><th nowrap>編輯</th><th nowrap>刪除</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${ users }" var="user">
										<tr>
											<td nowrap>${ user.id }</td>
											<td nowrap>${ user.name }</td>
											<td nowrap>${ user.age }</td>
											<td nowrap>
												<fmt:formatDate value="${ user.birth }" pattern="yyyy-MM-dd" />
											</td>
											<td nowrap>${ user.education.name }</td>
											<td nowrap>${ user.sex.name }</td>
											<td nowrap>
												<c:forEach items="${ user.interests }" var="interest">
													${ interest.name } &nbsp;
												</c:forEach>
											</td>
											<td>${ user.resume }</td>
											<td nowrap>
												<a href="javascript:void(0);" onClick="" class="pure-button">編輯</a>
											</td>
											<td nowrap>
												<a href="javascript:void(0);" onClick="" class="pure-button">刪除</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</fieldset>
					</form>
				</td>
			</tr>
		</table>
	</body>
</html>