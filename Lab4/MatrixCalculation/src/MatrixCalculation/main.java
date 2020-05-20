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
		//首先算一波串行计算的结果
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
		System.out.println("串行计算运行时间:" + (endTime - startTime));
		//System.out.println("串行计算的结果为：");
		//for(int i = 0; i < m1row; i++) {
		//	for(int j =0; j < m2col;j++) {
		//		System.out.print(presult[i][j]+" ");
		//	}
		//	System.out.println();
		//}
		
		//并行计算
		startTime = System.currentTimeMillis();
		for(int i = 0; i <m1.length/5;i++) {
			new ThreadOperate(m,"计算第一个矩阵的第"+(i+1)+"行*第二个矩阵的所有列").start();
		}
		endTime = System.currentTimeMillis();
		System.out.println("并行计算运行时间:" + (endTime - startTime));
		//System.out.println("并行计算的结果为：");
		//presult = m.getResult();
		//for(int i = 0; i < m1row; i++) {
		//	for(int j =0; j<m2col;j++) {
		//		System.out.print(presult[i][j]+" ");
		//	}
		//	System.out.println();
		//}
	}
}
