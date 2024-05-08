package ts.volta.it.chatgpt.business;

public class ChatGptWord {

    public String produceSentence() {
        return String.format("Sentence %d", (int)(Math.random() * 10000));
    }

}
