package com.riwi.workshop2.Services;


import com.riwi.workshop2.Entities.EventEntity;
import com.riwi.workshop2.Repositories.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class EventService {

    @Autowired
    private IUserRepository iUserRepository;

    public EventEntity createEvent(EventEntity event){
        if (event.getDate().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("The event date cannot be in the past.");
        }

        if (event.getCapacity() < 0 ){
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        return iUserRepository.save(event);
    }

    public List<EventEntity> getAllEvents (){
        return iUserRepository.findAll();
    }

    public Optional<EventEntity> getEventById(String id){
        return iUserRepository.findById(id);
    }

    @Transactional
    public EventEntity modifyEvent (String id, EventEntity event){
        return iUserRepository
                .findById(id)
                .map( existingEvent -> {
                    existingEvent.setName(event.getName());
                    existingEvent.setDate(event.getDate());
                    existingEvent.setLocation(event.getLocation());
                    existingEvent.setCapacity(event.getCapacity());

                    if (existingEvent.getDate().isBefore(LocalDate.now())){
                        throw new IllegalArgumentException("La fecha del evento no puede estar en el pasado.");
                    }

                    if (existingEvent.getCapacity() < 0 ){
                        throw new IllegalArgumentException("La capacidad no puede ser negativa");
                    }
                    return iUserRepository.save(existingEvent);
                })
                .orElseThrow(() -> new EntityNotFoundException("Evento no encontrado con id: " + id));
    }

    public void deleteById (String id){
        iUserRepository.deleteById(id);
    }
}