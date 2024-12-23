import java.sql.Date;
import java.util.Scanner;

public class FarmManagementSystem {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Farm farm = new Farm();

            while (true) {
                System.out.println("1. Tambah Sapi");
                System.out.println("2. Tampilkan Semua Sapi");
                System.out.println("3. Perbarui Data Sapi");
                System.out.println("4. Hapus Sapi");
                System.out.println("5. Keluar");
                System.out.print("Pilih opsi: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1: // Tambah Sapi
                    System.out.println("Pilih Tipe Sapi:");
                    System.out.println("1. Dairy Cow");
                    System.out.println("2. Beef Cow");
                    System.out.print("Pilihan: ");
                    int cowType = scanner.nextInt();
                    scanner.nextLine(); // Bersihkan newline

                    System.out.print("Masukkan Nama Sapi: ");
                    String name = scanner.nextLine();
                    System.out.print("Masukkan Tanggal Lahir (yyyy-mm-dd): ");
                    String birthDateStr = scanner.nextLine();
                    Date birthDate = java.sql.Date.valueOf(birthDateStr);
                    System.out.print("Masukkan Biaya Perawatan: ");
                    double maintenanceCost = scanner.nextDouble();

                    Cow newCow = null;
                    if (cowType == 1) {
                        System.out.print("Masukkan Produksi Susu (liter): ");
                        double milkProduction = scanner.nextDouble();
                        newCow = new DairyCow(0, name, birthDate, maintenanceCost, milkProduction);
                    } else if (cowType == 2) {
                        System.out.print("Masukkan Berat (kg): ");
                        double weight = scanner.nextDouble();
                        newCow = new BeefCow(0, name, birthDate, maintenanceCost, weight);
                    }

                    if (newCow != null) {
                        farm.addCow(newCow);
                        System.out.println("Sapi berhasil ditambahkan!");
                    }
                    break;

                    case 2:
                        farm.getCows().forEach(System.out::println);
                        break;

                    case 5:
                        System.out.println("Keluar program.");
                        return;

                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            }
        }
    }
}
