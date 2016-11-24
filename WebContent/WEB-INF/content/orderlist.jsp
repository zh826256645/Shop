<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/cart.css"
	rel="stylesheet" type="text/css" />

</head>
<body>
	<%@ include file="menu.jsp"%>
	<div class="container cart">
		<div class="span24">
			<div class="step step1">
				<ul>
					<li class="current"></li>
					<li>我的订单：</li>
				</ul>
			</div>
			<s:iterator var="order" value="orderBean.list">
				<table>
					<tbody>
						<tr>
							<th colspan="3">订单编号：<s:property value="#order.oid" />&nbsp;&nbsp;&nbsp;&nbsp;</th>
							<th colspan="2">订单状态：
								<s:if test="#order.state == 0">
									<a href="${pageContext.request.contextPath}/findByOid.action?oid=<s:property value="#order.oid"/>"><font color="red">付款</font></a>
								</s:if> <s:if test="#order.state == 1">
									<font color="green">已付款，等待发货</font>
								</s:if> <s:if test="#order.state == 2">
								已发货，<a href="#"><font color="blue">确认收货</font></a>
								</s:if> <s:if test="#order.state == 3">交易完成</s:if>
							</th>
							<th colspan="1"><a href="${pageContext.request.contextPath}/removeOrder.action?oid=<s:property value="#order.oid"/>" class="delete">删除订单</a></th>
						</tr>
						<tr>
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
							<th>操作</th>
						</tr>
						<s:iterator var="item" value="#order.orderItems">
							<tr>
								<td width="60"><input type="hidden" name="id" value="22" />
									<img src="<s:property value="#item.product.image"/>" /></td>
								<td><a target="_blank"><s:property
											value="#item.product.pname" /></a></td>
								<td><s:property value="#item.product.shop_price" /></td>
								<td class="quantity" width="60"><input type="text"
									name="count" value="<s:property value="#item.count"/>"
									maxlength="4" onpaste="return false;" />
									<div>
										<span class="increase">&nbsp;</span> <span class="decrease">&nbsp;</span>
									</div></td>
								<td width="140"><span class="subtotal">￥<s:property
											value="#item.subtotal" /></span></td>
								<td><a href="${pageContext.request.contextPath}/removeOrderItem?itemid=<s:property value="#item.itemid"/>">删除</a></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</s:iterator>
			<div class="pagination">
				<s:iterator var="p" begin="1" end="orderBean.totalPage">
					<s:if test="#p != orderBean.page">
						<a
							href="${pageContext.request.contextPath}/orderPage?page=<s:property value="p"/>"><s:property
								value="p" /></a>
					</s:if>
					<s:if test="#p == orderBean.page">
						<span class="currentPage"><s:property value="p"/></span>
					</s:if>
				</s:iterator>
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
			</div>
		</div>
	</div>
	<div class="container footer">
		<div class="span24">
			<div class="footerAd">
				<img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势"
					title="我们的优势" height="52" width="950">
			</div>
		</div>
		<div class="span24">
			<ul class="bottomNav">
				<li><a href="#">关于我们</a> |</li>
				<li><a href="#">联系我们</a> |</li>
				<li><a href="#">诚聘英才</a> |</li>
				<li><a href="#">法律声明</a> |</li>
				<li><a>友情链接</a> |</li>
				<li><a target="_blank">支付方式</a> |</li>
				<li><a target="_blank">配送方式</a> |</li>
				<li><a>SHOP++官网</a> |</li>
				<li><a>SHOP++论坛</a></li>
			</ul>
		</div>
		<div class="span24">
			<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
		</div>
	</div>
</body>
</html>