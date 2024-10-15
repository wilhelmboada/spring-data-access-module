package com.booking.dao;

import com.booking.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketDao extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUserId(long userId);

    List<Ticket> findByEventId(long eventId);

}
