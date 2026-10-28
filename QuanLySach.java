// Tuần 3: Cấu trúc điều kiện - quản lý sach thư viện
public class QuanLySach {
    public static void main(String[] args) {
        // Dữ liệu 14 cuốn sach (cùng chỉ số i là cùng một cuốn)
        String[] ten = {
            "Truyen Kieu", "De Men phieu luu ky", "Dac Nhan Tam", "Toan 12",
            "Nha gia kim"};
        double[] gia = {30000, 49999, 50000, 75000, 100000};
        int[] soLuong = {0, 1, 5, 6, 20};
        int[] namXuatBan = {2018, 2019, 2016, 2023, 2020};
        // Loại sach: 1 = giao khoa, 2 = tham khảo, 3 = chuyên ngành, 4 = khác
        int[] loai = {4, 4, 4, 1, 4};

        int namHienTai = java.time.Year.now().getValue();

        for (int i = 0; i < ten.length; i++) {
            System.out.println("=== " + ten[i] + " ===");

            // Bài 1: kiểm tra sach có hop le không 
            boolean hopLe = gia[i] > 0
                    && soLuong[i] >= 0
                    && namXuatBan[i] >= 1900
                    && namXuatBan[i] <= namHienTai;

            if (hopLe) {
                System.out.println("Bài 1: Sach hop le");

                // Bài 2: phân loại gia 
                String loaiGia;
                if (gia[i] < 50000) {
                    loaiGia = "Sach gia thap";
                } else if (gia[i] <= 100000) {
                    loaiGia = "Sach gia trung binh";
                } else if (gia[i] <= 200000) {
                    loaiGia = "Sach gia cao";
                } else {
                    loaiGia = "Sach gia dac biet";
                }
                System.out.println("Bài 2: " + gia[i] + " -> " + loaiGia);

                // Bài 3: phân loại tồn kho 
                String tonKho;
                if (soLuong[i] == 0) {
                    tonKho = "Het sach";
                } else if (soLuong[i] <= 5) {
                    tonKho = "Sap het";
                } else if (soLuong[i] <= 20) {
                    tonKho = "Con it";
                } else {
                    tonKho = "Con nhieu";
                }
                System.out.println("Bài 3: " + soLuong[i] + " cuon -> " + tonKho);

                // Bài 4: switch chọn loại sach 
                String tenLoai;
                switch (loai[i]) {
                    case 1:
                        tenLoai = "Sach giao khoa";
                        break;
                    case 2:
                        tenLoai = "Sach tham khao";
                        break;
                    case 3:
                        tenLoai = "Sach chuyen nganh";
                        break;
                    case 4:
                        tenLoai = "Sach khac";
                        break;
                    default:
                        tenLoai = "Lua chon không hop le";
                }
                System.out.println("Bài 4: loai " + loai[i] + " -> " + tenLoai);
            } else {
                System.out.println("Bài 1: Sach không hop le -> bo qua");
            }
            System.out.println();
        }
    }
}