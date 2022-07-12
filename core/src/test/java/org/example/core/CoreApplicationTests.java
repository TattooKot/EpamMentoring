package org.example.core;

import org.example.core.controller.BookingFacade;
import org.example.core.controller.BookingFacadeImpl;
import org.example.core.model.Event;
import org.example.core.model.Ticket;
import org.example.core.model.TicketInterface;
import org.example.core.model.User;
import org.example.core.service.EventService;
import org.example.core.service.TicketService;
import org.example.core.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
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
    public void before() {
        userService = Mockito.mock(UserService.class);
        eventService = Mockito.mock(EventService.class);
        ticketService = Mockito.mock(TicketService.class);
        bookingFacade = new BookingFacadeImpl(eventService, userService, ticketService);
    }


    @Test
    void contextLoads() {
    }

    @Test
    void userCreatingTest() {
        User user = new User();
		user.setName("Test");
		user.setPhone(123456789);
		user.setEmail("Email");

        when(userService.save(user)).thenReturn(user);
        User result = bookingFacade.createUser(user);
        assertEquals(user, result);
    }

	@Test
	void impossibleCreatingUserWithSameEmailsTest(){
		User user = new User();
		user.setName("Test");
		user.setPhone(123456789);
		user.setEmail("Email");
		User mockNotSavedUser = new User();

		when(userService.save(user)).thenReturn(user, mockNotSavedUser);
		User savedUser = bookingFacade.createUser(user);
		User notSavedUser = bookingFacade.createUser(user);
		assertNotEquals(savedUser, notSavedUser);
	}

    @Test
    void eventCreatingTest() {
		Event event = new Event();
		event.setDate(new Date());
		event.setTitle("Test");

		when(eventService.save(event)).thenReturn(event);
		Event result = bookingFacade.createEvent(event);
		assertEquals(event, result);
    }

	@Test
	void bookingTicketTest(){
		User user = new User();
		user.setId(1L);
		user.setName("Test");
		user.setPhone(123456789);
		user.setEmail("Email");

		Event event = new Event();
		event.setId(1L);
		event.setDate(new Date());
		event.setTitle("Test");

		Ticket ticket = new Ticket();
		ticket.setEventId(event.getId());
		ticket.setUserId(user.getId());
		ticket.setCategory(TicketInterface.Category.PREMIUM);
		ticket.setPlace(12);

		when(ticketService.save(ticket)).thenReturn(ticket);
		Ticket result = bookingFacade.bookTicket(event.getId(), user.getId(), 12, TicketInterface.Category.PREMIUM);
		assertEquals(ticket, result);
	}

	@Test
	void cancelingTicket(){
		long id = 1L;
		when(ticketService.cancelTicket(id)).thenReturn(true);
		boolean result = bookingFacade.cancelTicket(id);
		assertTrue(result);
	}

}
