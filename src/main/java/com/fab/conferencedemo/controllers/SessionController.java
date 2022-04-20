package com.fab.conferencedemo.controllers;

import com.fab.conferencedemo.models.Session;
import com.fab.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    @GetMapping
    //@RequestMapping("{id}")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Session get(@PathVariable Long id) {
        return sessionRepository.getReferenceById(id);
    }

    /*
    Request body example:
    {
        "sessionName" : "Spring Boot",
        "sessionLength" : 55,
        "sessionDescription" : "Spring Boot Description"
     }
    */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // to return a specific status
    public Session create(@RequestBody final Session session) {
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        try {
            // need to check children records before deleting
            sessionRepository.deleteById(id);
        } catch (org.springframework.dao.EmptyResultDataAccessException ex){
            System.err.println("No entity found");
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    // RequestMethod.PUT vs RequestMethod.PATCH = change ALL attributes vs change ONLY SOME attributes
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        // with PUT you must pass in all the attributes, otherwise error 400 is returned
        Session existingSession = sessionRepository.getReferenceById(id);
        // 3rd parameter is properties ignored in copy, in this case the id should not be copied
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }
}
