package knowledge.units;

public class SingleAnswerUnit extends KnowledgeUnit {

    private final String answer;

    public SingleAnswerUnit(String questionTitle, String answer) {
        super(questionTitle);
        this.answer = answer;
    }


    @Override
    public String getAnswerString() {
        return this.answer;
    }
}
