package com.fpt.niceshoes.service.impl;

import com.fpt.niceshoes.entity.Size;
import com.fpt.niceshoes.infrastructure.common.PageableObject;
import com.fpt.niceshoes.infrastructure.converter.SizeConvert;
import com.fpt.niceshoes.infrastructure.exception.RestApiException;
import com.fpt.niceshoes.dto.request.properties.SizeRequest;
import com.fpt.niceshoes.dto.response.SizeResponse;
import com.fpt.niceshoes.repository.ISizeRepository;
import com.fpt.niceshoes.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SizeServiceImpl implements SizeService {
    @Autowired
    private ISizeRepository repository;
    @Autowired
    private SizeConvert sizeConvert;

    @Override
    public PageableObject<SizeResponse> getAll(SizeRequest request) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSizePage());
        return new PageableObject<>(repository.getAllSize(request, pageable));
    }

    @Override
    public Size getOne(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Size create(SizeRequest request) {
        if (repository.existsByNameIgnoreCase(request.getName())) {
            throw new RestApiException("Size " + request.getName() + " đã tồn tại!");
        }
        Size size = sizeConvert.convertRequestToEntity(request);
        return repository.save(size);
    }

    @Override
    public Size update(Long id, SizeRequest request) {
        Size oldSize = repository.findById(id).get();
        if (repository.existsByNameIgnoreCase(request.getName())) {
            if (oldSize.getName().equals(request.getName())) {
                return repository.save(sizeConvert.convertRequestToEntity(oldSize, request));
            }
            throw new RestApiException("Size " + request.getName() + " đã tồn tại!");
        } else {
            return repository.save(sizeConvert.convertRequestToEntity(oldSize, request));
        }
    }

    @Override
    public Size delete(Long id) {
        Size size = this.getOne(id);
        size.setDeleted(!size.getDeleted());
        return repository.save(size);
    }
}
