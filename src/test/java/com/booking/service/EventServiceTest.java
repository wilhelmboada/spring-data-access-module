package com.booking.service;

import com.booking.dao.EventDao;
import com.booking.model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventServiceTest {

    @Mock
    private EventDao eventDao;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEventById() {
        long eventId = 1L;
        Event mockEvent = new Event();
        when(eventDao.getById(eventId)).thenReturn(mockEvent);

        Event result = eventService.getEventById(eventId);

        assertNotNull(result);
        assertEquals(mockEvent, result);
        verify(eventDao).getById(eventId);
    }

    @Test
    void testGetEventsByTitle() {
        String title = "Concert";
        int pageSize = 10;
        int pageNum = 1;
        List<Event> mockEvents = List.of(new Event(), new Event());
        when(eventDao.findByTitle(title)).thenReturn(mockEvents);

        List<Event> result = eventService.getEventsByTitle(title, pageSize, pageNum);

        assertNotNull(result);
        assertEquals(mockEvents.size(), result.size());
        verify(eventDao).findByTitle(title);
    }

    @Test
    void testGetEventsForDay() {
        Date day = new Date();
        int pageSize = 10;
        int pageNum = 1;
        List<Event> mockEvents = List.of(new Event(), new Event());
        when(eventDao.findByDate(day)).thenReturn(mockEvents);

        List<Event> result = eventService.getEventsForDay(day, pageSize, pageNum);

        assertNotNull(result);
        assertEquals(mockEvents.size(), result.size());
        verify(eventDao).findByDate(day);
    }

    @Test
    void testCreateEvent() {
        Event event = new Event();
        when(eventDao.save(event)).thenReturn(event);

        Event result = eventService.createEvent(event);

        assertNotNull(result);
        assertEquals(event, result);
        verify(eventDao).save(event);
    }

    @Test
    void testUpdateEvent() {
        Event event = new Event();
        when(eventDao.save(event)).thenReturn(event);

        Event result = eventService.updateEvent(event);

        assertNotNull(result);
        assertEquals(event, result);
        verify(eventDao).save(event);
    }

    @Test
    void testDeleteEvent() {
        long eventId = 1L;

        boolean result = eventService.deleteEvent(eventId);

        assertTrue(result);
        verify(eventDao).deleteById(eventId);
    }
}

