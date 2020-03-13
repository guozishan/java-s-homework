
public class IntelCPU implements CPU{
	private String Name="Intel_CPU";
	private int coreNum=8;
	private double price=211.3;
	public void CPUWork() {
		System.out.println("Intel_CPU work");
	};
	
	public String getName(){
        return Name;
    }
	public void setName(String Name) {
		this.Name=Name;
	}
    public int getCoreNum(){
        return coreNum;
    }
    public void setCoreNum(int coreNum){
        this.coreNum=coreNum;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price=price;
    }
}
