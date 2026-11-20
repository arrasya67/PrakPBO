# Laporan Praktikum PBO – Kasir Kantin UNS

Nama: ........................................  
NIM: ........................................  
Kelas: 3B

## Tahap 1 – Class Logika
Class `Pesanan` memiliki atribut `private double subtotal`, method `tambahItem`, `getSubtotal`, `hitungTotal`, dan `reset`.

Hasil pengujian:
- Setelah Nasi Goreng dan Es Teh: Rp45.000 (tanpa diskon).
- Setelah Ayam Bakar: subtotal Rp70.000, diskon 10% Rp7.000, total Rp63.000.

Lampirkan screenshot output pengujian.

## Tahap 2 – Dua Kasir sebagai Thread
Lampirkan dua screenshot hasil menjalankan `MainKasir`.

| Pengamatan | Eksekusi 1 | Eksekusi 2 |
|---|---|---|
| Urutan output Dina dan Eko | ... | ... |
| Pesan selesai tampil paling akhir? | ... | ... |

Penjelasan: `start()` memulai thread baru sehingga kedua kasir dapat berjalan bersamaan. `join()` membuat thread main menunggu kedua kasir selesai sebelum mencetak pesan akhir. Urutan output tidak dijamin selalu sama karena penjadwalan thread.

## Tahap 3 – Tampilan GUI Kasir
Lampirkan screenshot GUI setelah ketiga item dimasukkan dan tombol Hitung Total ditekan. Hasil yang diharapkan Rp63.000.

Validasi yang diuji:
1. Field kosong: ........................................
2. Harga bukan angka: ........................................
3. Jumlah bukan angka/bilangan desimal: ........................................

## Tahap 4 – Tombol Cetak Struk dengan Thread
Lampirkan screenshot saat status `Mencetak struk...` dan setelah status berubah menjadi `Struk selesai`.

Jelaskan hasil uji: selama proses tunggu dua detik, tombol Tambah dan Hitung Total tetap dapat digunakan karena `Thread.sleep(2000)` dijalankan pada thread baru, bukan pada Event Dispatch Thread.

## Kesimpulan
Tuliskan kesimpulan berdasarkan hasil eksekusi dan screenshot yang benar-benar diperoleh.
