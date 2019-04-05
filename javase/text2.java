package www.bit;


import java.lang.reflect.Method;


/**
 * 员工信息类
 */

class Emp {
    private String name;
    private String job;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}




/**
 * 操作Emp的类，提供给用户使用
 */

class EmpAction  {
    private Emp emp = new Emp();
    public void setEmpValue(String str) throws Exception {
        BeanUtil.setBeanValue(this,str);
    }
    public Emp getEmp() {
        return this.emp;
    }
}




/**
 * 操作简单类属性设置的工具
 */

class BeanUtil {
    private BeanUtil() {}

    /**
     *
     * @param actionObj 拿到给用户使用的xxAction类
     * @param beanValue  要设置的具体值
     * "emp.name:wanghong|emp.job:coder"
     */
    public static void setBeanValue(Object actionObj,String beanValue) throws Exception{
        //字符串拆分
        String[] temp = beanValue.split("\\|");
        //emp.name:wanghong
        //emp.job:coder
        for(int i = 0;i < temp.length;i++) {
            String[] result = temp[i].split(":");
            //emp.name
            //wanghong
            String realValue = result[1];
            String className = result[0].split("\\.")[0];
            String attrName = result[0].split("\\.")[1];

            //根据xxAction类取得真正操作的主题类Emp
            //根据反射调用getEmp()
            Object realObj = getRealObject(actionObj,className);


            //调用真正类的setter方法设置属性值
            //1.取得真正主题类的class对象
            Class<?> cls = realObj.getClass();
            //2.拼装setter方法名
            String setName = "set"+initCap(attrName);
            //3.取得setter方法的method对象
            Method setMethod = cls.getMethod(setName,String.class);
            //4.调用invoke通过反射设置值
            setMethod.invoke(realObj,realValue);
        }
    }

    /**
     * 取得真正操作的对象
     * @param actionObj
     * @param className  要操作的类名称
     * @return
     */

    private static Object getRealObject(Object actionObj,String className) throws Exception {
        //1.取得xxAction的Class对象
        Class<?> cls = actionObj.getClass();
        //2.拼装getxx()方法名称
        String methodName = "get"+initCap(className);
        //3.取得该方法名称的Method对象
        Method method = cls.getMethod(methodName);
        //4.通过反射调用该方法
        //等同与empAction.getEmp();
        return method.invoke(actionObj);
    }

    private static String initCap(String str) {
        return str.substring(0,1).toUpperCase()+str.substring(1);
    }
}




public class text2 {
    public static void main(String[] args) throws Exception {
        String str = "emp.name:wanghong|emp.job:coder";
        EmpAction empAction = new EmpAction();
        empAction.setEmpValue(str);
        System.out.println(empAction.getEmp());
    }

}
