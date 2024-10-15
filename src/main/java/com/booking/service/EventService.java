package com.booking.service;

import com.booking.dao.EventDao;
import com.booking.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventDao eventDao;

    public Event getEventById(long id) {
        logger.info("get event by id: {}", id);
        return eventDao.getById(id);
    }

    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        logger.info("get events by title: {}", title);
        return eventDao.findByTitle(title);
    }

    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        logger.info("get events for day: {}", day);
        return eventDao.findByDate(day);
    }

    public Event createEvent(Event event) {
        logger.info("create event: {}", event.getId());
        return eventDao.save(event);
    }

    public Event updateEvent(Event event) {
        logger.info("update event: {}", event.getId());
        return eventDao.save(event);
    }

    public boolean deleteEvent(long eventId) {
        logger.info("delete event: {}", eventId);
        eventDao.deleteById(eventId);
        return true;
    }

}
