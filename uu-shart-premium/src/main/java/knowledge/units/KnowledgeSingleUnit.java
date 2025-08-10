package knowledge.units;

public class KnowledgeSingleUnit extends KnowledgeUnit {

    private final String answer;

    public KnowledgeSingleUnit(String questionTitle, String answer) {
        super(questionTitle);
        this.answer = answer;
    }


    @Override
    public String getAnswerString() {
        return this.answer;
    }
}
