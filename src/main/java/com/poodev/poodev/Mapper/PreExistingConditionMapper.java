package com.poodev.poodev.Mapper;

import com.poodev.poodev.DTO.Request.PreExistingConditionCreateRequest;
import com.poodev.poodev.DTO.Response.PreExistingConditionResponse;
import com.poodev.poodev.Entity.Patient;
import com.poodev.poodev.Entity.PreExistingCondition;
import org.springframework.stereotype.Component;

@Component
public class PreExistingConditionMapper {

    public PreExistingCondition toEntity(
            PreExistingConditionCreateRequest request,
            Patient patient
    ) {
        PreExistingCondition condition = new PreExistingCondition();

        condition.setConditionName(request.conditionName());
        condition.setActive(request.active());
        condition.setPatient(patient);

        return condition;
    }

    public PreExistingConditionResponse toResponse(
            PreExistingCondition condition
    ) {
        return new PreExistingConditionResponse(
                condition.getId(),
                condition.getPatient().getId(),
                condition.getConditionName(),
                condition.isActive()
        );
    }
}