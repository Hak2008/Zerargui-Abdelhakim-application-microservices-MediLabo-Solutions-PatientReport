package com.medilabosolutions.PatientReport.controller;

import com.medilabosolutions.PatientReport.model.Report;
import com.medilabosolutions.PatientReport.proxies.NoteInfoServiceProxy;
import com.medilabosolutions.PatientReport.proxies.PatientInfoServiceProxy;
import com.medilabosolutions.PatientReport.service.ReportService;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.medilabosolutions.PatientReport.beans.PatientBean;
import com.medilabosolutions.PatientReport.beans.NoteBean;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportController {

    private final PatientInfoServiceProxy patientInfoServiceProxy;
    private final NoteInfoServiceProxy noteInfoServiceProxy;
    private final ReportService reportService;

    public ReportController(PatientInfoServiceProxy patientInfoServiceProxy, NoteInfoServiceProxy noteInfoServiceProxy, ReportService reportService) {
        this.patientInfoServiceProxy = patientInfoServiceProxy;
        this.noteInfoServiceProxy = noteInfoServiceProxy;
        this.reportService = reportService;
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Integer id) {

        // Initialize patient and patientNotes
        PatientBean patient;
        List<NoteBean> patientNotes;

        try {
            // Try to get patient information using the PatientInfoServiceProxy
            patient = patientInfoServiceProxy.getPatientById(id);
            // Try to get patient notes using the NoteInfoServiceProxy
            patientNotes = noteInfoServiceProxy.getNotesByPatId(id);
        } catch (FeignException.NotFound ex) {
            // If either patient or notes are not found, return an empty report
            Report report = new Report();
            return ResponseEntity.ok(report);
        }

        // Evaluate diabetes risk based on patient information and notes
        String riskLevel = reportService.evaluateRiskLevel(patient, patientNotes);

        // Create a report object
        Report report = new Report();
        report.setPatId(id);
        report.setExpectedRisk(riskLevel);

        // Return the report in the response
        return ResponseEntity.ok(report);
    }

}
