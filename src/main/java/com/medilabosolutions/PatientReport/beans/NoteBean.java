package com.medilabosolutions.PatientReport.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoteBean {

    private Integer patId;
    private String note;
}