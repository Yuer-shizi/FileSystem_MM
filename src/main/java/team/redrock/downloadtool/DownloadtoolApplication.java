package team.redrock.downloadtool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@ServletComponentScan
@EnableJpaAuditing
public class DownloadtoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(DownloadtoolApplication.class, args);
    }
}
