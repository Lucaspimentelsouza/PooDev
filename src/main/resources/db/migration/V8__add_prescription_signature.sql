ALTER TABLE prescriptions
    ADD COLUMN signature BYTEA,
    ADD COLUMN signature_algorithm VARCHAR(50),
    ADD COLUMN signed_at TIMESTAMP,
    ADD COLUMN public_key TEXT;

ALTER TABLE prescriptions
    ADD CONSTRAINT ck_prescriptions_signature_algorithm
        CHECK (
            signature_algorithm IS NULL
                OR signature_algorithm = 'SHA256withRSA'
            );