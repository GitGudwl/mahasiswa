package com.example.mahasiswa.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@EnableMapRepositories

public class ItemService {
    private final CrudRepository<Mahasiswa, Long> repository;

    public ItemService(CrudRepository<Mahasiswa, Long> repository) {
        this.repository = repository;

        this.repository.saveAll(DefaultMhs());

    }

    private static List<Mahasiswa> DefaultMhs() {
        return List.of(
                new Mahasiswa(211524032L, "Zahri Al Adzani Hidayat", Kelas.D4_1A, Gender.PRIA),
                new Mahasiswa(211524002L, "Amelia Dwi Agustiani", Kelas.D4_1A, Gender.WANITA),
                new Mahasiswa(211524013L, "Maolana Firmansyah", Kelas.D4_1A, Gender.PRIA));
    }

    public List<Mahasiswa> findall() {
        List<Mahasiswa> list = new ArrayList<>();
        Iterable<Mahasiswa> persons = repository.findAll();
        persons.forEach(list::add);
        return list;
    }

    public Optional<Mahasiswa> find(Long id) {
        return repository.findById(id);
    }

    public Mahasiswa createMahasiswa(Mahasiswa person) {
        Mahasiswa copy = new Mahasiswa(person.getNim(), person.getNama(), person.getKelas(), person.getGender());
        return repository.save(copy);
    }

    public Optional<Mahasiswa> update(Long nim, Mahasiswa person) {
        return repository.findById(nim).map(oldperson -> {
            Mahasiswa updated = oldperson.updatewith(person);
            return repository.save(updated);
        });
    }

    public void delete(Long nim) {
        repository.deleteById(nim);
    }

}
