package com.poodev.poodev.Service;

import com.poodev.poodev.DTO.Request.VitalSignsCreateRequest;
import com.poodev.poodev.DTO.Response.VitalSignsResponse;
import com.poodev.poodev.Entity.Consultation;
import com.poodev.poodev.Entity.VitalSigns;
import com.poodev.poodev.Exception.ConsultationNotFoundException;
import com.poodev.poodev.Exception.VitalSignsAlreadyExistsException;
import com.poodev.poodev.Exception.VitalSignsNotFoundException;
import com.poodev.poodev.Mapper.VitalSignsMapper;
import com.poodev.poodev.Repository.ConsultationRepository;
import com.poodev.poodev.Repository.VitalSignsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VitalSignsService {

    private final VitalSignsRepository vitalSignsRepository;
    private final ConsultationRepository consultationRepository;
    private final VitalSignsMapper vitalSignsMapper;

    @Transactional
    public VitalSignsResponse create(
            Long consultationId,
            VitalSignsCreateRequest request
    ) {
        Consultation consultation = consultationRepository
                .findById(consultationId)
                .orElseThrow(() ->
                        new ConsultationNotFoundException(consultationId)
                );

        if (vitalSignsRepository.existsByConsultation_Id(consultationId)) {
            throw new VitalSignsAlreadyExistsException(consultationId);
        }

        VitalSigns vitalSigns =
                vitalSignsMapper.toEntity(request, consultation);

        VitalSigns savedVitalSigns =
                vitalSignsRepository.save(vitalSigns);

        return vitalSignsMapper.toResponse(savedVitalSigns);
    }

    @Transactional(readOnly = true)
    public VitalSignsResponse findByConsultationId(Long consultationId) {
        if (!consultationRepository.existsById(consultationId)) {
            throw new ConsultationNotFoundException(consultationId);
        }

        VitalSigns vitalSigns = vitalSignsRepository
                .findByConsultation_Id(consultationId)
                .orElseThrow(() ->
                        new VitalSignsNotFoundException(consultationId)
                );

        return vitalSignsMapper.toResponse(vitalSigns);
    }
}