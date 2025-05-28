package ts.volta.it.writers;

import java.util.concurrent.Semaphore;

import ts.volta.it.writers.bean.Book;
import ts.volta.it.writers.bean.Libro;
import ts.volta.it.writers.business.Readers;
import ts.volta.it.writers.business.Writers;

public class Main {

    public static void main(String[] args) {

        final int NWriters = 2; // Number of writers

        Book book = new Libro();
        Semaphore s = new Semaphore(1);
        Semaphore sRead = new Semaphore(10);

        for (int i = 0; i < NWriters ; i++) {
            try {
                Writers writer1 = new Writers(s, "Writer" + i, book);
                writer1.join(); 
                writer1.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        final int NReaders = 20; // Number of readers
        for (int i = 0; i < NReaders ; i++) {
            try {
                Readers reader1 = new Readers(sRead, s, "Reader" + i, book);
                reader1.join(); 
                reader1.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}