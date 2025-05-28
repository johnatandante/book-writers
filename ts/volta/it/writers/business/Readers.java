package ts.volta.it.writers.business;

import java.util.Random;
import java.util.concurrent.Semaphore;

import ts.volta.it.writers.bean.Book;

public class Readers extends Thread {
    private static final String tag = "[readers] ";
    
    public static int NReaders = 0;

    private String name;
    private Semaphore lockSemaphore;
    private Semaphore readSemaphore;
    private Book book;
    private boolean canRead;

    private Random r = new Random();
    public Readers(Semaphore readSemaphore, Semaphore s, String name, Book book) {
        this.name = name;
        this.lockSemaphore = s;
        this.readSemaphore = readSemaphore;
        this.book = book;
        this.canRead = true;
    }

    public void run() {
        boolean exit = false;
        while(this.canRead && !exit) {

            try {
                
                readSemaphore.acquire();
                lockSemaphore.acquire();
                System.out.println(tag + "Readers: " + NReaders);
                NReaders++;
                lockSemaphore.release();
                
                System.out.println(tag + "Reader " + this.name + " start reading");
                String text = book.getText();
                Thread.sleep(1250 + r.nextInt(1000)); // Simulate writing time
                System.out.println(tag + "Reader " + this.name + " finished reading: " + text);

                lockSemaphore.acquire();
                NReaders--;
                System.out.println(tag + "Readers: " + NReaders);
                lockSemaphore.release();
                readSemaphore.release();
                Thread.sleep(1000 + r.nextInt(1000)); // Wait for next turn
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }

    }


}
