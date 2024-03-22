package com.medilabosolutions.PatientReport.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PatientBean {

    private Integer id;
    private Date dateOfBirth;
    private String gender;

    public int getAge() {
        // Calculate age based on the date of birth
        Date currentDate = new Date();
        long ageInMillis = currentDate.getTime() - dateOfBirth.getTime();
        long years = ageInMillis / (1000L * 60 * 60 * 24 * 365);
        return (int) years;
    }

}

