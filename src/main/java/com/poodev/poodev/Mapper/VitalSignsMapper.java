package com.poodev.poodev.Mapper;

import com.poodev.poodev.DTO.Request.VitalSignsCreateRequest;
import com.poodev.poodev.DTO.Response.VitalSignsResponse;
import com.poodev.poodev.Entity.Consultation;
import com.poodev.poodev.Entity.VitalSigns;
import org.springframework.stereotype.Component;

@Component
public class VitalSignsMapper {

    public VitalSigns toEntity(
            VitalSignsCreateRequest request,
            Consultation consultation
    ) {
        VitalSigns vitalSigns = new VitalSigns();

        vitalSigns.setBloodPressure(request.bloodPressure());
        vitalSigns.setWeight(request.weight());
        vitalSigns.setBodyTemperature(request.bodyTemperature());
        vitalSigns.setConsultation(consultation);

        return vitalSigns;
    }

    public VitalSignsResponse toResponse(VitalSigns vitalSigns) {
        return new VitalSignsResponse(
                vitalSigns.getId(),
                vitalSigns.getConsultation().getId(),
                vitalSigns.getBloodPressure(),
                vitalSigns.getWeight(),
                vitalSigns.getBodyTemperature()
        );
    }
}