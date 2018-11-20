package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private List<TimeEntry> result;

    @Autowired
    TimeEntryRepository timeEntryRepository;
    private long id;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
                this.timeEntryRepository=timeEntryRepository;
    }
    @PostMapping()
    public ResponseEntity create(@RequestBody  TimeEntry object)
    {     System.out.println(" test create " + object);
            TimeEntry objectTemp= timeEntryRepository.create(object);
            return new ResponseEntity(objectTemp,HttpStatus.CREATED);
    }

    @GetMapping("{timeEntryId}" )
    public ResponseEntity<TimeEntry> read(@PathVariable ("timeEntryId") long timeEntryId) {
        TimeEntry timeEntry= timeEntryRepository.find(timeEntryId);
        if(timeEntry!=null )
        {
            return new ResponseEntity(timeEntry,HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {

       // return new ResponseEntity<TimeEntry>(HttpStatus.OK);
        return new ResponseEntity(timeEntryRepository.list(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{timeEntryId}")
    public ResponseEntity update(@PathVariable("timeEntryId") long timeEntryId, @RequestBody TimeEntry timeEntry) {
        TimeEntry timeEntry1=timeEntryRepository.update(timeEntryId, timeEntry);
        if(timeEntry1!=null)
        {
            return new ResponseEntity(timeEntry1,HttpStatus.OK);
        }
        return new ResponseEntity( HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{timeEntryId}" )
    public ResponseEntity<TimeEntry> delete(@PathVariable("timeEntryId") long timeEntryId) {

        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }



}
