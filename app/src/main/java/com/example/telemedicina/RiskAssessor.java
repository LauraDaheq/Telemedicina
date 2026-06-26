package com.example.telemedicina;

public class RiskAssessor {
    public enum RiskLevel { LOW, MEDIUM, HIGH }

    public static RiskLevel assess(boolean fever, boolean cough, boolean breathDifficulty,
                                   boolean lossTasteSmell, boolean soreThroat, boolean fatigue,
                                   int age, boolean comorbidity) {
        int score = 0;
        if (fever) score += 2;
        if (cough) score += 1;
        if (breathDifficulty) score += 3;
        if (lossTasteSmell) score += 2;
        if (soreThroat) score += 1;
        if (fatigue) score += 1;
        if (age >= 60) score += 1;
        if (comorbidity) score += 2;

        if (score >= 6) return RiskLevel.HIGH;
        if (score >= 3) return RiskLevel.MEDIUM;
        return RiskLevel.LOW;
    }
}
