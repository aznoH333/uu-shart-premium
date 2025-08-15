package knowledge.units;


import java.util.List;

public class KnowledgeCollectionUnit extends KnowledgeUnit{

    private final List<String> answers;
    public KnowledgeCollectionUnit(String questionTitle, String knowledgeOrigin, List<String> answers) {
        super(questionTitle, knowledgeOrigin);
        this.answers = answers;
    }

    @Override
    public String getAnswerString() {
        return String.join(" ▶ ", answers);
    }
}
