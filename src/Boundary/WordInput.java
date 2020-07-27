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
		//���ô��ڱ����Լ����ڴ�С
		this.setMainui(mainui);
		setTitle("���ʲ�ѯ");
		setSize(400,300);
        this.setResizable(false);

		 //������ʾ
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		//���ͼ��
        Image icon = Toolkit.getDefaultToolkit().getImage("Images\\Icon.jpg");
        setIconImage(icon);
		/*
		 * ���崰���ϵ����
		 */
		//������ʾ��ѯ�����JTextArea
		resultArea = new JTextArea();
		resultArea.setBounds(10, 57, 364, 176);
		getContentPane().add(resultArea);
		
		//���������ѯ���ʵ�JTextArea
		wordArea = new JTextArea();
		wordArea.setBounds(10, 10, 266, 24);
		getContentPane().add(wordArea);
		
	    //����Label	
		JLabel label = new JLabel("\u82F1\u6587\u8BCD\u9891\u7EDF\u8BA1\u5668\u2014\u5355\u8BCD\u67E5\u8BE2");
		label.setBounds(10, 243, 183, 15);
		getContentPane().add(label);
		
		//�����ѯ��Button
		yesbutton = new JButton("\u67E5\u8BE2");
		yesbutton.setBounds(286, 10, 93, 23);
		getContentPane().add(yesbutton);
		
		//�����˳���Button
		exitbutton = new JButton("\u9000\u51FA");
		exitbutton.setBounds(286, 239, 93, 23);
		getContentPane().add(exitbutton);
		
		//���ò�ѯButton��ť�ļ�����
		yesbutton.addActionListener(this);
		//�˳���ť������
		exitbutton.addActionListener(this);
	}

//ʵ�ּ���������	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        Object  event = e.getSource();
        if(event == yesbutton)
        {
        	String word=wordArea.getText();
        	word=word.toLowerCase();
        	word=word.replaceAll(Matcher.quoteReplacement("[^a-z]"), "");// ����26����ĸ��'����������ַ�ȫ�ÿո����
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
