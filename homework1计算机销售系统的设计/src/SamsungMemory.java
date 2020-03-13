
public class SamsungMemory implements memory{
	private String Name="Samsung_memory";
	private int volume=2048;
	private double price=54.6;
	public void memoryWork() {
		System.out.println("Samsung_memory work");
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
