/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.model;

/**
 *
 * @author Lenovo
 */

public class Book {

    // Reference type
    private String judul;
    private String penulis;
    private String kategori;

    // Primitive type
    private int tahunTerbit;
    private boolean statusKetersediaan;

    // Constructor
    public Book(String judul, String penulis, int tahunTerbit, String kategori) {
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.kategori = kategori;
        this.statusKetersediaan = true;
    }

    // Getter
    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public String getKategori() {
        return kategori;
    }

    public boolean isStatusKetersediaan() {
        return statusKetersediaan;
    }

    // Setter status buku
    public void setStatusKetersediaan(boolean statusKetersediaan) {
        this.statusKetersediaan = statusKetersediaan;
    }

    // Method untuk menampilkan informasi buku
    public void tampilkanInfo() {
        System.out.println("----------------------------------------");
        System.out.println("Judul       : " + judul);
        System.out.println("Penulis     : " + penulis);
        System.out.println("Tahun Terbit: " + tahunTerbit);
        System.out.println("Kategori    : " + kategori);
        System.out.println("Status      : "
                + (statusKetersediaan ? "Tersedia" : "Dipinjam"));
    }

    @Override
    public String toString() {
        return judul + " - " + penulis + " (" + tahunTerbit + ")";
    }
}
