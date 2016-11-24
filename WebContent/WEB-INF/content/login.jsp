<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>会员登录</title>

<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/login.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
	// 刷新验证码
	function reCode() {
		var date = new Date();
		var image = document.getElementById("captchaImage");
		image.src = "http:localhost:8888/shopBySSH/verifyCode.action?"
				+ date.getTime();
	}

	// ajax 校验验证码
	function ajaxVerifyCode() {
		// 获得文件框值:
		var verifyCode = document.getElementById("captcha").value;
		// 1.创建异步交互对象
		var xhr = createXmlHttp();
		// 2.设置监听
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					document.getElementById("span4").innerHTML = xhr.responseText;
				}
			}
		}
		// 3.打开连接
		xhr.open("GET",
				"${pageContext.request.contextPath}/verify.action?time="
						+ new Date().getTime() + "&verifyCode=" + verifyCode,
				true);
		// 4.发送
		xhr.send(null);
	}
	function createXmlHttp() {
		var xmlHttp;
		try { // Firefox, Opera 8.0+, Safari
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try {// Internet Explorer
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
				}
			}
		}

		return xmlHttp;
	}
	function checkUsername() {
		var span1 = document.getElementById("span1");
		span1.innerHTML = "";
		var username = document.getElementById("username").value;
		if (username == null || username == '') {
			span1.innerHTML = "<font color='red'> 用户名不能为空！！</font>"
			return false;
		}
		if (username.length > 16 || username.length < 6) {
			span1.innerHTML = "<font color='red'> 用户名长度要大于6且小于16位</font>"
			return false;
		}
		ajaxUsername();
		return true;
	}
	function checkPassword() {
		var span2 = document.getElementById("span2");
		span2.innerHTML = "";
		var password = document.getElementById("password").value;
		if (password == null || password == '') {
			span2.innerHTML = "<font color='red'> 密码不能为空！！</font>"
			return false;
		}
		if (password.length > 16 || password.length < 6) {
			span2.innerHTML = "<font color='red'> 密码长度要大于6且小于16位</font>"
			return false;
		}
		return true;
	}
	function checkForm() {
		if (checkUsername() == true && checkPassword() == true) {
			return true;
		}
		return false;
	}
</script>
</head>
<body>

	<%@ include file="menu.jsp"%>
	<div class="container login">
		<div class="span12">
			<div class="ad">
				<img src="${pageContext.request.contextPath}/image/login.jpg"
					width="500" height="330" alt="会员登录" title="会员登录">
			</div>
		</div>
		<div class="span12 last">
			<div class="wrap">
				<div class="main">
					<div class="title">
						<strong>会员登录</strong>USER LOGIN
					</div>
					<form action="login" id="login" method="post" novalidate="novalidate" onsubmit="return true">
						<table>
							<tbody>
								<tr>
									<th>用户名/E-mail:</th>
									<td><input type="text" id="username" name="username"
										class="text" maxlength="20" onblur="checkUsername()"><span id="span1"><s:fielderror fieldName="user.username" style="color:red"/></span></td>
								</tr>
								<tr>
									<th>密&nbsp;&nbsp;码:</th>
									<td><input type="password" id="password" name="password"
										class="text" maxlength="20" autocomplete="off" onblur="checkPassword()"><span id="span2"><s:fielderror fieldName="user.password" style="color:red"/></span></td>
								</tr>
								<tr>
									<th>验证码:</th>
									<td><span class="fieldSet"> <input type="text"
											id="captcha" name="verifyCode" class="text captcha"
											maxlength="4" autocomplete="off" onblur="ajaxVerifyCode()"><img
											id="captchaImage" class="captchaImage"
											src="${pageContext.request.contextPath}/verifyCode.action"
											title="点击更换验证码" onclick="reCode()"><span id="span4"><s:fielderror fieldName="user.verifyCode" style="color:red"/></span>
									</span></td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td><label> <input type="checkbox"
											id="isRememberUsername" name="isRememberUsername"
											value="true">记住用户名
									</label> <label> &nbsp;&nbsp;<a>找回密码</a>
									</label></td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td><input type="submit" class="submit" value="登 录">
									</td>
								</tr>
								<tr class="register">
									<th>&nbsp;</th>
									<td>
										<dl>
											<dt>还没有注册账号？</dt>
											<dd>
												立即注册即可体验在线购物！ <a href="./会员注册.htm">立即注册</a>
											</dd>
										</dl>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="container footer">
		<div class="span24">
			<div class="footerAd">
				<img src="${pageContext.request.contextPath}/image/footer.jpg"
					width="950" height="52" alt="我们的优势" title="我们的优势" />
			</div>
		</div>
		<div class="span24">
			<ul class="bottomNav">
				<li><a>关于我们</a> |</li>
				<li><a>联系我们</a> |</li>
				<li><a>招贤纳士</a> |</li>
				<li><a>法律声明</a> |</li>
				<li><a>友情链接</a> |</li>
				<li><a target="_blank">支付方式</a> |</li>
				<li><a target="_blank">配送方式</a> |</li>
				<li><a>服务声明</a> |</li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<div class="span24">
			<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
		</div>
	</div>
</body>
</html>