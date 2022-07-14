package org.example.core;

import org.example.core.controller.BookingFacade;
import org.example.core.controller.BookingFacadeImpl;
import org.example.core.model.Event;
import org.example.core.model.Ticket;
import org.example.core.repository.EventRepo;
import org.example.core.repository.TicketRepo;
import org.example.core.repository.UserRepo;
import org.example.core.service.EventService;
import org.example.core.service.TicketService;
import org.example.core.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public DB db(){
        return new DB();
    }

    @Bean
    public UserRepo userRepo() {
        return new UserRepo(db());
    }

    @Bean
    public UserService userService(){
        return new UserService();
    }

    @Bean
    public EventRepo eventRepo(){
        return new EventRepo(db());
    }

    @Bean
    public EventService eventService(){
        return new EventService();
    }

    @Bean
    public TicketRepo ticketRepo(){
        return new TicketRepo(db());
    }

    @Bean TicketService ticketService(){
        return new TicketService();
    }

    @Bean
    public BookingFacade bookingFacade(){
        return new BookingFacadeImpl(eventService(), userService(), ticketService());
    }
}
