package com.poodev.poodev.Repository;

import com.poodev.poodev.Entity.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SurgeryRepository extends JpaRepository<Surgery, Long> {

    List<Surgery> findByPatientIdOrderBySurgeryDateDesc(Long patientId);

    boolean existsByPatientIdAndProcedureNameIgnoreCaseAndSurgeryDate(
            Long patientId,
            String procedureName,
            LocalDate surgeryDate
    );
}