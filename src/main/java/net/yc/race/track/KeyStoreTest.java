package net.yc.race.track;

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;



public class KeyStoreTest {
    @PostConstruct
    public void checkKeyStore() {
        try {
            ClassPathResource resource = new ClassPathResource("keystore.p12");
            if (resource.exists()) {
                System.out.println("Keystore found in classpath");
            } else {
                System.out.println("Keystore not found in classpath");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
