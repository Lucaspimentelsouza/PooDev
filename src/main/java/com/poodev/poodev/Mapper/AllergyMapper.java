package com.poodev.poodev.Mapper;

import com.poodev.poodev.DTO.Response.AllergyResponse;
import com.poodev.poodev.Entity.Allergy;
import org.springframework.stereotype.Component;

@Component
public class AllergyMapper {

    public AllergyResponse toResponse(Allergy allergy) {
        return new AllergyResponse(allergy.getId(), allergy.getAllergen());
    }
}