package com.poodev.poodev.Service;

import com.poodev.poodev.DTO.Request.PreExistingConditionCreateRequest;
import com.poodev.poodev.DTO.Response.PreExistingConditionResponse;
import com.poodev.poodev.Entity.Patient;
import com.poodev.poodev.Exception.PatientNotFoundException;
import com.poodev.poodev.Exception.PreExistingConditionAlreadyExistsException;
import com.poodev.poodev.Mapper.PreExistingConditionMapper;
import com.poodev.poodev.Repository.PatientRepository;
import com.poodev.poodev.Repository.PreExistingConditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PreExistingConditionService {

    private final PreExistingConditionRepository
            preExistingConditionRepository;
    private final PatientRepository patientRepository;
    private final PreExistingConditionMapper preExistingConditionMapper;

    @Transactional
    public PreExistingConditionResponse create(
            Long patientId,
            PreExistingConditionCreateRequest request
    ) {
        Patient patient = patientRepository
                .findById(patientId)
                .orElseThrow(() ->
                        new PatientNotFoundException(patientId)
                );

        if (preExistingConditionRepository
                .existsByPatientIdAndConditionNameIgnoreCase(
                        patientId,
                        request.conditionName()
                )) {
            throw new PreExistingConditionAlreadyExistsException(
                    patientId,
                    request.conditionName()
            );
        }

        var condition =
                preExistingConditionMapper.toEntity(request, patient);

        var savedCondition =
                preExistingConditionRepository.save(condition);

        return preExistingConditionMapper.toResponse(savedCondition);
    }

    @Transactional(readOnly = true)
    public List<PreExistingConditionResponse> findByPatientId(
            Long patientId
    ) {
        if (!patientRepository.existsById(patientId)) {
            throw new PatientNotFoundException(patientId);
        }

        return preExistingConditionRepository
                .findByPatientIdAndActiveTrue(patientId)
                .stream()
                .map(preExistingConditionMapper::toResponse)
                .toList();
    }
}