package model;

public class Mahasiswa {

    // Konstanta standar kelulusan
    public static final double FINAL_PASSING_GRADE = 70.0;

    // Atribut mahasiswa
    private String nama;
    private String nim;
    private double nilai;

    // Constructor
    public Mahasiswa(String nama, String nim, double nilai)
            throws NilaiInvalidException {

        this.nama = nama.trim().toUpperCase();

        // Validasi NIM
        validasiNim(nim);
        this.nim = nim.trim().toUpperCase();

        // Validasi nilai
        if (!Double.isFinite(nilai) || nilai < 0 || nilai > 100) {
            throw new NilaiInvalidException(
                "Nilai harus berada antara 0 sampai 100!"
            );
        }

        this.nilai = nilai;
    }

    // Validasi NIM menggunakan Assertion dan Character
    public static void validasiNim(String nim) {

        String nimBersih = nim.trim();

        // Assertion: NIM tidak boleh kosong
        assert !nimBersih.isEmpty() : "NIM tidak boleh kosong!";

        // Validasi tambahan agar tetap berjalan
        // meskipun Assertion tidak diaktifkan
        if (nimBersih.isEmpty()) {
            throw new IllegalArgumentException(
                "NIM tidak boleh kosong!"
            );
        }

        // Memastikan seluruh karakter NIM berupa angka
        for (int i = 0; i < nimBersih.length(); i++) {
            if (!Character.isDigit(nimBersih.charAt(i))) {
                throw new IllegalArgumentException(
                    "NIM hanya boleh berisi angka!"
                );
            }
        }
    }

    // Getter
    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public double getNilai() {
        return nilai;
    }

    // Menentukan grade berdasarkan nilai
    public String getGrade() {

        if (nilai >= 85) {
            return "A";
        } else if (nilai >= 75) {
            return "B";
        } else if (nilai >= FINAL_PASSING_GRADE) {
            return "C";
        } else {
            return "D";
        }
    }

    // Menentukan status kelulusan
    public boolean isLulus() {
        return nilai >= FINAL_PASSING_GRADE;
    }
}
