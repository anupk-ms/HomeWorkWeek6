package anupk;
import java.lang.reflect.*;

public interface InvocationHandler {
	public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException;
}
