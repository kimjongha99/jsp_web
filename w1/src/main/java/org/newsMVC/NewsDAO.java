package org.newsMVC;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class NewsDAO {
    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final String JDBC_URL = "jdbc:mysql://localhost:3306/newdb";

    // DB 연결을 가져오는 메서드, DBCP를 사용하는 것이 좋음
    public Connection open() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL,"root","rlawhdgk99");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public List<News> getAll() throws Exception {
        Connection conn = open();
        List<News> newsList = new ArrayList<>();

        String sql = "SELECT aid, title, DATE_FORMAT(date, '%Y-%m-%d %H:%i:%s') AS cdate FROM newdb.news";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        try(conn; pstmt; rs) {
            while(rs.next()) {
                News n = new News();
                n.setAid(rs.getInt("aid"));
                n.setTitle(rs.getString("title"));
                n.setDate(rs.getString("cdate"));

                newsList.add(n);
            }
            return newsList;
        }
    }

    public News getNews(int aid) throws SQLException {
        String sql = "SELECT aid, title, img, DATE_FORMAT(date, '%Y-%m-%d %H:%i:%s') AS cdate, content FROM newdb.news WHERE aid = ?";

        try (Connection conn = open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, aid);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    News n = new News();
                    n.setAid(rs.getInt("aid"));
                    n.setTitle(rs.getString("title"));
                    n.setImg(rs.getString("img"));
                    n.setDate(rs.getString("cdate"));
                    n.setContent(rs.getString("content"));
                    return n;
                }
            }
        }
        return null; // Return null if no news record is found for the given aid
    }

    public void addNews(News n) throws Exception {
        Connection conn = open();

        String sql = "insert into newdb.news(title,img,date,content) values(?,?,CURRENT_TIMESTAMP(),?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        try(conn; pstmt) {
            pstmt.setString(1, n.getTitle());
            pstmt.setString(2, n.getImg());
            pstmt.setString(3, n.getContent());
            pstmt.executeUpdate();
        }
    }

    public void delNews(int aid) throws SQLException {
        Connection conn = open();

        String sql = "delete from newdb.news where aid=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        try(conn; pstmt) {
            pstmt.setInt(1, aid);
            // 삭제된 뉴스 기사가 없을 경우
            if(pstmt.executeUpdate() == 0) {
                throw new SQLException("DB에러");
            }
        }
    }
}
