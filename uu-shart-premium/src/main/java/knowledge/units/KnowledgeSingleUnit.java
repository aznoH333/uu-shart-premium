package knowledge.units;

import utils.TextUtils;

public class KnowledgeSingleUnit extends KnowledgeUnit {

    private final String answer;

    public KnowledgeSingleUnit(String questionTitle, String knowledgeOrigin, String answer) {
        super(questionTitle, knowledgeOrigin);
        this.answer = TextUtils.removeBreaks(answer);
    }


    @Override
    public String getAnswerString() {
        return this.answer;
    }
}
