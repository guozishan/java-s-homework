
public class AsusMainboard implements mainboard{
	private String Name="Asus_mainboard";
	private int speed=100;
	private double price=54.8;
	public void mainboardWork() {
		System.out.println("Asus_mainboard work");
	};
	
	public String getName(){
        return Name;
    }
	public void setName(String Name) {
		this.Name=Name;
	}
    public int getspeed(){
        return speed;
    }
    public void setspeed(int speed){
        this.speed=speed;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price=price;
    }
}
