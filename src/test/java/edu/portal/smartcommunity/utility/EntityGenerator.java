package edu.portal.smartcommunity.utility;

import edu.portal.smartcommunity.lecturer.model.Lecturer;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.springframework.stereotype.Component;


import static org.instancio.Select.field;

@Component
@RequiredArgsConstructor
public class EntityGenerator {
    private final Faker faker;

    public Lecturer lecturer() {
        return Instancio.of(Lecturer.class)
                .ignore(field(Lecturer::getCreatedAt))
                .ignore(field(Lecturer::getUpdatedAt))
                .ignore(field(Lecturer::getId))
                .supply(field(Lecturer::getFirstName), () -> faker.name().firstName())
                .supply(field(Lecturer::getLastName), () -> faker.name().lastName())
                .supply(field(Lecturer::getEmail), () -> faker.internet().emailAddress())
                .supply(field(Lecturer::getBirthDate), () -> faker.date().birthday().toLocalDateTime().toLocalDate())
                .supply(field(Lecturer::getPhoneNumber), () -> faker.phoneNumber().phoneNumber())
                .create();
    }
}
