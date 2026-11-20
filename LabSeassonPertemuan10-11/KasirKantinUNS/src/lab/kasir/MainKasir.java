package lab.kasir;

public class MainKasir {
    public static void main(String[] args) {
        // Tahap 1: uji logika pesanan sesuai data pada PDF.
        Pesanan pesanan = new Pesanan();
        pesanan.tambahItem(15000, 2); // Nasi Goreng
        pesanan.tambahItem(5000, 3);  // Es Teh
        System.out.printf("Total setelah dua menu pertama: Rp %,.0f%n",
                pesanan.hitungTotal());

        pesanan.tambahItem(25000, 1); // Ayam Bakar
        System.out.printf("Subtotal setelah tiga menu: Rp %,.0f%n",
                pesanan.getSubtotal());
        System.out.printf("Total setelah tiga menu: Rp %,.0f%n",
                pesanan.hitungTotal());

        // Tahap 2: dua kasir melayani pelanggan secara bersamaan.
        Kasir dina = new Kasir("Dina");
        Kasir eko = new Kasir("Eko");
        dina.start();
        eko.start();

        try {
            dina.join();
            eko.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Proses utama terinterupsi.");
            return;
        }
        System.out.println("Semua pelanggan selesai dilayani");
    }
}
