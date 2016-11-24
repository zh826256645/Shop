package com.zhonghao.interceptor;import org.eclipse.jdt.internal.compiler.ast.Invocation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.zhonghao.user.domain.User;

/**
 * 用户登录后不能进行操作的 Action 的拦截器
 * @author lenovo
 *
 */

public class BeforeLoginInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;
	
	//　拦截 Action 处理的拦截方法
	public String doIntercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		// 从 Session 里取得 User 属性
		User user = (User) ctx.getSession().get("user");
		// 若果 user 为 null，表明没有登录
		if(user == null) {
			return invocation.invoke();
		}
		System.out.println("拦截");
		ctx.put("tip", "请先退出登录，在进行操作");
		return "index";
	}
}
