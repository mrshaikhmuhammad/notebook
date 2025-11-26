package note;

public class Note extends MetaNote{
    private String text;
    private String summary;

    public Note(String text) throws Exception{
        this.text = text;
        // this.summary = Summarize.summarize(text);
    }

    public String getTitle(){
        return title;
    }
}