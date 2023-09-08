package com.rinhadebackend.repository;

import com.rinhadebackend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
//    @Query(value = """
//            SELECT
//                   PE.id,
//                   PE.nick_name,
//                   PE.name,
//                   PE.date_of_birth
//               FROM
//                   PERSONS PE
//                       LEFT JOIN
//                           PERSONS_STACKS PS
//                                   ON PS.persons_id = PE.id
//                       LEFT JOIN
//                           STACKS ST
//                                   ON ST.id = PS.stacks_id
//               WHERE
//                  (
//                      (
//                        PE.name ILIKE :term OR PE.nick_name ILIKE :term
//                      ) OR
//                      ST.name ILIKE :term
//                  )
//                GROUP BY
//                    PE.id
//               LIMIT 50;
//
//            """, nativeQuery = true)
//     List<Person> getPersonBySearchTerm(String term);



//    A query comentada acima, é cerca de 10%-15% mais lenta que a a query abaixo.
//    Concatenar a string usando os pipes e depois comparando somente uma única vez
//    por execução é bem mais performático, do que usando OR e múltiplos ILIKES.



    @Query(value = """
                    SELECT
                            PE.id,
                            PE.nick_name,
                            PE.name,
                            PE.date_of_birth
                        FROM
                            PERSONS PE
                                LEFT JOIN
                                    PERSONS_STACKS PS
                                            ON PS.persons_id = PE.id
                                LEFT JOIN
                                    STACKS ST
                                            ON ST.id = PS.stacks_id
                        WHERE
                            (
                                    (

                                         COALESCE(PE.name, '') ||
                                         COALESCE(PE.nick_name, '') ||
                                         COALESCE(ST.name, '')

                                    ) ILIKE :term
                            )
                        GROUP BY
                            PE.id
                        LIMIT 50;

            """, nativeQuery = true)
     List<Person> getPersonBySearchTerm(String term);

}