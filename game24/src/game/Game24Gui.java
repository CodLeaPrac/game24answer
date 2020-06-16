package game;

import calclatour.Calculator;
import calclatour.Game24;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

public class Game24Gui {

    private JFrame jFrame;
    private JButton jButton;
    private JLabel jLabel;
    private JTextField jTextField;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JLabel resultJLabel;

    private int fontSize = 70;

    private Game24 game24;


    public Game24Gui(Game24 game24) {


        this.game24 = game24;

        GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice sd = g.getDefaultScreenDevice();

        DisplayMode displayMode = sd.getDisplayMode();

        this.jFrame = new JFrame("24点游戏答案生成！");

        jFrame.setSize(displayMode.getWidth(), displayMode.getHeight());



        Container contentPane = jFrame.getContentPane();
//        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        contentPane.setLayout(new BorderLayout());
//        contentPane.setLayout(new );

        jButton = new JButton();
        jButton.setText("生成答案");
        jButton.setPreferredSize(new Dimension(120, 40));
        int x = (jFrame.getWidth() - jButton.getWidth()) / 2;
        int y = (jFrame.getHeight() - jButton.getHeight()) / 2;
        jButton.setLocation(x, y);
        jButton.addActionListener(this::callBackButton);

//        jButton.setLocation(400, 800);
//        contentPane.add(jButton, BorderLayout.PAGE_END);
//        contentPane.add(jButton, BorderLayout.PAGE_END);

        jLabel = new JLabel("24点游戏答案生成");
        jLabel.setFont(new Font(null, Font.PLAIN, 40));
        jLabel.setForeground(Color.BLACK);
        jLabel.setPreferredSize(new Dimension(0, 100));
        contentPane.add(jLabel, BorderLayout.PAGE_START);

        jTextField = new JTextField();
        jTextField.setPreferredSize(new Dimension(80, 80));
        jTextField.setFont(new Font(null, Font.PLAIN, fontSize));
        jTextField.setDocument(new NumberDocument());
        jTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                keyHandle(e, jTextField);
            }
        });


//        contentPane.add(jTextField, BorderLayout.CENTER);

         jTextField1 = new JTextField();
         jTextField1.setPreferredSize(new Dimension(80, 80));
        jTextField1.setFont(new Font(null, Font.PLAIN, fontSize));
        jTextField1.setDocument(new NumberDocument());
        jTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                keyHandle(e, jTextField1);
            }
        });

         jTextField2 = new JTextField();
        jTextField2.setPreferredSize(new Dimension(80, 80));
        jTextField2.setFont(new Font(null, Font.PLAIN, fontSize));
        jTextField2.setDocument(new NumberDocument());
        jTextField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                keyHandle(e, jTextField2);
            }
        });

         jTextField3 = new JTextField();
        jTextField3.setPreferredSize(new Dimension(80, 80));
        jTextField3.setFont(new Font(null, Font.PLAIN, fontSize));
        jTextField3.setDocument(new NumberDocument());
        jTextField3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                keyHandle(e, jTextField3);
            }
        });

        JButton clear = new JButton("清空");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField.setText("");
                jTextField1.setText("");
                jTextField2.setText("");
                jTextField3.setText("");
                resultJLabel.setText("此处显示生成答案");
            }
        });

        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel("请输入要计算的4个数值，每个数值最大2位整数"));
        jPanel.add(this.jTextField);
        jPanel.add(this.jTextField1);
        jPanel.add(this.jTextField2);
        jPanel.add(this.jTextField3);
//        jPanel.add(this.showJLable);
        jPanel.add(jButton);
        jPanel.add(clear);
        jPanel.setBackground(Color.ORANGE);
        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(jPanel, BorderLayout.CENTER);



        resultJLabel = new JLabel("此处显示生成答案");
        resultJLabel.setFont(new Font(null, Font.PLAIN, 20));


        resultJLabel.setHorizontalAlignment(JLabel.CENTER);
        resultJLabel.setForeground(Color.RED);
        resultJLabel.setPreferredSize(new Dimension(0, 200));
        contentPane.add(resultJLabel, BorderLayout.PAGE_END);


        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setVisible(true);

    }

    private void callBackButton(ActionEvent e) {

        int[] ints = new int[4];

        String text = jTextField.getText();
        String text1 = jTextField1.getText();
        String text2 = jTextField2.getText();
        String text3 = jTextField3.getText();

        try {
            ints[0] = Integer.parseInt(text);
            ints[1] = Integer.parseInt(text1);
            ints[2] = Integer.parseInt(text2);
            ints[3] = Integer.parseInt(text3);

        }catch (Exception e1) {
            JOptionPane.showMessageDialog(jFrame, "请完整输入！");
            return;
        }



        StringBuilder sb = new StringBuilder();
        List<String> result = this.game24.getResult(ints);

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int i1 = random.nextInt(result.size());
            sb.append(result.get(i1));
            sb.append("     ");
        }

//        JOptionPane.showMessageDialog(jFrame, sb.toString());
        this.resultJLabel.setText(sb.toString());
    }

    private void keyHandle(KeyEvent e, JTextField jTextField) {

        int length = jTextField.getText().length();
        if(length > 2) {
//            JOptionPane.showMessageDialog(jTextField, "最多输入两位整数！");
            jTextField.setText(jTextField.getText().substring(length-2, length));
        }
    }



    public static void main(String[] args) {

        new Game24Gui(new Game24(new Calculator()));

    }


}
