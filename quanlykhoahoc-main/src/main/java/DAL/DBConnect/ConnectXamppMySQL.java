
package DAL.DBConnect;


import java.sql.*;

public class ConnectXamppMySQL implements AutoCloseable {
    String host="localhost";
    String username="root";
    String password="";
    String database="course-management";
    
    Statement statement=null;
    Connection connect=null;
    ResultSet result=null;

    public ConnectXamppMySQL() {
    }
    public ConnectXamppMySQL(String host,String username,String password, String database) {
        this.host=host;
        this.username=username;
        this.password=password;
        this.database=database;
    }
    public void DriverTest() throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
           
        }
        catch(java.lang.ClassNotFoundException e){
            throw new Exception("Cannot Connect to MySQL !!!");
        };
    }

    public Connection getConnect() throws Exception  {
        Connection cons = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cons = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/course-management", "root", "");
        } catch (Exception e) {
            System.out.print("Kết nối lỗi");
        }
        return cons;
    }
    public Statement getStatement()throws Exception {
        if(statement==null?true:statement.isClosed()){
           this.statement=this.getConnect().createStatement();
        }
        return statement;
    }

    public ResultSet excuteQuery(String query) throws Exception  {
         try{
                this.result=getStatement().executeQuery(query);
            }
         catch(Exception e){
            throw new Exception("Lỗi :"+e.getMessage()+" - "+query);
        }
       return result;
    }
    public int excuteUpdate(String query) throws Exception {
        int res = Integer.MIN_VALUE;

        try {
            res = getStatement().executeUpdate(query);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new Exception("Lỗi: Trùng lặp giá trị khóa chính - " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Lỗi: " + e.getMessage() + " - " + query);
        } finally {
            this.Close();
        }
        return res;
    }

    public void Close()throws SQLException{
        if(this.result!=null &&!this.result.isClosed()){
            this.result.close();
            this.result=null;
        }
        if(this.statement!=null &&!this.statement.isClosed()){
            this.statement.close();
            this.statement=null;
        }
        if(this.connect!=null &&!this.connect.isClosed()){
            this.connect.close();
            this.connect=null;
        }
    }

    @Override
    public void close() throws Exception {
        Close();
    }
}
