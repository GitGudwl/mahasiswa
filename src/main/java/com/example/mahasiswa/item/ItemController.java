package com.example.mahasiswa.item;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/mahasiswa/persons")
public class ItemController {
    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    // ✨ New! GET controller methods
    @GetMapping
    public ResponseEntity<List<Mahasiswa>> findAll() {
        List<Mahasiswa> persons = service.findall();
        return ResponseEntity.ok().body(persons);
    }

    @GetMapping("/{nim}")
    public ResponseEntity<Mahasiswa> find(@PathVariable("nim") Long nim) {
        Optional<Mahasiswa> person = service.find(nim);
        return ResponseEntity.of(person);
    }

    @PostMapping
    public ResponseEntity<Mahasiswa> create(@RequestBody Mahasiswa person) {
        Mahasiswa created = service.createMahasiswa(person);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{nim}")
                .buildAndExpand(created.getNim())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{nim}")
    public ResponseEntity<Mahasiswa> update(
            @PathVariable("nim") Long nim,
            @RequestBody Mahasiswa updatedMahasiswa) {

        Optional<Mahasiswa> updated = service.update(nim, updatedMahasiswa);

        return updated
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> {
                    Mahasiswa created = service.createMahasiswa(updatedMahasiswa);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{nim}")
                            .buildAndExpand(created.getNim())
                            .toUri();
                    return ResponseEntity.created(location).body(created);
                });
    }

    @DeleteMapping("/{nim}")
    public ResponseEntity<Mahasiswa> delete(@PathVariable("nim") Long nim) {
        service.delete(nim);
        return ResponseEntity.noContent().build();
    }
}