package zxf.java.resource.a;

import org.junit.jupiter.api.Test;
import sun.misc.IOUtils;

import java.io.IOException;
import java.net.JarURLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

public class Tests {
    @Test
    public void testResourceUse() throws IOException {
        System.out.println("*****Resource-A.getResource");
        System.out.println(ResourceA.class.getResource("about.txt"));
        System.out.println(ResourceA.class.getResource("/about.txt"));
        System.out.println("*****Resource-A.getClassLoader().getResource");
        System.out.println(ResourceA.class.getClassLoader().getResource("about.txt"));
        System.out.println("*****Resource-A.getClassLoader().getResources*");
        Collections.list(ResourceA.class.getClassLoader().getResources("about.txt")).forEach(System.out::println);

        System.out.println("#####ClassLoader.getSystemResource");
        System.out.println(ClassLoader.getSystemResource("about.txt"));
        System.out.println("#####ClassLoader.getSystemResources*");
        Collections.list(ClassLoader.getSystemResources("about.txt")).forEach(System.out::println);


        System.out.println("@@@@@ClassLoader.getSystemClassLoader().getResource");
        System.out.println(ClassLoader.getSystemClassLoader().getResource("about.txt"));
        System.out.println("@@@@@ClassLoader.getSystemClassLoader().getResources*");
        Collections.list(ClassLoader.getSystemClassLoader().getResources("about.txt")).forEach(System.out::println);

        System.out.println("$$$$$Thread.currentThread().getContextClassLoader().getResource");
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("about.txt"));
        System.out.println("$$$$$Thread.currentThread().getContextClassLoader().getResources*");
        Collections.list(Thread.currentThread().getContextClassLoader().getResources("about.txt")).forEach(System.out::println);


        System.out.println("#######################################################################################################");
        byte[] contextInPack = IOUtils.readAllBytes(ResourceA.class.getResource("about.txt").openStream());
        System.out.println(new String(contextInPack));

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
                        byte[] context = IOUtils.readAllBytes(urlConnection.getInputStream());
                        System.out.println(new String(context));
                    }
                    byte[] context = IOUtils.readAllBytes(url.openStream());
                    System.out.println(new String(context));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        System.out.println("#######################################################################################################");
    }
}
