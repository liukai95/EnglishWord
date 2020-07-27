package Boundary;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import word.CompareTwo;
import word.WordNum;
import word.WordVary;
import word.BeyondWord;

import javax.swing.JEditorPane;
import javax.swing.JRadioButton;

public class file extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private JFrame mainui;
	private ButtonGroup singledouble;
	private JCheckBox singlebox;
	private JCheckBox doublebox;
	
    private ButtonGroup fictionbutton;
   	private JButton Browse1 ;
	private JButton Browse2;
	
	private JRadioButton wordtype;
	private JRadioButton frequent;
	private JRadioButton outnum;
	private JRadioButton headfoot;
	private JRadioButton lengthword;
	private JRadioButton totalnumword;
	
	private JButton yesbutton;
	private JButton exitbutton;
	private JLabel casion;
	
	private JEditorPane path1;
	private JEditorPane path2;
	
	private String repath1=null;
	private String repath2=null;
	private String result ;
	private JFileChooser fileChooser;
    private JFrame outui;
	
	public file(JFrame mainui) {
		this.mainui = mainui;
        this.setResizable(false);
        setSize(550, 400);
        setTitle("文件分析");
		//设置居中显示
		//添加图标
        Image icon = Toolkit.getDefaultToolkit().getImage("Images\\Icon.jpg");
        setIconImage(icon);
	    setLocationRelativeTo(null); 
	    getContentPane().setLayout(null);
	    
		//1.统计结果的排序方式：词频升序、词频降序、字母升序、字母降序
		fictionbutton  = new ButtonGroup();
		new ButtonGroup();
		
	    singlebox = new JCheckBox("\u5355\u6587\u4EF6\u5206\u6790");
	    singlebox.setSelected(true);
	    singlebox.setBounds(102, 6, 103, 23);
	    getContentPane().add(singlebox);
	    
	    doublebox = new JCheckBox("\u53CC\u6587\u4EF6\u5206\u6790");
	    doublebox.setBounds(297, 6, 103, 23);
	    getContentPane().add(doublebox);
        //设置单选框
		singledouble = new ButtonGroup();
		singledouble.add(singlebox);
		singledouble.add(doublebox);
		
		JLabel file1 = new JLabel("\u6587\u4EF61");
		file1.setBounds(58, 35, 54, 15);
		getContentPane().add(file1);
		
		path1 = new JEditorPane();
		path1.setBounds(112, 35, 310, 21);
		getContentPane().add(path1);
		
		Browse1 = new JButton("Browse.");
		Browse1.setBounds(432, 31, 93, 23);
		getContentPane().add(Browse1);
		
		JLabel file2 = new JLabel("\u6587\u4EF62");
		file2.setBounds(58, 60, 54, 15);
		getContentPane().add(file2);
		
		path2 = new JEditorPane();
		path2.setBounds(112, 60, 310, 21);
		getContentPane().add(path2);
		
		Browse2 = new JButton("Browse..");
		Browse2.setEnabled(false);
		Browse2.setBounds(432, 64, 93, 23);
		getContentPane().add(Browse2);
		
		JLabel twoOrone = new JLabel("\u529F\u80FD\u9009\u62E9");
		twoOrone.setBounds(58, 143, 92, 28);
		getContentPane().add(twoOrone);
		
		wordtype = new JRadioButton("\u8BCD\u6027\u5206\u6790");
		wordtype.setBounds(156, 146, 121, 23);
		getContentPane().add(wordtype);
		
		frequent = new JRadioButton("\u8BCD\u9891\u7EDF\u8BA1");
		frequent.setBounds(297, 146, 121, 23);
		getContentPane().add(frequent);
		
		outnum = new JRadioButton("\u8D85\u7EB2\u7EDF\u8BA1");
		outnum.setBounds(156, 183, 121, 23);
		getContentPane().add(outnum);
		
		headfoot = new JRadioButton("\u8BCD\u5934\u8BCD\u5C3E\u63D0\u53D6");
		headfoot.setBounds(297, 183, 121, 23);
		getContentPane().add(headfoot);
		
		lengthword = new JRadioButton("\u5B9A\u957F\u53D6\u8BCD");
		lengthword.setBounds(156, 215, 121, 23);
		getContentPane().add(lengthword);
		
		totalnumword = new JRadioButton("\u53CC\u6587\u6863\u603B\u8BCD\u6C47");
		totalnumword.setEnabled(false);
		totalnumword.setBounds(297, 215, 121, 23);
		getContentPane().add(totalnumword);
		
		fictionbutton.add(frequent);
		fictionbutton.add(wordtype);
		fictionbutton.add(totalnumword);
		fictionbutton.add(outnum);
		fictionbutton.add(lengthword);
		fictionbutton.add(headfoot);
		
		yesbutton = new JButton("\u786E\u5B9A");
		yesbutton.setBounds(10, 326, 93, 23);
		getContentPane().add(yesbutton);
		
	    exitbutton = new JButton("\u8FD4\u56DE");
		exitbutton.setBounds(432, 326, 93, 23);
		getContentPane().add(exitbutton);
		
		casion = new JLabel("");
		casion.setBounds(151, 112, 267, 15);
		getContentPane().add(casion);


		fileChooser = new JFileChooser();
		//设置只打开txt和wod文档
		fileChooser.setFileFilter(new FileFilter()
		{

			@Override
			public boolean accept(File f)
			{
				if (f.getName().endsWith(".txt")||f.getName().endsWith(".doc"))
				{
					return true;
				}
				return false;
			}

			@Override
			public String getDescription()
			{
				return "TXT file";
			}

		});

		//添加按钮监听器
        Browse1.addActionListener(this);
        Browse2.addActionListener(this);
        yesbutton.addActionListener(this);
        exitbutton.addActionListener(this); 
        //设置鼠标监听器
        singlebox.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				casion.setText("请选择功能和导出方式（每种只能选择一项）");
				Browse1.setEnabled(true);
				Browse2.setEnabled(false);
				totalnumword.setEnabled(false);
				frequent.setEnabled(true);
				wordtype.setEnabled(true);
				outnum.setEnabled(true);
				lengthword.setEnabled(true);
				headfoot.setEnabled(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
        doublebox.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				Browse2.setEnabled(true);
				totalnumword.setEnabled(true);
				frequent.setEnabled(false);
				wordtype.setEnabled(false);
				outnum.setEnabled(false);
				lengthword.setEnabled(false);
				headfoot.setEnabled(false);
			}
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
        
        headfoot.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@SuppressWarnings("deprecation")
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				repath1 = path1.getText();
				JFrame headfootui = new headfoot_ui(repath1);
				headfootui.show();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
       lengthword.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@SuppressWarnings("deprecation")
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
			JFrame lengthwordui = new lengthword_ui(path1.getText());
			lengthwordui.show();
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	});

	}
	
	//鼠标点击监听器
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object event = e.getSource();
		if(event == yesbutton)
		{
			//如果是双文件分析被选中,则默认选择的是总词汇数目的选项
			if(doublebox.isSelected()){
				if(totalnumword.isSelected()){
					if(totalnumword.isSelected()){totalnumword.setSelected(false);}
					else if(!totalnumword.isSelected()){totalnumword.setSelected(true);}
				//获取两个文件的路径
				repath1 = path1.getText();
				repath2 = path2.getText();
				//调用查询函数
				try {
					
					outui  = new out_ui(new CompareTwo().Compare(repath1,repath2));
					outui.show();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
			//否则就是单文件被选中
			else {
				//词性分析
				if(wordtype.isSelected())	{
					//获取文件路径
					repath1 = path1.getText();			
					//结果显示
					result = new WordVary().VarySta(repath1);
					outui  = new out_ui(result);
					outui.show();
				}
				//词频统计
				else if(frequent.isSelected()){
                     
					//获取文件路径
					repath1 = path1.getText();
					//调用查询函数
					WordNum wn = new WordNum(repath1);
					wn.init();
					
					outui  = new out_ui(wn.getmap());
					outui.show();
				}
				//超纲词统计
				else if(outnum.isSelected()){
					//获取文件路径
					repath1 = path1.getText();
					System.out.print(repath1);
					if(repath1!=null){
						//调用查询函数
						BeyondWord by = new BeyondWord();
						//调用显示界面
						outui  = new out_ui(by.getbeyondWord(repath1));
						outui.show();
					}
					
			}
		}
	}
		else if(event == exitbutton){
			mainui.setVisible(true);
			this.setVisible(false);
		}
		else if(event == Browse1 ){
			fileChooser.showOpenDialog(this);
			File f = fileChooser.getSelectedFile();
			repath1=f.getAbsolutePath();
		    path1.setText(repath1);
		}
		else if(event == Browse2){
			fileChooser.showOpenDialog(this);
			File ff = fileChooser.getSelectedFile();
			repath2=ff.getAbsolutePath();
		    path2.setText(repath2);
		}	
      }

}