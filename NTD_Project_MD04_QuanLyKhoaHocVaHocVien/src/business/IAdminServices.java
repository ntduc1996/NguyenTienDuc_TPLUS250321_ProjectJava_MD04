package business;

import model.Admin;

public interface IAdminServices {
    Admin loginAdmin(String username, String password);
}
