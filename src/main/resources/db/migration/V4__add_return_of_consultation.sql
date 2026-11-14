ALTER TABLE consultations
    ADD COLUMN return_of_consultation_id BIGINT;

ALTER TABLE consultations
    ADD CONSTRAINT fk_consultations_return_of
        FOREIGN KEY (return_of_consultation_id) REFERENCES consultations (id);

CREATE INDEX idx_consultations_return_of
    ON consultations (return_of_consultation_id);