package com.poodev.poodev.Mapper;

import com.poodev.poodev.DTO.Request.AllergyCreateRequest;
import com.poodev.poodev.DTO.Response.AllergyResponse;
import com.poodev.poodev.Entity.Allergy;
import com.poodev.poodev.Entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class AllergyMapper {

    public Allergy toEntity(
            AllergyCreateRequest request,
            Patient patient
    ) {
        Allergy allergy = new Allergy();

        allergy.setAllergen(request.allergen());
        allergy.setActive(request.active());
        allergy.setPatient(patient);

        return allergy;
    }

    public AllergyResponse toResponse(Allergy allergy) {
        return new AllergyResponse(
                allergy.getId(),
                allergy.getPatient().getId(),
                allergy.getAllergen(),
                allergy.isActive()
        );
    }
}