package com.poodev.poodev.Controller;

import com.poodev.poodev.DTO.Response.ConsultationResponse;
import com.poodev.poodev.Service.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patients/{patientId}/consultations")
@RequiredArgsConstructor
public class PatientConsultationController {

    private final ConsultationService consultationService;

    @GetMapping
    public ResponseEntity<List<ConsultationResponse>> findByPatientId(
            @PathVariable("patientId") Long patientId
    ) {
        return ResponseEntity.ok(
                consultationService.findByPatientId(patientId)
        );
    }
}