package com.poodev.poodev.Service;

import com.poodev.poodev.DTO.Request.PatientCreateRequest;
import com.poodev.poodev.DTO.Response.PatientResponse;
import com.poodev.poodev.Entity.Patient;
import com.poodev.poodev.Mapper.PatientMapper;
import com.poodev.poodev.Repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.poodev.poodev.Exception.PatientNotFoundException;
import org.springframework.data.domain.Sort;
import com.poodev.poodev.DTO.Request.PatientUpdateRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Transactional
    public PatientResponse create(PatientCreateRequest request) {
        Patient patient = patientMapper.toEntity(request);

        Patient savedPatient = patientRepository.save(patient);

        return patientMapper.toResponse(savedPatient);
    }

    @Transactional(readOnly = true)
    public PatientResponse findById(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(patientId));

        return patientMapper.toResponse(patient);
    }

    @Transactional(readOnly = true)
    public List<PatientResponse> findAll() {
        List<Patient> patients = patientRepository.findAll(
                Sort.by("name", "id").ascending()
        );

        return patients.stream()
                .map(patientMapper::toResponse)
                .toList();
    }

    @Transactional
    public PatientResponse update(
            Long patientId,
            PatientUpdateRequest request
    ) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(patientId));

        patientMapper.updateEntity(request, patient);

        Patient savedPatient = patientRepository.save(patient);

        return patientMapper.toResponse(savedPatient);
    }
}