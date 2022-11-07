package com.Minor1.gui;

import com.Minor1.dto.Directory;
import com.Minor1.service.TelPhoneDirectory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class SearchBox extends JPanel {
    private final JTextField tf;
    private final JComboBox combo = new JComboBox();

    public SearchBox(TelPhoneDirectory trie, JTextField jtfName, JTextField jtfPhone, JTextField jtfEmail, JTextField jtfCompany) {
        super(new BorderLayout());
        combo.setEditable(true);
        tf = (JTextField) combo.getEditor().getEditorComponent();
        tf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        String text = tf.getText();
                        if (text.length() == 0) {
                            combo.hidePopup();
                            setModel(new DefaultComboBoxModel(), "");
                        } else {
                            DefaultComboBoxModel m = getSuggestedModel(trie, text);
                            if (m.getSize() == 0) {
                                combo.hidePopup();
                            } else {
                                setModel(m, text);
                                combo.showPopup();
                            }
                        }
                    }
                });
            }
        });

        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cBox = (JComboBox) e.getSource();
                Directory selectedItem = (Directory) cBox.getSelectedItem();
                if (selectedItem != null) {
                    jtfName.setText(selectedItem.getFullName());
                    jtfCompany.setText(selectedItem.getCompanyName());
                    jtfPhone.setText(selectedItem.getPhone());
                    jtfEmail.setText(selectedItem.getEmail());
                } else {
                    jtfName.setText(null);
                    jtfCompany.setText(null);
                    jtfPhone.setText(null);
                    jtfEmail.setText(null);
                }
            }
        });

        setModel(new DefaultComboBoxModel(), "");
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createTitledBorder("Select Contact to check details"));
        p.add(combo, BorderLayout.NORTH);
        add(p);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setPreferredSize(new Dimension(300, 150));
    }

    private void setModel(DefaultComboBoxModel mdl, String str) {
        combo.setModel(mdl);
        combo.setSelectedIndex(-1);
        tf.setText(str);
    }

    private static DefaultComboBoxModel getSuggestedModel(TelPhoneDirectory trie, String text) {
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        List<Directory> names = trie.queryResults(text);
        if (names != null) {
            names.stream().forEach(x -> m.addElement(x));
        }
        return m;
    }
}
