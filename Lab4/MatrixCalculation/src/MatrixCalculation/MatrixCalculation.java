package MatrixCalculation;

public class MatrixCalculation {
	double[][] matrix1 = null;//第一个矩阵
	double[][] matrix2 = null;//第二个矩阵
	double[][] result = null;//存放矩阵相乘结果
	public static int line = 0;//记录当前参与计算的是第一个矩阵的第几行
	
	//定义构造方法
	public MatrixCalculation() {}
	     
	public MatrixCalculation(double[][] m1,double[][] m2) {
	         
	    this.matrix1 = m1;
	    this.matrix2 = m2;
	    result = new double[matrix1.length][matrix2[0].length];
	}
	
	//返回矩阵相乘的结果
	public double[][] getResult() {
		try {
			while(MatrixCalculation.line<matrix1.length) {
				Thread.sleep(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return this.result;
	}
	
	public void operate() {
		MatrixCalculation.line += 5;
		
		for(int i = 0; i < matrix1[0].length;i++) {
			double sum = 0;
			for(int j = 0; j < matrix2.length; j++) {
				sum += matrix1[MatrixCalculation.line-5][j]*matrix2[j][i];
			}
			result[MatrixCalculation.line-5][i] = sum;
		}
		for(int i = 0; i < matrix1[0].length;i++) {
			double sum = 0;
			for(int j = 0; j < matrix2.length; j++) {
				sum += matrix1[MatrixCalculation.line-4][j]*matrix2[j][i];
			}
			result[MatrixCalculation.line-4][i] = sum;
		}
		for(int i = 0; i < matrix1[0].length;i++) {
			double sum = 0;
			for(int j = 0; j < matrix2.length; j++) {
				sum += matrix1[MatrixCalculation.line-3][j]*matrix2[j][i];
			}
			result[MatrixCalculation.line-3][i] = sum;
		}
		for(int i = 0; i < matrix1[0].length;i++) {
			double sum = 0;
			for(int j = 0; j < matrix2.length; j++) {
				sum += matrix1[MatrixCalculation.line-2][j]*matrix2[j][i];
			}
			result[MatrixCalculation.line-2][i] = sum;
		}
		for(int i = 0; i < matrix1[0].length;i++) {
			double sum = 0;
			for(int j = 0; j < matrix2.length; j++) {
				sum += matrix1[MatrixCalculation.line-1][j]*matrix2[j][i];
			}
			result[MatrixCalculation.line-1][i] = sum;
		}
	}
	
}
