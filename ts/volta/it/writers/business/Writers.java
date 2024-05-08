package ts.volta.it.writers.business;

import java.util.concurrent.Semaphore;

import ts.volta.it.chatgpt.business.ChatGptWord;
import ts.volta.it.writers.bean.Book;
import ts.volta.it.writers.bean.Libro;

public class Writers extends Thread {
    
    private String name;
    private Semaphore s;
    private Book book;
    private ChatGptWord chatGptWord;
    private boolean canWrite;

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
                String sentence = chatGptWord.produceSentence();
                s.acquire();
                
                if(!book.isFinished()) {
                    // qua inizia l'area critica, ovvero quella di utilizzo dell'area condivisa
                    System.out.printf("Mancano %d caratteri - ", Libro.MAX_CHARS - this.book.getText().length());
                    this.canWrite = book.addSentence(sentence);
                } else {
                    exit = true;
                }

                s.release();
                
                if(!exit) {

                    System.out.printf("%s ha aggiunto la frase '%s'\n", name, sentence);
                    
                    if(!this.canWrite){
                        System.out.printf("%s ha appena finito il libro'\n", name);
                    } else {
                        Thread.sleep(250 + (long)(Math.random() * 1000));

                    }
                }

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }

    }


}
