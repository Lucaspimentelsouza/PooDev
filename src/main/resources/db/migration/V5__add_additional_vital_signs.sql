ALTER TABLE vital_signs
    ADD COLUMN heart_rate INTEGER,
    ADD COLUMN oxygen_saturation NUMERIC(5, 2),
    ADD COLUMN blood_glucose NUMERIC(7, 2);

ALTER TABLE vital_signs
    ADD CONSTRAINT ck_vital_signs_heart_rate
        CHECK (heart_rate > 0),
    ADD CONSTRAINT ck_vital_signs_oxygen_saturation
        CHECK (oxygen_saturation BETWEEN 0 AND 100),
    ADD CONSTRAINT ck_vital_signs_blood_glucose
        CHECK (blood_glucose >= 0);