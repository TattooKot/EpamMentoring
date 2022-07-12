package org.example.core;

import org.example.core.controller.BookingFacade;
import org.example.core.controller.BookingFacadeImpl;
import org.example.core.model.User;
import org.example.core.service.EventService;
import org.example.core.service.TicketService;
import org.example.core.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class CoreApplicationTests {

	@Mock
	private BookingFacade bookingFacade;

	@Mock
	private UserService userService;

	@Mock
	private EventService eventService;

	@Mock
	private TicketService ticketService;


	@BeforeEach
	public void before(){
		userService = Mockito.mock(UserService.class);
		eventService = Mockito.mock(EventService.class);
		ticketService = Mockito.mock(TicketService.class);
		bookingFacade = new BookingFacadeImpl(eventService, userService, ticketService);
	}


	@Test
	void contextLoads() {
	}

	@Test
	void userCreatingTest(){
		User user = new User();
		when(userService.save(user)).thenReturn(user);
		User result = bookingFacade.createUser(user);
		assertEquals(user, result);
	}

}
