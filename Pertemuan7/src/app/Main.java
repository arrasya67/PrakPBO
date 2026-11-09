package app;

import model.Mahasiswa;
import model.NilaiInvalidException;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Collection untuk menyimpan objek mahasiswa
        ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();

        int jumlahMahasiswa = 0;
        int jumlahLulus = 0;
        int jumlahTidakLulus = 0;
        double totalNilai = 0;

        System.out.println("======================================");
        System.out.println("       SISTEM DATA NILAI MAHASISWA");
        System.out.println("======================================");

        // Input jumlah mahasiswa
        while (true) {
            try {
                System.out.print("Masukkan jumlah mahasiswa: ");
                jumlahMahasiswa = Integer.parseInt(input.nextLine());

                if (jumlahMahasiswa <= 0) {
                    System.out.println(
                        "Jumlah mahasiswa harus lebih dari 0!"
                    );
                    continue;
                }

                break;

            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat!");
            }
        }

        // Input data setiap mahasiswa
        for (int i = 1; i <= jumlahMahasiswa; i++) {

            System.out.println("\nData Mahasiswa ke-" + i);
            System.out.println("--------------------------------------");

            System.out.print("Nama : ");
            String nama = input.nextLine();

            // Input dan validasi NIM
            String nim;

            while (true) {
                System.out.print("NIM  : ");
                nim = input.nextLine();

                try {
                    Mahasiswa.validasiNim(nim);
                    break;

                } catch (AssertionError | IllegalArgumentException e) {
                    System.out.println("Kesalahan: " + e.getMessage());
                }
            }

            // Input dan validasi nilai
            while (true) {
                try {
                    System.out.print("Nilai: ");
                    double nilai = Double.parseDouble(input.nextLine());

                    // Membuat objek mahasiswa
                    Mahasiswa mahasiswa =
                        new Mahasiswa(nama, nim, nilai);

                    // Menambahkan objek ke ArrayList
                    daftarMahasiswa.add(mahasiswa);

                    System.out.println("Data berhasil ditambahkan!");
                    break;

                } catch (NumberFormatException e) {
                    System.out.println("Nilai harus berupa angka!");

                } catch (NilaiInvalidException e) {
                    System.out.println("Kesalahan: " + e.getMessage());
                }
            }
        }

        // Menghitung statistik menggunakan for-each
        for (Mahasiswa mahasiswa : daftarMahasiswa) {

            totalNilai += mahasiswa.getNilai();

            if (mahasiswa.isLulus()) {
                jumlahLulus++;
            } else {
                jumlahTidakLulus++;
            }
        }

        double rataRata = totalNilai / daftarMahasiswa.size();

        // Menampilkan data di konsol
        System.out.println("\n======================================");
        System.out.println("          REKAP DATA MAHASISWA");
        System.out.println("======================================");

        for (Mahasiswa mahasiswa : daftarMahasiswa) {

            System.out.println("Nama   : " + mahasiswa.getNama());
            System.out.println("NIM    : " + mahasiswa.getNim());
            System.out.println("Nilai  : " + mahasiswa.getNilai());
            System.out.println("Grade  : " + mahasiswa.getGrade());

            System.out.println("Status : "
                + (mahasiswa.isLulus() ? "Lulus" : "Tidak Lulus"));

            System.out.println("--------------------------------------");
        }

        System.out.println("Jumlah Mahasiswa : "
            + daftarMahasiswa.size());

        System.out.printf(Locale.US, "Rata-rata Nilai   : %.2f%n",
            rataRata);

        System.out.println("Jumlah Lulus      : " + jumlahLulus);
        System.out.println("Jumlah Tidak Lulus: " + jumlahTidakLulus);

        // Menyimpan laporan ke file
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("laporan_nilai.txt"))) {

            writer.write("======================================");
            writer.newLine();
            writer.write("          LAPORAN NILAI MAHASISWA");
            writer.newLine();
            writer.write("======================================");
            writer.newLine();

            for (Mahasiswa mahasiswa : daftarMahasiswa) {

                writer.write("Nama   : " + mahasiswa.getNama());
                writer.newLine();

                writer.write("NIM    : " + mahasiswa.getNim());
                writer.newLine();

                writer.write("Nilai  : "
                    + String.format(Locale.US, "%.2f",
                        mahasiswa.getNilai()));
                writer.newLine();

                writer.write("Grade  : " + mahasiswa.getGrade());
                writer.newLine();

                writer.write("Status : "
                    + (mahasiswa.isLulus()
                        ? "Lulus" : "Tidak Lulus"));

                writer.newLine();
                writer.write("--------------------------------------");
                writer.newLine();
            }

            // Menulis statistik kelas
            writer.newLine();
            writer.write("============= STATISTIK =============");
            writer.newLine();

            writer.write("Jumlah Mahasiswa : "
                + daftarMahasiswa.size());
            writer.newLine();

            writer.write("Rata-rata Nilai  : "
                + String.format(Locale.US, "%.2f", rataRata));
            writer.newLine();

            writer.write("Jumlah Lulus     : " + jumlahLulus);
            writer.newLine();

            writer.write("Jumlah Tidak Lulus: " + jumlahTidakLulus);
            writer.newLine();

            System.out.println(
                "\nLaporan berhasil disimpan ke laporan_nilai.txt"
            );

        } catch (IOException e) {
            System.out.println(
                "Gagal menyimpan laporan: " + e.getMessage()
            );
        }

        input.close();

        System.out.println("\nProgram selesai.");
    }
}
