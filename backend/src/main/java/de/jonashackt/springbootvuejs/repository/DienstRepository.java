package de.jonashackt.springbootvuejs.repository;

import de.jonashackt.springbootvuejs.domain.Dienst;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DienstRepository extends CrudRepository<Dienst, Long> {

    List<Dienst> findByLastName(@Param("name") String name);

    List<Dienst> findByFirstName(@Param("name") String name);

}