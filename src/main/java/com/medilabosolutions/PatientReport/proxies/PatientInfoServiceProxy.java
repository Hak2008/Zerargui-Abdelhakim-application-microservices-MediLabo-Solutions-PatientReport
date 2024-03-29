package com.medilabosolutions.PatientReport.proxies;

import com.medilabosolutions.PatientReport.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "PatientInfoService", url = "Localhost:8080")
public interface PatientInfoServiceProxy {

    @GetMapping("/patient/details/{id}")
    PatientBean getPatientById(@PathVariable("id") int id);

}
