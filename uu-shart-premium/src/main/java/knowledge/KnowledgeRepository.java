package knowledge;

import knowledge.units.KnowledgeUnit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KnowledgeRepository {

    private final HashMap<String, ArrayList<KnowledgeUnit>> knowledge;
    private String currentSectionName;
    /**
     * debug value. omits answers from questions with differentTypes
     */
    private String acceptAnswersFrom;


    public KnowledgeRepository() {
        this.knowledge = new HashMap<>();
        this.acceptAnswersFrom = null;
        this.currentSectionName = null;
    }

    public void startLoggingSection(String sectionName) {
        this.currentSectionName = sectionName;
    }


    public void logKnowledge(KnowledgeUnit knowledgeUnit) {
        if (this.acceptAnswersFrom == null || this.acceptAnswersFrom.equals(knowledgeUnit.getKnowledgeOrigin())) {

            if (!this.knowledge.containsKey(this.currentSectionName)) {
                this.knowledge.put(this.currentSectionName, new ArrayList<>());
            }
            this.knowledge.get(this.currentSectionName).add(knowledgeUnit);

        }
    }

    public boolean containsKnowledge(String knowledgeName) {
        for (KnowledgeUnit unit : this.knowledge.get(this.currentSectionName)) {
            if (knowledgeName.equals(unit.getTitle())) {
                return true;
            }
        }

        return false;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, ArrayList<KnowledgeUnit>> section : this.knowledge.entrySet()) {
            builder.append("=======================================\n");
            builder.append("Section : ").append(section.getKey()).append("\n");
            builder.append("=======================================\n");

            for (KnowledgeUnit knowledgeUnit : section.getValue()) {
                builder.append("\n").append(knowledgeUnit.toString()).append("\n");
            }
        }

        return builder.toString();
    }

    public void acceptOnlyAnswersFrom(String questionType) {
        this.acceptAnswersFrom = questionType;
    }
}
