package com.fab.conferencedemo.controllers;

import com.fab.conferencedemo.models.Speaker;
import com.fab.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakerController {

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public List<Speaker> list() {
        return speakerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id) {
        return speakerRepository.getReferenceById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // to return a specific status
    public Speaker create(@RequestBody final Speaker speaker) {
        return speakerRepository.saveAndFlush(speaker);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        // need to check children records before deleting
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    // RequestMethod.PUT vs RequestMethod.PATCH = change ALL attributes vs change ONLY SOME attributes
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
        // with PUT you must pass in all the attributes, otherwise error 400 is returned
        Speaker existingSpeaker = speakerRepository.getReferenceById(id);
        // 3rd parameter is properties ignored in copy, in this case the id should not be copied
        BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
        return speakerRepository.saveAndFlush(existingSpeaker);
    }
}
