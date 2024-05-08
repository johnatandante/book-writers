package ts.volta.it.writers.bean;

public class Libro implements Book{
    public static int MAX_CHARS = 1000;
    private String text;

    public Libro() {
        this.text = "";
    }

    public String getText(){
        return this.text;
    }

    public boolean addSentence(String sentence){
        this.text += sentence;
        return !this.isFinished();
    }

    public boolean isFinished() {
        return this.text.length() >= MAX_CHARS;   
    }
}
