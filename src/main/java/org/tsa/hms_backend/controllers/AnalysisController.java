package org.tsa.hms_backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tsa.hms_backend.dtos.AnalysisFilterDto;
import org.tsa.hms_backend.entities.Analysis;
import org.tsa.hms_backend.entities.AnalysisType;
import org.tsa.hms_backend.services.AnalysisService;

import java.util.List;

@RestController
@RequestMapping("/analysis")
@RequiredArgsConstructor
public class AnalysisController {

    private final AnalysisService analysisService;

    @GetMapping("/types")
    public ResponseEntity<List<AnalysisType>> getAllTypes() {
        List<AnalysisType> analysisTypes = analysisService.analysisTypes();
        return new ResponseEntity<>(analysisTypes, HttpStatus.OK);
    }

    @GetMapping("/{analysisId}/info")
    public ResponseEntity<Analysis> getAnalysisInfo(@PathVariable("analysisId") Long analysisId) {
        Analysis analyses = analysisService.findAnalysisById(analysisId);
        return new ResponseEntity<>(analyses, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Page<Analysis>> getAnalysis(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int limit,
                                                      @RequestParam(required = false) String analysisName, @RequestParam(required = false) String analysisDescription,
                                                      @RequestParam(required = false) Integer price, @RequestParam(required = false) String analysisRoom,
                                                      @RequestParam(required = false) String analysisTypeName){
        AnalysisFilterDto analysisFilterDto = new AnalysisFilterDto(analysisName, analysisDescription, price, analysisRoom, analysisTypeName);
        Page<Analysis> analysisPage = analysisService.searchAnalysis(page, limit, analysisFilterDto);
        return new ResponseEntity<>(analysisPage, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<Analysis> createAnalysis(@RequestBody Analysis analysis) {
        Analysis savedAnalysis = analysisService.createAnalysis(analysis);
        return new ResponseEntity<>(savedAnalysis, HttpStatus.CREATED);
    }

    @PostMapping(value = "/{analysisId}/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Analysis> uploadFile(@RequestParam("file") MultipartFile file,
                                               @PathVariable Long analysisId) {
        Analysis analysis = analysisService.uploadFile(analysisId, file);
        return new ResponseEntity<>(analysis, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Analysis> updateAnalysis(@PathVariable("id") Long id, @RequestBody Analysis analysis) {
        Analysis savedAnalysis = analysisService.prepareUpdateAnalysis(id, analysis);
        return new ResponseEntity<>(savedAnalysis, HttpStatus.OK); 
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteAnalysis(@PathVariable("id") Long id) {
        analysisService.deleteAnalysis(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
