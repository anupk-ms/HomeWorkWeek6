package anupk;
import java.lang.reflect.*;
import java.lang.reflect.InvocationHandler;

public class OwnerInvocationHandler implements InvocationHandler {
	PersonBean person;
	public OwnerInvocationHandler(PersonBean person) {
		this.person = person;
	}
	public Object invoke(Object proxy, Method method, Object[] args) 
	throws IllegalAccessException 
	{
		try {
			if (method.getName().startsWith("get")) {
				return method.invoke(person, args);
			} else if (method.getName().equals("setHotOrNotRating")) {
				throw new IllegalAccessException();
			} else if (method.getName().startsWith("set")) {
				return method.invoke(person, args);
			}
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	/*
	PersonBean getOwnerProxy(PersonBean person) {
		return (PersonBean) Proxy.newProxyInstance(
		person.getClass().getClassLoader(),
		person.getClass().getInterfaces(),
		(InvocationHandler) new OwnerInvocationHandler(person));
	}*/
}
