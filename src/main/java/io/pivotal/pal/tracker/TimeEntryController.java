package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @RequestMapping
    public ResponseEntity<TimeEntry> create(TimeEntry timeEntryToCreate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(timeEntryRepository.create(timeEntryToCreate));
    }

    @RequestMapping
    public ResponseEntity<TimeEntry> read(long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        return ResponseEntity.status(timeEntry == null ? HttpStatus.NOT_FOUND:HttpStatus.OK).body(timeEntry);
    }

    @RequestMapping
    public ResponseEntity update(long timeEntryId, TimeEntry expected) {
        TimeEntry timeEntry = timeEntryRepository.update(timeEntryId,expected);
        return ResponseEntity.status(timeEntry == null ? HttpStatus.NOT_FOUND:HttpStatus.OK).body(timeEntry);
    }

    @RequestMapping
    public ResponseEntity<TimeEntry> delete(long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @RequestMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return ResponseEntity.ok(timeEntryRepository.list());
    }
}
