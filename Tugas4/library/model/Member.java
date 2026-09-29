/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.model;

/**
 *
 * @author Lenovo
 */


import java.util.ArrayList;

public class Member {

    private String id;
    private String nama;

    // Reference type
    private ArrayList<Book> daftarPinjaman;

    // Constructor
    public Member(String id, String nama) {
        this.id = id;
        this.nama = nama;
        this.daftarPinjaman = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public ArrayList<Book> getDaftarPinjaman() {
        return daftarPinjaman;
    }

    // Menambahkan buku ke daftar pinjaman
    public void tambahPinjaman(Book book) {
        daftarPinjaman.add(book);
    }

    // Menghapus buku dari daftar pinjaman
    public void hapusPinjaman(Book book) {
        daftarPinjaman.remove(book);
    }

    // Menghitung jumlah buku yang sedang dipinjam
    public int jumlahPinjaman() {
        return daftarPinjaman.size();
    }

    // Menampilkan data anggota
    public void tampilkanInfo() {
        System.out.println("----------------------------------------");
        System.out.println("ID Anggota : " + id);
        System.out.println("Nama       : " + nama);
        System.out.println("Jumlah Pinjaman: " + jumlahPinjaman());

        if (daftarPinjaman.isEmpty()) {
            System.out.println("Daftar Pinjaman: Tidak ada");
        } else {
            System.out.println("Daftar Pinjaman:");

            for (Book book : daftarPinjaman) {
                System.out.println("- " + book.getJudul());
            }
        }
    }
}