
public class AMDCPU implements CPU{
	private String Name="AMD_CPU";
	private int coreNum=4;
	private double price=100.5;
	public void CPUWork() {
		System.out.println("AMD_CPU work");
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
