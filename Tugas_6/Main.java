
import java.util.Scanner;
import java.util.Locale;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        input.useLocale(Locale.US);

        int jumlahSiswa;
        int jumlahLulus = 0;
        int jumlahTidakLulus = 0;
        double totalNilai = 0;

        System.out.println("==================================");
        System.out.println("     PROGRAM DATA NILAI SISWA");
        System.out.println("==================================");

        System.out.print("Masukkan jumlah siswa: ");
        jumlahSiswa = input.nextInt();
        input.nextLine();

      
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("laporan_nilai.txt"))) {

            writer.write("==================================");
            writer.newLine();
            writer.write("        LAPORAN NILAI SISWA");
            writer.newLine();
            writer.write("==================================");
            writer.newLine();

           
            for (int i = 1; i <= jumlahSiswa; i++) {

                System.out.println("\nData Siswa ke-" + i);

                System.out.print("Nama siswa : ");
                String nama = input.nextLine();

                System.out.print("Nilai siswa: ");
                double nilai = input.nextDouble();
                input.nextLine();

               
                String status;

                if (nilai >= 70) {
                    status = "Lulus";
                    jumlahLulus++;
                } else {
                    status = "Tidak Lulus";
                    jumlahTidakLulus++;
                }

                
                totalNilai += nilai;

                writer.write("Nama   : " + nama);
                writer.newLine();

                writer.write("Nilai  : " + nilai);
                writer.newLine();

                writer.write("Status : " + status);
                writer.newLine();

                writer.write("----------------------------------");
                writer.newLine();
            }

          
            double rataRata = totalNilai / jumlahSiswa;

           
            writer.newLine();
            writer.write("========== RINGKASAN ==========");
            writer.newLine();

            writer.write("Rata-rata nilai : "
                    + String.format(Locale.US, "%.2f", rataRata));
            writer.newLine();

            writer.write("Jumlah Lulus    : " + jumlahLulus);
            writer.newLine();

            writer.write("Jumlah Tidak Lulus : " + jumlahTidakLulus);
            writer.newLine();

            System.out.println("\nData berhasil disimpan ke file.");

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menulis file: "
                    + e.getMessage());
            input.close();
            return;
        }

       
        System.out.println("\n==================================");
        System.out.println("       ISI LAPORAN NILAI");
        System.out.println("==================================");

        try (BufferedReader reader = new BufferedReader(
                new FileReader("laporan_nilai.txt"))) {

            String baris;

           
            while ((baris = reader.readLine()) != null) {
                System.out.println(baris);
            }

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: "
                    + e.getMessage());
        }

        input.close();

        System.out.println("\n==================================");
        System.out.println("          PROGRAM SELESAI");
        System.out.println("==================================");
    }
}
