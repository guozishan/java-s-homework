
public class WestDigitalsHardDisk implements HardDisk{
	private String Name="WestDigitals_hardDisk";
	private int volume=2;
	private double price=27.9;
	public void hardDiskWork() {
		System.out.println("WestDigitals_hardDisk work");
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
