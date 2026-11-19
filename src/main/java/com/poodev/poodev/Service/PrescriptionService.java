package com.poodev.poodev.Service;

import com.poodev.poodev.DTO.Request.PrescriptionRequest;
import com.poodev.poodev.DTO.Response.PrescriptionResponse;
import com.poodev.poodev.Entity.Consultation;
import com.poodev.poodev.Entity.Prescription;
import com.poodev.poodev.Exception.ConsultationNotFoundException;
import com.poodev.poodev.Exception.PrescriptionAlreadyExistsException;
import com.poodev.poodev.Exception.PrescriptionNotFoundException;
import com.poodev.poodev.Mapper.PrescriptionMapper;
import com.poodev.poodev.Repository.ConsultationRepository;
import com.poodev.poodev.Repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import com.poodev.poodev.Exception.PrescriptionAlreadySignedException;
import com.poodev.poodev.Exception.PrescriptionNotSignedException;
import com.poodev.poodev.Service.DigitalSignatureService.SignedContent;

@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final ConsultationRepository consultationRepository;
    private final PrescriptionMapper prescriptionMapper;
    private final DigitalSignatureService digitalSignatureService;

    @Transactional
    public PrescriptionResponse create(Long consultationId, PrescriptionRequest request)
    {
        Consultation consultation = consultationRepository
                .findById(consultationId)
                .orElseThrow(() ->
                        new ConsultationNotFoundException(consultationId)
                );

        if (prescriptionRepository.existsByConsultation_Id(consultationId)) {
            throw new PrescriptionAlreadyExistsException(consultationId);
        }

        LocalDateTime now = LocalDateTime.now();

        Prescription prescription = new Prescription();
        prescription.setConsultation(consultation);
        prescription.setContent(request.content());
        prescription.setCreatedAt(now);
        prescription.setUpdatedAt(now);

        Prescription saved = prescriptionRepository.save(prescription);

        return prescriptionMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public PrescriptionResponse findByConsultationId(Long consultationId) {
        checkConsultationExists(consultationId);

        Prescription prescription = findPrescription(consultationId);

        return prescriptionMapper.toResponse(prescription);
    }

    @Transactional
    public PrescriptionResponse update(
            Long consultationId,
            PrescriptionRequest request
    ) {
        checkConsultationExists(consultationId);

        Prescription prescription = findPrescription(consultationId);

        prescription.setContent(request.content());
        prescription.setUpdatedAt(LocalDateTime.now());

        Prescription saved = prescriptionRepository.save(prescription);

        return prescriptionMapper.toResponse(saved);
    }

    private void checkConsultationExists(Long consultationId) {
        if (!consultationRepository.existsById(consultationId)) {
            throw new ConsultationNotFoundException(consultationId);
        }
    }

    private Prescription findPrescription(Long consultationId) {
        return prescriptionRepository
                .findByConsultation_Id(consultationId)
                .orElseThrow(() ->
                        new PrescriptionNotFoundException(consultationId)
                );
    }

    @Transactional
    public PrescriptionResponse sign(Long consultationId) {
        Prescription prescription = findPrescription(consultationId);

        if (prescription.getSignature() != null) {
            throw new PrescriptionAlreadySignedException(consultationId);
        }

        SignedContent signedContent =
                digitalSignatureService.sign(prescription.getContent());

        prescription.setSignature(signedContent.signature());
        prescription.setSignatureAlgorithm(signedContent.algorithm());
        prescription.setSignedAt(LocalDateTime.now());
        prescription.setPublicKey(signedContent.publicKey());

        Prescription saved = prescriptionRepository.save(prescription);

        return prescriptionMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public boolean verify(Long consultationId) {
        Prescription prescription = findPrescription(consultationId);

        if (prescription.getSignature() == null) {
            throw new PrescriptionNotSignedException(consultationId);
        }

        return digitalSignatureService.verify(
                prescription.getContent(),
                prescription.getSignature(),
                prescription.getPublicKey()
        );
    }
}