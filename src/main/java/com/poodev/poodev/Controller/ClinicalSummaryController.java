package com.poodev.poodev.Controller;

import com.poodev.poodev.DTO.Response.ClinicalSummaryResponse;
import com.poodev.poodev.Service.ClinicalSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patients/{patientId}/clinical-summary")
@RequiredArgsConstructor
public class ClinicalSummaryController {

    private final ClinicalSummaryService clinicalSummaryService;

    @GetMapping
    public ResponseEntity<ClinicalSummaryResponse> getSummary(
            @PathVariable("patientId") Long patientId
    ) {
        return ResponseEntity.ok(
                clinicalSummaryService.build(patientId)
        );
    }
}