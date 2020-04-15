import java.io.*;
import java.net.*;

public class PivitolStateA {

    public static void main(String args[]) throws Exception
    {

        String url = "http://avm-mia2k317/epower/Rdaui.htm?Production&http://AVM-MIA2k317";// url to check
        int i = 0;

        while (i<1){

            int status1 = getStatus(url);
            System.out.println(url+" : " + status1);
           // add extra cases according to need
           if (status1==404) {
               System.out.println("We can't reach");
               write_down();

               break;
           }
           else{
               System.out.println("Reachable");
               write_up();
           }
            Thread.sleep(3000);
            i+=1;
        }
    }



    public static void write_down() throws IOException
    {
        try {
        String fileContent = "Down";
        FileWriter fileWriter = new FileWriter("C:\\Program Files\\AvMed\\Outbound\\Pivotal\\PivitolIsDown.txt");//File name created when url is down
        fileWriter.write(fileContent);
        fileWriter.close();}
        catch (Exception e){
            System.out.println("Error in Writing File down as directory is invalid");
        }
    }
    public static void write_up() throws IOException
    {
        try {

            String fileContent = "Up";
            FileWriter fileWriter = new FileWriter("C:\\Program Files\\AvMed\\Outbound\\Pivotal\\PivitolIsUp.txt");//File name created when url is down
            fileWriter.write(fileContent);
            fileWriter.close();
        }
        catch (Exception e){
            System.out.println("Error in Writing File as up directory is invalid");
        }

    }

    public static int getStatus(String url) throws IOException {

        int result=0 ;
        try {
            URL urlObj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            con.setRequestMethod("GET");
            // Set connection timeout
            con.setConnectTimeout(3000);
            con.connect();

            int code = con.getResponseCode();
            if (code == 200) {
                result = 200;
            }
        } catch (Exception e) {
            result = 404;
        }
        return result;
    }



}
