
public class Singleton1 {
	// ָ���Լ�ʵ����˽�о�̬���ã���������
    private static Singleton1 Singleton1 = new Singleton1();
 
    // ˽�еĹ��췽�� 
    private Singleton1(){}
 
    // ���Լ�ʵ��Ϊ����ֵ�ľ�̬�Ĺ��з�������̬��������
    public static Singleton1 getSingleton1(){
        return Singleton1;
    }
}
