package org.example.core;

import org.example.core.controller.BookingFacade;
import org.example.core.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CoreApplication {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		BookingFacade bookingFacade = context.getBean(BookingFacade.class);

		User user = new User();
		user.setName("Test");
		user.setPhone(123456789);
		user.setEmail("Email");

		user = bookingFacade.createUser(user);
		System.out.println(user);
	}

}
