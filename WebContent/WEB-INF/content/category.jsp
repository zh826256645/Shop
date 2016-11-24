<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="span6">
	<div class="hotProductCategory">
		<s:iterator var="category" value="#session.categories">
			<dl>
				<dt>
					<a href="${pageContext.request.contextPath}/productByCidPage.action?cid=<s:property value="#category.cid"/>&&page=1"><s:property value="#category.cname" /></a>
				</dt>
				<s:iterator var="categorySecond" value="#category.categorySecond">
					<dd>
						<a href="${pageContext.request.contextPath}/productByCsidPage.action?csid=<s:property value="#categorySecond.csid"/>&&page=1"><s:property value="#categorySecond.csname" /></a>
					</dd>
				</s:iterator>
			</dl>
		</s:iterator>
	</div>
</div>