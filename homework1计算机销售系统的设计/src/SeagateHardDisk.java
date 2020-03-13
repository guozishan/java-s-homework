
public class SeagateHardDisk implements HardDisk{
	private String Name="Seagate_hardDisk";
	private int volume=1;
	private double price=17.2;
	public void hardDiskWork() {
		System.out.println("Seagate_hardDisk work");
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
