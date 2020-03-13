
public class GigabyteMainboard implements mainboard{
	private String Name="Gigabyte_mainboard";
	private int speed=50;
	private double price=25.4;
	public void mainboardWork() {
		System.out.println("Gigabyte_mainboard work");
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
