package MatrixCalculation;

public class ThreadOperate extends Thread{
	private MatrixCalculation m = null;
	
	public ThreadOperate() {
		super();
	}
	
	public ThreadOperate(MatrixCalculation m,String name) {
		super(name);
		this.m = m;
	}
	
	@Override
	public void run() {
		try {
			//System.out.println(Thread.currentThread().getName()+Thread.currentThread().getState());
		}catch(Exception e) {
			e.printStackTrace();
		}
		this.m.operate();
	}
	
}
