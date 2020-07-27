package Boundary;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import word.TiquWord;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class headfoot_ui extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String head,foot,path,result = "结果显示";
	private JMenuBar  menubar;
	private JTextField headtext;
	private JTextField foottext;
	private JButton   yesbutton;
	private JButton exitbutton;
	private JTextArea resultArea;
	private JScrollPane scrollPane;

	
	public headfoot_ui(String _path) {
		//获得文件路径
		this.path = _path;
		//设置居中显示
		
		//添加图标
        Image icon = Toolkit.getDefaultToolkit().getImage("Images\\Icon.jpg");
        setIconImage(icon);
	    setLocationRelativeTo(null); 
	    setSize(400,300);
	    getContentPane().setLayout(null);
	    setResizable(false);
	    menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		JLabel label = new JLabel("\u8BCD\u5934");
		menubar.add(label);
		
		headtext = new JTextField();
		menubar.add(headtext);
		headtext.setColumns(10);
		
		JLabel label_1 = new JLabel("\u8BCD\u5C3E");
		menubar.add(label_1);
		
		foottext = new JTextField();
		menubar.add(foottext);
		foottext.setColumns(10);
		
		yesbutton = new JButton("\u786E\u5B9A");
		menubar.add(yesbutton);
		
		exitbutton = new JButton("\u9000\u51FA");
		menubar.add(exitbutton);
		
		resultArea = new JTextArea();
		resultArea.setEditable(false);
		resultArea.setTabSize(4);
		resultArea.setLineWrap(true);
		resultArea.setText(result);
		
		scrollPane = new JScrollPane(resultArea);
		scrollPane.setBounds(0, 0, 394, 272);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);


		getContentPane().add(scrollPane);
		yesbutton.addActionListener(this);
		exitbutton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object event = e.getSource();
		if(event == yesbutton)
		{
			//获取词头词尾
			head = headtext.getText();
			foot = foottext.getText();
			//调用函数
			TiquWord ti = new TiquWord(path);
			ti.init();
			result = ti.htWord(head, foot);
			//结果显示
			resultArea.setText(result);
		}
		else if(event == exitbutton)
		{
			setVisible(false);
		}
	}
}
