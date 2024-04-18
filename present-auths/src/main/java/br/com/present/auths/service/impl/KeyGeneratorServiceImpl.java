package br.com.present.auths.service.impl;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.auth0.jwt.algorithms.Algorithm;

import br.com.present.auths.config.exception.ApiKeyGenerateException;
import br.com.present.auths.service.IKeyGeneratorService;

@Service
public class KeyGeneratorServiceImpl implements IKeyGeneratorService {

	private int keySize = -1;
	private boolean useRSA; 
	
	private Algorithm algorithm;
	
	private RSAPublicKey rsaPublicKey;
	private RSAPrivateKey rsaPrivateKey;
    private String symmetricKey = "";

    public KeyGeneratorServiceImpl(int keySize, boolean useRSA) {
		this.keySize = keySize;
		this.useRSA = useRSA;
	}

	@Override
	public Algorithm getAlgorithm() {
        if(algorithm == null) {
            if(useRSA) {
            	algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
            }else {
            	algorithm = Algorithm.HMAC512(symmetricKey);
            }
        }
        return algorithm;
    }
    
    @Override
	public void generateKey() throws ApiKeyGenerateException {
        validateKeySize();

        if(useRSA) {
            genarateRSAKeys();
        } else {
            symmetricKey = UUID.randomUUID().toString();
        }
    }

	private void validateKeySize() {
		if(keySize == -1 && useRSA) {
            throw new ApiKeyGenerateException("");
        }
        if(keySize < 512 && useRSA) {
            throw new ApiKeyGenerateException("");
        }
	}

    private void genarateRSAKeys() {
    	KeyPairGenerator keyPairGenerator;
    	KeyFactory keyFactory;
    	
        try {
        	keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(this.keySize);
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
        	throw new ApiKeyGenerateException("");
        }
        
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        X509EncodedKeySpec encodedPublicKey = new X509EncodedKeySpec(keyPair.getPublic().getEncoded());
        PKCS8EncodedKeySpec encodedPrivateKey = new PKCS8EncodedKeySpec(keyPair.getPrivate().getEncoded());
        
        try {
			rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(encodedPublicKey);
			rsaPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(encodedPrivateKey);
		} catch (InvalidKeySpecException e) {
			throw new ApiKeyGenerateException("");
		}
    }
    
}