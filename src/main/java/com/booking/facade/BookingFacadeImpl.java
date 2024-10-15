package com.booking.facade;

import com.booking.model.Event;
import com.booking.model.Ticket;
import com.booking.model.User;
import com.booking.model.UserAccount;
import com.booking.service.EventService;
import com.booking.service.TicketService;
import com.booking.service.UserAccountService;
import com.booking.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BookingFacadeImpl implements BookingFacade {

    private static final Logger logger = LoggerFactory.getLogger(BookingFacadeImpl.class);

    private final UserService userService;
    private final EventService eventService;
    private final TicketService ticketService;
    private final UserAccountService userAccountService;

    public BookingFacadeImpl(UserService userService, EventService eventService, TicketService ticketService, UserAccountService userAccountService) {
        this.userService = userService;
        this.eventService = eventService;
        this.ticketService = ticketService;
        this.userAccountService = userAccountService;
    }

    @Override
    public Event getEventById(long id) {
        logger.info("getEventById: {}", id);
        return eventService.getEventById(id);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        logger.info("get events by title: {}", title);
        return eventService.getEventsByTitle(title, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        logger.info("get events for day: {}", day);
        return eventService.getEventsForDay(day, pageSize, pageNum);
    }

    @Override
    public Event createEvent(Event event) {
        logger.info("create event: {}", event.getId());
        return eventService.createEvent(event);
    }

    @Override
    public Event updateEvent(Event event) {
        logger.info("update event: {}", event.getId());
        return eventService.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        logger.info("delete event: {}", eventId);
        return eventService.deleteEvent(eventId);
    }

    @Override
    public User getUserById(long id) {
        logger.info("get user by id: {}", id);
        return userService.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        logger.info("get user by email: {}", email);
        return userService.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        logger.info("get user by name: {}", name);
        return userService.getUsersByName(name, pageSize, pageNum);
    }

    @Override
    public User createUser(User user) {
        logger.info("create user: {}", user.getId());
        return userService.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        logger.info("update user: {}", user.getId());
        return userService.updateUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        logger.info("delete user: {}", userId);
        return userService.deleteUser(userId);
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, int category) {
        logger.info("book ticket userId: {}, eventId: {}", userId, eventId);
        return ticketService.bookTicket(userId, eventId, place, category);
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        logger.info("get booked tickets: {}", user.getId());
        return ticketService.getBookedTickets(user, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        logger.info("get booked tickets: {}", event.getId());
        return ticketService.getBookedTickets(event, pageSize, pageNum);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        logger.info("cancel ticket: {}", ticketId);
        return ticketService.cancelTicket(ticketId);
    }

    @Override
    public UserAccount createUserAccount(UserAccount userAccount) {
        return userAccountService.createUserAccount(userAccount);
    }

    @Override
    public UserAccount addMoneyToUserAccount(long userAccountId, double money) {
        return userAccountService.addMoneyToUserAccount(userAccountId, money);
    }
}
