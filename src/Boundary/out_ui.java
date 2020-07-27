package Boundary;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileFilter;

import FileIO.FileSave;
import word.Sort;

public class out_ui extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JTextArea   resultArea;
	private JMenuBar    menubar;
	private JMenu        file;

	private JMenuItem   exitmenu;
	private JMenuItem   savemenu;
	private JMenuItem   savemoremenu;
	
	private JMenu        sort;
	private JMenuItem   frequentinmenu;
	private JMenuItem   frequentdemenu;
	private JMenuItem   alphinmenu;
	private JMenuItem   alphdemenu;

	private JMenu        filetype;
	private  JMenuItem   txttype;
	private JMenuItem    doctype;
	private JMenuItem    xlstype;
	
	private JFileChooser fileChooser;
	private String fileName=null;
    private String result=null;
    @SuppressWarnings("rawtypes")
	private  Map resultmap=null;
    
    //保存存储类型
    String savetype=".txt";
    //传入排序结果
    Sort sorts=new Sort();
    //标记保存，0：保存默认，1：词频升序，2：词频降序，3：字母升序，4：字母降序
    int flag=0;
    
	/**
	 * @wbp.parser.constructor
	 */
	public out_ui(String result1){
		//调用生成界面的方法
		ui();
		//结果显示
		result=result1;
		resultArea.setText(result);
	}
	//设置界面
	public void ui (){
		        //居中显示
		        Image icon = Toolkit.getDefaultToolkit().getImage("Images\\Icon.jpg");
		        setIconImage(icon);
				setLocationRelativeTo(null); 
				setSize(400,300);
				setTitle("查询结果");
				setResizable(false);
				getContentPane().setLayout(null);
				//设置居中显示
			    setLocationRelativeTo(null); 
				// 菜单的添加
				
				//1.操作菜单集的添加
				menubar = new JMenuBar();
				file = new JMenu("\u6587\u4EF6");
				
		        savemenu = new JMenuItem("保存");
				savemenu.addActionListener(this);
				file.add(savemenu);
				
				savemoremenu = new JMenuItem("另存为...");
				savemoremenu.addActionListener(this);
				file.addSeparator();
				file.add(savemoremenu);

				exitmenu = new JMenuItem("退出");
				exitmenu.addActionListener(this);
				file.addSeparator();
				file.add(exitmenu);

				//2.排序方式菜单的添加
				sort = new JMenu("排序方式");
				frequentinmenu = new JMenuItem("词频升序");
				frequentinmenu.addActionListener(this);
				sort.add(frequentinmenu);
				
		        frequentdemenu = new JMenuItem("词频降序");
		        frequentdemenu.addActionListener(this);
		        sort.addSeparator();		     
		        sort.add(frequentdemenu);
		        
		        alphinmenu = new JMenuItem("字母升序");
		        alphinmenu.addActionListener(this);
		        sort.addSeparator();
		        sort.add(alphinmenu);
		        
		        alphdemenu = new JMenuItem("字母降序");
		        alphdemenu.addActionListener(this);
		        sort.addSeparator();
		        sort.add(alphdemenu);
		        
		        filetype = new JMenu("保存格式");
		        txttype = new JMenuItem("Txt格式");
		        txttype.addActionListener(this);
		        filetype.add(txttype);
		        
		        doctype  = new JMenuItem("Doc格式");
		        doctype.addActionListener(this);
		        filetype.addSeparator();
		        filetype.add(doctype);
		        
		        xlstype = new JMenuItem("xls格式");
		        xlstype.addActionListener(this);
		        filetype.addSeparator();
		        filetype.add(xlstype);
		        
				menubar.add(file);
				menubar.add(sort);
				menubar.add(filetype);
				setJMenuBar(menubar);
								
				resultArea = new JTextArea();
				resultArea.setTabSize(4);
				resultArea.setEditable(false);
				resultArea.setLineWrap(true);

				scrollPane = new JScrollPane(resultArea);
				scrollPane.setBounds(0, 0, 394, 272);
				scrollPane
						.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scrollPane
						.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

				getContentPane().add(scrollPane);
				
				fileChooser = new JFileChooser();
				//设置只打开txt和doc文档
				fileChooser.setFileFilter(new FileFilter()
				{

					@Override
					public boolean accept(File f)
					{
						if (f.getName().endsWith(".txt")||f.getName().endsWith(".doc"))
						{
							return true;
						}
						else return false;
					}

					@Override
					public String getDescription()
					{
						return ".txt";
					}
				});
	}
	@SuppressWarnings("rawtypes")
	public out_ui(Map result2) {
		// TODO Auto-generated constructor stub 
		this.resultmap = result2;
		//调用界面生成方法
		ui();
		//显示结果
		change(resultmap);
	}
	//保存文件方法
	@SuppressWarnings("unchecked")
	public void saveFile()
	{
			fileChooser.showSaveDialog(this);
			File f = fileChooser.getSelectedFile();
			fileName=f.getAbsolutePath()+savetype;
		try 
		{
			FileSave fs =  new FileSave();
			switch(flag){
				case 0:
					fs.save(fileName,resultmap,"单词","词频统计");
					break;
				case 1:
					fs.save(fileName,sorts.getLhm3(),"单词","词频升序");
					break;
				case 2:
					fs.save(fileName,sorts.getLhm4(),"单词","词频降序");
					break;
				case 3:
					fs.save(fileName,sorts.getTm(),"单词","字母升序");
					break;
				case 4:
					fs.save(fileName,sorts.getLhm2(),"单词","字母降序");
					break;
				default:
					
			}						
		} 
		catch (IOException ee) {
			System.out.println("保存文件错误");
		}
	}
	
	//监听器
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		sorts.sort(resultmap);
		Object event = e.getSource();
		
		if(event == savemenu)
		{
			saveFile();
		}
		else if(event == savemoremenu)
		{
			saveFile();
		}
		else if(event == exitmenu)
		{
			setVisible(false);
		}
		//flag标记保存，0：保存默认，1：词频升序，2：词频降序，3：字母升序，4：字母降序
		else if(event == frequentinmenu)
		{
			sort.setText("词频升序");
			change(sorts.getLhm3());
			flag=1;
		}
		else if(event == frequentdemenu)
		{
			sort.setText("词频降序");
			change(sorts.getLhm4());
			flag=2;
		}
		else if(event == alphinmenu)
		{
			sort.setText("字母升序");
			change(sorts.getTm());
			flag=4;
		}
		else if(event == alphdemenu)
		{
			sort.setText("字母降序");
			change(sorts.getLhm2());
			flag=3;
		}
		else if(event == txttype)  {savetype = ".txt";}
		else if(event == doctype)  {savetype = ".doc";}
		else if(event == xlstype)  {savetype = ".xls";}

	}
	public void change(@SuppressWarnings("rawtypes") Map a){
		/**
		 * 将HashMap转化为String显示
		 */
		resultArea.setText("单词数为："+a.size()+"\n");
		StringBuffer string=new StringBuffer("");
		@SuppressWarnings("unchecked")
		Iterator<String> it = a.keySet().iterator();
		while (it.hasNext()) { // 迭代循环输出结果
			String key = it.next();
			string .append(key + " -------->" + a.get(key)+"\n");				
		}
		resultArea.append(string.toString());
	}
}
