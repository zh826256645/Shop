<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>传智网上商城</title>
<link href="./css/common.css" rel="stylesheet" type="text/css" />
<link href="./css/product.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<%@ include file="menu.jsp"%>
	<div class="container productList">
		<%@ include file="category.jsp"%>
		<div class="span18 last">

			<form id="productForm"
				action="./image/蔬菜 - Powered By Mango Team.htm" method="get">
				<input type="hidden" id="brandId" name="brandId" value=""> <input
					type="hidden" id="promotionId" name="promotionId" value="">
				<input type="hidden" id="orderType" name="orderType" value="">
				<input type="hidden" id="pageNumber" name="pageNumber" value="1">
				<input type="hidden" id="pageSize" name="pageSize" value="20">

				<div id="result" class="result table clearfix">
					<ul>
						<s:iterator var="product" value="pageBean.list">
							<li><a
								href="${pageContext.request.contextPath}/productPage.action?pid=<s:property value='#product.pid'/>">
									<img
									src="${pageContext.request.contextPath}/<s:property value="#product.image"/>"
									width="170" height="170" style="display: inline-block;">
									<span style='color: green'> <s:property
											value="#product.pname" /></span> <span class="price"> 商城价： ￥<s:property
											value="#product.shop_price" />/份
								</span>

							</a></li>
						</s:iterator>
					</ul>
				</div>
				<div class="pagination">
					<span>第<s:property value="pageBean.page" />/<s:property
							value="pageBean.totalPage" />页
					</span>
					<s:if test="pageBean.page != 1">
						<a class="firstPage"
							href="${pageContext.request.contextPath}/productByCidPage.action?cid=<s:property value="cid"/>&&page=1">&nbsp;</a>
						<a class="previousPage"
							href="${pageContext.request.contextPath}/productByCidPage.action?cid=<s:property value="cid"/>&&page=<s:property value="pageBean.page-1"/>">&nbsp;</a>
					</s:if>
					<s:if test="pageBean.totalPage<=5">
						<s:iterator begin="1" end="pageBean.totalPage" var="i">
							<s:if test="pageBean.page != #i">
								<a
									href="${pageContext.request.contextPath}/productByCidPage.action?cid=<s:property value="cid"/>&&page=<s:property value="#i"/>"><s:property
										value="#i" /></a>
							</s:if>
							<s:if test="pageBean.page == #i">
								<span class="currentPage"> <s:property value="#i" /></span>
							</s:if>
						</s:iterator>
					</s:if>
					<s:else>
						<s:if test="pageBean.page - 3 >= 0">
							<s:if test="pageBean.totalPage - 3 >= pageBean.pageBean">
								<s:iterator begin="1" end="5" var="i">
									<s:if test="#i != 3">
										<a href="${pageContext.request.contextPath}/productByCidPage.action?cid=<s:property value="cid"/>&&page=<s:property value="pageBean.page - 3 + #i"/>"><s:property value="pageBean.page - 3 + #i" /></a>
									</s:if>
									<s:if test="#i == 3">
										<span class="currentPage"> <s:property value="pageBean.page" /></span>
									</s:if>
								</s:iterator>
							</s:if>
							<s:else>
								<s:iterator begin="1" end="5" var="i">
									<s:if test="pageBean.totalPage - 5 + #i != pageBean.page">
										<a href="${pageContext.request.contextPath}/productByCidPage.action?cid=<s:property value="cid"/>&&page=<s:property value="pageBean.totalPage - 5 + #i"/>"><s:property value="pageBean.totalPage - 5 + #i" /></a>
									</s:if>
									<s:else>
										<span class="currentPage"> <s:property value="pageBean.page" /></span>
									</s:else>
								</s:iterator>
							</s:else>
						</s:if>
						<s:else>
							<s:iterator begin="1" end="5" var="i">
								<s:if test="pageBean.page != #i">
									<a href="${pageContext.request.contextPath}/productByCidPage.action?cid=<s:property value="cid"/>&&page=<s:property value="#i"/>"><s:property value="#i" /></a>
								</s:if>
								<s:if test="pageBean.page == #i">
									<span class="currentPage"> <s:property value="pageBean.page" /></span>
								</s:if>
							</s:iterator>
						</s:else>
					</s:else>
					<s:if test="pageBean.page != pageBean.totalPage">
						<a class="nextPage"
							href="${pageContext.request.contextPath}/productByCidPage.action?cid=<s:property value="cid"/>&&page=<s:property value="pageBean.page+1"/>">&nbsp;</a>
						<a class="lastPage"
							href="${pageContext.request.contextPath}/productByCidPage.action?cid=<s:property value="cid"/>&&page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
					</s:if>
				</div>
			</form>
		</div>
	</div>
	<div class="container footer">
		<div class="span24">
			<div class="footerAd">
				<img src="./image/footer.jpg" width="950" height="52" alt="我们的优势"
					title="我们的优势">
			</div>
		</div>
		<div class="span24">
			<ul class="bottomNav">
				<li><a>关于我们</a> |</li>
				<li><a>联系我们</a> |</li>
				<li><a>诚聘英才</a> |</li>
				<li><a>法律声明</a> |</li>
				<li><a>友情链接</a> |</li>
				<li><a target="_blank">支付方式</a> |</li>
				<li><a target="_blank">配送方式</a> |</li>
				<li><a>官网</a> |</li>
				<li><a>论坛</a></li>
			</ul>
		</div>
		<div class="span24">
			<div class="copyright">Copyright©2005-2015 网上商城 版权所有</div>
		</div>
	</div>
</body>
</html>