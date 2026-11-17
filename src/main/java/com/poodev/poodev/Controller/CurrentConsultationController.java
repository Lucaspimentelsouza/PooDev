package com.poodev.poodev.Controller;

import com.poodev.poodev.DTO.Response.CurrentConsultationResponse;
import com.poodev.poodev.Service.CurrentConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consultations/{consultationId}/current")
@RequiredArgsConstructor
public class CurrentConsultationController {

    private final CurrentConsultationService currentConsultationService;

    @GetMapping
    public ResponseEntity<CurrentConsultationResponse> findByConsultationId(
            @PathVariable("consultationId") Long consultationId
    ) {
        return ResponseEntity.ok(
                currentConsultationService
                        .findByConsultationId(consultationId)
        );
    }
}