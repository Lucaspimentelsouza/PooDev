package com.poodev.poodev.Controller;

import com.poodev.poodev.DTO.Request.ConsultationCreateRequest;
import com.poodev.poodev.DTO.Response.ConsultationResponse;
import com.poodev.poodev.Service.ConsultationService;
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
@RequestMapping("/api/consultations")
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

    @PostMapping
    public ResponseEntity<ConsultationResponse> create(
            @Valid @RequestBody ConsultationCreateRequest request
    ) {
        ConsultationResponse response = consultationService.create(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationResponse> findById(
            @PathVariable("id") Long consultationId
    ) {
        return ResponseEntity.ok(
                consultationService.findById(consultationId)
        );
    }
}