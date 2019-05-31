package Laboratoriska2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FileScanner extends Thread {

    private String fileToScan;
    //TODO: Initialize the start value of the counter
    private static Long counter = 0L;

    public FileScanner(String fileToScan) {
        this.fileToScan = fileToScan;
        //TODO: Increment the counter on every creation of FileScanner object
        synchronized (FileScanner.class) {
            counter++;
        }
    }

    public static void printInfo(File file) {

        /*
         * TODO: Print the info for the @argument File file, according to the requirement of the task
         * */
        if (file.isDirectory())
            System.out.println("dir: " + file.getAbsolutePath() + " " + file.length());
        else
            System.out.println("file: " + file.getAbsolutePath() + " " + file.length());
    }

    public static Long getCounter() {
        return counter;
    }

    public void run() {
        //TODO Create object File with the absolute path fileToScan.
        File file;
        file = new File(fileToScan);
        //TODO Create a list of all the files that are in the directory file.
        File[] files = file.listFiles();
        List<FileScanner> fileScanners = new ArrayList<>();
        printInfo(file);
        for (File f : files) {
            /*
             * TODO If the File f is not a directory, print its info using the function printInfo(f)
             * */
            if (!f.isDirectory())
                printInfo(f);
            else {
                FileScanner fs = new FileScanner(f.getAbsolutePath());
                fileScanners.add(fs);
                fs.start();
            }
            /*
             * TODO If the File f is a directory, create a thread from type FileScanner and start it.
             * */

            //TODO: wait for all the FileScanner-s to finish
        }
        try {
            for (FileScanner fs : fileScanners) {
                fs.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws InterruptedException {
        String FILE_TO_SCAN = ".";

        //TODO Construct a FileScanner object with the fileToScan = FILE_TO_SCAN
        FileScanner fileScanner = new FileScanner(FILE_TO_SCAN);

        fileScanner.start();
        //TODO Start the thread from type FileScanner

        fileScanner.join();
        //TODO wait for the fileScanner to finish
        System.out.println(counter);
        //TODO print a message that displays the number of thread that were created


    }
}