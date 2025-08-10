package knowledge.units;

public abstract class KnowledgeUnit {

    private final String questionTitle;

    public KnowledgeUnit(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public abstract String getAnswerString();


    public String getTitle() {
        return questionTitle;
    }

    public String toString() {
        return getTitle() + " = " + getAnswerString();
    }
}
