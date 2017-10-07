package cn.tax.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ControllerAop {
	@Around(value = "execution(* cn.tax.nswf.controller.*.*(..))")
	public Object aroundMethod(ProceedingJoinPoint join) {
		Object result = null;
		String name = join.getSignature().getName();
		/* �õ�Ŀ���� */
		try {
			// ִ��Ŀ�귽��
			result = join.proceed();
			// ����֪ͨ
			System.out.println("method"+name+" run message");
		} catch (Throwable e) {
			// �쳣֪ͨ
			System.out.println("method"+name+" run error"+" throw exception:" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
