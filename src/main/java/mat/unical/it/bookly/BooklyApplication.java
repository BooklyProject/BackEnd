package mat.unical.it.bookly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;


@ServletComponentScan
@SpringBootApplication
public class BooklyApplication {

    public static void main(String[] args) throws MessagingException, UnsupportedEncodingException {
        boolean ok = true;
        SpringApplication.run(BooklyApplication.class, args);

    }

}
