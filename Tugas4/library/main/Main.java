/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.main;

/**
 *
 * @author Lenovo
 */

import library.model.Book;
import library.model.Member;
import library.service.LibraryService;
import library.exception.BookNotFoundException;
import library.exception.BookAlreadyBorrowedException;
import library.exception.BorrowLimitExceededException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        LibraryService library = new LibraryService();

        // Memasukkan data awal
        library.isiDataAwal();

        int pilihan;

        do {

            tampilkanMenu();

            System.out.print("Pilih menu: ");

            try {

                pilihan = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println(
                        "Input harus berupa angka."
                );

                pilihan = 0;
            }

            switch (pilihan) {

                case 1:
                    tambahBuku(scanner, library);
                    break;

                case 2:
                    library.tampilkanSemuaBuku();
                    break;

                case 3:
                    cariBuku(scanner, library);
                    break;

                case 4:
                    pinjamBuku(scanner, library);
                    break;

                case 5:
                    kembalikanBuku(scanner, library);
                    break;

                case 6:
                    library.tampilkanLaporan();
                    break;

                case 7:
                    tambahMember(scanner, library);
                    break;

                case 8:
                    tampilkanMember(scanner, library);
                    break;

                case 0:
                    System.out.println(
                            "\nTerima kasih telah menggunakan "
                                    + "Sistem Perpustakaan."
                    );
                    break;

                default:
                    System.out.println(
                            "Menu tidak tersedia."
                    );
            }

        } while (pilihan != 0);

        scanner.close();
    }


    // MENU


    public static void tampilkanMenu() {

        System.out.println("\n========================================");
        System.out.println("       SISTEM PERPUSTAKAAN MINI");
        System.out.println("========================================");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Daftar Buku");
        System.out.println("3. Cari Buku");
        System.out.println("4. Pinjam Buku");
        System.out.println("5. Kembalikan Buku");
        System.out.println("6. Laporan Perpustakaan");
        System.out.println("7. Tambah Anggota");
        System.out.println("8. Data Anggota");
        System.out.println("0. Keluar");
        System.out.println("========================================");
    }

    // TAMBAH BUKU


    public static void tambahBuku(
            Scanner scanner,
            LibraryService library) {

        System.out.println("\n========== TAMBAH BUKU ==========");

        System.out.print("Judul       : ");
        String judul = scanner.nextLine();

        System.out.print("Penulis     : ");
        String penulis = scanner.nextLine();

        System.out.print("Tahun Terbit: ");

        int tahunTerbit;

        try {

            tahunTerbit =
                    Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {

            System.out.println(
                    "Tahun harus berupa angka."
            );

            return;
        }

        System.out.print("Kategori    : ");
        String kategori = scanner.nextLine();

        // Manipulasi Character
        char karakterAwal = judul.charAt(0);

        karakterAwal =
                Character.toUpperCase(karakterAwal);

        System.out.println(
                "Karakter awal judul: " + karakterAwal
        );

        Book book = new Book(
                judul,
                penulis,
                tahunTerbit,
                kategori
        );

        library.tambahBuku(book);

        System.out.println(
                "Buku berhasil ditambahkan."
        );
    }

    // CARI BUKU


    public static void cariBuku(
            Scanner scanner,
            LibraryService library) {

        System.out.println("\n========== CARI BUKU ==========");

        System.out.print(
                "Masukkan judul atau kategori: "
        );

        String keyword = scanner.nextLine();

        library.cariBuku(keyword);
    }

 
    // PINJAM BUKU


    public static void pinjamBuku(
            Scanner scanner,
            LibraryService library) {

        System.out.println("\n========== PEMINJAMAN ==========");

        System.out.print("ID Anggota : ");
        String idMember = scanner.nextLine();

        System.out.print("Judul Buku : ");
        String judul = scanner.nextLine();

        try {

            library.pinjamBuku(
                    idMember,
                    judul
            );

        } catch (BookNotFoundException e) {

            System.out.println(
                    "ERROR: " + e.getMessage()
            );

        } catch (BookAlreadyBorrowedException e) {

            System.out.println(
                    "ERROR: " + e.getMessage()
            );

        } catch (BorrowLimitExceededException e) {

            System.out.println(
                    "ERROR: " + e.getMessage()
            );
        }
    }

       // PENGEMBALIAN
 

    public static void kembalikanBuku(
            Scanner scanner,
            LibraryService library) {

        System.out.println("\n========== PENGEMBALIAN ==========");

        System.out.print("ID Anggota : ");
        String idMember = scanner.nextLine();

        System.out.print("Judul Buku : ");
        String judul = scanner.nextLine();

        try {

            library.kembalikanBuku(
                    idMember,
                    judul
            );

        } catch (BookNotFoundException e) {

            System.out.println(
                    "ERROR: " + e.getMessage()
            );
        }
    }

    // TAMBAH MEMBER
 
    public static void tambahMember(
            Scanner scanner,
            LibraryService library) {

        System.out.println("\n========== TAMBAH ANGGOTA ==========");

        System.out.print("ID Anggota : ");
        String id = scanner.nextLine();

        System.out.print("Nama       : ");
        String nama = scanner.nextLine();

        // Validasi sederhana menggunakan Character
        if (!Character.isLetter(nama.charAt(0))) {

            System.out.println(
                    "Nama harus diawali dengan huruf."
            );

            return;
        }

        Member member =
                new Member(id, nama);

        library.tambahMember(member);

        System.out.println(
                "Anggota berhasil ditambahkan."
        );
    }

    // TAMPILKAN MEMBER

    public static void tampilkanMember(
            Scanner scanner,
            LibraryService library) {

        System.out.println(
                "\nFitur daftar seluruh anggota "
                        + "dapat dikembangkan melalui service."
        );

        System.out.println(
                "Untuk melihat aktivitas anggota, "
                        + "gunakan menu Laporan Perpustakaan."
        );
    }
}