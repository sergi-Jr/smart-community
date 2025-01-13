package edu.portal.smartcommunity.lecturer.dal;

import edu.portal.smartcommunity.exception.ResourceNotFoundException;
import edu.portal.smartcommunity.lecturer.dto.LecturerCreateDTO;
import edu.portal.smartcommunity.lecturer.dto.LecturerDTO;
import edu.portal.smartcommunity.lecturer.dto.LecturerMapper;
import edu.portal.smartcommunity.lecturer.dto.LecturerUpdateDTO;
import edu.portal.smartcommunity.lecturer.model.Lecturer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LecturerService {
    private final LecturerRepository lecturerRepository;
    private final LecturerMapper lecturerMapper;

    public List<LecturerDTO> getAll() {
        List<Lecturer> lecturers = lecturerRepository.findAll();
        return lecturers.stream().map(lecturerMapper::map).collect(Collectors.toList());
    }

    public LecturerDTO getOne(UUID id) {
        Lecturer model = lecturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity doesn't exists" + id));
        return lecturerMapper.map(model);
    }

    @Transactional
    public LecturerDTO update(UUID id, LecturerUpdateDTO dto) {
        Lecturer model = lecturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity doesn't exists" + id));
        lecturerMapper.update(dto, model);
        lecturerRepository.save(model);
        return lecturerMapper.map(model);
    }

    @Transactional
    public LecturerDTO create(LecturerCreateDTO dto) {
        Lecturer model = lecturerMapper.map(dto);
        lecturerRepository.save(model);
        return lecturerMapper.map(model);
    }

    @Transactional
    public void delete(UUID id) {
        lecturerRepository.deleteById(id);
    }
}
