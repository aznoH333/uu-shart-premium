package knowledge.units;

import java.util.ArrayList;

public class KnowledgeGroupUnit extends KnowledgeUnit{
    private final ArrayList<ArrayList<String>> groups;

    public KnowledgeGroupUnit(String questionTitle, String knowledgeOrigin, ArrayList<ArrayList<String>> groups) {
        super(questionTitle, knowledgeOrigin);
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
