package dao;
import exception.AdminException;
public interface AdminDao {
    public boolean checkAdmin(String e,String p) throws AdminException;
    public void AdminHomeWindow() throws AdminException;
}
