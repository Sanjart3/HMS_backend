package org.tsa.hms_backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.tsa.hms_backend.converters.AnalysisConverter;
import org.tsa.hms_backend.dtos.AnalysisFilterDto;
import org.tsa.hms_backend.dtos.result.AnalysisDto;
import org.tsa.hms_backend.entities.Analysis;
import org.tsa.hms_backend.entities.AnalysisType;
import org.tsa.hms_backend.exceptions.ResourceNotFoundException;
import org.tsa.hms_backend.repositories.AnalysisRepository;
import org.tsa.hms_backend.repositories.AnalysisTypeRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalysisService {

    private final AnalysisRepository analysisRepository;
    private final AnalysisTypeRepository analysisTypeRepository;
    private final AnalysisConverter analysisConverter;

    public List<AnalysisType> analysisTypes(){
        return analysisTypeRepository.findAll();
    }

    public AnalysisDto findAnalysisById(Long id) {
        Analysis analysis = analysisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Analysis not found with id: " + id));
        return analysisConverter.toDto(analysis);
    }

    public Analysis createAnalysis(Analysis analysis) {
        return analysisRepository.save(analysis);
    }

    public Analysis uploadFile(Long analysisId, MultipartFile file) {
        try {
            // 1. Load the Analysis entity
            Analysis analysis = analysisRepository.findById(analysisId)
                    .orElseThrow(() -> new RuntimeException("Analysis not found"));

            // 2. Define the file storage location (you can change this path)
            String uploadDir = "uploads/analysis_results/";
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs(); // Create directory if it doesn't exist
            }

            Path filePath = Paths.get(uploadDir + fileName);

            // 3. Save the file to the file system
            Files.write(filePath, file.getBytes());

            // 4. Save the path to the database
            analysis.setFilePath(filePath.toString());
            analysisRepository.save(analysis);

            return analysis;
        } catch (IOException e) {
            throw new RuntimeException("Could not upload file", e);
        }
    }

    public Analysis prepareUpdateAnalysis(Long id, Analysis analysis) {
        Analysis analysisToUpdate = analysisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Analysis not found with id: " + id));
        Analysis updatingAnalysis = prepareUpdateAnalysis(analysisToUpdate, analysis);
        return analysisRepository.save(updatingAnalysis);
    }

    public void deleteAnalysis(Long id) {
        Analysis analysisToDelete = analysisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Analysis not found with id: " + id));
        analysisRepository.delete(analysisToDelete);
    }

    public Page<Analysis> searchAnalysis(Integer page, Integer limit, AnalysisFilterDto filterDto) {
        page = (page == null) ? 0 : page;
        limit = (limit == null) ? 10 : limit;
        Pageable pageable = PageRequest.of(page, limit);
        return analysisRepository.findFilteredAnalysis(filterDto.getAnalysisName(), filterDto.getPrice(), filterDto.getAnalysisRoom(), filterDto.getAnalysisTypeName(), pageable);
    }

    private Analysis prepareUpdateAnalysis(Analysis analysisToUpdate, Analysis analysis) {
        analysisToUpdate.setName(analysis.getName());
        analysisToUpdate.setDescription(analysis.getDescription());
        analysisToUpdate.setPrice(analysis.getPrice());
        analysisToUpdate.setRoom(analysis.getRoom());
        return analysisToUpdate;
    }
}
