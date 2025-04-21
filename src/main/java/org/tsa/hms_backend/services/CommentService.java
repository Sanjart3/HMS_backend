package org.tsa.hms_backend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tsa.hms_backend.entities.Comments;
import org.tsa.hms_backend.repositories.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;

    public Comments create(Comments comment) {
        log.info("Creating comment {}", comment);
        return commentRepository.save(comment);
    }

//    public List<Comments> getComments(Long doctorId, Long patientId, LocalDateTime from, LocalDateTime to) {
//        return commentRepository.getComments(doctorId, patientId, from, to);
//    }

    public List<Comments> getFilteredComments(Long doctorId, Long patientId, LocalDateTime from, LocalDateTime to) {
        log.info("Getting Filtered Comments!");
        List<Comments> all = commentRepository.findAll(); // or custom base filter
        return all.stream()
                .filter(c -> doctorId == null || c.getDoctor().getId().equals(doctorId))
                .filter(c -> patientId == null || c.getPatient().getId().equals(patientId))
                .filter(c -> from == null || !c.getCreationDateTime().isBefore(from))
                .filter(c -> to == null || !c.getCreationDateTime().isAfter(to))
                .collect(Collectors.toList());
    }

}
