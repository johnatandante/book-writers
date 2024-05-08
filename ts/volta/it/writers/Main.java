package ts.volta.it.writers;

import java.util.concurrent.Semaphore;

import ts.volta.it.writers.bean.Book;
import ts.volta.it.writers.bean.Libro;
import ts.volta.it.writers.business.Writers;

public class Main {

    public static void main(String[] args) {

        Book book = new Libro();
        Semaphore s = new Semaphore(1);

        new Writers(s, "gianni", book).start();
        new Writers(s, "pinotto", book).start();
        new Writers(s, "federico", book).start();
        new Writers(s, "dante", book).start();
        new Writers(s, "andrea", book).start();
        new Writers(s, "andrea zolli", book).start();
        new Writers(s, "pino scotto", book).start();
        new Writers(s, "sergio", book).start();

    }
}