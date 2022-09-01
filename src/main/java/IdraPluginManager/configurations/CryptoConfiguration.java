package IdraPluginManager.configurations;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.bol.crypt.CryptVault;
import com.bol.secure.CachedEncryptionEventListener;

@Configuration
public class CryptoConfiguration{

	@Value("${mongodb.encrypt.key1}")
	private String key1;
	@Value("${mongodb.encrypt.key2}")
	private String key2;
	
    @Bean
    public CryptVault cryptVault() {
        return new CryptVault()
                .with256BitAesCbcPkcs5PaddingAnd128BitSaltKey(0, Base64.getDecoder().decode(key1))
                .with256BitAesCbcPkcs5PaddingAnd128BitSaltKey(1, Base64.getDecoder().decode(key2))
                .withDefaultKeyVersion(1);
    }

    @Bean
    public CachedEncryptionEventListener encryptionEventListener(CryptVault cryptVault) {
        return new CachedEncryptionEventListener(cryptVault);
    }
    
//    @Bean
//    public ReflectionEncryptionEventListener encryptionEventListener(CryptVault cryptVault) {
//        return new ReflectionEncryptionEventListener(cryptVault).withSilentDecryptionFailure(true);
//    }
}
