package dao;

import model.Admin;

import java.util.Scanner;

public interface AdminDAO {
    Admin loginAdmin(String username, String password);
}
