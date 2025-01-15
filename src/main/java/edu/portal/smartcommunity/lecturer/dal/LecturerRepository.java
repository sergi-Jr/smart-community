package edu.portal.smartcommunity.lecturer.dal;

import edu.portal.smartcommunity.lecturer.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, UUID> {
}
