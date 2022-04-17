package com.example.mahasiswa.item;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InMemoryRepository extends CrudRepository<Mahasiswa, Long> {
}
