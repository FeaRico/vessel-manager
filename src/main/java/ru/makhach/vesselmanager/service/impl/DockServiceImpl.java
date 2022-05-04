package ru.makhach.vesselmanager.service.impl;

import org.springframework.stereotype.Service;
import ru.makhach.vesselmanager.exceptions.ResourceNotFoundException;
import ru.makhach.vesselmanager.mapper.DockMapper;
import ru.makhach.vesselmanager.model.dto.DockDto;
import ru.makhach.vesselmanager.model.entity.DockEntity;
import ru.makhach.vesselmanager.repository.DockRepository;
import ru.makhach.vesselmanager.service.DockService;

import java.util.List;

@Service
public class DockServiceImpl implements DockService {
    private final DockMapper dockMapper;
    private final DockRepository dockRepository;

    public DockServiceImpl(DockMapper dockMapper, DockRepository dockRepository) {
        this.dockMapper = dockMapper;
        this.dockRepository = dockRepository;
    }

    @Override
    public List<DockDto> getAllDocks() {
        return dockMapper.entityToDto(dockRepository.findAll());
    }

    @Override
    public List<DockDto> getAllDocksByPort(Long portId) {
        return dockMapper.entityToDto(dockRepository.findAllByPort(portId));
    }

    @Override
    public DockDto getDockById(Long id) {
        return dockMapper.entityToDto(dockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(DockEntity.class, "id", id)));
    }

    @Override
    public DockDto updateDock(DockDto dock) {
        Long id = dock.getId();
        dockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(DockEntity.class, "id", id));

        DockEntity entity = dockMapper.dtoToEntity(dock);
        return dockMapper.entityToDto(dockRepository.save(entity));
    }

    @Override
    public DockDto saveDock(DockDto dock) {
        DockEntity entity = dockMapper.dtoToEntity(dock);
        return dockMapper.entityToDto(dockRepository.save(entity));
    }

    @Override
    public DockDto deleteDock(Long id) {
        DockEntity foundEntity = dockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(DockEntity.class, "id", id));

        dockRepository.delete(foundEntity);
        return dockMapper.entityToDto(foundEntity);
    }
}
