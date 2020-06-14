package com.baka.Main.UI;

import com.baka.Main.data.ProductEntity;
import com.baka.Main.data.ProductJdbcRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ResultForm extends JFrame {
    private List<ProductEntity> list;
    private ProductJdbcRepo rep;
    private int counter = 0;

    public ResultForm(List<ProductEntity> list, ProductJdbcRepo rep) {
        this.list = list;
        this.rep = rep;
        JFrame frame = new JFrame();
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(new GridBagLayout());

        if (list.size() == 0) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "No options found, retry?", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                new FormVMRam(rep.findAll(), rep);
                this.dispose();
            } else {
                System.exit(0);
            }
        } else {
            frame.setSize(new Dimension(600, 400));
            JTextField result = new JTextField();
            result.setEditable(false);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridwidth = 2;
            gbc.gridheight = 1;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(0, 0, 0, 0);
            result.setText(list.get(counter).print());
            result.setHorizontalAlignment(JTextField.CENTER);
            frame.add(result, gbc);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.ipady = 10;
            gbc.ipadx = 300;
            gbc.gridy = 1;
            gbc.gridx = 0;

            JButton back = new JButton();
            back.setText("<<");

            back.addActionListener(x -> {
                if (counter == 0) {
                    counter = list.size() - 1;
                } else {
                    counter--;
                }
                frame.setTitle(String.format("Results %d/%d", counter + 1, list.size()));
                result.setText(list.get(counter).print());
            });

            frame.add(back, gbc);

            gbc.gridx = 1;
            JButton forward = new JButton();
            forward.setText(">>");

            forward.addActionListener(x -> {
                if (counter == list.size() - 1) {
                    counter = 0;
                } else {
                    counter++;
                }
                frame.setTitle(String.format("Results %d/%d", counter + 1, list.size()));
                result.setText(list.get(counter).print());
            });

            frame.add(forward, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;

            JButton exit = new JButton("exit");
            frame.add(exit, gbc);
            exit.addActionListener(x -> System.exit(0));
            gbc.gridx = 1;

            JButton restart = new JButton("restart");
            restart.addActionListener(x -> {
                new FormVMRam(rep.findAll(), rep);
                frame.dispose();
            });
            frame.add(restart, gbc);

            frame.pack();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setTitle(String.format("Results %d/%d", counter + 1, list.size()));

            frame.setVisible(true);

        }
    }
}
