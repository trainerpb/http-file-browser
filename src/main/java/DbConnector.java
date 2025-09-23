import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Header;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import model.Student;

import java.io.*;
import java.nio.file.Files;
import java.sql.*;

import static com.itextpdf.text.pdf.PdfName.DEST;

public class DbConnector {


    public static void main(String[] args) throws Exception {
        saveAllStudentsRecordsToPdf();
    }

    public static void connectToDatabasse() throws ClassNotFoundException, SQLException{
        //        Class.forName("org.mariadb.jdbc.Driver"); // Step-1 No more needed nowadays

        Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.137.176:3306/csedb", "soham", "india123"); // obtain connection from a jdbc url using ip, port, dbname, user , pwd etc.

        // Create a statement object
        PreparedStatement  preparedStatement = connection.prepareStatement("select * from student");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name")+ resultSet.getString("class")+" "+resultSet.getString("email")+ "  "+ resultSet.getString("roll_no") +"  "+ resultSet.getString("phone_no"));
        }
    }


    public static  void saveAllStudentsRecordsToPdf() throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.137.176:3306/csedb", "soham", "india123"); // obtain connection from a jdbc url using ip, port, dbname, user , pwd etc.

        // Create a statement object
        PreparedStatement  preparedStatement = connection.prepareStatement("select * from student");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
           Student student=Student.builder()
                   .name(resultSet.getString("name"))
                   .roll_no(resultSet.getString("roll_no"))
                   .phone_no(resultSet.getString("phone_no"))
                   .email(resultSet.getString("email"))
                   .build();
           generatePdf(student);
        }

    }

    public static void generatePdf(Student student) throws Exception {
        // PdfDocument pdf = new PdfDocument(new PdfWriter(DEST));
        Document document = new Document();

        String filename = String.format("%s_%s.pdf", student.getName(), student.getRoll_no());
        OutputStream fos = Files.newOutputStream(new File("D:/student_details", filename).toPath());
        PdfWriter writer = PdfWriter.getInstance(document,fos);

        String
          userPassword = getUserPassword(student),
          ownerPassword = getOwnerPassword(student);

        writer.setEncryption(
                userPassword.getBytes(),
                ownerPassword.getBytes(),
                PdfWriter.ALLOW_PRINTING,
                PdfWriter.ENCRYPTION_AES_256
        );

        document.open();
        String line = "Hello! Welcome "+ student.getName() +" to IIE";
//        document.add(new Header("h1",line));
        document.add(new Paragraph(String.format("Your email address is: %s",student.getEmail())));
        document.add(new Paragraph(String.format("your mobile is %s",student.getPhone_no())));
        document.close();

    }

    public static String getUserPassword(Student student) {
        return String.format("%s%s%s",
                student.getName().substring(0, 4),
                student.getPhone_no().substring(0, 4),
                student.getPhone_no().substring(student.getPhone_no().length()-4)
        );
    }

    public static String getOwnerPassword(Student student) {
        return student.getEmail();
    }
}
