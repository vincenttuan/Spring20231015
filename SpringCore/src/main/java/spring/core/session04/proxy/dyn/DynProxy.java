package spring.core.session04.proxy.dyn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 動態代理
public class DynProxy {
	
	// 被代理的對象(物件)
	private Object object;
	
	public DynProxy(Object object) {
		this.object = object;
	}
	
	// 取得被代理的物件
	public Object getProxy() {
		Object proxyObj = null;
		
		// 1. 載入類別器
		ClassLoader loader = object.getClass().getClassLoader();
		
		// 2. 被代理的物件所實作的介面
		Class<?>[] interfaces = object.getClass().getInterfaces();
		
		// 3. 處理代理的實現
		InvocationHandler handler = (Object proxy, Method method, Object[] args) -> {
			Object result = null;
			
			// before: 前置通知-公用邏輯/方法
			//System.out.println("before: 前置通知-公用邏輯/方法 ...");
			MyAspect.before(); // 第一個切面程式
			MyLoggerAspect.before(args); // 第二個切面程式
			
			// 調用被代理物件的業務方法
			try {
				result = method.invoke(object, args); // object 被代理的物件, args 方法參數
			} catch (Exception e) {
				// exception: 例外通知-公用邏輯/方法
				//System.out.println("exception: 例外通知-公用邏輯/方法 ...");
				MyAspect.throwing();
			} finally {
				// end: 後置通知-公用邏輯/方法
				//System.out.println("end: 後置通知-公用邏輯/方法 ...");
				MyAspect.end();
			}			
			
			return result;
		};
		
		// 4. 取得代理的物件
		proxyObj = Proxy.newProxyInstance(loader, interfaces, handler);
		return proxyObj;
	}
	
}
