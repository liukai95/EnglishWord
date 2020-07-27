package Boundary;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.*;
import javax.swing.JButton;
 
public class main_ui extends JFrame implements ActionListener {
   
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   private JLabel selectFiction;
   private JButton wordfind;
   private JButton fileanalyse;
   private JButton exitbutton;
   private JPanel content_panel;
   
  public main_ui(){
	  //设置各种组件及其内部显示的内容
    setTitle("英文词频统计-by 极限四人组");
    setFont(new Font("Times New Roman",Font.PLAIN,12));
    setBackground(Color.WHITE);
    setSize(500,400);   
    
    setLocationRelativeTo(null); 
	this.setResizable(true);
	getContentPane().setLayout(null);
	 //初始化内容面板
	Image icon = Toolkit.getDefaultToolkit().getImage("Images\\Icon.jpg");
	setIconImage(icon);
    content_panel = new JPanel() {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(new ImageIcon("Images\\image3.jpg").getImage(), 0, 0, getWidth(), getHeight(), null);
        }
    };
    setContentPane(content_panel);
    content_panel.setLayout(null);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	
	selectFiction = new JLabel("\u529F\u80FD\u9009\u62E9\uFF1A");
	selectFiction.setBounds(14, 86, 68, 15);
	content_panel.add(selectFiction);
	
	wordfind = new JButton("\u5355\u8BCD\u67E5\u8BE2");
	wordfind.setBounds(92, 82, 93, 23);
	content_panel.add(wordfind);
	
	fileanalyse = new JButton("\u6587\u4EF6\u5206\u6790");
	fileanalyse.setBounds(92, 145, 93, 23);
	content_panel.add(fileanalyse);
	
	exitbutton = new JButton("\u9000\u51FA");
	exitbutton.setBounds(92, 207, 93, 23);
	content_panel.add(exitbutton);
	 
    //添加监听器
    addWindowListener(new WindowAdapter(){
                      public void windowClosing(WindowEvent e){
                          System.exit(0);}});
    
    wordfind.addActionListener(this);
    fileanalyse.addActionListener(this);
    exitbutton.addActionListener(this);

  }
  
  
  @SuppressWarnings("deprecation")
public static void main(String []args){
	   new main_ui().show();
  }
  
 
   @SuppressWarnings("deprecation")
public void actionPerformed(ActionEvent e) {  //监听事件
    Object eventSource = e.getSource();
    //如果点击“查询单词”选项
    if(eventSource == wordfind){
    	//显示查询的子窗口
    	JFrame wordinput = new WordInput(this);
    	setVisible(false);
    	wordinput.show();
    }
    //如果点击“单文件文档分析”选项
    else if(eventSource == fileanalyse){
    	JFrame File = new file(this);
    	File.show();
    	setVisible(false);
    }
    //如果点击“查退出”选项
    else if(eventSource == exitbutton){
      System.exit(0);
    }
  }

}