package com.booking.service;

import com.booking.dao.TicketDao;
import com.booking.model.Event;
import com.booking.model.Ticket;
import com.booking.model.User;
import com.booking.model.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private TicketDao ticketDao;
    @Autowired
    private EventService eventService;
    @Autowired
    private UserAccountService userAccountService;

    @Transactional
    public Ticket bookTicket(long userId, long eventId, int place, int category) {
        logger.info("booking ticket userId: {}, eventId: {}", userId, eventId);

        Event event = eventService.getEventById(eventId);
        UserAccount userAccount = userAccountService.getUserAccountByUserId(userId);

        if(event.getTicketPrice() <= userAccount.getPrepaidAmount()) {
            userAccountService.withdrawFromUserAccount(userAccount.getId(), event.getTicketPrice());
            return ticketDao.save(new Ticket(new Date().getTime(), eventId, userId, category, place));
        } else {
            throw new RuntimeException("Ticket could not be booked");
        }
    }

    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        logger.info("get booked tickets userId: {}", user.getId());
        return ticketDao.findByUserId(user.getId());
    }

    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        logger.info("get booked tickets eventId: {}", event.getId());
        return ticketDao.findByEventId(event.getId());
    }

    public boolean cancelTicket(long id) {
        logger.info("cancel ticket id: {}", id);
        ticketDao.deleteById(id);
        return true;
    }
}
