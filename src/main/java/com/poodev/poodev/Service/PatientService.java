package com.poodev.poodev.Service;

import com.poodev.poodev.Entity.Patient;
import com.poodev.poodev.Repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PatientService implements PatientServiceInterface{
    private final PatientRepository patientRepository;

    public Patient createPatient(Patient patient){
        return patientRepository.save(patient);
    }
}
