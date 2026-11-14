package com.poodev.poodev.Controller;

import com.poodev.poodev.DTO.Response.ConsultationHistoryItemResponse;
import com.poodev.poodev.Service.ConsultationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patients/{patientId}/consultation-history")
@RequiredArgsConstructor
public class ConsultationHistoryController {

    private final ConsultationHistoryService consultationHistoryService;

    @GetMapping
    public ResponseEntity<List<ConsultationHistoryItemResponse>> findByPatientId(
            @PathVariable("patientId") Long patientId
    ) {
        return ResponseEntity.ok(
                consultationHistoryService.findByPatientId(patientId)
        );
    }
}