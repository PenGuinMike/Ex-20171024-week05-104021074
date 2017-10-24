import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class MainFrame extends JFrame{
    int FrameW =600 ,FrameH=400;
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private JMenuBar jmb = new JMenuBar();

    private JMenu jmf = new JMenu("File");
    private JMenu jmg = new JMenu("Game");
    private JMenu jmab = new JMenu("About");


    private JMenuItem jmexit = new JMenuItem("exit");
    private JMenuItem jmgame = new JMenuItem("game");

    private JDesktopPane jdp = new JDesktopPane();
    private JInternalFrame jif = new JInternalFrame();

    private LoginFrame lgf = new LoginFrame();

    //------------------------------------------------//
    JPanel jpn = new JPanel(new GridLayout(1,6,3,5));
    JPanel jpn1 = new JPanel(new GridLayout(1,2,0,2));
    JButton jbtrun = new JButton("run");
    JButton jbtexit = new JButton("exit");
    JLabel jlb[] = new JLabel[6];

    public MainFrame (LoginFrame loginFrame){
        lgf=loginFrame;
        init();
    }

    private void init(){
//        this.setLayout(new BorderLayout());
        this.setBounds(dim.width/2-FrameW/2,dim.height/2-FrameH/2,FrameW,FrameH);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setJMenuBar(jmb);
        this.setContentPane(jdp);
//        this.add(jmb,BorderLayout.NORTH);
        jmb.add(jmf);
        jmb.add(jmg);
        jmb.add(jmab);

        jmf.add(jmexit);
        jmg.add(jmgame);

        jdp.add(jif);
        jif.setBounds(10,10,250,100);
        jmgame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jif.setVisible(true);
            }
        });

        jmexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jmexit.setAccelerator(KeyStroke.getKeyStroke('X',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmgame.setAccelerator(KeyStroke.getKeyStroke('L',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));


        jif.setLayout(new BorderLayout());
        jif.add(jpn,BorderLayout.CENTER);
        jif.add(jpn1,BorderLayout.SOUTH);

        jpn1.add(jbtrun);
        jpn1.add(jbtexit);

        for(int i=0;i<6;i++){
            jlb[i] = new JLabel("0");
            jlb[i].setHorizontalAlignment(SwingConstants.CENTER);
            jlb[i].setOpaque(true);
            jlb[i].setBackground(new Color(177, 175, 44));
            jpn.add(jlb[i]);
        }
        generate();

        jbtexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jif.setVisible(false);
//                jif.dispose();
            }
        });

        jbtrun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generate();
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                lgf.clear1();
                lgf.setVisible(true);
            }
        });


    }

    private void generate (){
        Random rnd = new Random(System.currentTimeMillis());
        for(int i=0;i<6;i++){
            jlb[i].setText(Integer.toString(rnd.nextInt(48)+1));
            for(int j=0;j<i;j++){
                if(jlb[i].equals(jlb[j])){
                    i--;
                }else{

                }
            }
        }
    }
}
