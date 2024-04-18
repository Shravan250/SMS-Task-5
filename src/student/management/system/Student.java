
package student.management.system;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Student {
    
    Connection con = MyConnection.getConnection();
    PreparedStatement ps;
    
    
    //table max row
    public int getMax(){
        int id = 0;
        Statement st;
        
        try{
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select max(id) from student");
            
            while(rs.next()){
                id = rs.getInt(1);
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        return id + 1;
    } 
    
    //insert data into student table
    public void insert(int id , String name , String date , String gender , String email , String phone ,
            String father , String mother , String address , String imagepath){
        
        String sql = "insert into student values(?,?,?,?,?,?,?,?,?,?)";
        
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1 , id);
            ps.setString(2, name);
            ps.setString(3, date);
            ps.setString(4, gender);
            ps.setString(5, email);
            ps.setString(6, phone);
            ps.setString(7, father);
            ps.setString(8, mother);
            ps.setString(9, address);
            ps.setString(10, imagepath);
            
            if(ps.executeUpdate()>0){
                JOptionPane.showMessageDialog(null,"New Student Added Succesfully");
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
    }   
        
    //get all student values form the student table
    public void getStudentValue(JTable table , String searchValue){
            String sql = "select * from student where concat(id,name,email,phone)like ? order by id desc";
            
            try{
                ps = con.prepareStatement(sql);
                ps.setString(1 , "%"+searchValue+"%");
                ResultSet rs = ps.executeQuery();
                DefaultTableModel model = (DefaultTableModel)table.getModel();
                Object[] row;
                while(rs.next()){
                    row = new Object[10];
                    row[0] = rs.getInt(1);
                    row[1] = rs.getString(2);
                    row[2] = rs.getString(3);
                    row[3] = rs.getString(4);
                    row[4] = rs.getString(5);
                    row[5] = rs.getString(6);
                    row[6] = rs.getString(7);
                    row[7] = rs.getString(8);
                    row[8] = rs.getString(9);
                    row[9] = rs.getString(10);
                    model.addRow(row);
                }

            }catch(Exception e){
                System.out.println(e);
            }
            
        }   
        
    
    //check if id exist
    public boolean isIdExist(int id){
        try{
            ps = con.prepareStatement("select * from student where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }    
        
    //studet data delete 
    public void delete(int id){
        int yesNo = JOptionPane.showConfirmDialog(null , "Permanently Delete this Record","Confirm Delete",JOptionPane.OK_CANCEL_OPTION,0);
        if(yesNo == JOptionPane.OK_OPTION){
            try{
                ps = con.prepareStatement("delete from student where id = ?");
                ps.setInt(1, id);
                if(ps.executeUpdate() > 0){
                    JOptionPane.showMessageDialog(null,"Student Deleted Succesfully");                    
                }

        }catch(Exception e){
            System.out.println(e);
        }
            
        }else{
            
        }    
        
    
    }
}
            
    
    

