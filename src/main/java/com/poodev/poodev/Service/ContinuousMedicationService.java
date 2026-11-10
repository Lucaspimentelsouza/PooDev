package com.poodev.poodev.Service;

import com.poodev.poodev.DTO.Request.ContinuousMedicationCreateRequest;
import com.poodev.poodev.DTO.Response.ContinuousMedicationResponse;
import com.poodev.poodev.Entity.Patient;
import com.poodev.poodev.Exception.ContinuousMedicationAlreadyExistsException;
import com.poodev.poodev.Exception.PatientNotFoundException;
import com.poodev.poodev.Mapper.ContinuousMedicationMapper;
import com.poodev.poodev.Repository.ContinuousMedicationRepository;
import com.poodev.poodev.Repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContinuousMedicationService {

    private final ContinuousMedicationRepository continuousMedicationRepository;
    private final PatientRepository patientRepository;
    private final ContinuousMedicationMapper continuousMedicationMapper;

    @Transactional
    public ContinuousMedicationResponse create(
            Long patientId,
            ContinuousMedicationCreateRequest request
    ) {
        Patient patient = patientRepository
                .findById(patientId)
                .orElseThrow(() ->
                        new PatientNotFoundException(patientId)
                );

        if (continuousMedicationRepository
                .existsByPatientIdAndMedicationNameIgnoreCase(
                        patientId,
                        request.medicationName()
                )) {
            throw new ContinuousMedicationAlreadyExistsException(
                    patientId,
                    request.medicationName()
            );
        }

        var medication =
                continuousMedicationMapper.toEntity(request, patient);

        var savedMedication =
                continuousMedicationRepository.save(medication);

        return continuousMedicationMapper.toResponse(savedMedication);
    }

    @Transactional(readOnly = true)
    public List<ContinuousMedicationResponse> findByPatientId(
            Long patientId
    ) {
        if (!patientRepository.existsById(patientId)) {
            throw new PatientNotFoundException(patientId);
        }

        return continuousMedicationRepository
                .findByPatientIdAndActiveTrue(patientId)
                .stream()
                .map(continuousMedicationMapper::toResponse)
                .toList();
    }
}