package www.bit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface ISbject {
    void eat(String name,int count);
}


class RealSubject implements ISbject{

    @Override
    public void eat(String name, int count) {
        System.out.println("eat"+count+"斤"+name);
    }
}

class ProxySubject implements InvocationHandler {
    private Object object;
    public Object bind(Object realObject) {
        this.object = realObject;
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),this);
    }
    /**
     *
     * @param proxy  代理类对象
     * @param method  真实业务方法
     * @param args   真实方法入参
     * @return    真实接口对象
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.before();
        Object obj = method.invoke(this.object,args);
        this.after();
        return obj;
    }
    public void before() {
        System.out.println("before handle");
    }
    public void after() {
        System.out.println("after handle");
    }
}


public class test3 {
    public static void main(String[] args) throws Exception {
        ISbject subject = (ISbject) new ProxySubject().bind(new RealSubject());
        subject.eat("丈母娘水饺",5);
    }
}
