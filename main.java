import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Main extends JFrame {

    JTextField nameField;
    JTextField cityField;
    JTextArea displayArea;

    public Main() {

        setTitle("NayePankh Volunteer Management System");
        setSize(700,500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel nameLabel = new JLabel("Volunteer Name:");
        nameField = new JTextField(20);

        JLabel cityLabel = new JLabel("City:");
        cityField = new JTextField(20);

        JButton addButton = new JButton("Add Volunteer");
        JButton searchButton = new JButton("Search");
        JButton viewButton = new JButton("View All");
        JButton reportButton = new JButton("Generate Report");

        displayArea = new JTextArea(20,50);
        JScrollPane scrollPane =
                new JScrollPane(displayArea);

        add(nameLabel);
        add(nameField);

        add(cityLabel);
        add(cityField);

        add(addButton);
        add(searchButton);
        add(viewButton);
        add(reportButton);

        add(scrollPane);

        addButton.addActionListener(
                e -> addVolunteer());

        searchButton.addActionListener(
                e -> searchVolunteer());

        viewButton.addActionListener(
                e -> viewVolunteers());

        reportButton.addActionListener(
                e -> generateReport());

        setVisible(true);
    }

    private void addVolunteer() {

        String name = nameField.getText();
        String city = cityField.getText();

        if(name.isEmpty() || city.isEmpty()){

            JOptionPane.showMessageDialog(
                    this,
                    "Enter all details");

            return;
        }

        try{

            FileWriter writer =
                    new FileWriter(
                            "volunteers.txt",
                            true);

            writer.write(
                    name + "," + city + "\n");

            writer.close();

            JOptionPane.showMessageDialog(
                    this,
                    "Volunteer Added");

            nameField.setText("");
            cityField.setText("");

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    private void viewVolunteers() {

        displayArea.setText("");

        try{

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    "volunteers.txt"));

            String line;

            while((line=reader.readLine())!=null){

                displayArea.append(
                        line + "\n");
            }

            reader.close();

        }catch(Exception e){

            displayArea.setText(
                    "No Volunteers Found");
        }
    }

    private void searchVolunteer() {

        String searchName =
                JOptionPane.showInputDialog(
                        "Enter Name");

        displayArea.setText("");

        try{

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    "volunteers.txt"));

            String line;

            boolean found=false;

            while((line=reader.readLine())!=null){

                if(line.toLowerCase()
                        .contains(
                                searchName
                                        .toLowerCase())){

                    displayArea.append(
                            line + "\n");

                    found=true;
                }
            }

            if(!found){

                displayArea.setText(
                        "Volunteer Not Found");
            }

            reader.close();

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    private void generateReport() {

        int count=0;

        try{

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    "volunteers.txt"));

            while(reader.readLine()!=null){

                count++;
            }

            reader.close();

            JOptionPane.showMessageDialog(
                    this,
                    "Total Volunteers = "
                            + count);

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                Main::new);
    }
}