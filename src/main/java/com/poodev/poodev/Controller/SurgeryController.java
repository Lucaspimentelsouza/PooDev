package com.poodev.poodev.Controller;

import com.poodev.poodev.DTO.Request.SurgeryCreateRequest;
import com.poodev.poodev.DTO.Response.SurgeryResponse;
import com.poodev.poodev.Service.SurgeryService;
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
import java.util.List;

@RestController
@RequestMapping("/api/patients/{patientId}/surgeries")
@RequiredArgsConstructor
public class SurgeryController {

    private final SurgeryService surgeryService;

    @PostMapping
    public ResponseEntity<SurgeryResponse> create(
            @PathVariable("patientId") Long patientId,
            @Valid @RequestBody SurgeryCreateRequest request
    ) {
        SurgeryResponse response =
                surgeryService.create(patientId, request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<List<SurgeryResponse>> findByPatientId(
            @PathVariable("patientId") Long patientId
    ) {
        return ResponseEntity.ok(
                surgeryService.findByPatientId(patientId)
        );
    }
}