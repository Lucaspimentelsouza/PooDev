package com.poodev.poodev.Controller;

import com.poodev.poodev.DTO.Request.PrescriptionRequest;
import com.poodev.poodev.DTO.Response.PrescriptionResponse;
import com.poodev.poodev.Service.PrescriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import com.poodev.poodev.Service.PrescriptionPdfService;
import org.springframework.http.CacheControl;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
@RequestMapping("/api/consultations/{consultationId}/prescription")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    private final PrescriptionPdfService prescriptionPdfService;

    @PostMapping
    public ResponseEntity<PrescriptionResponse> create(
            @PathVariable("consultationId") Long consultationId,
            @Valid @RequestBody PrescriptionRequest request
    ) {
        PrescriptionResponse response =
                prescriptionService.create(consultationId, request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<PrescriptionResponse> findByConsultationId(
            @PathVariable("consultationId") Long consultationId
    ) {
        return ResponseEntity.ok(
                prescriptionService.findByConsultationId(consultationId)
        );
    }

    @PutMapping
    public ResponseEntity<PrescriptionResponse> update(
            @PathVariable("consultationId") Long consultationId,
            @Valid @RequestBody PrescriptionRequest request
    ) {
        return ResponseEntity.ok(
                prescriptionService.update(consultationId, request)
        );
    }

    //added because pdf file
    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> downloadPdf(
            @PathVariable("consultationId") Long consultationId
    ) {
        byte[] pdf = prescriptionPdfService.generate(consultationId);

        String filename =
                "receita-consulta-" + consultationId + ".pdf";

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename(filename)
                                .build()
                                .toString()
                )
                .cacheControl(CacheControl.noStore())
                .body(pdf);
    }

    @PatchMapping("/sign")
    public ResponseEntity<PrescriptionResponse> sign(
            @PathVariable("consultationId") Long consultationId
    ) {
        return ResponseEntity.ok(
                prescriptionService.sign(consultationId)
        );
    }

    @GetMapping("/verify")
    public ResponseEntity<Boolean> verify(
            @PathVariable("consultationId") Long consultationId
    ) {
        return ResponseEntity.ok(
                prescriptionService.verify(consultationId)
        );
    }
}