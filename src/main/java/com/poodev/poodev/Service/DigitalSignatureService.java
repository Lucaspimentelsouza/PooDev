package com.poodev.poodev.Service;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Service
public class DigitalSignatureService {

    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    private static final String KEY_ALGORITHM = "RSA";
    private static final int KEY_SIZE = 2048;

    private final KeyPair doctorKeyPair;

    public DigitalSignatureService() {
        try {
            KeyPairGenerator generator =
                    KeyPairGenerator.getInstance(KEY_ALGORITHM);

            generator.initialize(KEY_SIZE, new SecureRandom());

            this.doctorKeyPair = generator.generateKeyPair();

        } catch (Exception exception) {
            throw new IllegalStateException(
                    "Não foi possível inicializar o serviço de assinatura digital.",
                    exception
            );
        }
    }

    public SignedContent sign(String content) {
        try {
            byte[] contentBytes =
                    content.getBytes(StandardCharsets.UTF_8);

            Signature signature =
                    Signature.getInstance(SIGNATURE_ALGORITHM);

            signature.initSign(doctorKeyPair.getPrivate());
            signature.update(contentBytes);

            byte[] signatureBytes = signature.sign();

            String publicKey = Base64.getEncoder()
                    .encodeToString(
                            doctorKeyPair.getPublic().getEncoded()
                    );

            return new SignedContent(
                    signatureBytes,
                    SIGNATURE_ALGORITHM,
                    publicKey
            );

        } catch (Exception exception) {
            throw new IllegalStateException(
                    "Não foi possível assinar o conteúdo.",
                    exception
            );
        }
    }

    public boolean verify(
            String content,
            byte[] signature,
            String publicKeyBase64
    ) {
        try {
            byte[] publicKeyBytes = Base64.getDecoder()
                    .decode(publicKeyBase64);

            KeyFactory keyFactory =
                    KeyFactory.getInstance(KEY_ALGORITHM);

            PublicKey publicKey = keyFactory.generatePublic(
                    new X509EncodedKeySpec(publicKeyBytes)
            );

            Signature verifier =
                    Signature.getInstance(SIGNATURE_ALGORITHM);

            verifier.initVerify(publicKey);
            verifier.update(
                    content.getBytes(StandardCharsets.UTF_8)
            );

            return verifier.verify(signature);

        } catch (Exception exception) {
            return false;
        }
    }

    public record SignedContent(
            byte[] signature,
            String algorithm,
            String publicKey
    ) {
    }
}