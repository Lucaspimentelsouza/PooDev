package com.poodev.poodev.Controller;

import com.poodev.poodev.DTO.Request.VitalSignsCreateRequest;
import com.poodev.poodev.DTO.Response.VitalSignsResponse;
import com.poodev.poodev.Service.VitalSignsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/consultations/{consultationId}/vital-signs")
@RequiredArgsConstructor
public class VitalSignsController {

    private final VitalSignsService vitalSignsService;

    @PostMapping
    public ResponseEntity<VitalSignsResponse> create(
            @PathVariable("consultationId") Long consultationId,
            @Valid @RequestBody VitalSignsCreateRequest request
    ) {
        VitalSignsResponse response =
                vitalSignsService.create(consultationId, request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<VitalSignsResponse> findByConsultationId(
            @PathVariable("consultationId") Long consultationId
    ) {
        return ResponseEntity.ok(
                vitalSignsService.findByConsultationId(consultationId)
        );
    }
}