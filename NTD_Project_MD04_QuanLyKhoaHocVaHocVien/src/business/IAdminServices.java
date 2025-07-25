package business;

import model.entity.Admin;

public interface IAdminServices {
    Admin loginAdmin(String username, String password);
}
