/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ua.oleksandrbuhaiov;

import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
    @Test
    public void applicationCanStart() {
        @Data
        class Hello {
            private String hello = "HELLO";
        }

        System.out.println(new Hello().getHello());
    }
}