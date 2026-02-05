package com.uisrael.hikvision.backend.infraestructura.configuracion.seguridad;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class CryptoService {

    private static final String ALGO = "AES";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH_BITS = 128;
    private static final int IV_LENGTH_BYTES = 12;

    private final byte[] keyBytes;
    private final SecureRandom random = new SecureRandom();

    public CryptoService(@Value("${security.crypto.master-key}") String masterKey) {
        if (masterKey == null || masterKey.isBlank()) {
            throw new IllegalArgumentException("security.crypto.master-key no está definido o está vacío");
        }
        byte[] bytes = masterKey.getBytes(StandardCharsets.UTF_8);
        if (bytes.length < 32) {
            throw new IllegalArgumentException("security.crypto.master-key debe tener al menos 32 bytes");
        }
        // tomamos 32 bytes para AES-256
        this.keyBytes = new byte[32];
        System.arraycopy(bytes, 0, this.keyBytes, 0, 32);
    }

    public EncryptedValue encrypt(String plain) {
        try {
            byte[] iv = new byte[IV_LENGTH_BYTES];
            random.nextBytes(iv);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKey key = new SecretKeySpec(keyBytes, ALGO);
            cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(GCM_TAG_LENGTH_BITS, iv));

            byte[] ciphertext = cipher.doFinal(plain.getBytes(StandardCharsets.UTF_8));

            return new EncryptedValue(
                    Base64.getEncoder().encodeToString(ciphertext),
                    Base64.getEncoder().encodeToString(iv)
            );
        } catch (Exception e) {
            throw new RuntimeException("Error cifrando", e);
        }
    }

    public String decrypt(String cipherB64, String ivB64) {
        try {
            byte[] ciphertext = Base64.getDecoder().decode(cipherB64);
            byte[] iv = Base64.getDecoder().decode(ivB64);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKey key = new SecretKeySpec(keyBytes, ALGO);
            cipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(GCM_TAG_LENGTH_BITS, iv));

            byte[] plain = cipher.doFinal(ciphertext);
            return new String(plain, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Error descifrando", e);
        }
    }

    public record EncryptedValue(String cipherText, String iv) {}
}

