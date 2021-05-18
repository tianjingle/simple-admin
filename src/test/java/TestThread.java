/**
 * @Author: tianjl
 * @Date: 2021/3/31 17:58
 * @Eamil: 2695062879@qq.com
 */
public class TestThread extends  Thread{

    private static ThreadLocal<MyThread> myThreadThreadLocal=new ThreadLocal<>();

    public static void main(String[] args) {
        MyThread thread=new MyThread();
        thread.setName("tianjl");
        //设置threadLocal变量
        myThreadThreadLocal.set(thread);
        try{
            //里边抛异常
            doSomeThing();
            //下边的代码是不执行的，也就是this.threadLocal.remove();不执行
            System.out.println(myThreadThreadLocal.get().toString());
            myThreadThreadLocal.remove();
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
        //这里可以获取到本该remove的threadlocal的值
        System.out.println(myThreadThreadLocal.get().toString());
    }

    private static void doSomeThing() throws Exception {
        throw new Exception("测试异常");
    }
}
