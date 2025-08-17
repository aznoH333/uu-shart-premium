package knowledge;

import knowledge.units.KnowledgeUnit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KnowledgeRepository {

    private final HashMap<String, ArrayList<KnowledgeUnit>> knowledge;
    private String currentSectionName;
    private ArrayList<KnowledgeUnit> currentSectionUnits;
    /**
     * debug value. omits answers from questions with differentTypes
     */
    private String acceptAnswersFrom;


    public KnowledgeRepository() {
        this.knowledge = new HashMap<>();
        this.currentSectionName = null;
        this.currentSectionUnits = null;
        this.acceptAnswersFrom = null;
    }

    public void startLoggingSection(String sectionName) {

        this.saveCurrentSection();


        this.currentSectionName = sectionName;
        this.currentSectionUnits = new ArrayList<>();

    }

    private void saveCurrentSection() {
        if (this.currentSectionName != null) {
            this.knowledge.put(this.currentSectionName, this.currentSectionUnits);
        }
    }

    public void logKnowledge(KnowledgeUnit knowledgeUnit) {
        if (this.acceptAnswersFrom == null || this.acceptAnswersFrom.equals(knowledgeUnit.getKnowledgeOrigin())) {
            this.currentSectionUnits.add(knowledgeUnit);
        }
    }

    public String toString() {
        this.saveCurrentSection();
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
