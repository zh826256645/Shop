package com.zhonghao.interceptor;import org.eclipse.jdt.internal.compiler.ast.Invocation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.zhonghao.user.domain.User;

/**
 * �û�δ��¼���ܽ��в����� Action ��������
 * @author ZhongHao
 *
 */
public class AfterLoginInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;
	
	//������ Action ��������ط���
	public String doIntercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		// �� Session ��ȡ�� User ����
		User user = (User) ctx.getSession().get("user");
		// ���� user ��Ϊ null�������Ѿ���¼
		if(user != null) {
			return invocation.invoke();
		}
		System.out.println("����");
		ctx.put("tip", "���ȵ�¼���ڽ��в���");
		return "index";
	}
}
