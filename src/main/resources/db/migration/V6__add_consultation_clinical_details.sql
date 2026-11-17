ALTER TABLE consultations
    ADD COLUMN history_of_present_illness TEXT,
    ADD COLUMN physical_examination TEXT,
    ADD COLUMN recommended_return_days INTEGER;

ALTER TABLE consultations
    ADD CONSTRAINT ck_consultations_recommended_return_days
        CHECK (recommended_return_days > 0);