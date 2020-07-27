package Boundary;

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
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Image;

public class lengthword_ui extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
    private String      result = " 结果显示";	
    private int     length=0;
    private String    path = null;
    private JTextArea  resultArea;
	private JMenuBar    menubar;
	private JLabel label;
	private JTextField lengthword;
	private JButton button;
	private JButton exitbutton;

    
	 lengthword_ui(String _path) {
		 this.path = _path;
		//设置居中显示
	   //添加图标
	    Image icon = Toolkit.getDefaultToolkit().getImage("Images\\Icon.jpg");
	    setIconImage(icon);
	    setLocationRelativeTo(null); 
		setResizable(false);
		setTitle("\u63D0\u53D6\u5B9A\u957F\u5EA6\u5355\u8BCD");
		setSize(400, 300);
		getContentPane().setLayout(null);

		menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		label = new JLabel("\u5355\u8BCD\u957F\u5EA6");
		menubar.add(label);
		
		lengthword = new JTextField();
		menubar.add(lengthword);
		lengthword.setColumns(10);
        
		
		button = new JButton("\u786E\u5B9A");
		menubar.add(button);
		
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
		
		button.addActionListener(this);
		exitbutton.addActionListener(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object event = e.getSource();
		if(event == button)
		{
			//获取单词长度
			length = Integer.parseInt(lengthword.getText());
			//调用查询方法
			TiquWord ti = new TiquWord(path);
			ti.init();
			result = ti.lengthWord(length);
			//结果显示
			resultArea.setText(result);
		}
		else if(event == exitbutton)
		{
			 setVisible(false);
		}
	}

}
