package com.poodev.poodev.Service;

import com.poodev.poodev.DTO.Request.ConsultationCreateRequest;
import com.poodev.poodev.DTO.Response.ConsultationResponse;
import com.poodev.poodev.Entity.Consultation;
import com.poodev.poodev.Entity.Doctor;
import com.poodev.poodev.Entity.Patient;
import com.poodev.poodev.Exception.ConsultationNotFoundException;
import com.poodev.poodev.Exception.DoctorNotFoundException;
import com.poodev.poodev.Exception.PatientNotFoundException;
import com.poodev.poodev.Mapper.ConsultationMapper;
import com.poodev.poodev.Repository.ConsultationRepository;
import com.poodev.poodev.Repository.DoctorRepository;
import com.poodev.poodev.Repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.poodev.poodev.DTO.Request.ConsultationUpdateRequest;

@Service
@RequiredArgsConstructor
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ConsultationMapper consultationMapper;

    @Transactional
    public ConsultationResponse create(ConsultationCreateRequest request) {
        Patient patient = patientRepository.findById(request.patientId())
                .orElseThrow(() ->
                        new PatientNotFoundException(request.patientId())
                );

        Doctor doctor = doctorRepository.findById(request.doctorId())
                .orElseThrow(() ->
                        new DoctorNotFoundException(request.doctorId())
                );

        Consultation consultation =
                consultationMapper.toEntity(request, patient, doctor);

        Consultation savedConsultation =
                consultationRepository.save(consultation);

        return consultationMapper.toResponse(savedConsultation);
    }

    @Transactional(readOnly = true)
    public ConsultationResponse findById(Long consultationId) {
        Consultation consultation =
                consultationRepository.findById(consultationId)
                        .orElseThrow(() ->
                                new ConsultationNotFoundException(consultationId)
                        );

        return consultationMapper.toResponse(consultation);
    }

    //5
    @Transactional(readOnly = true)
    public List<ConsultationResponse> findByPatientId(Long patientId) {
        patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new PatientNotFoundException(patientId)
                );

        return consultationRepository
                .findByPatientIdOrderByConsultationDateTimeDesc(patientId)
                .stream()
                .map(consultationMapper::toResponse)
                .toList();
    }

    @Transactional
    public ConsultationResponse update(
            Long consultationId,
            ConsultationUpdateRequest request
    ) {
        Consultation consultation = consultationRepository
                .findById(consultationId)
                .orElseThrow(() ->
                        new ConsultationNotFoundException(consultationId)
                );

        consultationMapper.updateEntity(request, consultation);

        Consultation savedConsultation =
                consultationRepository.save(consultation);

        return consultationMapper.toResponse(savedConsultation);
    }
}