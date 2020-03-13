
public class ComputerStore {
    private static Computer computerA=new Computer("A","Intel","Samsung","Seagate","Asus");
    private static Computer computerB=new Computer("B","AMD","Samsung","WestDigitals","Gigabyte");
    private static Computer computerC=new Computer("C","AMD","Kingston","Seagate","Asus");
    
    public static void Show(Computer computer) {
        System.out.println(computer.getName()+" is showing below");
        System.out.println("CPU"+":"+computer.getCPU().getName()+" CoreNum:"+computer.getCPU().getCoreNum());
        computer.getCPU().CPUWork();
        System.out.println("Memory:"+computer.getMemory().getName()+" Volume:"+computer.getMemory().getvolume());
        computer.getMemory().memoryWork();
        System.out.println("Disk"+":"+computer.getHardDisk().getName()+" Volume:"+computer.getHardDisk().getvolume());
        computer.getHardDisk().hardDiskWork();
        System.out.println("Mainboard"+":"+computer.getMainboard().getName()+" Speed:"+computer.getMainboard().getspeed());
        computer.getMainboard().mainboardWork();
        System.out.println("TotalPrice:"+computer.getPrice()+"\n");
    }

    public static void main(String[] args) {
    	Show(computerA);
    	Show(computerB);
    	Show(computerC);
    }
}
