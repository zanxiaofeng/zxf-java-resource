package zxf.springboot.resource.rest;

import org.springframework.core.io.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    @GetMapping("/file")
    public FileSystemResource file(@RequestParam String path) {
        return new FileSystemResource(path);
    }

    @GetMapping("/class")
    public ClassPathResource klass(@RequestParam String path) {
        return new ClassPathResource(path);
    }

    @GetMapping("/steam")
    public InputStreamResource stream() {
        return null;
    }

    @GetMapping("/bytes")
    public ByteArrayResource bytes() {
        return null;
    }

    @GetMapping("/url")
    public UrlResource url() {
        return null;
    }
}
