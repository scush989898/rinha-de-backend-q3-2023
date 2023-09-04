package com.rinhadebackend.repository;

import com.rinhadebackend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
    @Query(value = """
            SELECT
                   PE.id,
                   PE.nick_name,
                   PE.name,
                   PE.date_of_birth
               FROM
                   PERSONS PE
                       INNER JOIN
                           PERSONS_STACKS PS
                                   ON PS.persons_id = PE.id
                       INNER JOIN
                           STACKS ST
                                   ON ST.id = PS.stacks_id
               WHERE
                  (
                      (
                        PE.name ILIKE CONCAT('%', :term, '%') OR PE.nick_name ILIKE CONCAT('%', :term, '%')
                      ) OR
                      ST.name ILIKE CONCAT('%', :term, '%')
                  )
                GROUP BY 
                    PE.id
               LIMIT 50;
                      
            """, nativeQuery = true)
     List<Person> getPersonBySearchTerm(String term);
}