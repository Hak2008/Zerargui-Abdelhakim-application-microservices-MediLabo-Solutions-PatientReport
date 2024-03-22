package com.medilabosolutions.PatientReport.proxies;

import com.medilabosolutions.PatientReport.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PatientNoteService", url = "localhost:8081")
public interface NoteInfoServiceProxy {

    @GetMapping("/note/details/{patId}")
    List<NoteBean> getNotesByPatId(@PathVariable("patId") int patId);

}
