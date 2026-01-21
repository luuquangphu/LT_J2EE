
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<>();
        Scanner x = new Scanner(System.in);

        // Sử dụng Text Block (Java 15+) để hiển thị menu giống trong hình
        String msg = """
                Chương trình quản lý sách
                1. Thêm 1 cuốn sách
                2. Xóa 1 cuốn sách
                3. Thay đổi sách
                4. Xuất thông tin
                5. Tìm sách Lập trình
                6. Lấy sách tối đa theo giá
                7. Tìm kiếm theo tác giả
                0. Thoát
                Chọn chức năng:""";

        int chon = 0;
        do {
            System.out.printf(msg);
            chon = x.nextInt();
            switch (chon) {
                case 1 -> {
                    Book newBook = new Book();
                    newBook.input();
                    listBook.add(newBook);
                }
                case 2 -> {
                    System.out.print("Nhập vào mã sách cần xóa:");
                    int bookId = x.nextInt();
                    // kiem tra ma sach
                    Book find = listBook.stream()
                            .filter(p -> p.getId() == bookId)
                            .findFirst()
                            .orElseThrow();
                    listBook.remove(find);
                    System.out.print("Đã xóa sách thành công");
                }
                case 3 -> {
                    System.out.print("Nhập vào mã sách cần điều chỉnh:");
                    int bookId = x.nextInt();
                    Book find = listBook.stream()
                            .filter(p -> p.getId() == bookId)
                            .findFirst()
                            .orElseThrow();
                    if (find != null) {
                        System.out.println("--- Đang sửa thông tin sách: ---");
                        find.output();
                        x.nextLine();

                        System.out.print("Nhập tên sách mới: ");
                        String newTitle = x.nextLine();
                        find.setTitle(newTitle);

                        System.out.print("Nhập tác giả mới: ");
                        String newAuthor = x.nextLine();
                        find.setAuthor(newAuthor);

                        System.out.print("Nhập giá mới: ");
                        long newPrice = x.nextLong();
                        find.setPrice(newPrice);

                        System.out.println("--> Đã cập nhật thành công!");
                    } else {
                        System.out.println("--> Lỗi: Không tìm thấy sách có mã " + bookId);
                    }
                }
                case 4 -> {
                    System.out.println("\n Xuất thông tin danh sách ");
                    listBook.forEach(p -> p.output());
                }
                case 5 -> {
                    List<Book> list5 = listBook.stream()
                            .filter(u -> u.getTitle().toLowerCase().contains("lập trình"))
                            .toList();
                    list5.forEach(Book::output);
                }
                case 6 -> {
                    System.out.println("--- Lọc sách theo giá và giới hạn số lượng ---");
                    System.out.print("Nhập mức giá tối thiểu: ");
                    var minPrice = x.nextLong();

                    System.out.print("Nhập số lượng sách muốn lấy tối đa: ");
                    var limitCount = x.nextInt();

                    System.out.println("Kết quả:");
                    listBook.stream()
                            .filter(book -> book.getPrice() >= minPrice)
                            .sorted((b1, b2) -> Long.compare(b2.getPrice(), b1.getPrice()))
                            .limit(limitCount)
                            .forEach(Book::output);
                }
                case 7 -> {
                    System.out.println("--- Tìm kiếm theo danh sách tác giả ---");
                    x.nextLine();
                    System.out.println("Nhập tên các tác giả (cách nhau bởi dấu phẩy, vd: Nam,Lan): ");
                    var line = x.nextLine();

                    var authorSet = java.util.Arrays.stream(line.split(","))
                            .map(String::trim)
                            .collect(java.util.stream.Collectors.toSet());

                    System.out.println("Sách của các tác giả: " + authorSet);

                    var resultList = listBook.stream()
                            .filter(book -> authorSet.contains(book.getAuthor()))
                            .toList();

                    if (resultList.isEmpty()) {
                        System.out.println("-> Không tìm thấy sách nào!");
                    } else {
                        resultList.forEach(Book::output);
                    }
                }
            }
        } while (chon != 0);
    }
}