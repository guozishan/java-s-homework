package MatrixCalculation;

public class MatrixCalculation {
	double[][] matrix1 = null;//��һ������
	double[][] matrix2 = null;//�ڶ�������
	double[][] result = null;//��ž�����˽��
	public static int line = 0;//��¼��ǰ���������ǵ�һ������ĵڼ���
	
	//���幹�췽��
	public MatrixCalculation() {}
	     
	public MatrixCalculation(double[][] m1,double[][] m2) {
	         
	    this.matrix1 = m1;
	    this.matrix2 = m2;
	    result = new double[matrix1.length][matrix2[0].length];
	}
	
	//���ؾ�����˵Ľ��
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
