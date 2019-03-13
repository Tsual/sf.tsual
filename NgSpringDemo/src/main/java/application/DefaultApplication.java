package application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.net.MalformedURLException;

@SpringBootApplication
public class DefaultApplication {
    public static void main(String[] args) throws MalformedURLException {
        new SpringApplicationBuilder(DefaultApplication.class)
                //.banner(new ResourceBanner(new UrlResource(ClassLoader.getSystemResource("banner.txt"))))
                //.bannerMode(Banner.Mode.CONSOLE)
                .run(args);
    }
}
