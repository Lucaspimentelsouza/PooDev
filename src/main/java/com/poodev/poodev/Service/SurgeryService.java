package com.poodev.poodev.Service;

import com.poodev.poodev.DTO.Request.SurgeryCreateRequest;
import com.poodev.poodev.DTO.Response.SurgeryResponse;
import com.poodev.poodev.Entity.Patient;
import com.poodev.poodev.Exception.PatientNotFoundException;
import com.poodev.poodev.Exception.SurgeryAlreadyExistsException;
import com.poodev.poodev.Mapper.SurgeryMapper;
import com.poodev.poodev.Repository.PatientRepository;
import com.poodev.poodev.Repository.SurgeryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurgeryService {

    private final SurgeryRepository surgeryRepository;
    private final PatientRepository patientRepository;
    private final SurgeryMapper surgeryMapper;

    @Transactional
    public SurgeryResponse create(
            Long patientId,
            SurgeryCreateRequest request
    ) {
        Patient patient = patientRepository
                .findById(patientId)
                .orElseThrow(() ->
                        new PatientNotFoundException(patientId)
                );

        if (surgeryRepository
                .existsByPatientIdAndProcedureNameIgnoreCaseAndSurgeryDate(
                        patientId,
                        request.procedureName(),
                        request.surgeryDate()
                )) {
            throw new SurgeryAlreadyExistsException(
                    patientId,
                    request.procedureName(),
                    request.surgeryDate()
            );
        }

        var surgery = surgeryMapper.toEntity(request, patient);

        var savedSurgery = surgeryRepository.save(surgery);

        return surgeryMapper.toResponse(savedSurgery);
    }

    @Transactional(readOnly = true)
    public List<SurgeryResponse> findByPatientId(Long patientId) {
        if (!patientRepository.existsById(patientId)) {
            throw new PatientNotFoundException(patientId);
        }

        return surgeryRepository
                .findByPatientIdOrderBySurgeryDateDesc(patientId)
                .stream()
                .map(surgeryMapper::toResponse)
                .toList();
    }
}