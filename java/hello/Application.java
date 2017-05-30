package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
	try
	{
        	SpringApplication.run(Application.class, args);
	}
	catch(Exception e)
	{
		System.err.println(e);
		System.exit(1);
	}
    }
}
