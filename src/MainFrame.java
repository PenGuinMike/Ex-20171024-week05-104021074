import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

public class MainFrame extends JFrame{
    int FrameW =600 ,FrameH=400;
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private JMenuBar jmb = new JMenuBar();

    private JMenu jmf = new JMenu("File");
    private JMenu jmset = new JMenu("Set");
    private JMenu jmg = new JMenu("Game");
    private JMenu jmab = new JMenu("About");


    private JMenuItem jmexit = new JMenuItem("exit");
    private JMenuItem jmload = new JMenuItem("load");
    private JMenuItem jmgame = new JMenuItem("game");
    private JMenuItem jmfont = new JMenuItem("Font");


    private JDesktopPane jdp = new JDesktopPane();
    private JInternalFrame jif = new JInternalFrame();
    private JInternalFrame jif2 = new JInternalFrame();
    private JButton jbtData = new JButton("data");

    private LoginFrame lgf = new LoginFrame();

    private JPanel jpn2 = new JPanel(new GridLayout(2,3,5,5));
    //------------------------------------------------//
    JPanel jpn = new JPanel(new GridLayout(1,6,3,5));
    JPanel jpn1 = new JPanel(new GridLayout(1,2,0,2));
    JButton jbtrun = new JButton("run");
    JButton jbtexit = new JButton("exit");
    JLabel jlb[] = new JLabel[6];

    //-----------------------------------------------//

    private JLabel jlbFamily = new JLabel("Family");
    private JLabel jlbStyle = new JLabel("Style");
    private JLabel jlbSize = new JLabel("Size");

    String data[] ={"PLAIN","BOLD","ITALY","BOLD+ITALY"};

    private JTextField jtfFamily = new JTextField("新細明體");
//    private JTextField jtfStyle = new JTextField("Style");
    private JComboBox jcbStyle = new JComboBox(data);
    private JTextField jtfSize = new JTextField("15");
    private JTextArea jta1 = new JTextArea();
    private JScrollPane jsp1 = new JScrollPane(jta1);
    private JButton jbtnIf2exit = new JButton("exit");
    private JFileChooser jfc = new JFileChooser();




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
        jmb.add(jmset);
        jmb.add(jmg);
        jmb.add(jmab);


        jmf.add(jmload);
        jmg.add(jmgame);
        jmset.add(jmfont);
        jmf.add(jmexit);

        jdp.add(jif);
        jdp.add(jif2);
        jif.setBounds(10,10,250,100);
        jif2.setBounds(10,10,500,300);

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

        jmload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jif2.setVisible(true);
            }
        });
        jif.setLayout(new BorderLayout());
        jif.add(jpn,BorderLayout.CENTER);
        jif.add(jpn1,BorderLayout.SOUTH);

        jif2.setLayout(new BorderLayout());
        jif2.add(jbtData,BorderLayout.NORTH);
        jif2.add(jsp1,BorderLayout.CENTER);
        jif2.add(jbtnIf2exit,BorderLayout.SOUTH);
        jif2.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        jpn1.add(jbtrun);
        jpn1.add(jbtexit);

        jpn2.add(jlbFamily);
        jpn2.add(jlbStyle);
        jpn2.add(jlbSize);
        jpn2.add(jtfFamily);
        jpn2.add(jcbStyle);
        jpn2.add(jtfSize);


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

        jmfont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                clear2();

                int result  = JOptionPane.showConfirmDialog(MainFrame.this,jpn2,"Font-Setting",JOptionPane.OK_CANCEL_OPTION);

                int fontStyle = 0;
                switch (fontStyle){
                    case 0:
                        fontStyle=Font.PLAIN;
                        break;
                    case 1:
                        fontStyle=Font.BOLD;
                        break;
                    case 2:
                        fontStyle=Font.ITALIC;
                        break;
                    case 3:
                        fontStyle=Font.BOLD+Font.ITALIC;
                        break;
                }
                    if (result == JOptionPane.OK_OPTION){
                    UIManager.put("Menu.font",new Font(jtfFamily.getText(),fontStyle,Integer.parseInt(jtfSize.getText())));
                    UIManager.put("MenuItem.font",new Font(jtfFamily.getText(),fontStyle,Integer.parseInt(jtfSize.getText())-2));
                    }

            }
        });

        jbtrun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generate();
            }
        });

        jbtnIf2exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jif2.setVisible(false);
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                lgf.clear1();
                lgf.setVisible(true);
            }
        });

        jbtData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jfc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                    try{
                        File inFile = jfc.getSelectedFile();
                        BufferedReader br = new BufferedReader(new FileReader(inFile));
                        System.out.println("FileName: "+ inFile.getName());
                        String str = "";
                        while((str = br.readLine()) != null){
//                            int x = 5;
                            String original[] = {"書名:","作者:","出版商:","價格:","類別:"};
                            String splitString [] = str.split(",");
                            int splitData = splitString.length;
                            for(int i=0;i<splitData;i++){
                                if(i == splitData-1){
                                    jta1.append(original[i]+splitString[i]+"\n");
                                    jta1.append("---------------------------------------------------------------"+"\n");
                                }else{
                                    jta1.append(original[i]+splitString[i]+"\n");
                                }
                            }
//                                    jta1.append(str+"\n");
                            System.out.println(splitString[0]);
                        }
                        System.out.println("Read File finished!!!");
                    }catch (Exception ioe){
                        JOptionPane.showMessageDialog(null,"Open file error:"+ioe.toString());
                    }
                }
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

    public void clear2 (){
        jtfFamily.setText("");
        jtfSize.setText("");
    }
}
