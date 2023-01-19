package mat.unical.it.bookly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class BooklyApplication {

    public static void main(String[] args) {

        SpringApplication.run(BooklyApplication.class, args);
    }

}
