package com.csii.ljj.singleton;

import java.io.*;

/**
 * 静态内部类：序列化与反序列化
 *
 *
 */
public class Singleton5 implements Serializable {

    private static final long serialVersionUID = 1L;

    private static class Inner{
        private static Singleton5 singleton5 = new Singleton5();
    }

    private Singleton5(){}

    public static Singleton5 get(){
        return Inner.singleton5;
    }

    //该方法在反序列化时会被调用，该方法不是接口定义的方法，有点儿约定俗成的感觉
    // 不增加该方法 序列化与反序列化不一致
   /* protected Object readResolve() throws ObjectStreamException {
        System.out.println("调用了readResolve方法！");
        return  Inner.singleton5;
    }
*/
    public static void main(String[] args) {
        Singleton5 singleton =Singleton5.get();

        File file = new File("MySingleton.txt");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(singleton);
            fos.close();
            oos.close();
            System.out.println(singleton.hashCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Singleton5 rSingleton = (Singleton5) ois.readObject();
            fis.close();
            ois.close();
            System.out.println(rSingleton.hashCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
