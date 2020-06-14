package com.baka.Main.UI;

import com.baka.Main.data.ProductEntity;
import com.baka.Main.data.ProductJdbcRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class FormSessionCount extends JFrame{
    private List<ProductEntity> list;
    private JPanel mainP;
    private JLabel text;
    private ProductJdbcRepo rep;
    private JRadioButton lessThan3;
    private JRadioButton from3to6;
    private JRadioButton from6to12;
    private JRadioButton from12;
    private ButtonGroup group = new ButtonGroup();

    public FormSessionCount(List<ProductEntity> list, ProductJdbcRepo rep) {
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
        text = new JLabel("Choose VM's concurrent session amount");
        mainP.add(text);
        lessThan3 = new JRadioButton("till 3 sessions");
        from3to6 = new JRadioButton("3 to 6 sessions");
        from6to12 = new JRadioButton("6 to 12 sessions");
        from12 = new JRadioButton("from 12");
        group.add(lessThan3);
        group.add(from3to6);
        group.add(from6to12);
        group.add(from12);
        Iterator<AbstractButton> iterator = group.getElements().asIterator();
        while (iterator.hasNext()) {
            mainP.add(iterator.next());
        }
        JButton butt = new JButton("Results");
        butt.addActionListener(x -> {
            List<ProductEntity> result = null;
            if (lessThan3.isSelected()) {
                result = list.stream().filter((a) -> a.getSeessionCount() < 3).collect(Collectors.toList());
            } else if (from3to6.isSelected()) {
                result = list.stream().filter((a) -> a.getSeessionCount() < 6 && a.getSeessionCount() >= 3).collect(Collectors.toList());
            } else if (from6to12.isSelected()) {
                result = list.stream().filter((a) -> a.getSeessionCount() < 12 && a.getVMram() >= 6).collect(Collectors.toList());
            } else if (from12.isSelected()) {
                result = list.stream().filter((a) -> a.getSeessionCount() >= 12).collect(Collectors.toList());
            }
            System.out.println("3. step results");
            assert result != null;
            result.forEach(System.out::println);
            new ResultForm(result, rep);
            this.dispose();

        });

        JButton skip = new JButton("Skip");

        skip.addActionListener(x -> {
            System.out.println("1. step result");
            list.forEach(System.out::println);
            new ResultForm(list, rep);
            this.dispose();
        });


        lessThan3.setSelected(true);
        mainP.add(butt);
        mainP.add(skip);
    }

}
