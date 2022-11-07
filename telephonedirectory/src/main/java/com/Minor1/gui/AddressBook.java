package com.Minor1.gui;

import com.Minor1.service.ApplicationService;
import com.Minor1.service.ApplicationServiceImpl;
import com.Minor1.service.TelPhoneDirectory;
import com.Minor1.utility.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddressBook implements ActionListener {

    ApplicationService service = new ApplicationServiceImpl();
    private JFrame appFrame;
    private JLabel jlbName, jlbPhone, jlbEmail, jlbCompany;
    private JTextField jtfName, jtfPhone, jtfEmail, jtfCompany;
    private JButton jbnSave, jbnDelete, jbnClear, jbnUpdate, jbnSearch,
            jbnForward, jbnBack, jbnExit;
    private String name, address, email, phone;

    int recordNumber;
    Container cPane;

    public AddressBook() {
        name = "";
        address = "";
        email = "";
        phone = "";
        recordNumber = -1;
        createGUI();
    }

    public void createGUI() {
        appFrame = new JFrame(Constants.FRAME_TITLE);
        cPane = appFrame.getContentPane();
        cPane.setLayout(new GridBagLayout());

        addComponents();
        appFrame.setSize(600, 600);
        appFrame.setResizable(false);
        appFrame.setLocationRelativeTo(null);
        appFrame.setVisible(true);
    }

    public void addComponents() {
        jlbName = new JLabel("Name");
        jlbPhone = new JLabel("Phone");
        jlbEmail = new JLabel("Email");
        jlbCompany = new JLabel("Company");

        jtfName = new JTextField(50);
        jtfPhone = new JTextField(20);
        jtfEmail = new JTextField(20);
        jtfCompany = new JTextField(50);

        jbnSave = new JButton("Save");
        jbnDelete = new JButton("Delete");
        jbnClear = new JButton("Clear");
        jbnUpdate = new JButton("Update");
        jbnSearch = new JButton("Search");
        jbnForward = new JButton("Next");
        jbnBack = new JButton("Previous");
        jbnExit = new JButton("Exit");

        /*add all initialized components to the container*/
        GridBagConstraints gridBagConstraintsx01 = new GridBagConstraints();
        gridBagConstraintsx01.gridx = 0;
        gridBagConstraintsx01.gridy = 0;
        gridBagConstraintsx01.insets = new Insets(5, 5, 5, 5);
        cPane.add(jlbName, gridBagConstraintsx01);
        GridBagConstraints gridBagConstraintsx02 = new GridBagConstraints();
        gridBagConstraintsx02.gridx = 1;
        gridBagConstraintsx02.insets = new Insets(5, 5, 5, 5);
        gridBagConstraintsx02.gridy = 0;
        gridBagConstraintsx02.gridwidth = 2;
        gridBagConstraintsx02.fill = GridBagConstraints.BOTH;
        cPane.add(jtfName, gridBagConstraintsx02);

        GridBagConstraints gridBagConstraintsx03 = new GridBagConstraints();
        gridBagConstraintsx03.gridx = 0;
        gridBagConstraintsx03.insets = new Insets(5, 5, 5, 5);
        gridBagConstraintsx03.gridy = 1;
        cPane.add(jlbCompany, gridBagConstraintsx03);
        GridBagConstraints gridBagConstraintsx04 = new GridBagConstraints();
        gridBagConstraintsx04.gridx = 1;
        gridBagConstraintsx04.insets = new Insets(5, 5, 5, 5);
        gridBagConstraintsx04.gridy = 1;
        gridBagConstraintsx04.gridwidth = 2;
        gridBagConstraintsx04.fill = GridBagConstraints.BOTH;
        cPane.add(jtfCompany, gridBagConstraintsx04);


        GridBagConstraints gridBagConstraintsx05 = new GridBagConstraints();
        gridBagConstraintsx05.gridx = 0;
        gridBagConstraintsx05.insets = new Insets(5, 5, 5, 5);
        gridBagConstraintsx05.gridy = 2;
        cPane.add(jlbPhone, gridBagConstraintsx05);
        GridBagConstraints gridBagConstraintsx06 = new GridBagConstraints();
        gridBagConstraintsx06.gridx = 1;
        gridBagConstraintsx06.gridy = 2;
        gridBagConstraintsx06.insets = new Insets(5, 5, 5, 5);
        gridBagConstraintsx06.gridwidth = 2;
        gridBagConstraintsx06.fill = GridBagConstraints.BOTH;
        cPane.add(jtfPhone, gridBagConstraintsx06);

        GridBagConstraints gridBagConstraintsx07 = new GridBagConstraints();
        gridBagConstraintsx07.gridx = 0;
        gridBagConstraintsx07.insets = new Insets(5, 5, 5, 5);
        gridBagConstraintsx07.gridy = 3;
        cPane.add(jlbEmail, gridBagConstraintsx07);
        GridBagConstraints gridBagConstraintsx08 = new GridBagConstraints();
        gridBagConstraintsx08.gridx = 1;
        gridBagConstraintsx08.gridy = 3;
        gridBagConstraintsx08.gridwidth = 2;
        gridBagConstraintsx08.insets = new Insets(5, 5, 5, 5);
        gridBagConstraintsx08.fill = GridBagConstraints.BOTH;
        cPane.add(jtfEmail, gridBagConstraintsx08);

        GridBagConstraints gridBagConstraintsx09 = new GridBagConstraints();
        gridBagConstraintsx09.gridx = 0;
        gridBagConstraintsx09.gridy = 4;
        gridBagConstraintsx09.insets = new Insets(5, 5, 5, 5);
        cPane.add(jbnSave, gridBagConstraintsx09);

        GridBagConstraints gridBagConstraintsx10 = new GridBagConstraints();
        gridBagConstraintsx10.gridx = 1;
        gridBagConstraintsx10.gridy = 4;
        gridBagConstraintsx10.insets = new Insets(5, 5, 5, 5);
        cPane.add(jbnDelete, gridBagConstraintsx10);

        GridBagConstraints gridBagConstraintsx11 = new GridBagConstraints();
        gridBagConstraintsx11.gridx = 2;
        gridBagConstraintsx11.gridy = 4;
        gridBagConstraintsx11.insets = new Insets(5, 5, 5, 5);
        cPane.add(jbnUpdate, gridBagConstraintsx11);

        GridBagConstraints gridBagConstraintsx12 = new GridBagConstraints();
        gridBagConstraintsx12.gridx = 0;
        gridBagConstraintsx12.gridy = 5;
        gridBagConstraintsx12.insets = new Insets(5, 5, 5, 5);
        cPane.add(jbnBack, gridBagConstraintsx12);

        GridBagConstraints gridBagConstraintsx13 = new GridBagConstraints();
        gridBagConstraintsx13.gridx = 1;
        gridBagConstraintsx13.gridy = 5;
        gridBagConstraintsx13.insets = new Insets(5, 5, 5, 5);
        cPane.add(jbnSearch, gridBagConstraintsx13);

        GridBagConstraints gridBagConstraintsx14 = new GridBagConstraints();
        gridBagConstraintsx14.gridx = 2;
        gridBagConstraintsx14.gridy = 5;
        gridBagConstraintsx14.insets = new Insets(5, 5, 5, 5);
        cPane.add(jbnForward, gridBagConstraintsx14);

        GridBagConstraints gridBagConstraintsx15 = new GridBagConstraints();
        gridBagConstraintsx15.gridx = 1;
        gridBagConstraintsx15.insets = new Insets(5, 5, 5, 5);
        gridBagConstraintsx15.gridy = 6;
        cPane.add(jbnClear, gridBagConstraintsx15);

        GridBagConstraints gridBagConstraintsx16 = new GridBagConstraints();
        gridBagConstraintsx16.gridx = 2;
        gridBagConstraintsx16.gridy = 6;
        gridBagConstraintsx16.insets = new Insets(5, 5, 5, 5);
        cPane.add(jbnExit, gridBagConstraintsx16);

        jbnSave.addActionListener(this);
        jbnDelete.addActionListener(this);
        jbnClear.addActionListener(this);
        jbnUpdate.addActionListener(this);
        jbnSearch.addActionListener(this);
        jbnForward.addActionListener(this);
        jbnBack.addActionListener(this);
        jbnExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbnSave) {
            try {
                service.saveDetails(jtfName.getText(), jtfCompany.getText(), jtfPhone.getText(), jtfEmail.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            clear();
        } else if (e.getSource() == jbnDelete) {
            clear();
        } else if (e.getSource() == jbnUpdate) {
            clear();
        } else if (e.getSource() == jbnSearch) {
            openSearchBox();
        } else if (e.getSource() == jbnForward) {

        } else if (e.getSource() == jbnBack) {

        } else if (e.getSource() == jbnClear) {
            clear();
        } else if (e.getSource() == jbnExit) {
            System.exit(0);
        }
    }

    public void clear() {
        jtfName.setText("");
        jtfCompany.setText("");
        jtfPhone.setText("");
        jtfEmail.setText("");
        /*clear contents of arraylist*/
        recordNumber = -1;
        jbnForward.setEnabled(true);
        jbnBack.setEnabled(true);
    }

    public void openSearchBox() {
        JFrame frame = new JFrame();
        TelPhoneDirectory trie = new TelPhoneDirectory();
        frame.getContentPane().add(new SearchBox(trie, jtfName, jtfPhone, jtfEmail, jtfCompany));
        frame.pack();
        frame.setLocationRelativeTo(jbnSearch);
        frame.setVisible(true);
    }
}
