package MatrixCalculation;

public class main {
	public static void main(String args[]) {
		int m1row = 200;
		int m1col = 200;
		int m2col = 200;
		double[][] m1 = new double[m1row][m1col];
		double[][] m2 = new double[m1col][m2col];
		double[][] presult = new double[m1row][m2col];
		MatrixCalculation m = new MatrixCalculation(m1,m2);
		long startTime;
		long endTime;
		
		for (int i = 0; i < m1row; i++)
			for (int j = 0; j < m1col; j++) {
				m1[i][j] = (int) (Math.random() * 100);
			}
		for (int i = 0; i < m1col; i++)
			for (int j = 0; j < m2col; j++) {
				m2[i][j] = (int) (Math.random() * 100);
			}
		//������һ�����м���Ľ��
		startTime = System.currentTimeMillis();
		//System.out.println(startTime);
		for(int i = 0; i< m1.length;i++) {
			for(int j = 0; j < m2[0].length;j++) {
				for(int k=0;k < m1[0].length;k++) {
					presult[i][j] += m1[i][k]*m2[k][j];
				}
			}
		}
		endTime = System.currentTimeMillis();
		//System.out.println(endTime);
		System.out.println("���м�������ʱ��:" + (endTime - startTime));
		//System.out.println("���м���Ľ��Ϊ��");
		//for(int i = 0; i < m1row; i++) {
		//	for(int j =0; j < m2col;j++) {
		//		System.out.print(presult[i][j]+" ");
		//	}
		//	System.out.println();
		//}
		
		//���м���
		startTime = System.currentTimeMillis();
		for(int i = 0; i <m1.length/5;i++) {
			new ThreadOperate(m,"�����һ������ĵ�"+(i+1)+"��*�ڶ��������������").start();
		}
		endTime = System.currentTimeMillis();
		System.out.println("���м�������ʱ��:" + (endTime - startTime));
		//System.out.println("���м���Ľ��Ϊ��");
		//presult = m.getResult();
		//for(int i = 0; i < m1row; i++) {
		//	for(int j =0; j<m2col;j++) {
		//		System.out.print(presult[i][j]+" ");
		//	}
		//	System.out.println();
		//}
	}
}
