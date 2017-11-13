package edu.usach.tbdgrupo5.repository;

import edu.usach.tbdgrupo5.entities.Registro;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RegistroRepository extends PagingAndSortingRepository<Registro, Integer> {
}
