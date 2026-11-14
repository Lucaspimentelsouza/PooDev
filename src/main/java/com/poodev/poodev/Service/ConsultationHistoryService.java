package com.poodev.poodev.Service;

import com.poodev.poodev.DTO.Response.ConsultationHistoryItemResponse;
import com.poodev.poodev.Exception.PatientNotFoundException;
import com.poodev.poodev.Mapper.ConsultationHistoryMapper;
import com.poodev.poodev.Repository.ConsultationRepository;
import com.poodev.poodev.Repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultationHistoryService {

    private final ConsultationRepository consultationRepository;
    private final PatientRepository patientRepository;
    private final ConsultationHistoryMapper consultationHistoryMapper;

    @Transactional(readOnly = true)
    public List<ConsultationHistoryItemResponse> findByPatientId(
            Long patientId
    ) {
        if (!patientRepository.existsById(patientId)) {
            throw new PatientNotFoundException(patientId);
        }

        return consultationRepository
                .findByPatientIdOrderByConsultationDateTimeDesc(patientId)
                .stream()
                .map(consultationHistoryMapper::toHistoryItem)
                .toList();
    }
}