package ts.volta.it.writers.bean;

public interface Book {
    
    boolean addSentence(String sentence);

    boolean isFinished();

    String getText();

}
