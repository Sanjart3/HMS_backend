package org.tsa.hms_backend.converters;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractConverter<E, D> {

    protected final ModelMapper modelMapper;

    public E toEntity(D dto) {
        return modelMapper.map(dto, getEntity());
    }

    public D toDto(E entity) {
        return modelMapper.map(entity, getDto());
    }

    public List<E> toEntityList(List<D> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<D> toDtoList(List<E> entities) {
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    protected abstract Class<E> getEntity();
    protected abstract Class<D> getDto();
}
