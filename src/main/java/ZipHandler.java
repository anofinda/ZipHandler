import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.zip.*;

/**
 * @author dongyudeng
 */
public class ZipHandler {
    public static void unpack(String zipPath, String targetPath, String targetName) {

    }

    public static void pack(File nowFile, String nowFileName, ZipArchiveOutputStream zipOutputStream) throws IOException {
        if (nowFile.isHidden()) {
            return;
        }
        if (!nowFile.isDirectory()) {
            ZipArchiveEntry entry = new ZipArchiveEntry(nowFileName);
            zipOutputStream.putArchiveEntry(entry);
            zipOutputStream.write(Files.readAllBytes(nowFile.toPath()));
            zipOutputStream.closeArchiveEntry();
        } else {
            zipOutputStream.putArchiveEntry(new ZipArchiveEntry(nowFileName));
            zipOutputStream.closeArchiveEntry();
            File[] children = nowFile.listFiles();
            if(children!=null){
                for(File file:children){
                    pack(file,nowFileName+"/"+file.getName(),zipOutputStream);
                }
            }
        }
    }

    public static void pack(String sourcePath, String targetPath, String zipName) {
        File sourceFile = new File(sourcePath), zipFile = new File(targetPath+"/"+zipName+".zip");
        if (!sourceFile.exists() || zipFile.exists()) {
            System.out.println("Error!");
            return;
        }
        try (ZipArchiveOutputStream outputStream = new ZipArchiveOutputStream(zipFile)) {
            pack(sourceFile, sourceFile.getName(), outputStream);
        }catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void doPack() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String sourcePath = scanner.nextLine(), targetPath = scanner.nextLine(), zipName = scanner.nextLine();
        pack(sourcePath, targetPath, zipName);
        scanner.close();
    }

    public static void doUnpack() {
        Scanner scanner = new Scanner(System.in);
        String zipPath = scanner.nextLine(), targetPath = scanner.nextLine(), targetName = scanner.nextLine();
        unpack(zipPath, targetPath, targetName);
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String operation = scanner.nextLine();
        switch (operation) {
            case "pack" -> doPack();
            case "unpack" -> doUnpack();
            default -> System.out.println("unknown operation.");
        }
        scanner.close();
    }
}
