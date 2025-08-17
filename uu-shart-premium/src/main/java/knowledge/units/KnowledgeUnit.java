package knowledge.units;

public abstract class KnowledgeUnit {

    private final String questionTitle;
    private final String knowledgeOrigin;

    public KnowledgeUnit(String questionTitle, String knowledgeOrigin) {
        this.questionTitle = questionTitle;
        this.knowledgeOrigin = knowledgeOrigin;
    }

    public abstract String getAnswerString();


    public String getTitle() {
        return questionTitle;
    }

    public String getKnowledgeOrigin() {
        return knowledgeOrigin;
    }

    public String toString() {
        return getTitle() + "\n => " + getAnswerString();
    }
}
