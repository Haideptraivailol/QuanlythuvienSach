import java.util.Scanner;

// Quan ly sach thu vien: tuan 3 (cau truc dieu kien) + tuan 5 (vong lap, menu, tim kiem)
public class QuanLySach {
    // ---------- Du lieu: cac mang song song (cung chi so i la cung mot cuon) ----------
    static final int MAX = 100;                 // toi da 100 cuon
    static String[] ma = new String[MAX];       // ma sach
    static String[] ten = new String[MAX];      // ten sach
    static double[] gia = new double[MAX];      // gia
    static int[] soLuong = new int[MAX];        // so luong
    static int[] namXuatBan = new int[MAX];     // nam xuat ban
    static int[] loai = new int[MAX];           // 1 = giao khoa, 2 = tham khao, 3 = chuyen nganh, 4 = khac
    static int soSach = 0;                      // so sach hien co trong danh sach
    static Scanner sc = new Scanner(System.in);

    // Bai 3: Menu lap den khi chon Thoat
    public static void main(String[] args) {
        int chon;
        do {
            System.out.println();
            System.out.println("===== QUAN LY THU VIEN =====");
            System.out.println("1. Nhap sach");
            System.out.println("2. Hien thi danh sach sach");
            System.out.println("3. Tong so luong va tong gia tri");
            System.out.println("4. Tim sach theo ma");
            System.out.println("5. Nap 10 sach mau (de thu nhanh)");
            System.out.println("0. Thoat");
            chon = docSoNguyen("Chon chuc nang: ");

            switch (chon) {
                case 1:
                    nhapSach();
                    break;
                case 2:
                    hienThi();
                    break;
                case 3:
                    thongKe();
                    break;
                case 4:
                    timTheoMa();
                    break;
                case 5:
                    napDuLieuMau();
                    break;
                case 0:
                    System.out.println("Tam biet!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le, vui long chon lai!");
            }
        } while (chon != 0);
    }

    // Bai 1: Nhap n sach bang vong lap
    static void nhapSach() {
        int n = docSoNguyen("Nhap so sach can nhap: ");
        int namHienTai = java.time.Year.now().getValue();

        for (int k = 1; k <= n; k++) {
            if (soSach >= MAX) {
                System.out.println("Danh sach da day (toi da " + MAX + " cuon)!");
                break;
            }
            System.out.println("--- Nhap sach thu " + k + "/" + n + " ---");

            // Ma sach: khong duoc trong va khong duoc trung
            String maMoi;
            while (true) {
                System.out.print("Ma sach: ");
                maMoi = sc.nextLine().trim();
                if (maMoi.isEmpty()) {
                    System.out.println("Ma sach khong duoc de trong!");
                } else if (timViTri(maMoi) != -1) {
                    System.out.println("Ma sach da ton tai, nhap ma khac!");
                } else {
                    break;
                }
            }

            System.out.print("Ten sach: ");
            String tenMoi = sc.nextLine().trim();

            // Gia phai > 0 (nhap sai thi nhap lai) - luat hop le cua tuan 3
            double giaMoi;
            do {
                giaMoi = docSoThuc("Gia (> 0): ");
                if (giaMoi <= 0) {
                    System.out.println("Gia phai lon hon 0!");
                }
            } while (giaMoi <= 0);

            // So luong phai >= 0
            int soLuongMoi;
            do {
                soLuongMoi = docSoNguyen("So luong (>= 0): ");
                if (soLuongMoi < 0) {
                    System.out.println("So luong khong duoc am!");
                }
            } while (soLuongMoi < 0);

            // Nam xuat ban tu 1900 den nam hien tai
            int namMoi;
            do {
                namMoi = docSoNguyen("Nam xuat ban (1900 - " + namHienTai + "): ");
                if (namMoi < 1900 || namMoi > namHienTai) {
                    System.out.println("Nam xuat ban khong hop le!");
                }
            } while (namMoi < 1900 || namMoi > namHienTai);

            // Loai sach 1 - 4
            int loaiMoi;
            do {
                loaiMoi = docSoNguyen("Loai sach (1. Giao khoa, 2. Tham khao, 3. Chuyen nganh, 4. Khac): ");
                if (loaiMoi < 1 || loaiMoi > 4) {
                    System.out.println("Loai sach phai tu 1 den 4!");
                }
            } while (loaiMoi < 1 || loaiMoi > 4);

            // Hop le het thi luu vao mang
            ma[soSach] = maMoi;
            ten[soSach] = tenMoi;
            gia[soSach] = giaMoi;
            soLuong[soSach] = soLuongMoi;
            namXuatBan[soSach] = namMoi;
            loai[soSach] = loaiMoi;
            soSach++;
        }
        System.out.println("Da xong. Hien co " + soSach + " cuon sach.");
    }

    // Hien thi toan bo danh sach bang vong for
    static void hienThi() {
        if (soSach == 0) {
            System.out.println("Chua co sach nao.");
            return;
        }
        System.out.println("--- Danh sach " + soSach + " cuon sach ---");
        for (int i = 0; i < soSach; i++) {
            System.out.print((i + 1) + ". ");
            inSach(i);
        }
    }

    // Bai 2: Tinh tong so luong va tong gia tri sach
    static void thongKe() {
        if (soSach == 0) {
            System.out.println("Chua co sach nao de thong ke.");
            return;
        }
        int tongSoLuong = 0;
        double tongGiaTri = 0;
        for (int i = 0; i < soSach; i++) {
            tongSoLuong += soLuong[i];
            tongGiaTri += gia[i] * soLuong[i];   // gia tri cua mot dau sach = gia x so luong
        }
        System.out.println("So dau sach: " + soSach);
        System.out.println("Tong so luong: " + tongSoLuong + " cuon");
        System.out.println("Tong gia tri: " + dinhDang(tongGiaTri) + " dong");
    }

    // Bai 4: Tim sach theo ma
    static void timTheoMa() {
        System.out.print("Nhap ma sach can tim: ");
        String maTim = sc.nextLine().trim();
        int vt = timViTri(maTim);
        if (vt == -1) {
            System.out.println("Khong tim thay sach co ma \"" + maTim + "\".");
        } else {
            System.out.println("Da tim thay:");
            inSach(vt);
        }
    }

    // Duyet mang bang for, tra ve vi tri cua sach co ma can tim, khong co thi tra ve -1
    static int timViTri(String maTim) {
        for (int i = 0; i < soSach; i++) {
            if (ma[i].equalsIgnoreCase(maTim)) {
                return i;
            }
        }
        return -1;
    }

    // Cac phan cua tuan 3: phan loai gia, ton kho, loai sach
    static String phanLoaiGia(double g) {
        if (g < 50000) {
            return "Sach gia thap";
        } else if (g <= 100000) {
            return "Sach gia trung binh";
        } else if (g <= 200000) {
            return "Sach gia cao";
        } else {
            return "Sach gia dac biet";
        }
    }

    static String phanLoaiTon(int sl) {
        if (sl == 0) {
            return "Het sach";
        } else if (sl <= 5) {
            return "Sap het";
        } else if (sl <= 20) {
            return "Con it";
        } else {
            return "Con nhieu";
        }
    }

    static String tenLoai(int l) {
        switch (l) {
            case 1:
                return "Sach giao khoa";
            case 2:
                return "Sach tham khao";
            case 3:
                return "Sach chuyen nganh";
            case 4:
                return "Sach khac";
            default:
                return "Loai khong hop le";
        }
    }

    // In thong tin mot cuon sach (dung chung cho hien thi va tim kiem)
    static void inSach(int i) {
        System.out.println("[" + ma[i] + "] " + ten[i] + " (" + namXuatBan[i] + ") - " + tenLoai(loai[i]));
        System.out.println("      Gia: " + dinhDang(gia[i]) + " VND (" + phanLoaiGia(gia[i]) + ")"
                + " | So luong: " + soLuong[i] + " (" + phanLoaiTon(soLuong[i]) + ")");
    }

    // Dinh dang so co dau phan cach hang nghin, vi du 28.149.999
    static String dinhDang(double so) {
        return String.format("%,.0f", so);
    }

    // Ham ho tro: doc so, nhap sai chu thi bao va nhap lai (khong bi vang chuong trinh)
    static int docSoNguyen(String loiNhac) {
        while (true) {
            System.out.print(loiNhac);
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap mot so nguyen!");
            }
        }
    }

    static double docSoThuc(String loiNhac) {
        while (true) {
            System.out.print(loiNhac);
            String s = sc.nextLine().trim();
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap mot so!");
            }
        }
    }

    // Du lieu mau de thu nhanh (khong thuoc de)
    static void them(String m, String t, double g, int sl, int nam, int lo) {
        ma[soSach] = m;
        ten[soSach] = t;
        gia[soSach] = g;
        soLuong[soSach] = sl;
        namXuatBan[soSach] = nam;
        loai[soSach] = lo;
        soSach++;
    }

    static void napDuLieuMau() {
        if (timViTri("S01") != -1) {
            System.out.println("Da nap sach mau roi!");
            return;
        }
        them("S01", "Truyen Kieu", 30000, 0, 2018, 4);
        them("S02", "De Men phieu luu ky", 49999, 1, 2019, 4);
        them("S03", "Dac Nhan Tam", 50000, 5, 2016, 4);
        them("S04", "Toan 12", 75000, 6, 2023, 1);
        them("S05", "Nha gia kim", 100000, 20, 2020, 4);
        them("S06", "Lap trinh Java co ban", 150000, 21, 2022, 3);
        them("S07", "Cau truc du lieu va giai thuat", 200000, 8, 2021, 3);
        them("S08", "Co so du lieu nang cao", 250000, 3, 2024, 3);
        them("S09", "Tu dien Anh - Viet", 350000, 50, 2015, 2);
        them("S10", "Sach anh nghe thuat", 1200000, 2, 2025, 4);
        System.out.println("Da nap 10 sach mau.");
    }
}