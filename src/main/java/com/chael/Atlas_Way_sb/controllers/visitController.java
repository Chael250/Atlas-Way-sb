package com.chael.Atlas_Way_sb.controllers;

import com.chael.Atlas_Way_sb.entities.Visits;
import com.chael.Atlas_Way_sb.services.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atlas-way/visits")
public class visitController {
    private final VisitService visitService;

    public visitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Visits> getVisits(@PathVariable("id") Long id){
        return visitService.findAllByOwner(id);
    }

    @GetMapping("/{user}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Visits getVisitsByUser(@PathVariable("user") Long userId, @PathVariable("id") Long id){
        return visitService.findByIdAndOwner(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Visits createVisit(@RequestBody Visits visit){
        return visitService.createByOwner(visit);
    }

    @DeleteMapping("/{id}/{visitId}")
    @ResponseStatus(HttpStatus.OK)
    public Visits deleteVisit(@PathVariable("id") Long id, @PathVariable("visitId") Long visitId){
        Visits visit = visitService.findByIdAndOwner(visitId);
        visitService.deleteByOwner(visitId);
        return visit;
    }

    @PutMapping("/{id}/{visit}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Visits updateVisit(@PathVariable("id") Long id, @PathVariable("visit") Long visitId, @RequestBody Visits visit){
        Visits oldVisit = visitService.findByIdAndOwner(visitId);
        visitService.updateByOwner(visit, id);
        return oldVisit;
    }
}
