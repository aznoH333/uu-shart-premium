package knowledge.units;

import java.util.List;

public class AnswerGroupUnit extends KnowledgeUnit{
    private final List<List<String>> groups;

    public AnswerGroupUnit(String questionTitle, List<List<String>> groups) {
        super(questionTitle);
        this.groups = groups;
    }

    @Override
    public String getAnswerString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < groups.size(); i++) {
            builder.append("[").append(i + 1).append("] :").append(String.join(" ▶ ", groups.get(i))).append("\n");
        }

        return builder.toString();
    }
}
