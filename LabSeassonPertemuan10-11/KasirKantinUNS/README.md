# Kasir Kantin UNS – Praktikum PBO Pertemuan 11 dan 12

Project ini mengikuti PDF `LAB SESSION P11-12(1).pdf` dan dikerjakan sebagai satu aplikasi bertahap Tahap 1–4.

## Struktur
```text
KasirKantinUNS/
└── src/lab/kasir/
    ├── Pesanan.java
    ├── Kasir.java
    ├── MainKasir.java
    ├── KasirFrame.java
    └── MainGUI.java
```
Semua class berada di package `lab.kasir`; atribut class menggunakan modifier `private`.

## Kompilasi (JDK 8+)
Dari folder project:
```bash
javac -d out src/lab/kasir/*.java
```

## Menjalankan Tahap 1 dan 2
```bash
java -cp out lab.kasir.MainKasir
```
Program menguji total setelah dua menu (Rp45.000), subtotal setelah tiga menu (Rp70.000), total setelah diskon (Rp63.000), kemudian menjalankan thread Kasir Dina dan Eko. Jalankan dua kali untuk membandingkan urutan output. Kalimat `Semua pelanggan selesai dilayani` harus muncul paling akhir karena main menunggu kedua thread dengan `join()`.

## Menjalankan Tahap 3 dan 4
```bash
java -cp out lab.kasir.MainGUI
```
Masukkan nama menu, harga, dan jumlah. Tambah tiga menu sesuai data uji PDF. Klik Hitung Total untuk melihat Rp63.000. Klik Cetak Struk untuk menguji status. Selama jeda dua detik, thread Event Dispatch Thread tidak ditahan sehingga tombol Tambah dan Hitung Total tetap responsif.

## Data uji dari PDF
| Menu | Harga | Jumlah | Subtotal |
|---|---:|---:|---:|
| Nasi Goreng | 15.000 | 2 | 30.000 |
| Es Teh | 5.000 | 3 | 15.000 |
| Ayam Bakar | 25.000 | 1 | 25.000 |

Dua menu pertama: Rp45.000 tanpa diskon. Tiga menu: subtotal Rp70.000, diskon 10% Rp7.000, total Rp63.000.

## Catatan pengujian laporan
- Jalankan `MainKasir` dua kali dan ambil screenshot masing-masing output.
- Urutan Dina/Eko dapat berubah karena scheduler; jangan menganggap satu urutan tetap.
- Screenshot GUI setelah ketiga menu ditambahkan dan total dihitung.
- Screenshot status `Mencetak struk...` dan `Struk selesai`.
- Uji field kosong dan input bukan angka untuk membuktikan validasi.
