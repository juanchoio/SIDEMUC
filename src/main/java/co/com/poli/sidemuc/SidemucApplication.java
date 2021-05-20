package co.com.poli.sidemuc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SidemucApplication implements CommandLineRunner {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SidemucApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String password = "12345";

        for (int i = 0; i < 4; i++){
            String passwordBcrypt = bCryptPasswordEncoder.encode(password);
            System.out.println(passwordBcrypt);
        }
    }
}
