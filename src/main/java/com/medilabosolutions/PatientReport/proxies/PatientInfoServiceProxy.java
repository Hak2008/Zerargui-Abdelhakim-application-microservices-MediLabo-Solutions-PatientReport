package com.medilabosolutions.PatientReport.proxies;

import com.medilabosolutions.PatientReport.beans.PatientBean;
import com.medilabosolutions.PatientReport.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PatientInfoService", url = "${patientinfoservice.url}", configuration = FeignConfig.class)
public interface PatientInfoServiceProxy {

    @GetMapping("/patient/details/{id}")
    PatientBean getPatientById(@PathVariable("id") int id);

}
