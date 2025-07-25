package business.imp;

import business.IAdminServices;
import dao.AdminDAO;
import dao.imp.AdminDAOImp;
import model.entity.Admin;

public class AdminServices implements IAdminServices {
    private final AdminDAO adminDAO;

    public AdminServices() {
        adminDAO = new AdminDAOImp();
    }

    @Override
    public Admin loginAdmin(String username, String password) {
        return adminDAO.loginAdmin(username, password);
    }
}
