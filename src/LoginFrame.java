import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    Container cp ;
    private JLabel jlbID = new JLabel("ID:");
    private JLabel jlbPW = new JLabel("PassWord:");
    private JTextField jtfID = new JTextField();
    //    private JTextField jtfPW = new JTextField();
    private JPasswordField jtfPW = new JPasswordField();
    private JButton jbtExit = new JButton("Exit");
    private JButton jbtLogin = new JButton("Login");
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private int FrameW =300,FrameH =150;

    public LoginFrame (){
        init();
    }
    private void init(){
        this.setBounds(dim.width/2-FrameW/2,dim.height/2-FrameH/2,FrameW,FrameH);
        cp = this.getContentPane();
        cp.setLayout(new GridLayout(3,2,2,2));
        this.setDefaultCloseOperation(3);
        cp.add(jlbID);
        cp.add(jtfID);
        cp.add(jlbPW);
        cp.add(jtfPW);
        cp.add(jbtExit);
        cp.add(jbtLogin);

        jbtExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jbtLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(jtfID.getText().equals("h304")&&new String(jtfPW.getPassword()).equals("23323456")){
//                    System.out.println("Hello ~ it`s me");
                    MainFrame mfe = new MainFrame(LoginFrame.this);
//                    MainFrame mfe = new MainFrame();
//                    setDefaultCloseOperation(LoginFrame.DISPOSE_ON_CLOSE);
                    mfe.setVisible(true);
//                    LoginFrame.this.setVisible(false);

                }else{
                    JOptionPane.showMessageDialog(LoginFrame.this, "帳號或密碼錯誤!");
                }
            }
        });
    }

    public void clear1 (){
        jtfID.setText("");
        jtfPW.setText("");
    }
}