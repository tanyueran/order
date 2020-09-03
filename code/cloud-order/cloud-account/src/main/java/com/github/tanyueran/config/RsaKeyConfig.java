package com.github.tanyueran.config;

import com.github.tanyueran.utils.RsaUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

@Configuration
@RefreshScope
public class RsaKeyConfig {

    @Bean
    public PublicKey publicKey(@Value("${key.public}") String publicKeyString) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return RsaUtil.getPublicKey(publicKeyString);
    }

    @Bean
    public PrivateKey privateKey(@Value("${key.private}") String privateKeyString) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return RsaUtil.getPrivateKey(privateKeyString);
    }
}
