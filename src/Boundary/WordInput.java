package Boundary;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import javax.swing.JFrame;
import word.FindWord;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;



public class WordInput extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame mainui = new main_ui();
	private String result = null;
	private JButton yesbutton ;
	private JButton exitbutton;
	private JTextArea wordArea;
	private JTextArea resultArea;
	Map<String, String> hm = new HashMap<String, String>();
	public WordInput(JFrame mainui) {
		//设置窗口标题以及窗口大小
		this.setMainui(mainui);
		setTitle("单词查询");
		setSize(400,300);
        this.setResizable(false);

		 //居中显示
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		//添加图标
        Image icon = Toolkit.getDefaultToolkit().getImage("Images\\Icon.jpg");
        setIconImage(icon);
		/*
		 * 定义窗口上的组件
		 */
		//定义显示查询结果的JTextArea
		resultArea = new JTextArea();
		resultArea.setBounds(10, 57, 364, 176);
		getContentPane().add(resultArea);
		
		//定义输入查询单词的JTextArea
		wordArea = new JTextArea();
		wordArea.setBounds(10, 10, 266, 24);
		getContentPane().add(wordArea);
		
	    //定义Label	
		JLabel label = new JLabel("\u82F1\u6587\u8BCD\u9891\u7EDF\u8BA1\u5668\u2014\u5355\u8BCD\u67E5\u8BE2");
		label.setBounds(10, 243, 183, 15);
		getContentPane().add(label);
		
		//定义查询的Button
		yesbutton = new JButton("\u67E5\u8BE2");
		yesbutton.setBounds(286, 10, 93, 23);
		getContentPane().add(yesbutton);
		
		//定义退出的Button
		exitbutton = new JButton("\u9000\u51FA");
		exitbutton.setBounds(286, 239, 93, 23);
		getContentPane().add(exitbutton);
		
		//设置查询Button按钮的监听器
		yesbutton.addActionListener(this);
		//退出按钮被按下
		exitbutton.addActionListener(this);
	}

//实现监听器函数	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        Object  event = e.getSource();
        if(event == yesbutton)
        {
        	String word=wordArea.getText();
        	word=word.toLowerCase();
        	word=word.replaceAll(Matcher.quoteReplacement("[^a-z]"), "");// 除了26个字母和'以外的所有字符全用空格替代
        	result=new FindWord().find(word);
        	resultArea.setText(result);
        }
        else if(event == exitbutton)
        {
        	setVisible(false);
        	mainui.setVisible(true);
        }
	}

	public JFrame getMainui() {
		return mainui;
	}

	public void setMainui(JFrame mainui) {
		this.mainui = mainui;
	}
	
}
