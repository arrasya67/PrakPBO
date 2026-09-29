/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lenovo
 */

package library.service;

import library.model.Book;
import library.model.Member;
import library.exception.BookNotFoundException;
import library.exception.BookAlreadyBorrowedException;
import library.exception.BorrowLimitExceededException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LibraryService {

    // ArrayList untuk menyimpan koleksi buku
    private ArrayList<Book> daftarBuku;

    // ArrayList untuk menyimpan anggota
    private ArrayList<Member> daftarMember;

    // HashMap untuk mencatat jumlah peminjaman setiap buku
    private HashMap<String, Integer> statistikPeminjaman;

    // HashMap untuk mencatat aktivitas anggota
    private HashMap<String, Integer> aktivitasMember;

    // Constructor
    public LibraryService() {
        daftarBuku = new ArrayList<>();
        daftarMember = new ArrayList<>();
        statistikPeminjaman = new HashMap<>();
        aktivitasMember = new HashMap<>();
    }


    // MANAJEMEN BUKU
 
    public void tambahBuku(Book book) {
        daftarBuku.add(book);

        // Awalnya buku belum pernah dipinjam
        statistikPeminjaman.put(book.getJudul(), 0);
    }

    public void tampilkanSemuaBuku() {

        if (daftarBuku.isEmpty()) {
            System.out.println("\nBelum ada buku.");
            return;
        }

        System.out.println("\n========== DAFTAR BUKU ==========");

        for (Book book : daftarBuku) {
            book.tampilkanInfo();
        }
    }


    // PENCARIAN BUKU
  
    public void cariBuku(String keyword) {

        boolean ditemukan = false;

        // Manipulasi String
        String kataKunci = keyword.toLowerCase();

        System.out.println("\n========== HASIL PENCARIAN ==========");

        for (Book book : daftarBuku) {

            String judul = book.getJudul().toLowerCase();
            String kategori = book.getKategori().toLowerCase();

            // String contains()
            if (judul.contains(kataKunci)
                    || kategori.contains(kataKunci)) {

                book.tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Buku tidak ditemukan.");
        }
    }

     // TAMBAH MEMBER
 
    public void tambahMember(Member member) {
        daftarMember.add(member);

        aktivitasMember.put(member.getId(), 0);
    }

    // Mencari member berdasarkan ID
    public Member cariMember(String id) {

        for (Member member : daftarMember) {

            if (member.getId().equalsIgnoreCase(id)) {
                return member;
            }
        }

        return null;
    }

    // MENCARI BUKU

    private Book cariBukuByJudul(String judul)
            throws BookNotFoundException {

        for (Book book : daftarBuku) {

            if (book.getJudul().equalsIgnoreCase(judul)) {
                return book;
            }
        }

        throw new BookNotFoundException(
                "Buku dengan judul \"" + judul + "\" tidak ditemukan."
        );
    }


    // PROSES PEMINJAMAN


    public void pinjamBuku(String idMember, String judul)
            throws BookNotFoundException,
            BookAlreadyBorrowedException,
            BorrowLimitExceededException {

        Member member = cariMember(idMember);

        // Assertion untuk memastikan data anggota valid
        assert member != null : "Data anggota tidak boleh null.";

        if (member == null) {
            System.out.println("Anggota tidak ditemukan.");
            return;
        }

        // Batas maksimal 3 buku
        if (member.jumlahPinjaman() >= 3) {
            throw new BorrowLimitExceededException(
                    "Anggota sudah mencapai batas maksimal 3 buku."
            );
        }

        // Cari buku
        Book book = cariBukuByJudul(judul);

        // Cek apakah buku tersedia
        if (!book.isStatusKetersediaan()) {
            throw new BookAlreadyBorrowedException(
                    "Buku \"" + book.getJudul()
                            + "\" sedang dipinjam."
            );
        }

        // Ubah status buku
        book.setStatusKetersediaan(false);

        // Masukkan buku ke daftar pinjaman
        member.tambahPinjaman(book);

        // Update statistik buku
        int jumlah = statistikPeminjaman.get(book.getJudul());
        statistikPeminjaman.put(book.getJudul(), jumlah + 1);

        // Update aktivitas member
        int aktivitas = aktivitasMember.get(member.getId());
        aktivitasMember.put(member.getId(), aktivitas + 1);

        System.out.println(
                "Buku berhasil dipinjam oleh "
                        + member.getNama()
                        + "."
        );
    }


    // PROSES PENGEMBALIAN


    public void kembalikanBuku(String idMember, String judul)
            throws BookNotFoundException {

        Member member = cariMember(idMember);

        assert member != null : "Data anggota tidak boleh null.";

        if (member == null) {
            System.out.println("Anggota tidak ditemukan.");
            return;
        }

        Book book = cariBukuByJudul(judul);

        if (!member.getDaftarPinjaman().contains(book)) {

            System.out.println(
                    "Buku tersebut tidak sedang dipinjam oleh anggota ini."
            );

            return;
        }

        // Hapus dari daftar pinjaman
        member.hapusPinjaman(book);

        // Ubah status menjadi tersedia
        book.setStatusKetersediaan(true);

        System.out.println(
                "Buku \"" + book.getJudul()
                        + "\" berhasil dikembalikan."
        );
    }

    // ANALISIS KATEGORI


    public void analisisKategori() {

        HashMap<String, Integer> jumlahKategori = new HashMap<>();

        for (Book book : daftarBuku) {

            String kategori = book.getKategori();

            if (jumlahKategori.containsKey(kategori)) {

                int jumlah = jumlahKategori.get(kategori);

                jumlahKategori.put(kategori, jumlah + 1);

            } else {

                jumlahKategori.put(kategori, 1);
            }
        }

        System.out.println("\n========== JUMLAH BUKU PER KATEGORI ==========");

        for (Map.Entry<String, Integer> entry
                : jumlahKategori.entrySet()) {

            System.out.println(
                    entry.getKey()
                            + " : "
                            + entry.getValue()
                            + " buku"
            );
        }
    }


    // BUKU PALING SERING DIPINJAM


    public void bukuPalingSeringDipinjam() {

        if (statistikPeminjaman.isEmpty()) {
            System.out.println("Belum ada data peminjaman.");
            return;
        }

        String bukuTerpopuler = null;
        int jumlahTerbesar = 0;

        for (Map.Entry<String, Integer> entry
                : statistikPeminjaman.entrySet()) {

            if (entry.getValue() > jumlahTerbesar) {

                jumlahTerbesar = entry.getValue();
                bukuTerpopuler = entry.getKey();
            }
        }

        if (jumlahTerbesar == 0) {

            System.out.println(
                    "Belum ada buku yang pernah dipinjam."
            );

        } else {

            System.out.println(
                    "Buku paling sering dipinjam : "
                            + bukuTerpopuler
            );

            System.out.println(
                    "Jumlah peminjaman           : "
                            + jumlahTerbesar
            );
        }
    }

    // ANGGOTA PALING AKTIF
  

    public void anggotaPalingAktif() {

        String memberTeraktif = null;
        int aktivitasTerbesar = 0;

        for (Map.Entry<String, Integer> entry
                : aktivitasMember.entrySet()) {

            if (entry.getValue() > aktivitasTerbesar) {

                aktivitasTerbesar = entry.getValue();
                memberTeraktif = entry.getKey();
            }
        }

        if (aktivitasTerbesar == 0) {

            System.out.println(
                    "Belum ada aktivitas peminjaman."
            );

        } else {

            Member member = cariMember(memberTeraktif);

            System.out.println(
                    "Anggota paling aktif : "
                            + member.getNama()
            );

            System.out.println(
                    "Total peminjaman     : "
                            + aktivitasTerbesar
            );
        }
    }

    // KATEGORI PALING POPULER


    public void kategoriPalingPopuler() {

        HashMap<String, Integer> jumlahPinjamanKategori
                = new HashMap<>();

        for (Book book : daftarBuku) {

            int jumlah =
                    statistikPeminjaman.get(book.getJudul());

            String kategori = book.getKategori();

            if (jumlahPinjamanKategori.containsKey(kategori)) {

                int total =
                        jumlahPinjamanKategori.get(kategori);

                jumlahPinjamanKategori.put(
                        kategori,
                        total + jumlah
                );

            } else {

                jumlahPinjamanKategori.put(
                        kategori,
                        jumlah
                );
            }
        }

        String kategoriTerpopuler = null;
        int jumlahTerbesar = 0;

        for (Map.Entry<String, Integer> entry
                : jumlahPinjamanKategori.entrySet()) {

            if (entry.getValue() > jumlahTerbesar) {

                jumlahTerbesar = entry.getValue();
                kategoriTerpopuler = entry.getKey();
            }
        }

        if (jumlahTerbesar == 0) {

            System.out.println(
                    "Belum ada data kategori yang dipinjam."
            );

        } else {

            System.out.println(
                    "Kategori paling populer : "
                            + kategoriTerpopuler
            );

            System.out.println(
                    "Total peminjaman        : "
                            + jumlahTerbesar
            );
        }
    }


    // TOTAL PEMINJAMAN


    public void totalPeminjaman() {

        int total = 0;

        for (int jumlah : statistikPeminjaman.values()) {
            total += jumlah;
        }

        System.out.println(
                "Jumlah total pinjaman : " + total
        );
    }


    // LAPORAN LENGKAP


    public void tampilkanLaporan() {

        System.out.println("\n========================================");
        System.out.println("       LAPORAN PERPUSTAKAAN");
        System.out.println("========================================");

        totalPeminjaman();

        bukuPalingSeringDipinjam();

        anggotaPalingAktif();

        kategoriPalingPopuler();

        analisisKategori();

        System.out.println("========================================");
    }

  
    // DATA AWAL


    public void isiDataAwal() {

        tambahBuku(
                new Book(
                        "Pemrograman Java",
                        "Budi Santoso",
                        2023,
                        "Teknologi"
                )
        );

        tambahBuku(
                new Book(
                        "Algoritma dan Struktur Data",
                        "Andi Wijaya",
                        2022,
                        "Teknologi"
                )
        );

        tambahBuku(
                new Book(
                        "Laskar Pelangi",
                        "Andrea Hirata",
                        2005,
                        "Novel"
                )
        );

        tambahBuku(
                new Book(
                        "Bumi",
                        "Tere Liye",
                        2014,
                        "Novel"
                )
        );

        tambahBuku(
                new Book(
                        "Sejarah Indonesia",
                        "Ahmad Fauzi",
                        2020,
                        "Sejarah"
                )
        );

        tambahMember(
                new Member("M001", "Agus")
        );

        tambahMember(
                new Member("M002", "Budi")
        );
    }
}
