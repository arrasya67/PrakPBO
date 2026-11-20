package lab.kasir;

import java.awt.Font;
import javax.swing.*;

public class KasirFrame extends JFrame {
    private JTextField txtMenu;
    private JTextField txtHarga;
    private JTextField txtJumlah;
    private JTextArea areaStruk;
    private JLabel lblTotal;
    private JLabel lblStatus;
    private JButton btnTambah;
    private JButton btnHitung;
    private JButton btnCetak;
    private Pesanan pesanan;

    public KasirFrame() {
        pesanan = new Pesanan();
        setTitle("Kasir Kantin UNS");
        setSize(430, 570);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- 1. KOMPONEN GUI ---
        JLabel judul = new JLabel("Kasir Kantin UNS");
        judul.setFont(new Font("Arial", Font.BOLD, 20));
        judul.setBounds(25, 15, 350, 30);
        add(judul);

        JLabel lblMenu = new JLabel("Nama Menu :");
        lblMenu.setBounds(25, 65, 100, 25);
        add(lblMenu);
        txtMenu = new JTextField();
        txtMenu.setBounds(130, 65, 255, 25);
        add(txtMenu);

        JLabel lblHarga = new JLabel("Harga       :");
        lblHarga.setBounds(25, 105, 100, 25);
        add(lblHarga);
        txtHarga = new JTextField();
        txtHarga.setBounds(130, 105, 255, 25);
        add(txtHarga);

        JLabel lblJumlah = new JLabel("Jumlah      :");
        lblJumlah.setBounds(25, 145, 100, 25);
        add(lblJumlah);
        txtJumlah = new JTextField();
        txtJumlah.setBounds(130, 145, 255, 25);
        add(txtJumlah);

        btnTambah = new JButton("Tambah");
        btnTambah.setBounds(25, 190, 150, 32);
        add(btnTambah);
        
        btnHitung = new JButton("Hitung Total");
        btnHitung.setBounds(190, 190, 195, 32);
        add(btnHitung);

        areaStruk = new JTextArea();
        areaStruk.setEditable(false);
        areaStruk.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(areaStruk);
        scroll.setBounds(25, 240, 360, 150);
        add(scroll);

        lblTotal = new JLabel("Total Bayar : Rp 0");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotal.setBounds(25, 405, 360, 30);
        add(lblTotal);

        btnCetak = new JButton("Cetak Struk");
        btnCetak.setBounds(25, 450, 150, 32);
        add(btnCetak);
        
        lblStatus = new JLabel("Status: Siap");
        lblStatus.setBounds(25, 495, 360, 25);
        add(lblStatus);

        // --- 2. ACTION LISTENER ---
        btnTambah.addActionListener(e -> aksiTambah());
        btnHitung.addActionListener(e -> aksiHitungTotal());
        btnCetak.addActionListener(e -> aksiCetakStruk());
    }

    // --- 3. LOGIKA TOMBOL TAMBAH ---
    private void aksiTambah() {
        String nama = txtMenu.getText().trim();
        String hargaText = txtHarga.getText().trim();
        String jumlahText = txtJumlah.getText().trim();

        // Validasi jika ada field yang kosong
        if (nama.isEmpty() || hargaText.isEmpty() || jumlahText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double harga = Double.parseDouble(hargaText);
            int jumlah = Integer.parseInt(jumlahText);

            // Validasi jika angka minus atau 0
            if (harga <= 0 || jumlah <= 0) {
                JOptionPane.showMessageDialog(this, "Harga dan Jumlah harus lebih dari 0!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Tambah ke logika class Pesanan
            pesanan.tambahItem(harga, jumlah);
            
            // Tampilkan ke area struk dengan format Rupiah (%,.0f menghilangkan .0 dan memberi titik ribuan)
            areaStruk.append(String.format("%-15s x%d = Rp %,.0f\n", nama, jumlah, (harga * jumlah)));
            
            // Kosongkan field input
            txtMenu.setText("");
            txtHarga.setText("");
            txtJumlah.setText("");
            txtMenu.requestFocus();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka dan Jumlah harus bilangan bulat!", "Format Salah", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- 4. LOGIKA TOMBOL HITUNG TOTAL ---
    private void aksiHitungTotal() {
        double total = pesanan.hitungTotal();
        // Format Rupiah dengan titik ribuan
        lblTotal.setText(String.format("Total Bayar : Rp %,.0f", total));
    }

    // --- 5. LOGIKA TOMBOL CETAK STRUK (THREAD) ---
    private void aksiCetakStruk() {
        if (pesanan.getSubtotal() <= 0) {
            JOptionPane.showMessageDialog(this, "Belum ada pesanan yang ditambahkan!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Ubah status dan matikan tombol sementara
        lblStatus.setText("Mencetak struk...");
        btnCetak.setEnabled(false);

        // Jalankan Thread baru agar GUI tidak macet (freeze)
        Thread threadCetak = new Thread(() -> {
            try {
                Thread.sleep(2000); // Simulasi tunggu 2 detik
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            // Setelah 2 detik, update GUI (harus pakai invokeLater)
            SwingUtilities.invokeLater(() -> {
                // Tampilkan detail struk di kotak areaStruk
                areaStruk.append("\n");
                areaStruk.append("================================\n");
                areaStruk.append("       STRUK KASIR KANTIN UNS\n");
                areaStruk.append("================================\n");
                areaStruk.append(String.format("Subtotal : Rp %,.0f\n", pesanan.getSubtotal()));
                
                double diskon = (pesanan.getSubtotal() > 50000) ? pesanan.getSubtotal() * 0.10 : 0;
                areaStruk.append(String.format("Diskon   : Rp %,.0f\n", diskon));
                areaStruk.append(String.format("TOTAL    : Rp %,.0f\n", pesanan.hitungTotal()));
                areaStruk.append("================================\n");
                areaStruk.append("       TERIMA KASIH\n");
                areaStruk.append("================================\n");

                // Auto-scroll ke paling bawah
                areaStruk.setCaretPosition(areaStruk.getDocument().getLength());

                // Kembalikan status
                lblStatus.setText("Struk selesai");
                btnCetak.setEnabled(true);
            });
        });
        
        threadCetak.start();
    }
}