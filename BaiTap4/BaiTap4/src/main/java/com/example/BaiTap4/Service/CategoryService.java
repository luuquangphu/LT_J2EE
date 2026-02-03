package com.example.BaiTap4.Service;

import com.example.BaiTap4.Model.Category;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    // Khởi tạo danh sách Category tạm thời trong bộ nhớ
    private List<Category> listCategory = new ArrayList<>();

    public CategoryService() {
        // Thêm một số dữ liệu mẫu để bạn dễ test trên giao diện
        listCategory.add(new Category(1, "Công nghệ"));
        listCategory.add(new Category(2, "Đời sống"));
    }

    // Lấy toàn bộ danh sách Category
    public List<Category> getAll() {
        return listCategory;
    }

    // Tìm Category theo ID
    public Category get(int id) {
        return listCategory.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Thêm mới Category với ID tự tăng (Auto-increment logic)
    public void add(Category newCategory) {
        int maxId = listCategory.stream()
                .mapToInt(Category::getId)
                .max()
                .orElse(0);
        newCategory.setId(maxId + 1);
        listCategory.add(newCategory);
    }

    // Cập nhật thông tin Category
    public void update(Category editCategory) {
        Category find = get(editCategory.getId());
        if (find != null) {
            find.setName(editCategory.getName());
        }
    }

    // Xóa Category theo ID
    public void delete(int id) {
        listCategory.removeIf(c -> c.getId() == id);
    }
}