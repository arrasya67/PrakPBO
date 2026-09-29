# Dokumentasi Tugas 4 — Sistem Perpustakaan

> Dokumentasi ini dibuat berdasarkan analisis langsung terhadap seluruh source code pada folder `Tugas4` repository [`arrasya67/PrakPBO`](https://github.com/arrasya67/PrakPBO/tree/main/Tugas4).

## Daftar Isi

1. [Identitas Mahasiswa](#1-identitas-mahasiswa)
2. [Latar Belakang dan Deskripsi Sistem](#2-latar-belakang-dan-deskripsi-sistem)
3. [Tujuan Pembelajaran dan Ruang Lingkup](#3-tujuan-pembelajaran-dan-ruang-lingkup)
4. [Arsitektur Proyek dan Struktur Package](#4-arsitektur-proyek-dan-struktur-package)
5. [Pemetaan Konsep OOP dan Fitur Bahasa Java](#5-pemetaan-konsep-oop-dan-fitur-bahasa-java)
6. [Bedah Kode Sumber dan Penjelasan Sintaks Detail](#6-bedah-kode-sumber-dan-penjelasan-sintaks-detail)
   - [6.1 Package `library.model`](#61-package-librarymodel)
   - [6.2 Package `library.exception`](#62-package-libraryexception)
   - [6.3 Package `library.service`](#63-package-libraryservice)
   - [6.4 Package `library.main`](#64-package-librarymain)
7. [Alur Bisnis dan Mekanisme Validasi](#7-alur-bisnis-dan-mekanisme-validasi-flow-logic)
8. [Panduan Kompilasi dan Eksekusi Program](#8-panduan-kompilasi-dan-eksekusi-program)
9. [Analisis Hasil Pengujian dan Output Sistem](#9-analisis-hasil-pengujian-dan-output-sistem)
10. [Kesimpulan](#10-kesimpulan)

---

## 1. Identitas Mahasiswa

| Keterangan | Isi |
|---|---|
| Nama | **Dwi Agus Maulana** |
| NIM | **L0325022** |
| Kelas | **B** |
| Mata Kuliah | Pemrograman Berorientasi Objek |
| Tugas | Tugas 4 — Sistem Perpustakaan Mini |
| Bahasa Pemrograman | Java |
| Repository | `arrasya67/PrakPBO` |
| Folder | `Tugas4` |



---

## 2. Latar Belakang dan Deskripsi Sistem

### 2.1 Latar Belakang

Pengelolaan perpustakaan membutuhkan pencatatan buku, anggota, status ketersediaan, transaksi peminjaman, transaksi pengembalian, dan statistik aktivitas. Jika seluruh proses dilakukan secara manual, petugas dapat mengalami kesulitan ketika harus mencari buku, memastikan buku tidak dipinjam oleh dua orang sekaligus, menghitung batas pinjaman anggota, atau menyusun laporan peminjaman.

Program pada folder `Tugas4` merupakan aplikasi konsol **Sistem Perpustakaan** yang dibuat menggunakan Java dan menerapkan konsep pemrograman berorientasi objek. Program memodelkan buku serta anggota sebagai objek, memusatkan aturan bisnis di dalam service, memisahkan exception khusus, dan menyediakan menu interaktif melalui terminal.

### 2.2 Deskripsi Sistem

Sistem memiliki empat lapisan package utama:

- `library.model` — menyimpan objek data `Book` dan `Member`.
- `library.exception` — menyimpan exception khusus untuk kondisi bisnis yang tidak valid.
- `library.service` — menyimpan koleksi data serta seluruh proses pengelolaan perpustakaan.
- `library.main` — menjadi titik masuk aplikasi dan menangani interaksi dengan pengguna.

Pada saat program dijalankan, sistem mengisi lima buku awal dan dua anggota awal. Setelah itu pengguna dapat memilih menu untuk:

1. Menambahkan buku.
2. Menampilkan seluruh buku.
3. Mencari buku berdasarkan judul atau kategori.
4. Meminjam buku.
5. Mengembalikan buku.
6. Menampilkan laporan perpustakaan.
7. Menambahkan anggota.
8. Membuka menu informasi anggota.
0. Keluar dari program.

Data disimpan di memori menggunakan `ArrayList` dan `HashMap`, sehingga data tidak persisten setelah program dihentikan. Tidak terdapat database atau file penyimpanan eksternal.

---

## 3. Tujuan Pembelajaran dan Ruang Lingkup

### 3.1 Tujuan Pembelajaran

Implementasi ini dapat digunakan untuk mempelajari:

- pembuatan class, object, constructor, attribute, dan method;
- penggunaan access modifier `private` dan method accessor;
- enkapsulasi data pada class model;
- penggunaan package dan `import`;
- relasi antarkelas, khususnya `Member` yang memiliki daftar `Book`;
- penggunaan koleksi `ArrayList`, `HashMap`, `Map`, dan `Map.Entry`;
- pencarian dan pemrosesan data menggunakan perulangan;
- pengambilan keputusan dengan `if`, `else`, `switch`, dan operator logika;
- penanganan kesalahan menggunakan checked exception;
- penggunaan `try-catch`, assertion, `String`, dan `Character`;
- pemisahan antarmuka pengguna, aturan bisnis, model data, dan exception;
- pembuatan laporan agregat dari data transaksi.

### 3.2 Ruang Lingkup Fungsional

Ruang lingkup program meliputi:

- menyimpan data buku: judul, penulis, tahun terbit, kategori, dan status;
- menyimpan data anggota: ID, nama, serta daftar buku yang sedang dipinjam;
- menambahkan buku dan anggota baru;
- menampilkan daftar buku beserta status ketersediaannya;
- mencari buku secara *case-insensitive* berdasarkan judul atau kategori;
- memproses peminjaman dengan batas maksimal tiga buku per anggota;
- menolak peminjaman buku yang sedang dipinjam;
- memproses pengembalian buku;
- menghitung total peminjaman, buku terpopuler, anggota paling aktif, kategori terpopuler, dan jumlah buku per kategori.

### 3.3 Batasan Sistem

Beberapa batasan berdasarkan source code saat ini:

- data hanya berada di memori;
- tidak ada autentikasi atau hak akses pengguna;
- tidak ada validasi duplikasi ID anggota atau judul buku;
- menu nomor 8 belum menampilkan seluruh anggota, melainkan hanya memberikan informasi bahwa fitur tersebut dapat dikembangkan melalui service;
- pengembalian tidak mengurangi statistik total peminjaman karena statistik dirancang sebagai jumlah historis transaksi peminjaman;
- input string kosong belum divalidasi secara menyeluruh;
- format tahun hanya divalidasi sebagai angka, bukan rentang tahun yang logis.

---

## 4. Arsitektur Proyek dan Struktur Package

### 4.1 Struktur Folder

```text
Tugas4/
└── library/
    ├── exception/
    │   ├── BookAlreadyBorrowedException.java
    │   ├── BookNotFoundException.java
    │   └── BorrowLimitExceededException.java
    ├── main/
    │   └── Main.java
    ├── model/
    │   ├── Book.java
    │   └── Member.java
    └── service/
        └── LibraryService.java
```

### 4.2 Tanggung Jawab Tiap Package

#### `library.model`

Package ini berisi representasi objek dunia nyata. `Book` mewakili buku, sedangkan `Member` mewakili anggota. Keduanya menyimpan state dan menyediakan method untuk membaca atau mengubah state yang diizinkan.

#### `library.exception`

Package ini berisi jenis kesalahan bisnis yang sengaja dibuat spesifik. Dengan demikian, layer pemanggil dapat membedakan buku tidak ditemukan, buku sedang dipinjam, dan batas pinjaman terlampaui.

#### `library.service`

`LibraryService` berfungsi sebagai pusat operasi sistem. Class ini mengelola koleksi buku, anggota, statistik peminjaman, validasi transaksi, dan pembentukan laporan.

#### `library.main`

`Main` merupakan lapisan antarmuka konsol. Class ini membuat `Scanner`, membangun service, memuat data awal, menampilkan menu, membaca input, memanggil service, dan menampilkan pesan kepada pengguna.

### 4.3 Pola Aliran Data

```text
Pengguna
   │ input melalui Scanner
   ▼
library.main.Main
   │ memanggil operasi
   ▼
library.service.LibraryService
   │ mengelola dan memvalidasi
   ├── library.model.Book
   ├── library.model.Member
   └── library.exception.*
```

Pemisahan ini membuat `Main` tidak perlu mengetahui detail cara data disimpan, sedangkan `LibraryService` tidak perlu mengurus detail menu dan pembacaan input.

---

## 5. Pemetaan Konsep OOP dan Fitur Bahasa Java

### 5.1 Class dan Object

Class `Book`, `Member`, dan `LibraryService` adalah cetak biru. Object dibuat menggunakan keyword `new`, misalnya `new Book(...)` dan `new Member(...)`. Setiap object memiliki state sendiri.

### 5.2 Enkapsulasi

Attribute pada `Book`, `Member`, dan `LibraryService` dideklarasikan `private`. Data dibaca melalui getter seperti `getJudul()` dan `getNama()`. Status ketersediaan hanya diubah melalui `setStatusKetersediaan`, sedangkan daftar pinjaman dimanipulasi melalui `tambahPinjaman` dan `hapusPinjaman`.

Enkapsulasi mencegah class lain mengakses attribute secara sembarangan dan menyediakan titik kontrol untuk perubahan state.

### 5.3 Abstraksi

`LibraryService` menyembunyikan detail pencarian, perubahan status, pembaruan statistik, dan validasi. Pemanggil cukup menjalankan `pinjamBuku(idMember, judul)` tanpa mengelola sendiri isi `ArrayList` atau `HashMap`.

### 5.4 Relasi dan Komposisi

`Member` memiliki `ArrayList<Book>` bernama `daftarPinjaman`. Ini menunjukkan relasi bahwa seorang anggota dapat memiliki banyak buku yang sedang dipinjam. Daftar tersebut dibuat saat constructor `Member` berjalan.

### 5.5 Pewarisan

Tiga exception mewarisi class `Exception` menggunakan `extends`:

```java
public class BookNotFoundException extends Exception
```

Pewarisan ini memungkinkan exception buatan sendiri diperlakukan sebagai exception Java, tetapi tetap memiliki makna bisnis yang spesifik.

### 5.6 Polimorfisme dan Overriding

`Book` melakukan overriding terhadap method `toString()` dari class `Object` menggunakan anotasi `@Override`. Hasilnya, object buku dapat direpresentasikan sebagai `judul - penulis (tahun)`.

### 5.7 Constructor

Constructor digunakan untuk memastikan object memiliki data awal. `Book` langsung berstatus tersedia, sedangkan `Member` langsung memiliki daftar pinjaman kosong.

### 5.8 Koleksi Java

- `ArrayList<Book>` menyimpan urutan koleksi buku.
- `ArrayList<Member>` menyimpan anggota.
- `HashMap<String, Integer>` menyimpan statistik berdasarkan judul buku atau ID anggota.
- `Map.Entry<String, Integer>` digunakan untuk melakukan iterasi pasangan key-value.

### 5.9 Exception Handling

Method peminjaman mendeklarasikan tiga checked exception melalui `throws`. `Main` menangkapnya dengan beberapa blok `catch` sehingga pesan kesalahan dapat ditampilkan tanpa menghentikan program.

### 5.10 Fitur `String` dan `Character`

Program menggunakan:

- `toLowerCase()` untuk pencarian tanpa membedakan huruf besar-kecil;
- `contains()` untuk pencarian sebagian kata;
- `equalsIgnoreCase()` untuk mencocokkan ID dan judul secara case-insensitive;
- `charAt(0)` untuk mengambil karakter pertama judul atau nama;
- `Character.toUpperCase()` untuk mengubah karakter awal judul;
- `Character.isLetter()` untuk memastikan nama diawali huruf.

### 5.11 Assertion

Pada proses pinjam dan kembali terdapat:

```java
assert member != null : "Data anggota tidak boleh null.";
```

Assertion merupakan pemeriksaan internal yang aktif jika program dijalankan dengan opsi `-ea`. Karena masih ada pemeriksaan `if (member == null)` setelahnya, program juga memiliki jalur penanganan biasa ketika assertion tidak diaktifkan.

---

## 6. Bedah Kode Sumber dan Penjelasan Sintaks Detail

### 6.1 Package `library.model`

#### 6.1.1 `Book.java`

File ini mendefinisikan data dan perilaku sebuah buku.

Attribute yang digunakan:

- `String judul` — judul buku;
- `String penulis` — nama penulis;
- `String kategori` — kategori buku;
- `int tahunTerbit` — tahun terbit;
- `boolean statusKetersediaan` — `true` jika tersedia dan `false` jika sedang dipinjam.

Semua attribute bersifat `private`, sehingga mengikuti prinsip enkapsulasi. Constructor menerima empat data utama dan menetapkan status awal menjadi `true`:

```java
this.statusKetersediaan = true;
```

Keyword `this` menunjuk attribute milik object saat nama parameter sama dengan nama attribute.

Getter menyediakan akses baca. Method `isStatusKetersediaan()` menggunakan pola penamaan `is` karena nilai yang dikembalikan bertipe boolean. Setter status hanya digunakan untuk mengubah ketersediaan ketika proses peminjaman atau pengembalian terjadi.

Method `tampilkanInfo()` mencetak detail buku dan menggunakan operator ternary:

```java
statusKetersediaan ? "Tersedia" : "Dipinjam"
```

Jika kondisi bernilai `true`, teks pertama dipilih; jika `false`, teks kedua dipilih. Method `toString()` menghasilkan representasi ringkas buku dan meng-override implementasi dari `Object`.

#### 6.1.2 `Member.java`

Class `Member` menyimpan:

- `id` sebagai identitas anggota;
- `nama` sebagai nama anggota;
- `daftarPinjaman` sebagai `ArrayList<Book>`.

Pada constructor, daftar pinjaman dibuat dengan:

```java
this.daftarPinjaman = new ArrayList<>();
```

Sintaks `<>` disebut diamond operator; compiler menyimpulkan tipe generic dari deklarasi di sebelah kiri.

` tambahPinjaman(Book book)` menambahkan buku dengan `add`, sedangkan `hapusPinjaman(Book book)` menghapus buku dengan `remove`. `jumlahPinjaman()` mengembalikan ukuran list melalui `size()` dan digunakan sebagai dasar pemeriksaan batas maksimal tiga buku.

Method `tampilkanInfo()` memakai `isEmpty()` untuk membedakan anggota tanpa pinjaman dan anggota yang mempunyai pinjaman. Perulangan *enhanced for*:

```java
for (Book book : daftarPinjaman)
```

membaca setiap object `Book` tanpa mengelola index secara manual.

---

### 6.2 Package `library.exception`

Ketiga class exception memiliki pola yang sama: mewarisi `Exception` dan menyediakan constructor yang meneruskan pesan ke parent melalui `super(message)`.

#### 6.2.1 `BookNotFoundException`

Dipakai ketika judul buku yang diminta tidak ada pada `daftarBuku`. Exception dilempar dari method private `cariBukuByJudul`.

#### 6.2.2 `BookAlreadyBorrowedException`

Dipakai ketika status buku adalah `false`, yang berarti buku sedang dipinjam. Exception mencegah transaksi peminjaman kedua terhadap object buku yang sama.

#### 6.2.3 `BorrowLimitExceededException`

Dipakai jika jumlah pinjaman anggota sudah mencapai tiga buku. Batas ini diperiksa sebelum buku dicari dan sebelum status buku diubah.

Ketiganya merupakan checked exception karena langsung atau tidak langsung merupakan turunan `Exception` dan bukan `RuntimeException`. Oleh sebab itu, method yang dapat melemparnya perlu menuliskan `throws`, dan pemanggil perlu menangkap atau meneruskannya.

---

### 6.3 Package `library.service`

`LibraryService` adalah inti aplikasi.

#### 6.3.1 Struktur Data Internal

```java
private ArrayList<Book> daftarBuku;
private ArrayList<Member> daftarMember;
private HashMap<String, Integer> statistikPeminjaman;
private HashMap<String, Integer> aktivitasMember;
```

`daftarBuku` dan `daftarMember` menyimpan object utama. `statistikPeminjaman` memakai judul buku sebagai key dan total jumlah peminjaman sebagai value. `aktivitasMember` memakai ID anggota sebagai key dan jumlah transaksi peminjaman sebagai value.

Constructor menginisialisasi semua collection agar method lain dapat langsung menggunakannya.

#### 6.3.2 Manajemen Buku

`tambahBuku(Book book)` menambahkan buku ke list dan membuat statistik awal dengan nilai nol. `tampilkanSemuaBuku()` memeriksa list kosong, kemudian memanggil `tampilkanInfo()` untuk setiap buku.

#### 6.3.3 Pencarian Buku

`cariBuku(String keyword)` mengubah keyword menjadi huruf kecil. Judul dan kategori setiap buku juga diubah menjadi huruf kecil, lalu dibandingkan memakai `contains`. Dengan demikian pencarian `java`, `Java`, dan `JAVA` menghasilkan perilaku yang sama.

Method private `cariBukuByJudul` menggunakan `equalsIgnoreCase` untuk pencocokan judul penuh. Jika loop selesai tanpa hasil, method melempar `BookNotFoundException` beserta pesan yang menyebutkan judul yang dicari.

#### 6.3.4 Manajemen Anggota

`tambahMember(Member member)` memasukkan member ke list dan membuat nilai aktivitas awal nol. `cariMember` membandingkan ID dengan `equalsIgnoreCase`, lalu mengembalikan member pertama yang sesuai atau `null` jika tidak ditemukan.

#### 6.3.5 Proses Peminjaman

Urutan `pinjamBuku` adalah:

1. Mencari anggota berdasarkan ID.
2. Memastikan referensi anggota valid melalui assertion dan pemeriksaan `null`.
3. Memeriksa apakah jumlah pinjaman sudah `>= 3`.
4. Mencari buku berdasarkan judul.
5. Memeriksa status ketersediaan buku.
6. Mengubah status buku menjadi tidak tersedia.
7. Menambahkan buku ke daftar pinjaman anggota.
8. Menambah statistik buku.
9. Menambah aktivitas anggota.
10. Menampilkan pesan sukses.

Pembaruan dilakukan setelah semua validasi lolos. Hal ini penting agar data tidak berubah ketika transaksi gagal.

#### 6.3.6 Proses Pengembalian

`kembalikanBuku` mencari anggota dan buku, kemudian memeriksa apakah daftar pinjaman anggota mengandung object buku melalui `contains`. Jika tidak ada, method memberikan pesan dan berhenti. Jika ada, buku dihapus dari daftar member dan statusnya dikembalikan menjadi `true`.

Statistik peminjaman tidak dikurangi karena map tersebut berfungsi sebagai histori jumlah transaksi peminjaman, bukan jumlah buku yang sedang dipinjam.

#### 6.3.7 Analisis dan Laporan

- `analisisKategori()` menghitung jumlah buku yang tersedia pada setiap kategori.
- `bukuPalingSeringDipinjam()` mencari value statistik terbesar.
- `anggotaPalingAktif()` mencari anggota dengan jumlah peminjaman tertinggi.
- `kategoriPalingPopuler()` menjumlahkan statistik peminjaman seluruh buku dalam kategori yang sama.
- `totalPeminjaman()` menjumlahkan seluruh value pada `statistikPeminjaman`.
- `tampilkanLaporan()` memanggil seluruh method analisis secara berurutan.

Jika terdapat nilai yang sama, implementasi hanya mempertahankan entry yang pertama kali membuat nilai lebih besar; program belum menampilkan daftar seri.

#### 6.3.8 Data Awal

`isiDataAwal()` memasukkan lima buku:

| Judul | Penulis | Tahun | Kategori |
|---|---|---:|---|
| Pemrograman Java | Budi Santoso | 2023 | Teknologi |
| Algoritma dan Struktur Data | Andi Wijaya | 2022 | Teknologi |
| Laskar Pelangi | Andrea Hirata | 2005 | Novel |
| Bumi | Tere Liye | 2014 | Novel |
| Sejarah Indonesia | Ahmad Fauzi | 2020 | Sejarah |

serta dua anggota:

| ID | Nama |
|---|---|
| M001 | Agus |
| M002 | Budi |

---

### 6.4 Package `library.main`

#### 6.4.1 Method `main`

Method `main` adalah titik masuk JVM. Program membuat `Scanner`, membuat `LibraryService`, memanggil `isiDataAwal()`, lalu masuk ke perulangan `do-while`.

Input menu dibaca menggunakan `scanner.nextLine()` kemudian dikonversi melalui `Integer.parseInt`. Jika input bukan angka, `NumberFormatException` ditangkap dan pilihan diubah menjadi `0`. Konsekuensinya, input menu yang bukan angka akan mengakhiri program karena kondisi perulangan berhenti ketika pilihan bernilai nol.

`switch` meneruskan pilihan ke method terkait. Setelah pilihan diproses, menu ditampilkan lagi sampai pengguna memilih `0`. `scanner.close()` dipanggil setelah loop berakhir.

#### 6.4.2 Method Menu

`tampilkanMenu()` hanya bertugas mencetak pilihan. Method ini dipisahkan dari `main` agar kode utama lebih mudah dibaca.

#### 6.4.3 Menambah Buku

`tambahBuku` membaca judul, penulis, tahun terbit, dan kategori. Tahun dikonversi ke `int`; jika gagal, method berhenti dengan pesan kesalahan. Program juga mengambil karakter pertama judul menggunakan `charAt(0)` dan mengubahnya menjadi huruf kapital.

Catatan: apabila judul kosong, `charAt(0)` akan menyebabkan `StringIndexOutOfBoundsException`, sehingga validasi string kosong sebaiknya ditambahkan pada pengembangan berikutnya.

#### 6.4.4 Mencari, Meminjam, dan Mengembalikan Buku

`cariBuku` meneruskan keyword ke service. `pinjamBuku` dan `kembalikanBuku` membaca ID serta judul, kemudian menggunakan `try-catch` untuk menangani exception khusus. Pesan exception ditampilkan dengan format `ERROR: ...`.

#### 6.4.5 Menambah Anggota

`tambahMember` membaca ID dan nama. Program memeriksa karakter pertama nama memakai `Character.isLetter`. Jika bukan huruf, input ditolak. Setelah validasi, object `Member` dibuat dan dikirim ke service.

Catatan: nama kosong juga dapat menyebabkan error pada `nama.charAt(0)`, sehingga sebaiknya diperiksa terlebih dahulu.

#### 6.4.6 Menu Data Anggota

Method `tampilkanMember` saat ini belum mengambil data dari `LibraryService`. Method hanya mencetak bahwa daftar anggota dapat dikembangkan melalui service dan menyarankan pengguna melihat laporan. Ini adalah bagian yang secara fungsional masih berupa placeholder.

---

## 7. Alur Bisnis dan Mekanisme Validasi (Flow Logic)

### 7.1 Alur Startup

```text
Mulai
  ↓
Buat Scanner
  ↓
Buat LibraryService
  ↓
Isi 5 buku dan 2 anggota awal
  ↓
Tampilkan menu
  ↓
Baca pilihan
```

### 7.2 Alur Peminjaman

```text
Input ID anggota dan judul buku
          ↓
Cari anggota
          ↓
Anggota ditemukan?
  Tidak → pesan "Anggota tidak ditemukan" → selesai
  Ya
          ↓
Jumlah pinjaman >= 3?
  Ya → BorrowLimitExceededException
  Tidak
          ↓
Cari buku berdasarkan judul
  Tidak ada → BookNotFoundException
  Ada
          ↓
Buku tersedia?
  Tidak → BookAlreadyBorrowedException
  Ya
          ↓
Status = dipinjam
Tambahkan buku ke member
Update statistik buku dan aktivitas member
Tampilkan pesan berhasil
```

### 7.3 Alur Pengembalian

```text
Input ID anggota dan judul buku
          ↓
Cari anggota dan buku
          ↓
Buku ada di daftar pinjaman anggota?
  Tidak → pesan buku tidak dipinjam anggota ini
  Ya
          ↓
Hapus dari daftar pinjaman
Ubah status buku menjadi tersedia
Tampilkan pesan berhasil
```

### 7.4 Tabel Validasi

| Kondisi | Mekanisme | Hasil |
|---|---|---|
| Pilihan menu bukan angka | `try-catch NumberFormatException` | Pesan input harus angka; pilihan diubah menjadi 0 |
| Tahun terbit bukan angka | `try-catch NumberFormatException` | Buku tidak jadi ditambahkan |
| Nama diawali bukan huruf | `Character.isLetter` | Anggota tidak jadi ditambahkan |
| Anggota tidak ditemukan | pengecekan `null` | Proses berhenti |
| Buku tidak ditemukan | `BookNotFoundException` | Error ditampilkan |
| Buku sedang dipinjam | `BookAlreadyBorrowedException` | Transaksi ditolak |
| Pinjaman sudah 3 buku | `BorrowLimitExceededException` | Transaksi ditolak |
| Buku bukan pinjaman member | `contains` | Pengembalian dibatalkan |
| Data member internal null | `assert` | Assertion error jika `-ea` aktif |

### 7.5 Konsistensi Data

Program mengubah status buku, daftar pinjaman anggota, dan statistik hanya setelah validasi peminjaman berhasil. Jika peminjaman gagal karena buku tidak ditemukan, buku sedang dipinjam, atau limit tercapai, tidak ada perubahan transaksi yang seharusnya dilakukan.

Namun, implementasi masih dapat ditingkatkan dengan validasi null pada object yang dikirim ke service, pencegahan data duplikat, validasi input kosong, serta penggunaan satu sumber identitas buku yang lebih kuat daripada judul.

---

## 8. Panduan Kompilasi dan Eksekusi Program

### 8.1 Prasyarat

- Java Development Kit (JDK), disarankan Java 8 atau lebih baru;
- terminal atau command prompt;
- posisi direktori berada di folder `Tugas4`.

Periksa Java dengan:

```bash
java -version
javac -version
```

### 8.2 Kompilasi dari Folder `Tugas4`

Linux/macOS/Git Bash:

```bash
cd Tugas4
mkdir -p out
javac -d out library/model/*.java library/exception/*.java library/service/*.java library/main/*.java
```

Windows Command Prompt:

```bat
cd Tugas4
if not exist out mkdir out
javac -d out library\model\*.java library\exception\*.java library\service\*.java library\main\*.java
```

Opsi `-d out` mengarahkan hasil `.class` ke folder `out` dengan struktur package yang benar.

### 8.3 Eksekusi

```bash
java -cp out library.main.Main
```

Untuk mengaktifkan assertion:

```bash
java -ea -cp out library.main.Main
```

### 8.4 Kompilasi Sekaligus dari Root Repository

Jika terminal berada di root repository:

```bash
mkdir -p Tugas4/out
javac -d Tugas4/out Tugas4/library/model/*.java Tugas4/library/exception/*.java Tugas4/library/service/*.java Tugas4/library/main/*.java
java -cp Tugas4/out library.main.Main
```

### 8.5 Contoh Urutan Pengujian Manual

1. Jalankan program.
2. Pilih `2` untuk memastikan lima buku awal tampil.
3. Pilih `4`, masukkan `M001`, lalu masukkan `Pemrograman Java`.
4. Pilih `2` dan pastikan status buku berubah menjadi `Dipinjam`.
5. Coba pinjam buku yang sama lagi untuk menguji `BookAlreadyBorrowedException`.
6. Pilih `5` dengan `M001` dan judul yang sama untuk mengembalikan buku.
7. Pilih `6` untuk melihat laporan.
8. Pilih `7` untuk menambah anggota.
9. Pilih `0` untuk keluar.

---

## 9. Analisis Hasil Pengujian dan Output Sistem

### 9.1 Kondisi Awal

Saat laporan dipilih sebelum transaksi, statistik seluruh buku bernilai nol. Output penting yang diharapkan antara lain:

```text
Jumlah total pinjaman : 0
Belum ada buku yang pernah dipinjam.
Belum ada aktivitas peminjaman.
Belum ada data kategori yang dipinjam.
```

Analisis kategori tetap menampilkan jumlah koleksi berdasarkan data awal:

```text
Teknologi : 2 buku
Novel : 2 buku
Sejarah : 1 buku
```

Urutan kategori dapat berbeda karena `HashMap` tidak menjamin urutan iterasi.

### 9.2 Pengujian Peminjaman Berhasil

Jika `M001` meminjam `Pemrograman Java`, sistem:

- mengubah status buku dari `Tersedia` menjadi `Dipinjam`;
- menambahkan buku ke `daftarPinjaman` Agus;
- menaikkan statistik `Pemrograman Java` dari 0 menjadi 1;
- menaikkan aktivitas `M001` dari 0 menjadi 1;
- menampilkan pesan bahwa buku berhasil dipinjam oleh Agus.

### 9.3 Pengujian Buku Tidak Ditemukan

Jika pengguna memasukkan judul yang tidak ada, `cariBukuByJudul` melempar exception dan `Main` menampilkan:

```text
ERROR: Buku dengan judul "Judul Tidak Ada" tidak ditemukan.
```

### 9.4 Pengujian Buku Sedang Dipinjam

Jika buku yang sama dipinjam kembali sebelum dikembalikan, status buku bernilai `false` dan sistem menampilkan pesan dari `BookAlreadyBorrowedException`:

```text
ERROR: Buku "Pemrograman Java" sedang dipinjam.
```

### 9.5 Pengujian Batas Tiga Buku

Jika seorang anggota telah memiliki tiga buku, peminjaman keempat ditolak:

```text
ERROR: Anggota sudah mencapai batas maksimal 3 buku.
```

Pemeriksaan ini terjadi sebelum proses buku mengubah status, sehingga transaksi keempat tidak mengubah data.

### 9.6 Pengujian Pengembalian

Pengembalian yang valid menghapus object buku dari daftar pinjaman dan membuat statusnya tersedia kembali. Pengembalian buku yang tidak ada di daftar anggota menampilkan:

```text
Buku tersebut tidak sedang dipinjam oleh anggota ini.
```

### 9.7 Pengujian Pencarian

Keyword dicari pada judul dan kategori dengan `contains`, sehingga input `novel` akan menemukan `Laskar Pelangi` dan `Bumi`, sedangkan input `java` akan menemukan `Pemrograman Java`.

### 9.8 Pengujian Laporan Setelah Transaksi

Misalnya terjadi transaksi:

- Agus meminjam `Pemrograman Java` satu kali;
- Budi meminjam `Laskar Pelangi` dua kali secara terpisah, dengan pengembalian di antara transaksi.

Maka:

- total peminjaman = 3;
- buku paling sering dipinjam = `Laskar Pelangi`;
- anggota paling aktif = anggota dengan dua transaksi;
- kategori paling populer = `Novel` dengan dua peminjaman.

Statistik tetap menghitung seluruh riwayat peminjaman meskipun buku sudah dikembalikan.

### 9.9 Temuan dan Rekomendasi Pengujian

Dari analisis kode, beberapa kasus tepi perlu diuji atau diperbaiki:

- input judul kosong pada menu tambah buku dapat membuat `charAt(0)` error;
- input nama kosong dapat membuat `nama.charAt(0)` error;
- ID anggota duplikat dapat membuat dua object anggota memiliki identitas sama;
- judul buku duplikat dapat menimpa key statistik pada `HashMap`;
- menu 8 belum benar-benar menampilkan anggota;
- input menu nonangka keluar dari program karena `pilihan` diset menjadi 0;
- `HashMap` membuat urutan laporan kategori tidak deterministik;
- assertion sebaiknya tidak dijadikan satu-satunya validasi input pengguna.

---

## 10. Kesimpulan

Folder `Tugas4` berisi aplikasi konsol Sistem Perpustakaan Mini yang telah memisahkan model data, exception, service, dan antarmuka utama ke dalam package berbeda. Struktur tersebut sudah menunjukkan penerapan dasar OOP yang baik: class dan object, constructor, enkapsulasi, relasi object, pewarisan exception, overriding `toString`, koleksi generic, serta pemisahan tanggung jawab.

Fitur utama yang telah berjalan adalah manajemen buku dan anggota, pencarian buku, peminjaman, pengembalian, validasi batas pinjaman, exception khusus, dan laporan statistik. `LibraryService` menjadi pusat aturan bisnis, sedangkan `Main` bertugas sebagai pengendali interaksi terminal.

Program masih bersifat sederhana dan in-memory. Pengembangan berikutnya dapat menambahkan penyimpanan permanen, validasi input kosong dan duplikasi, ID buku unik, daftar anggota pada menu 8, tanggal transaksi, denda keterlambatan, antarmuka GUI/web, serta pengujian otomatis menggunakan JUnit. Meskipun demikian, sebagai latihan pemrograman berorientasi objek, implementasi ini sudah mencakup alur bisnis perpustakaan yang lengkap dan memperlihatkan hubungan antar-konsep Java secara nyata.

---

## Referensi Source Code yang Dianalisis

- [`Book.java`](./library/model/Book.java)
- [`Member.java`](./library/model/Member.java)
- [`BookAlreadyBorrowedException.java`](./library/exception/BookAlreadyBorrowedException.java)
- [`BookNotFoundException.java`](./library/exception/BookNotFoundException.java)
- [`BorrowLimitExceededException.java`](./library/exception/BorrowLimitExceededException.java)
- [`LibraryService.java`](./library/service/LibraryService.java)
- [`Main.java`](./library/main/Main.java)
