package com.booking.facade;

import com.booking.model.Event;
import com.booking.model.Ticket;
import com.booking.model.User;
import com.booking.service.EventService;
import com.booking.service.TicketService;
import com.booking.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingFacadeImplTest {

    @Mock
    private UserService userService;

    @Mock
    private EventService eventService;

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private BookingFacadeImpl bookingFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEventById() {
        long eventId = 1L;
        Event mockEvent = new Event();
        when(eventService.getEventById(eventId)).thenReturn(mockEvent);

        Event result = bookingFacade.getEventById(eventId);

        assertNotNull(result);
        assertEquals(mockEvent, result);
        verify(eventService).getEventById(eventId);
    }

    @Test
    void testGetEventsByTitle() {
        String title = "Concert";
        int pageSize = 10;
        int pageNum = 1;
        List<Event> mockEvents = List.of(new Event(), new Event());
        when(eventService.getEventsByTitle(title, pageSize, pageNum)).thenReturn(mockEvents);

        List<Event> result = bookingFacade.getEventsByTitle(title, pageSize, pageNum);

        assertNotNull(result);
        assertEquals(mockEvents.size(), result.size());
        verify(eventService).getEventsByTitle(title, pageSize, pageNum);
    }

    @Test
    void testGetEventsForDay() {
        Date day = new Date();
        int pageSize = 10;
        int pageNum = 1;
        List<Event> mockEvents = List.of(new Event(), new Event());
        when(eventService.getEventsForDay(day, pageSize, pageNum)).thenReturn(mockEvents);

        List<Event> result = bookingFacade.getEventsForDay(day, pageSize, pageNum);

        assertNotNull(result);
        assertEquals(mockEvents.size(), result.size());
        verify(eventService).getEventsForDay(day, pageSize, pageNum);
    }

    @Test
    void testCreateEvent() {
        Event event = new Event();
        when(eventService.createEvent(event)).thenReturn(event);

        Event result = bookingFacade.createEvent(event);

        assertNotNull(result);
        assertEquals(event, result);
        verify(eventService).createEvent(event);
    }

    @Test
    void testUpdateEvent() {
        Event event = new Event();
        when(eventService.updateEvent(event)).thenReturn(event);

        Event result = bookingFacade.updateEvent(event);

        assertNotNull(result);
        assertEquals(event, result);
        verify(eventService).updateEvent(event);
    }

    @Test
    void testDeleteEvent() {
        long eventId = 1L;
        when(eventService.deleteEvent(eventId)).thenReturn(true);

        boolean result = bookingFacade.deleteEvent(eventId);

        assertTrue(result);
        verify(eventService).deleteEvent(eventId);
    }

    @Test
    void testGetUserById() {
        long userId = 1L;
        User mockUser = new User();
        when(userService.getUserById(userId)).thenReturn(mockUser);

        User result = bookingFacade.getUserById(userId);

        assertNotNull(result);
        assertEquals(mockUser, result);
        verify(userService).getUserById(userId);
    }

    @Test
    void testGetUserByEmail() {
        String email = "test@example.com";
        User mockUser = new User();
        when(userService.getUserByEmail(email)).thenReturn(mockUser);

        User result = bookingFacade.getUserByEmail(email);

        assertNotNull(result);
        assertEquals(mockUser, result);
        verify(userService).getUserByEmail(email);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        when(userService.createUser(user)).thenReturn(user);

        User result = bookingFacade.createUser(user);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userService).createUser(user);
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        when(userService.updateUser(user)).thenReturn(user);

        User result = bookingFacade.updateUser(user);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userService).updateUser(user);
    }

    @Test
    void testDeleteUser() {
        long userId = 1L;
        when(userService.deleteUser(userId)).thenReturn(true);

        boolean result = bookingFacade.deleteUser(userId);

        assertTrue(result);
        verify(userService).deleteUser(userId);
    }

    @Test
    void testBookTicket() {
        long userId = 1L;
        long eventId = 100L;
        int place = 5;
        int category = 1;
        Ticket mockTicket = new Ticket();
        when(ticketService.bookTicket(userId, eventId, place, category)).thenReturn(mockTicket);

        Ticket result = bookingFacade.bookTicket(userId, eventId, place, category);

        assertNotNull(result);
        assertEquals(mockTicket, result);
        verify(ticketService).bookTicket(userId, eventId, place, category);
    }

    @Test
    void testGetBookedTicketsByUser() {
        User user = new User();
        int pageSize = 10;
        int pageNum = 1;
        List<Ticket> mockTickets = List.of(new Ticket(), new Ticket());
        when(ticketService.getBookedTickets(user, pageSize, pageNum)).thenReturn(mockTickets);

        List<Ticket> result = bookingFacade.getBookedTickets(user, pageSize, pageNum);

        assertNotNull(result);
        assertEquals(mockTickets.size(), result.size());
        verify(ticketService).getBookedTickets(user, pageSize, pageNum);
    }

    @Test
    void testGetBookedTicketsByEvent() {
        Event event = new Event();
        int pageSize = 10;
        int pageNum = 1;
        List<Ticket> mockTickets = List.of(new Ticket(), new Ticket());
        when(ticketService.getBookedTickets(event, pageSize, pageNum)).thenReturn(mockTickets);

        List<Ticket> result = bookingFacade.getBookedTickets(event, pageSize, pageNum);

        assertNotNull(result);
        assertEquals(mockTickets.size(), result.size());
        verify(ticketService).getBookedTickets(event, pageSize, pageNum);
    }
}


