
public class Computer {
 private CPU cpu;
 private memory memory;
 private HardDisk harddisk;
 private mainboard mainboard;
 private String name;
 private double price;
 
 public Computer(String name,String cpu,String memory,String harddisk,String mainboard) {
	 this.name=name;
	 this.cpu=Wcpu(cpu);
	 this.mainboard=Wmainboard(mainboard);
	 this.memory=Wmemory(memory);
	 this.harddisk=Wharddisk(harddisk);
	 this.price=this.cpu.getPrice()+this.memory.getPrice()+this.harddisk.getPrice()+this.mainboard.getPrice();
 }
 
 public CPU Wcpu(String brand) {
	 if(brand.equals("AMD")) {
		 return new AMDCPU();
	 }else if(brand.equals("Intel")) {
		 return new IntelCPU();
	 }else {
		 return null;
	 }
 }
 public memory Wmemory(String brand) {
	 if(brand.equals("Samsung")) {
		 return new SamsungMemory();
	 }else if(brand.equals("Kingston")) {
		 return new KingstonMemory();
	 }else {
		 return null;
	 }
 }
 public HardDisk Wharddisk(String brand) {
	 if(brand.equals("Seagate")) {
		 return new SeagateHardDisk();
	 }else if(brand.equals("WestDigitals")) {
		 return new WestDigitalsHardDisk();
	 }else {
		 return null;
	 }
 }
 public mainboard Wmainboard(String brand) {
	 if(brand.equals("Asus")) {
		 return new AsusMainboard();
	 }else if(brand.equals("Gigabyte")) {
		 return new GigabyteMainboard();
	 }else {
		 return null;
	 }
 }
 
 public CPU getCPU() {
	 return this.cpu;
 }
 public void setCPU(String brand) {
	 if(brand.equals("AMD")) {
		 this.cpu = new AMDCPU();
	 }else if(brand.equals("Intel")) {
		 this.cpu = new IntelCPU();
	 }else {
		 System.out.println("InValid!");
	 }	 
 }
 public memory getMemory() {
	 return this.memory;
 }
 public void setMemory(String brand) {
	 if(brand.equals("Samsung")) {
		 this.memory = new SamsungMemory();
	 }else if(brand.equals("Kingston")) {
		 this.memory =  new KingstonMemory();
	 }else {
		 System.out.println("InValid!");
	 } 
 }
 public mainboard getMainboard() {
	 return this.mainboard;
 }
 public void setMainboard(String brand) {
	 if(brand.equals("Asus")) {
		 this.mainboard = new AsusMainboard();
	 }else if(brand.equals("Gigabyte")) {
		 this.mainboard = new GigabyteMainboard();
	 }else {
		 System.out.println("InValid!");
	 }
 }
 public HardDisk getHardDisk() {
	 return this.harddisk;
 }
 public void setHardDisk(String brand) {
	 if(brand.equals("Seagate")) {
		 this.harddisk = new SeagateHardDisk();
	 }else if(brand.equals("WestDigitals")) {
		 this.harddisk = new WestDigitalsHardDisk();
	 }else {
		 System.out.println("InValid!");
	 }
 }
 
 public double getPrice() {
	 return this.price;
 }
 public void setPrice(double price) {
	 this.price=price;
 }
 public String getName() {
	 return this.name;
 }
 public void setName(String name) {
	 this.name=name;
 } 
}
