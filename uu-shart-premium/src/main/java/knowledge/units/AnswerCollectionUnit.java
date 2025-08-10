package knowledge.units;


import java.util.List;

public class AnswerCollectionUnit extends KnowledgeUnit{

    private final List<String> answers;
    public AnswerCollectionUnit(String questionTitle, List<String> answers) {
        super(questionTitle);
        this.answers = answers;
    }

    @Override
    public String getAnswerString() {
        return String.join(" ▶ ", answers);
    }
}
