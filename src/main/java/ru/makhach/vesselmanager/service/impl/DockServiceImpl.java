package ru.makhach.vesselmanager.service.impl;

import org.springframework.stereotype.Service;
import ru.makhach.vesselmanager.exceptions.ResourceNotFoundException;
import ru.makhach.vesselmanager.model.entity.Dock;
import ru.makhach.vesselmanager.repository.DockRepository;
import ru.makhach.vesselmanager.service.DockService;

import java.util.List;

@Service
public class DockServiceImpl implements DockService {
    private final DockRepository dockRepository;

    public DockServiceImpl(DockRepository dockRepository) {
        this.dockRepository = dockRepository;
    }

    @Override
    public List<Dock> getAllDocks() {
        return dockRepository.findAll();
    }

    @Override
    public List<Dock> getAllDocksByPort(Long portId) {
        return dockRepository.findAllByPort(portId);
    }

    @Override
    public Dock getDockById(Long id) {
        return dockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Dock.class, "id", id));
    }

    @Override
    public Dock updateDock(Dock dock) {
        Long id = dock.getId();
        dockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Dock.class, "id", id));

        return dockRepository.save(dock);
    }

    @Override
    public Dock saveDock(Dock dock) {
        return dockRepository.save(dock);
    }

    @Override
    public Dock deleteDock(Long id) {
        Dock foundEntity = dockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Dock.class, "id", id));

        dockRepository.delete(foundEntity);
        return foundEntity;
    }
}
