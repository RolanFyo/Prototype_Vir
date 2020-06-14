package com.baka.Main.UI;

import com.baka.Main.data.ProductEntity;
import com.baka.Main.data.ProductJdbcRepo;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class FormVMRam extends JFrame {

    private List<ProductEntity> list;
    private JPanel mainP;
    private ProductJdbcRepo rep;
    private JRadioButton lessThan12;
    private JRadioButton from12to48;
    private JRadioButton from48to128;
    private JRadioButton from128to512;
    private JRadioButton from512;
    private ButtonGroup group = new ButtonGroup();

    public FormVMRam(List<ProductEntity> list, ProductJdbcRepo rep) {
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
        JLabel text = new JLabel("Choose VM's ram amount");
        mainP.add(text);
        lessThan12 = new JRadioButton("till 12 GB");
        from12to48 = new JRadioButton("12 to 48 GB");
        from48to128 = new JRadioButton("48 to 128 GB");
        from128to512 = new JRadioButton("128 to 512 GB");
        from512 = new JRadioButton("from 512 GB");
        group.add(lessThan12);
        group.add(from12to48);
        group.add(from48to128);
        group.add(from128to512);
        group.add(from512);
        Iterator<AbstractButton> iterator = group.getElements().asIterator();
        while (iterator.hasNext()) {
            mainP.add(iterator.next());
        }
        JButton butt = new JButton("Next");
        butt.addActionListener(x -> {
            List<ProductEntity> result = null;
            if (lessThan12.isSelected()) {
                result = list.stream().filter((a) -> a.getVMram() < 12).collect(Collectors.toList());
            } else if (from12to48.isSelected()) {
                result = list.stream().filter((a) -> a.getVMram() < 48 && a.getVMram() >= 12).collect(Collectors.toList());
            } else if (from48to128.isSelected()) {
                result = list.stream().filter((a) -> a.getVMram() < 128 && a.getVMram() >= 48).collect(Collectors.toList());
            } else if (from128to512.isSelected()) {
                result = list.stream().filter((a) -> a.getVMram() < 512 && a.getVMram() >= 128).collect(Collectors.toList());
            } else if (from512.isSelected()) {
                result = list.stream().filter((a) -> a.getVMram() >= 512).collect(Collectors.toList());
            }
            assert result != null;
            System.out.println("1. step result");
            result.forEach(System.out::println);
            new FormDataAmount(result, rep);
            this.dispose();

        });

        JButton skip = new JButton("Skip");

        skip.addActionListener(x -> {
            System.out.println("1. step result");
            list.forEach(System.out::println);
            new FormDataAmount(list, rep);
            this.dispose();
        });

        lessThan12.setSelected(true);
        mainP.add(butt);
        mainP.add(skip);

    }

}
