import java.io.*;
import java.nio.file.*;
import java.util.Scanner;
import java.util.zip.*;

/**
 * @author dongyudeng
 */
public class ZipHandler {
    public static void unpack(String zipPath,String targetPath,String targetName) {

    }

    public static void pack(String sourcePath, String targetPath, String zipName) {

    }
    public static void doPack(){
        Scanner scanner=new Scanner(System.in);
        String sourcePath=scanner.nextLine(),targetPath=scanner.nextLine(),zipName=scanner.nextLine();
        pack(sourcePath, targetPath, zipName);
    }
    public static void doUnpack(){
        Scanner scanner=new Scanner(System.in);
        String zipPath=scanner.nextLine(),targetPath=scanner.nextLine(),targetName=scanner.nextLine();
        unpack(zipPath, targetPath, targetName);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String operation = scanner.nextLine();
        scanner.close();
        switch (operation) {
            case "pack" -> doPack();
            case "unpack" -> doUnpack();
            default -> System.out.println("unknown operation.");
        }
    }
}
