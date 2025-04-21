package org.tsa.hms_backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tsa.hms_backend.entities.Comments;
import org.tsa.hms_backend.services.CommentService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<Comments> createComment(@RequestBody Comments comment) {
        return new ResponseEntity<>(commentService.create(comment), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Comments>> getComments(@RequestParam(required = false) Long doctorId,
                                                      @RequestParam(required = false) Long patientId,
                                                      @RequestParam(required = false) LocalDateTime from,
                                                      @RequestParam(required = false) LocalDateTime to) {
        List<Comments> comments = commentService.getFilteredComments(doctorId, patientId, from, to);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
