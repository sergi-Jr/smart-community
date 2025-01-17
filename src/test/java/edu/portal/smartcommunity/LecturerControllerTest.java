package edu.portal.smartcommunity;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.portal.smartcommunity.lecturer.dal.LecturerRepository;
import edu.portal.smartcommunity.lecturer.dto.LecturerMapper;
import edu.portal.smartcommunity.lecturer.model.Lecturer;
import edu.portal.smartcommunity.utility.EntityGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class LecturerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityGenerator generator;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LecturerMapper lecturerMapper;

    private Lecturer lecturer;

    @BeforeEach
    void setUp() {
        lecturer = generator.lecturer();
        lecturerRepository.save(lecturer);
    }

    @Test
    public void testGetAll() throws Exception {
        var result = mockMvc.perform(get("/api/lecturers"))
                .andExpect(status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();

        assertThatJson(body).isArray().isNotEmpty();
    }

    @Test
    public void testGetOne() throws Exception {
        var result = mockMvc.perform(get("/api/lecturers/" + lecturer.getId()))
                .andExpect(status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();

        assertThatJson(body).and(
                v -> v.node("email").isEqualTo(lecturer.getEmail()),
                v -> v.node("birthDate").isEqualTo(lecturer.getBirthDate().toString()),
                v -> v.node("lastName").isEqualTo(lecturer.getLastName()),
                v -> v.node("firstName").isEqualTo(lecturer.getFirstName()),
                v -> v.node("phoneNumber").isEqualTo(lecturer.getPhoneNumber())
        );
    }

    @Test
    public void testCreate() throws Exception {
        Lecturer newLecturer = generator.lecturer();
        var request = post("/api/lecturers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(lecturerMapper.mapToCreateDTO(newLecturer)));
        mockMvc.perform(request).andExpect(status().isCreated());
        Optional<Lecturer> preActual = lecturerRepository.findByEmail(newLecturer.getEmail());

        assertThat(preActual).isNotNull();
        Lecturer actual = preActual.get();
        assertThat(actual).usingRecursiveComparison()
                .ignoringFields("updatedAt", "createdAt", "id", "birthDate")
                .isEqualTo(newLecturer);
        assertThat(actual.getBirthDate()).isBetween(newLecturer.getBirthDate(), newLecturer.getBirthDate());
    }

    @Test
    public void testUpdate() throws Exception {
        Map<String, String> data = Map.of("email", "trueTest@gmail.com", "firstName", "John");

        var request = patch("/api/lecturers/" + lecturer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(data));
        mockMvc.perform(request)
                .andExpect(status().isOk());
        Optional<Lecturer> opActual = lecturerRepository.findById(lecturer.getId());

        assertThat(opActual).isNotNull();
        Lecturer actual = opActual.get();
        assertThat(data).containsEntry("email", actual.getEmail());
        assertThat(lecturer.getLastName()).isEqualTo(actual.getLastName());
        assertThat(data).containsEntry("firstName", actual.getFirstName());
    }

    @Test
    public void testDelete() throws Exception {
        var request = delete("/api/lecturers/" + lecturer.getId());
        mockMvc.perform(request)
                .andExpect(status().isNoContent());
        Lecturer opActual = lecturerRepository.findById(lecturer.getId()).orElse(null);

        assertThat(opActual).isNull();
    }
}
