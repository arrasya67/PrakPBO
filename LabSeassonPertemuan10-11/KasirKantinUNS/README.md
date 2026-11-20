# 🍽️ Sistem Kasir Kantin UNS

> **Tugas Praktikum Pemrograman Berorientasi Objek (PBO)**
> Pertemuan 11 (Multithreading) & Pertemuan 12 (GUI Programming)
> Fakultas Teknologi Informasi dan Sains Data Universitas Sebelas Maret (UNS)

Aplikasi desktop berbasis **Java Swing** ini dirancang untuk mensimulasikan sistem kasir pada Kantin UNS. Aplikasi ini mengintegrasikan logika perhitungan diskon, antarmuka grafis (GUI) yang interaktif, serta penerapan *Multithreading* agar aplikasi tetap responsif saat melakukan proses yang memakan waktu (simulasi cetak struk).

---

## 👨‍🎓 Identitas Mahasiswa
*   **Nama**  : [Isi Nama Lengkap Kamu]
*   **NIM**   : [Isi NIM Kamu]
*   **Kelas** : 3B
*   **Mata Kuliah** : Pemrograman Berorientasi Objek
*   **Institusi** : Universitas Sebelas Maret

---

## ✨ Fitur Utama
1.  **Manajemen Pesanan (GUI):** Input nama menu, harga, dan jumlah dengan antarmuka yang *user-friendly*.
2.  **Perhitungan Diskon Otomatis:** Sistem secara cerdas mendeteksi jika subtotal belanja melebihi **Rp 50.000**, maka akan otomatis memberikan diskon sebesar **10%**.
3.  **Validasi Input Ketat:** Mencegah *crash* (error) jika pengguna memasukkan huruf pada kolom angka, atau membiarkan kolom kosong. Peringatan akan muncul menggunakan `JOptionPane`.
4.  **Simulasi Cetak Struk (Non-Blocking):** Proses cetak struk disimulasikan selama 2 detik. Berkat *Multithreading*, antarmuka GUI **tidak akan macet** (freeze) dan pengguna tetap bisa mengklik tombol lain selama proses berjalan.
5.  **Simulasi Antrean 2 Kasir:** Demonstrasi *concurrent execution* menggunakan `Thread` di mana dua kasir (Dina dan Eko) melayani pelanggan secara bersamaan.

---

## 🧠 Penerapan Konsep PBO (OOP)
Proyek ini bukan sekadar aplikasi GUI biasa, melainkan implementasi nyata dari materi PBO:
*   **Enkapsulasi:** Atribut pada class `Pesanan` (seperti `subtotal`) dibuat `private` dan hanya bisa diakses/diubah melalui *method* publik (`tambahItem`, `getSubtotal`, `reset`).
*   **Inheritance (Pewarisan):** Class `Kasir` menggunakan konsep pewarisan (`extends Thread`) untuk menjadi sebuah *thread* yang bisa dijalankan.
*   **Polymorphism (Override):** Method `run()` pada class `Kasir` di-*override* untuk mendefinisikan tugas spesifik yang akan dijalankan oleh *thread* tersebut.
*   **Event-Driven Programming:** Penggunaan `ActionListener` (Lambda) pada tombol untuk merespons aksi dari pengguna (klik).

---

## 📂 Struktur Direktori & File
Semua *class* berada di dalam satu *package*: `lab.kasir`.

```text
📦 Project-Kasir-Kantin
 ┣ 📂 lab
 ┃ ┗ 📂 kasir
 ┃   ┣ 📜 Pesanan.java       # Class model/logika bisnis (menghitung total & diskon)
 ┃   ┣ 📜 Kasir.java         # Class untuk simulasi thread (2 kasir)
 ┃   ┣ 📜 KasirFrame.java    # Class utama untuk membangun komponen GUI Swing
 ┃   ┣ 📜 MainGUI.java       # Entry point untuk menjalankan aplikasi GUI
 ┃   ┗ 📜 MainKasir.java     # Entry point untuk pengujian logika & thread di console
 ┗ 📜 README.md
```
## 🧪 Dokumentasi Pengujian (Percobaan Mandiri)

Berikut adalah hasil pengujian aplikasi menggunakan data pesanan riil untuk membuktikan bahwa logika perhitungan (OOP) dan penerapan diskon berjalan dengan benar.

### Skenario Pengujian:
Kasir memasukkan 4 jenis pesanan dengan rincian sebagai berikut:
1. **Ayam Goreng** (Harga satuan: Rp 10.000) x 2 porsi = Rp 20.000
2. **Sop Ayam** (Harga satuan: Rp 15.000) x 3 porsi = Rp 45.000
3. **Ikan** (Harga satuan: Rp 10.000) x 2 porsi = Rp 20.000
4. **Ikan Bakar** (Harga satuan: Rp 10.000) x 1 porsi = Rp 10.000

### Hasil Perhitungan Sistem:
*   **Subtotal Belanja:** Rp 20.000 + Rp 45.000 + Rp 20.000 + Rp 10.000 = **Rp 95.000**
*   **Penerapan Aturan:** Karena subtotal melebihi batas **Rp 50.000**, maka sistem secara otomatis menerapkan diskon sebesar **10%**.
*   **Nominal Diskon:** 10% x Rp 95.000 = **Rp 9.500**
*   **Total Bayar Akhir:** Rp 95.000 - Rp 9.500 = **Rp 85.500**

Aplikasi berhasil menampilkan rincian pesanan di `areaStruk` dan menghitung Total Bayar menjadi **Rp 85.500** saat tombol "Hitung Total" ditekan. Hal ini membuktikan bahwa **logika perhitungan diskon pada class `Pesanan` telah berjalan dengan sempurna**.

*(Lihat gambar di bawah ini untuk bukti visual)*

![Pengujian Kasir dengan Diskon](images/namafile_gambar_1.png)











