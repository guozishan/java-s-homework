
public class Singleton2 {
		 
	    // ָ���Լ�ʵ����˽�о�̬����
	    private static Singleton2 singleton2;
	 
	    // ˽�еĹ��췽��
	    private Singleton2(){}
	 
	    // ���Լ�ʵ��Ϊ����ֵ�ľ�̬�Ĺ��з�������̬��������
	    public static Singleton2 getSingleton2(){
	        // ������������������Ҫʹ��ʱ��ȥ����
	        if (singleton2 == null) {
	            singleton2 = new Singleton2();
	        }
	        return singleton2;
	    }
}
