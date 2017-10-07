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
		/* 得到目标类 */
		try {
			// 执行目标方法
			result = join.proceed();
			// 后置通知
			System.out.println("method"+name+" run message");
		} catch (Throwable e) {
			// 异常通知
			System.out.println("method"+name+" run error"+" throw exception:" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
