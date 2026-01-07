package note;

import java.time.LocalDateTime;

public class Note extends MetaNote{
    private String text;
    private String summary;

    public Note(String text) throws Exception{
        this.text = text;
        // this.summary = Summarize.summarize(text);
    }

    public Note() {
        this.text = "";
        this.summary = "";
        if (this.date == null) {
            this.date = LocalDateTime.now().toString();
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}