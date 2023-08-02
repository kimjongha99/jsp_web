package org.studentmvc;

import lombok.Cleanup;
import org.zero.jdbcex.dao.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    Connection conn = null;
    PreparedStatement pstmt;

    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final String JDBC_URL = "jdbc:mysql://localhost:3306/student";

    public void open(){
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL,"root","rlawhdgk99");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Student s) {
        open();
        String sql = "INSERT INTO student(username, univ, birth, email) values(?,?,?,?)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, s.getUsername());
            pstmt.setString(2, s.getUniv());
            pstmt.setDate(3, s.getBirth());
            pstmt.setString(4, s.getEmail());

            pstmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public List<Student> getAll() {
        open();
        List<Student> students = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement("select * from student");
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setUsername(rs.getString("username"));
                s.setUniv(rs.getString("univ"));
                s.setBirth(rs.getDate("birth"));
                s.setEmail(rs.getString("email"));

                students.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return students;
    }
//    public void deleteOne(Long tno) throws Exception {
//
//        String sql = "delete from tb1_table where tno = ?";
//
//        @Cleanup Connection    connection = ConnectionUtil.INSTANCE.getConnection();
//        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//        preparedStatement.setLong(1, tno);
//
//        preparedStatement.executeUpdate();
//    }

    public void deleteOne(int id) {
        open();
        String sql = "DELETE FROM student WHERE id = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("학생 정보가 삭제되었습니다.");
            } else {
                System.out.println("해당 학생 정보를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
}


