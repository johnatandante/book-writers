package ts.volta.it.writers.business;

import java.util.Random;
import java.util.concurrent.Semaphore;

import ts.volta.it.chatgpt.business.ChatGptWord;
import ts.volta.it.writers.bean.Book;

public class Writers extends Thread {
    private static final String tag = "[writers] ";
    private String name;
    private Semaphore s;
    private Book book;
    private ChatGptWord chatGptWord;
    private boolean canWrite;

    private Random r = new Random();
    public Writers(Semaphore s, String name, Book book) {
        this.name = name;
        this.s = s;
        this.book = book;
        this.chatGptWord = new ChatGptWord();
        this.canWrite = true;
    }

    public void run() {
        boolean exit = false;
        while(this.canWrite && !exit) {

            try {
                s.acquire();
                System.out.println(tag + "Readers: " + Readers.NReaders);
                if(Readers.NReaders > 0) {
                    System.out.println(tag + "Writer " + this.name + " waiting for readers to finish");
                    
                } else {
                    System.out.println(tag + tag + tag + "Writer " + this.name + " start writing");
                    String sentence = chatGptWord.produceSentence();
                    book.addSentence(sentence);
                    Thread.sleep(250 + r.nextInt(1000)); // Simulate writing time
                    System.out.println(tag + tag + tag + "Writer " + this.name + " wrote: " + sentence);
                }
                s.release();
                Thread.sleep(1000 + r.nextInt(1000)); // Wait for readers to finish
                
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }

    }


}
