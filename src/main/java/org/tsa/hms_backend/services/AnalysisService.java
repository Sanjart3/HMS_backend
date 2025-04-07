package org.tsa.hms_backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.tsa.hms_backend.dtos.AnalysisFilterDto;
import org.tsa.hms_backend.entities.Analysis;
import org.tsa.hms_backend.entities.AnalysisType;
import org.tsa.hms_backend.exceptions.ResourceNotFoundException;
import org.tsa.hms_backend.repositories.AnalysisRepository;
import org.tsa.hms_backend.repositories.AnalysisTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalysisService {

    private final AnalysisRepository analysisRepository;
    private final AnalysisTypeRepository analysisTypeRepository;

    public List<AnalysisType> analysisTypes(){
        return analysisTypeRepository.findAll();
    }

    public Analysis findAnalysisById(Long id) {
        return analysisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Analysis not found with id: " + id));
    }

    public Analysis createAnalysis(Analysis analysis) {
        return analysisRepository.save(analysis);
    }

    public Analysis prepareUpdateAnalysis(Long id, Analysis analysis) {
        Analysis analysisToUpdate = findAnalysisById(id);
        Analysis updatingAnalysis = prepareUpdateAnalysis(analysisToUpdate, analysis);
        return analysisRepository.save(updatingAnalysis);
    }

    public void deleteAnalysis(Long id) {
        Analysis analysisToDelete = findAnalysisById(id);
        analysisRepository.delete(analysisToDelete);
    }

    public Page<Analysis> searchAnalysis(Integer page, Integer limit, AnalysisFilterDto filterDto) {
        Pageable pageable = PageRequest.of(Math.max(page, 0), (limit>0)?limit:10);
        return analysisRepository.findFilteredAnalysis(filterDto.getAnalysisName(), filterDto.getAnalysisDescription(), filterDto.getPrice(), filterDto.getAnalysisRoom(), filterDto.getAnalysisTypeName(), pageable);
    }

    private Analysis prepareUpdateAnalysis(Analysis analysisToUpdate, Analysis analysis) {
        analysisToUpdate.setName(analysis.getName());
        analysisToUpdate.setDescription(analysis.getDescription());
        analysisToUpdate.setPrice(analysis.getPrice());
        analysisToUpdate.setRoom(analysis.getRoom());
        return analysisToUpdate;
    }
}
