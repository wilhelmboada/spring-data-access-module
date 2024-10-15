package com.booking.dao;

import com.booking.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventDao extends JpaRepository<Event, Long> {

    public List<Event> findByTitle(String title);

    public List<Event> findByDate(Date title);

}
