package zxf.springboot.resource.rest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

@RestController
public class HomeController {

    @GetMapping("/")
    public void home() throws IOException {
        System.out.println("#######################################################################################################");
        Collections.list(Thread.currentThread().getContextClassLoader().getResources("about.txt")).forEach(url -> {
            try {
                System.out.println(url + ":");
                if ("file".equalsIgnoreCase(url.getProtocol())) {
                    byte[] context = Files.readAllBytes(Paths.get(url.toURI()));
                    System.out.println(new String(context));
                } else {
                    assert "jar".equalsIgnoreCase(url.getProtocol());

                    if (false) {
                        //approach 1
                        JarURLConnection urlConnection = (JarURLConnection) url.openConnection();

                        System.out.println(StreamUtils.copyToString(urlConnection.getInputStream(), StandardCharsets.UTF_8));
                    }
                    System.out.println(StreamUtils.copyToString(url.openStream(), StandardCharsets.UTF_8));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        System.out.println("#######################################################################################################");
        Resource about = new ClassPathResource("about.txt");
        System.out.println("*********" + about.getURL());
        try (InputStream stream = about.getInputStream()) {
            System.out.println("*********" + StreamUtils.copyToString(stream, StandardCharsets.UTF_8));
        }
        System.out.println("#######################################################################################################");
        Resource aboutA = new ClassPathResource("about-a.txt");
        System.out.println("@@@@@@@@" + aboutA.getURL());
        try (InputStream stream = aboutA.getInputStream()) {
            System.out.println("@@@@@@@@" + StreamUtils.copyToString(stream, StandardCharsets.UTF_8));
        }
    }
}
