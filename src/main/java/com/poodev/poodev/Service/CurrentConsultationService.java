package com.poodev.poodev.Service;

import com.poodev.poodev.DTO.Response.ConsultationResponse;
import com.poodev.poodev.DTO.Response.CurrentConsultationResponse;
import com.poodev.poodev.DTO.Response.VitalSignsResponse;
import com.poodev.poodev.Mapper.VitalSignsMapper;
import com.poodev.poodev.Repository.VitalSignsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CurrentConsultationService {

    private final ConsultationService consultationService;
    private final VitalSignsRepository vitalSignsRepository;
    private final VitalSignsMapper vitalSignsMapper;

    @Transactional(readOnly = true)
    public CurrentConsultationResponse findByConsultationId(
            Long consultationId
    ) {
        ConsultationResponse consultation =
                consultationService.findById(consultationId);

        VitalSignsResponse vitalSigns = vitalSignsRepository
                .findByConsultation_Id(consultationId)
                .map(vitalSignsMapper::toResponse)
                .orElse(null);

        return new CurrentConsultationResponse(
                consultation,
                vitalSigns
        );
    }
}