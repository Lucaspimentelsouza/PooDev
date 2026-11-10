package com.poodev.poodev.Repository;

import com.poodev.poodev.Entity.PreExistingCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreExistingConditionRepository
        extends JpaRepository<PreExistingCondition, Long> {

    List<PreExistingCondition> findByPatientIdAndActiveTrue(Long patientId);

    boolean existsByPatientIdAndConditionNameIgnoreCase(
            Long patientId,
            String conditionName
    );
}