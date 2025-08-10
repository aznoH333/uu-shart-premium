package knowledge.units;


import java.util.List;

public class KnowledgeCollectionUnit extends KnowledgeUnit{

    private final List<String> answers;
    public KnowledgeCollectionUnit(String questionTitle, List<String> answers) {
        super(questionTitle);
        this.answers = answers;
    }

    @Override
    public String getAnswerString() {
        return String.join(" ▶ ", answers);
    }
}
