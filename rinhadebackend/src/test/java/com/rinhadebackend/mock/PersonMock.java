package com.rinhadebackend.mock;

import com.rinhadebackend.dto.request.PersonRequest;

import java.time.LocalDate;
import java.util.*;

public class PersonMock {

    public static final PersonRequest firstPersonRequest = new PersonRequest(
            "josé",
            "José Roberto",
            LocalDate.parse("2000-10-01"),
            new HashSet<>(Arrays.asList("C#", "Node", "Oracle"))
    );

    public static final PersonRequest secondPersonRequest = new PersonRequest(
            "ana",
            "Ana Barbosa",
            LocalDate.parse("1985-09-23"),
            null
    );

    public static final PersonRequest thirdPersonRequest = new PersonRequest(
            "zeca",
            "pagodinho",
            LocalDate.parse("1995-10-11"),
            new HashSet<>(Arrays.asList("Node", "Postgres"))
    );

    public static final List<PersonRequest> personRequestList = new ArrayList<>(
            List.of(firstPersonRequest, secondPersonRequest, thirdPersonRequest)
    );


    public static final PersonRequest personNameCantBeNull = new PersonRequest(
            "ana",
            null,
            LocalDate.parse("1985-09-23"),
            null
    );


    public static final PersonRequest personNicknameCantBeNull = new PersonRequest(
            null,
            "Ana Barbosa",
            LocalDate.parse("1985-09-23"),
            null
    );

    public static final PersonRequest personNameShouldBeAValidString = new PersonRequest(
            "apelido",
            "1",
            LocalDate.parse("1985-01-01"),
            null
    );

    public static final PersonRequest personStacksShouldBeAValidStringArray = new PersonRequest(
            "apelido",
            "nome",
            LocalDate.parse("1985-01-01"),
            new HashSet<>(Arrays.asList("1", "PHP"))
    );

    public static PersonRequest getRandomPersonRequest() {
        Random random = new Random();
        int randomIndex = random.nextInt(personRequestList.size());
        return personRequestList.get(randomIndex);

    }


}
