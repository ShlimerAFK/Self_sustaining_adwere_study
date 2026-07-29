package popup_program;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Popup_program{
    
    static void create_popup(){

        try{

            JFrame popup = new JFrame();

            JPanel panel = new JPanel();

            JLabel label = new JLabel("Hej ;)");

            // ImageIcon image = new ImageIcon("Annoying_popup_program\\popup_program\\Full_metal.jpg");

            popup.setTitle("Popup");
            popup.setAlwaysOnTop(true);
            popup.setSize(300,200);
            popup.setVisible(true);
            popup.setResizable(false);

            popup.setLocation((int)(get_random_number() * 1.8), get_random_number());

            panel.add(label);
            //panel.add(new JLabel(image));

            popup.add(panel);
            popup.pack();

            popup.addWindowListener(new WindowListener() {

                @Override
                public void windowOpened(WindowEvent e) {
                    System.out.println("Opening");
                }

                @Override
                public void windowClosing(WindowEvent e) {

                    System.out.println("Closing...");

                    for(int i = 0; i < 40; i++){

                        create_popup();
                    }
                }

                @Override
                public void windowClosed(WindowEvent e) {

                    System.out.println("Closed");
                }

                @Override
                public void windowIconified(WindowEvent e) {

                    System.out.println("Iconified");

                    for(int i = 0; i < 10; i++){
                        
                        create_popup();
                    }
                }

                @Override
                public void windowDeiconified(WindowEvent e) {

                    System.out.println("Deiconified");
                }

                @Override
                public void windowActivated(WindowEvent e) {

                    System.out.println("Activated");
                }

                @Override
                public void windowDeactivated(WindowEvent e) {

                    System.out.println("Deactivated");   
                }
            
            });

        }catch(Exception error){
            System.out.println(error);
        } 
        
    }

    static int get_random_number(){

        int random = (int)(Math.random() * 1000);

        return random;
    }

    static long get_process_id(){

        long process_id = ProcessHandle.current().pid();

        System.out.println(process_id);

        return process_id;
    }

    static void wright_to_file(long pid){


        try{

            String dir = System.getProperty("user.dir");

            FileWriter wrighter = new FileWriter(dir + "/C_version/java_pid.txt"); 

            System.out.println(pid);
            wrighter.write(String.valueOf(pid));
            wrighter.close();

        }catch(Exception e){

            System.out.println(e);
        }
    }

    static void check_if_cpp_running(){

        System.out.println("Serching for c++");
    
        String dir = System.getProperty("user.dir");
        File cpp_file = new File(dir + "/C_version/cpp_pid.txt");

        try(Scanner scanner = new Scanner(cpp_file)){

            String pid = null;

            while (scanner.hasNextLine()){

                pid = scanner.nextLine();

                System.out.println(pid);
            }

            Process get_process = Runtime.getRuntime().exec("Powershell.exe Get-Process -id " + pid);

            BufferedReader terminal_out = new BufferedReader(new InputStreamReader(get_process.getInputStream()));
            BufferedReader terminal_out_err = new BufferedReader(new InputStreamReader(get_process.getErrorStream()));

            String temp = terminal_out.readLine();
            String err_temp = terminal_out_err.readLine();
            
            while (temp != null) {

                System.out.println(temp);
        
                temp = terminal_out.readLine();
            }

            while (err_temp != null) {

                System.out.println("ERROR: " + err_temp);
        
                err_temp = terminal_out.readLine();
            }

        }catch(IOException e){

                System.out.println(e);
            }
    }

    public static void main(String[] args) {

        wright_to_file(get_process_id());

        check_if_cpp_running();

        create_popup(); 
    }


}
