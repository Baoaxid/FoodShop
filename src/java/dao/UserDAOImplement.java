package dao;

import utils.DBContext;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserDAOImplement extends DBContext implements UserDAO{

    @Override
    public boolean insertUser(String username, String firstName, String lastName, String email, String password) {
        try {
            Connection conn = getConnection();
            
            //Ma hoa password bang SHA-256
            String hashedPassword = hashPassword(password);
            String sql = "INSERT INTO users (username, first_name, last_name, email, password_hash, role) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, username);
                ps.setString(2, firstName);
                ps.setString(3, lastName);
                ps.setString(4, email);
                ps.setString(5, hashedPassword);
                ps.setInt(6, 0);
                
                int rowAffected = ps.executeUpdate();
                closeConnection(conn, ps, null);
                return rowAffected > 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    private String hashPassword(String password) throws Exception{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(password.getBytes());
        
        StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
        for(byte b : encodedHash){
            String hex = Integer.toHexString(b);
            if(hex.length() == 1){
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @Override
    public User getUserByUsername(String username) {
        String query = "SELECT * FROM users WHERE username='"+username+"'";
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String passwordHash = rs.getString("password_hash");
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");
                byte role = rs.getByte("role");
                Date createdAt = rs.getDate("created_at");
                Date lastLogin = rs.getDate("last_login");
                String intro = rs.getString("intro");
                String profile = rs.getString("profile");
                
                User user = new User();
                user.setUsername(username);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setPasswordHash(passwordHash);
                user.setPhoneNumber(phoneNumber);
                user.setEmail(email);
                user.setRole(role);
                user.setCreatedAt(createdAt);
                user.setLastLogin(lastLogin);
                user.setIntro(intro);
                user.setProfile(profile);
                
                return user;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
