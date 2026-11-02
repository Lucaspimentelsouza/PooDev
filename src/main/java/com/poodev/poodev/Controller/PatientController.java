package com.poodev.poodev.Controller;

import com.poodev.poodev.DTO.Request.PatientCreateRequest;
import com.poodev.poodev.DTO.Response.PatientResponse;
import com.poodev.poodev.Service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.net.URI;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponse> create(
            @Valid @RequestBody PatientCreateRequest request
    ) {
        PatientResponse response = patientService.create(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> findById(
            @PathVariable("id") Long patientId
    ) {
        PatientResponse response = patientService.findById(patientId);

        return ResponseEntity.ok(response);
    }
}