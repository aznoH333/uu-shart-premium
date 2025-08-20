package knowledge.units;

import utils.TextUtils;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class KnowledgeGroupUnit extends KnowledgeUnit{
    private final ArrayList<ArrayList<String>> groups;

    public KnowledgeGroupUnit(String questionTitle, String knowledgeOrigin, ArrayList<ArrayList<String>> groups) {
        super(questionTitle, knowledgeOrigin);
        this.groups = groups.stream().map(it->it.stream().map(TextUtils::removeBreaks).collect(Collectors.toCollection(ArrayList::new))).collect(Collectors.toCollection(ArrayList::new));
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
