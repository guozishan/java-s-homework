
public class KingstonMemory implements memory{
	private String Name="Kingston_memory";
	private int volume=1024;
	private double price=35.8;
	public void memoryWork() {
		System.out.println("Kingston_memory work");
	};
	
	public String getName(){
        return Name;
    }
	public void setName(String Name) {
		this.Name=Name;
	}
    public int getvolume(){
        return volume;
    }
    public void setvolume(int volume){
        this.volume=volume;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price=price;
    }
}
