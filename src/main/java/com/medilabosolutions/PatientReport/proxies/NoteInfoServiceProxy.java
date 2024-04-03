package com.medilabosolutions.PatientReport.proxies;

import com.medilabosolutions.PatientReport.beans.NoteBean;
import com.medilabosolutions.PatientReport.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PatientNoteService", url = "${patientnoteservice.url}", configuration = FeignConfig.class)
public interface NoteInfoServiceProxy {

    @GetMapping("/note/details/{patId}")
    List<NoteBean> getNotesByPatId(@PathVariable("patId") int patId);

}
