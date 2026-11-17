package unoeste.fipp.deliveryfx.db.util;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import unoeste.fipp.deliveryfx.db.util.Conexao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class SingletonDB {
    public static final String DATABASE="deliverydb";
    private static Conexao conexao=null;
    private SingletonDB(){
        //impede a instancia da classe
    }
    public static boolean conectar(){
        conexao=new Conexao();
        return conexao.conectar("jdbc:postgresql://localhost/",DATABASE,"postgres","postgres123");
    }

    public static Conexao getConexao() {
        return conexao;
    }

    public static boolean criarBD() {
        try{
            String url = "jdbc:postgresql://localhost/";
            Connection con = DriverManager.getConnection(url,"postgres","postgres123");

            Statement statement = con.createStatement();
            statement.execute("CREATE DATABASE "+DATABASE+" WITH OWNER = postgres ENCODING = 'UTF8'  "
                    + "TABLESPACE = pg_default LC_COLLATE = 'Portuguese_Brazil.1252'  "
                    + "LC_CTYPE = 'Portuguese_Brazil.1252'  CONNECTION LIMIT = -1;");
            statement.close();
            con.close();
        }catch(Exception e)
        {  System.out.println(e.getMessage()); return false;}
        return true;
    }

    public static boolean backup(String arquivo) throws Exception
    {
        final ArrayList<String> comandos = new ArrayList();
        comandos.add("bdutil/pg_dump.exe"); comandos.add("--host");
        comandos.add("localhost"); //ou  comandos.add("192.168.0.1");
        comandos.add("--port"); comandos.add("5432"); comandos.add("--username");
        comandos.add("postgres");comandos.add("--format");comandos.add("custom");
        comandos.add("--blobs");comandos.add("--verbose");comandos.add("--file");
        comandos.add(arquivo);
        comandos.add(SingletonDB.DATABASE);
        ProcessBuilder pb = new ProcessBuilder(comandos);
        pb.environment().put("PGPASSWORD", "postgres123");
        try {
            final Process process = pb.start();
            final BufferedReader r = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));
            String info="";
            String line = r.readLine();
            while (line != null) {
                System.err.println(line); line = r.readLine();
                info+=line+"\n";
            }
            r.close();
            process.waitFor();
            process.destroy();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Backup realizado com sucesso!");
            TextArea textArea=new TextArea(info);
            alert.getDialogPane().setContent(textArea);
            alert.showAndWait();
            return true;
        } catch (Exception e) {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Erro na realização do backup.");
            alert.showAndWait();
            return false;
        }
    }
    public static boolean restaurar(String arquivo) throws Exception {
        final ArrayList<String> comandos = new ArrayList();
        comandos.add("bdutil/pg_restore.exe");
        comandos.add("-c");
        comandos.add("--host");
        comandos.add("localhost");
        comandos.add("--port");
        comandos.add("5432");
        comandos.add("--username");
        comandos.add("postgres");
        comandos.add("--dbname");
        comandos.add(DATABASE);
        comandos.add("--verbose");
        comandos.add(arquivo);
        ProcessBuilder pb = new ProcessBuilder(comandos);
        pb.environment().put("PGPASSWORD", "postgres123");
        try {
            final Process process = pb.start();
            final BufferedReader r = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));
            String info="";
            String line = r.readLine();
            while (line != null) {
                System.err.println(line);
                line = r.readLine();
                info+=line+"\n";
            }
            r.close();
            process.waitFor();
            process.destroy();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Restauração realizada com sucesso!");
            TextArea textArea=new TextArea(info);
            alert.getDialogPane().setContent(textArea);
            //alert.setContentText(info);
            alert.showAndWait();
            return true;
        } catch (Exception e) {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Erro na restauração.");
            alert.showAndWait();
            return false;
        }
    }
}
