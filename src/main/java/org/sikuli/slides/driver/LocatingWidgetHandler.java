package org.sikuli.slides.driver;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class LocatingWidgetHandler implements InvocationHandler {
	private final WidgetLocator locator;

	public LocatingWidgetHandler(WidgetLocator locator) {
		this.locator = locator;
	}

	public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
		Widget element = locator.findElement();

		if ("getWrappedElement".equals(method.getName())) {
			return element;
		}

		try {
			return method.invoke(element, objects);
		} catch (InvocationTargetException e) {
			// Unwrap the underlying exception
			throw e.getCause();
		}
	}
}