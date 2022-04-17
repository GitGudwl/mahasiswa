package com.example.mahasiswa.item;

import org.springframework.data.annotation.Id;

public class Mahasiswa {
    private final Long nim;
    private final String nama;
    private final Kelas Kelas;
    private final Gender gender;

    public Mahasiswa(Long nim, String nama, Kelas kelas, Gender gender) {
        this.nim = nim;
        this.nama = nama;
        this.Kelas = kelas;
        this.gender = gender;
    }

    @Id
    public Long getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }
    

    public Kelas getKelas() {
        return Kelas;
    }

    public Gender getGender() {
        return gender;
    }

    public Mahasiswa updatewith(Mahasiswa mahasiswa) {
        return new Mahasiswa(mahasiswa.nim, mahasiswa.nama, mahasiswa.Kelas, mahasiswa.gender);
    }

 

}
