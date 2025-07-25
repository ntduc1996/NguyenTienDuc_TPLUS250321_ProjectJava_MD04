package dao;

import model.entity.Admin;

public interface AdminDAO {
    Admin loginAdmin(String username, String password);
}
