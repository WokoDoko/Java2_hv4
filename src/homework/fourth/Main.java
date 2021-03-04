package homework.fourth;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;


public class Main extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;


    //Большие элементы интерфейса
    private final JPanel panelTop = new JPanel(new FlowLayout(5));
    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JTextArea messageLog = new JTextArea();
    private final JList<String> userList = new JList<>();


    //Поля ввода
    private final JTextField tfMessage = new JTextField();
    private final JTextField tfIPAddress = new JTextField("Please insert IP address here");
    private final JTextField tfPort = new JTextField("Please insert PORT here");
    private final JTextField tfLogin = new JTextField("Nickname");
    private final JPasswordField tfPassword = new JPasswordField("password");


    //Кнопки
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top");
    private final JCheckBox cbDoNotDisturb = new JCheckBox("Don't disturb");
    private final JButton btnLogin = new JButton("Login");
    private final JButton btnDisconnect = new JButton("<html><b>Disconnect</b></html>");
    private final JButton btnSend = new JButton("Send");

    //Объявление окон с ползунками
    private JScrollPane scrollLog = new JScrollPane(messageLog);
    private JScrollPane scrollUsers = new JScrollPane(userList);


    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }


    public Main() {

        //Настройки окна
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH,HEIGHT));
        setResizable(true);
        setVisible(true);
        add(panelTop, BorderLayout.NORTH);
        add(scrollLog, BorderLayout.CENTER);
        add(scrollUsers, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        //Настройки верхней панели
        panelTop.setPreferredSize(new DimensionUIResource(700,70));
        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(cbDoNotDisturb);

        //Настройки нижней панели
        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);

        //Настройки лога сообщний
        messageLog.setEditable(false);
        messageLog.setRequestFocusEnabled(true);

        //Настройки окна пользователей
        scrollUsers.setPreferredSize(new Dimension(130, 0));

        //Настройки кнопок
        cbAlwaysOnTop.addActionListener(this);
        btnSend.addActionListener(this);






        Thread.setDefaultUncaughtExceptionHandler(this); //что это??


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        } else if (src == btnSend) {
           messageLog.append(tfMessage.getText() + System.lineSeparator());
           tfMessage.setText("");
           String message = tfMessage.getText();
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        StackTraceElement[] ste = e.getStackTrace();
        JOptionPane.showMessageDialog(this, ste[0].toString(), "Exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}
