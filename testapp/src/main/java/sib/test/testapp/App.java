package sib.test.testapp;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class App {
	private static String str;
	private static String[] av;
	public static void main( String[] args ){
		
		str = args[0];
		if(args.length > 2) {
			System.out.print("./your_arr \"serch request\" \"[-v]\"");
			return ;
		}
		else {
			av = args;
			checkVisio();
	        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
	                App.class);
		}
	}
	
	@Scheduled(fixedDelay = 10000)
	private static void action() {
		
		SearchPage sp = new SearchPage();
		sp.pushRequest(str);
		ArrayList<String> arrTOBase = sp.getContent();

		DBAction asd = new DBAction(arrTOBase);
		asd.connection();
		asd.inserContent();
	}
	
	public static int checkVisio() {
		if (av.length == 2 && av[1].equals("-v"))
			return 1;
		else
			return 0;
	}
	
	
}
