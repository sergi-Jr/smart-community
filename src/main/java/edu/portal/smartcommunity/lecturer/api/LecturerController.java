package edu.portal.smartcommunity.lecturer.api;

import edu.portal.smartcommunity.lecturer.dal.LecturerService;
import edu.portal.smartcommunity.lecturer.dto.LecturerCreateDTO;
import edu.portal.smartcommunity.lecturer.dto.LecturerDTO;
import edu.portal.smartcommunity.lecturer.dto.LecturerUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping(value = "/api/lecturers", produces = {"application/json"})
@RequiredArgsConstructor
public class LecturerController {
    private final LecturerService lecturerService;

    @GetMapping(path = "")
    public List<LecturerDTO> getAll() {
        return lecturerService.getAll();
    }

    @GetMapping(path = "/{id}")
    public LecturerDTO getOne(@PathVariable UUID id) {
        return lecturerService.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LecturerDTO create(@RequestBody @Valid LecturerCreateDTO dto) {
        return lecturerService.create(dto);
    }

    @PatchMapping(path = "/{id}")
    public LecturerDTO update(@PathVariable UUID id, @RequestBody @Valid LecturerUpdateDTO dto) {
        return lecturerService.update(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        lecturerService.delete(id);
    }
}
