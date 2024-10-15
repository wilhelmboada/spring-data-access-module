package com.booking.service;

import com.booking.dao.TicketDao;
import com.booking.model.Event;
import com.booking.model.Ticket;
import com.booking.model.User;
import com.booking.model.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketServiceTest {

    @Mock
    private TicketDao ticketDao;

    @Mock
    private EventService eventService;

    @Mock
    private UserAccountService userAccountService;

    @InjectMocks
    private TicketService ticketService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBookTicket_Success() {
        // Arrange
        long userId = 1L;
        long eventId = 2L;
        int place = 5;
        int category = 1;
        double ticketPrice = 50.0;
        double prepaidAmount = 100.0;

        Event event = new Event();
        event.setId(eventId);
        event.setTitle("Concert");
        event.setDate(new Date());
        event.setTicketPrice(ticketPrice);

        UserAccount userAccount = new UserAccount();
        userAccount.setPrepaidAmount(prepaidAmount);

        when(eventService.getEventById(eventId)).thenReturn(event);
        when(userAccountService.getUserAccountByUserId(userId)).thenReturn(userAccount);
        when(ticketDao.save(any(Ticket.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Ticket bookedTicket = ticketService.bookTicket(userId, eventId, place, category);

        // Assert
        assertNotNull(bookedTicket);
        assertEquals(eventId, bookedTicket.getEventId());
        assertEquals(userId, bookedTicket.getUserId());
        assertEquals(category, bookedTicket.getCategory());
        assertEquals(place, bookedTicket.getPlace());

        verify(userAccountService, times(1)).withdrawFromUserAccount(userAccount.getId(), ticketPrice);
        verify(ticketDao, times(1)).save(any(Ticket.class));
    }

    @Test
    void testBookTicket_InsufficientFunds() {
        // Arrange
        long userId = 1L;
        long eventId = 2L;
        int place = 5;
        int category = 1;
        double ticketPrice = 100.0;
        double prepaidAmount = 50.0;

        Event event = new Event();
        event.setId(eventId);
        event.setTitle("Concert");
        event.setDate(new Date());
        event.setTicketPrice(ticketPrice);

        UserAccount userAccount = new UserAccount();
        userAccount.setPrepaidAmount(prepaidAmount);

        when(eventService.getEventById(eventId)).thenReturn(event);
        when(userAccountService.getUserAccountByUserId(userId)).thenReturn(userAccount);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            ticketService.bookTicket(userId, eventId, place, category);
        });

        assertEquals("Ticket could not be booked", exception.getMessage());

        verify(userAccountService, never()).withdrawFromUserAccount(anyLong(), anyDouble());
        verify(ticketDao, never()).save(any(Ticket.class));
    }

    @Test
    void testGetBookedTicketsByUser() {
        User user = new User();
        int pageSize = 10;
        int pageNum = 1;
        List<Ticket> mockTickets = List.of(new Ticket(), new Ticket());

        when(ticketDao.findByUserId(user.getId())).thenReturn(mockTickets);

        List<Ticket> result = ticketService.getBookedTickets(user, pageSize, pageNum);

        assertNotNull(result);
        assertEquals(mockTickets.size(), result.size());
        verify(ticketDao).findByUserId(user.getId());
    }

    @Test
    void testGetBookedTicketsByEvent() {
        Event event = new Event();
        int pageSize = 10;
        int pageNum = 1;
        List<Ticket> mockTickets = List.of(new Ticket(), new Ticket());

        when(ticketDao.findByEventId(event.getId())).thenReturn(mockTickets);

        List<Ticket> result = ticketService.getBookedTickets(event, pageSize, pageNum);

        assertNotNull(result);
        assertEquals(mockTickets.size(), result.size());
        verify(ticketDao).findByEventId(event.getId());
    }
}

