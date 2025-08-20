package knowledge.units;



import utils.TextUtils;

import java.util.List;
import java.util.stream.Collectors;

public class KnowledgeCollectionUnit extends KnowledgeUnit{

    private final List<String> answers;
    public KnowledgeCollectionUnit(String questionTitle, String knowledgeOrigin, List<String> answers) {
        super(questionTitle, knowledgeOrigin);
        this.answers = answers.stream().map(TextUtils::removeBreaks).collect(Collectors.toList());
    }

    @Override
    public String getAnswerString() {
        return String.join(" ▶ ", answers);
    }
}
