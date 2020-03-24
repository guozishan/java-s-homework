package fileCopy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class fileCopy {
    public static void copy(File sourceFolder, File destinationFolder) throws IOException{
        if (sourceFolder.isDirectory()){
            if (!destinationFolder.exists()) {
                destinationFolder.mkdir();
            }
            String[] files = sourceFolder.list();
            for (String file : files){
                File srcFile = new File(sourceFolder, file);
                File destFile = new File(destinationFolder, file);
                copy(srcFile, destFile);
            }
        }
        else {
            Files.copy(sourceFolder.toPath(), destinationFolder.toPath());
            System.out.println("将 "+ sourceFolder.getName()+"复制到"+ destinationFolder.getName());
        }
    }
    
	public static void main(String[] args) throws IOException {

        File sourceFolder = new File("C:/Users/lenovo/Desktop/sourceFolder");
        File destinationFolder = new File("C:/Users/lenovo/Desktop/destinationFolder");
        copy(sourceFolder, destinationFolder);
    }
}
