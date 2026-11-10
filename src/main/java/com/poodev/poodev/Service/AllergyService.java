package com.poodev.poodev.Service;

import com.poodev.poodev.DTO.Request.AllergyCreateRequest;
import com.poodev.poodev.DTO.Response.AllergyResponse;
import com.poodev.poodev.Entity.Patient;
import com.poodev.poodev.Exception.AllergyAlreadyExistsException;
import com.poodev.poodev.Exception.PatientNotFoundException;
import com.poodev.poodev.Mapper.AllergyMapper;
import com.poodev.poodev.Repository.AllergyRepository;
import com.poodev.poodev.Repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AllergyService {

    private final AllergyRepository allergyRepository;
    private final PatientRepository patientRepository;
    private final AllergyMapper allergyMapper;

    @Transactional
    public AllergyResponse create(
            Long patientId,
            AllergyCreateRequest request
    ) {
        Patient patient = patientRepository
                .findById(patientId)
                .orElseThrow(() ->
                        new PatientNotFoundException(patientId)
                );

        if (allergyRepository.existsByPatientIdAndAllergenIgnoreCase(
                patientId,
                request.allergen()
        )) {
            throw new AllergyAlreadyExistsException(
                    patientId,
                    request.allergen()
            );
        }

        var allergy = allergyMapper.toEntity(request, patient);

        var savedAllergy = allergyRepository.save(allergy);

        return allergyMapper.toResponse(savedAllergy);
    }

    @Transactional(readOnly = true)
    public List<AllergyResponse> findByPatientId(Long patientId) {
        if (!patientRepository.existsById(patientId)) {
            throw new PatientNotFoundException(patientId);
        }

        return allergyRepository.findByPatientIdAndActiveTrue(patientId)
                .stream()
                .map(allergyMapper::toResponse)
                .toList();
    }
}