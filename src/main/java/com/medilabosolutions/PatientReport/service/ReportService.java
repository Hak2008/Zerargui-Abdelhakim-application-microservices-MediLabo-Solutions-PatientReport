package com.medilabosolutions.PatientReport.service;

import com.medilabosolutions.PatientReport.beans.NoteBean;
import com.medilabosolutions.PatientReport.beans.PatientBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class ReportService {

    public static String evaluateRiskLevel(PatientBean patient, List<NoteBean> patientNotes) {
        if (patientNotes.isEmpty()) {
            return "None";
        } else {
            int triggerCount = countTriggerTerms(patientNotes);
            int age = patient.getAge();
            String gender = patient.getGender();

            if (isPatientInEarlyOnset(age, gender, triggerCount)) {
                return "Early onset";
            } else if (isPatientInDanger(age, gender, triggerCount)) {
                return "In Danger";
            } else if (triggerCount >= 2 && triggerCount <= 5 && age > 30) {
                return "Borderline";
            } else {
                return "None";
            }
        }
    }

    private static int countTriggerTerms(List<NoteBean> patientNotes) {
        int count = 0;
        for (NoteBean note : patientNotes) {
            String noteContent = note.getNote().toLowerCase();
            count += countTriggerTermsInNote(noteContent);
        }
        log.info("Nombre de termes déclencheurs trouvés : {}", count);
        return count;
    }


    private static boolean isPatientInDanger(int age, String gender, int triggerCount) {
        if (age < 30) {
            return (gender.equalsIgnoreCase("M") && triggerCount >= 3) || (gender.equalsIgnoreCase("F") && triggerCount >= 4);
        } else {
            return (gender.equalsIgnoreCase("M") && triggerCount >= 6 && triggerCount <= 7) || (gender.equalsIgnoreCase("F") && triggerCount >= 7);
        }
    }

    private static boolean isPatientInEarlyOnset(int age, String gender, int triggerCount) {
        if (age < 30) {
            return (gender.equalsIgnoreCase("M") && triggerCount >= 5) || (gender.equalsIgnoreCase("F") && triggerCount >= 7);
        } else {
            return triggerCount >= 8;
        }
    }

    private static int countTriggerTermsInNote(String noteContent) {
        int noteTriggerCount = 0;
        // Convert noteContent to lowercase
        noteContent = noteContent.toLowerCase();

        // Check if the note content contains trigger terms
        List<String> triggerTerms = Arrays.asList(
                "hémoglobine a1c", "microalbumine", "taille", "poids",
                "fumeur", "fumeuse", "anormal", "cholestérol", "vertiges",
                "rechute", "réaction", "anticorps"
        );

        for (String term : triggerTerms) {
            if (noteContent.contains(term)) {
                log.info("Terme déclencheur trouvé: {}", term);
                noteTriggerCount++;
            }
        }
        if (noteTriggerCount == 0) {
            log.info("Aucun terme déclencheur trouvé");
        }
        return noteTriggerCount;
    }
}
