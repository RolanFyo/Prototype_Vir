package com.baka.Main.UI;

import com.baka.Main.data.ProductEntity;
import com.baka.Main.data.ProductJdbcRepo;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class FormDataAmount extends JFrame {
    private List<ProductEntity> list;
    private JPanel mainP;
    private JLabel text;
    private ProductJdbcRepo rep;
    private JRadioButton lessThan500;
    private JRadioButton from500to1000;
    private JRadioButton from1000to2000;
    private JRadioButton from2000to4000;
    private JRadioButton from4000;
    private ButtonGroup group = new ButtonGroup();

    public FormDataAmount(List<ProductEntity> list, ProductJdbcRepo rep) {
        this.list = list;
        this.rep = rep;
        setPanel();

        setContentPane(mainP);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void setPanel() {
        mainP = new JPanel();
        mainP.setLayout(new GridLayout(0, 1, 500, 10));
        text = new JLabel("Choose VM's data amount");
        mainP.add(text);
        lessThan500 = new JRadioButton("till 500 GB");
        from500to1000= new JRadioButton("500 to 1000 GB");
        from1000to2000 = new JRadioButton("1000 to 2000 GB");
        from2000to4000 = new JRadioButton("2000 to 4000 GB");
        from4000 = new JRadioButton("from 4000 GB");
        group.add(lessThan500);
        group.add(from500to1000);
        group.add(from1000to2000);
        group.add(from2000to4000);
        group.add(from4000);
        Iterator<AbstractButton> iterator = group.getElements().asIterator();
        while (iterator.hasNext()) {
            mainP.add(iterator.next());
        }
        JButton butt = new JButton("Next");
        butt.addActionListener(x -> {
            List<ProductEntity> result = null;
            if (lessThan500.isSelected()) {
                result = list.stream().filter((a) -> a.getDataAmount() < 500).collect(Collectors.toList());
            } else if (from500to1000.isSelected()) {
                result = list.stream().filter((a) -> a.getDataAmount() < 1000 && a.getDataAmount() >= 500).collect(Collectors.toList());
            } else if (from1000to2000.isSelected()) {
                result = list.stream().filter((a) -> a.getDataAmount() < 2000 && a.getDataAmount() >= 1000).collect(Collectors.toList());
            } else if (from2000to4000.isSelected()) {
                result = list.stream().filter((a) -> a.getDataAmount() < 4000 && a.getDataAmount() >= 2000).collect(Collectors.toList());
            } else if (from4000.isSelected()) {
                result = list.stream().filter((a) -> a.getDataAmount() >= 4000).collect(Collectors.toList());
            }
            System.out.println("2. step result");
            assert result != null;
            result.forEach(System.out::println);
            new FormSessionCount(result, rep);
            this.dispose();

        });

        JButton skip = new JButton("Skip");

        skip.addActionListener(x -> {
            System.out.println("2. step result");
            list.forEach(System.out::println);
            new FormSessionCount(list, rep);
            this.dispose();
        });

        lessThan500.setSelected(true);
        mainP.add(butt);
        mainP.add(skip);

    }

}
