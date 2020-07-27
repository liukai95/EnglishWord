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
    
    //����洢����
    String savetype=".txt";
    //����������
    Sort sorts=new Sort();
    //��Ǳ��棬0������Ĭ�ϣ�1����Ƶ����2����Ƶ����3����ĸ����4����ĸ����
    int flag=0;
    
	/**
	 * @wbp.parser.constructor
	 */
	public out_ui(String result1){
		//�������ɽ���ķ���
		ui();
		//�����ʾ
		result=result1;
		resultArea.setText(result);
	}
	//���ý���
	public void ui (){
		        //������ʾ
		        Image icon = Toolkit.getDefaultToolkit().getImage("Images\\Icon.jpg");
		        setIconImage(icon);
				setLocationRelativeTo(null); 
				setSize(400,300);
				setTitle("��ѯ���");
				setResizable(false);
				getContentPane().setLayout(null);
				//���þ�����ʾ
			    setLocationRelativeTo(null); 
				// �˵������
				
				//1.�����˵��������
				menubar = new JMenuBar();
				file = new JMenu("\u6587\u4EF6");
				
		        savemenu = new JMenuItem("����");
				savemenu.addActionListener(this);
				file.add(savemenu);
				
				savemoremenu = new JMenuItem("���Ϊ...");
				savemoremenu.addActionListener(this);
				file.addSeparator();
				file.add(savemoremenu);

				exitmenu = new JMenuItem("�˳�");
				exitmenu.addActionListener(this);
				file.addSeparator();
				file.add(exitmenu);

				//2.����ʽ�˵������
				sort = new JMenu("����ʽ");
				frequentinmenu = new JMenuItem("��Ƶ����");
				frequentinmenu.addActionListener(this);
				sort.add(frequentinmenu);
				
		        frequentdemenu = new JMenuItem("��Ƶ����");
		        frequentdemenu.addActionListener(this);
		        sort.addSeparator();		     
		        sort.add(frequentdemenu);
		        
		        alphinmenu = new JMenuItem("��ĸ����");
		        alphinmenu.addActionListener(this);
		        sort.addSeparator();
		        sort.add(alphinmenu);
		        
		        alphdemenu = new JMenuItem("��ĸ����");
		        alphdemenu.addActionListener(this);
		        sort.addSeparator();
		        sort.add(alphdemenu);
		        
		        filetype = new JMenu("�����ʽ");
		        txttype = new JMenuItem("Txt��ʽ");
		        txttype.addActionListener(this);
		        filetype.add(txttype);
		        
		        doctype  = new JMenuItem("Doc��ʽ");
		        doctype.addActionListener(this);
		        filetype.addSeparator();
		        filetype.add(doctype);
		        
		        xlstype = new JMenuItem("xls��ʽ");
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
				//����ֻ��txt��doc�ĵ�
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
		//���ý������ɷ���
		ui();
		//��ʾ���
		change(resultmap);
	}
	//�����ļ�����
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
					fs.save(fileName,resultmap,"����","��Ƶͳ��");
					break;
				case 1:
					fs.save(fileName,sorts.getLhm3(),"����","��Ƶ����");
					break;
				case 2:
					fs.save(fileName,sorts.getLhm4(),"����","��Ƶ����");
					break;
				case 3:
					fs.save(fileName,sorts.getTm(),"����","��ĸ����");
					break;
				case 4:
					fs.save(fileName,sorts.getLhm2(),"����","��ĸ����");
					break;
				default:
					
			}						
		} 
		catch (IOException ee) {
			System.out.println("�����ļ�����");
		}
	}
	
	//������
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
		//flag��Ǳ��棬0������Ĭ�ϣ�1����Ƶ����2����Ƶ����3����ĸ����4����ĸ����
		else if(event == frequentinmenu)
		{
			sort.setText("��Ƶ����");
			change(sorts.getLhm3());
			flag=1;
		}
		else if(event == frequentdemenu)
		{
			sort.setText("��Ƶ����");
			change(sorts.getLhm4());
			flag=2;
		}
		else if(event == alphinmenu)
		{
			sort.setText("��ĸ����");
			change(sorts.getTm());
			flag=4;
		}
		else if(event == alphdemenu)
		{
			sort.setText("��ĸ����");
			change(sorts.getLhm2());
			flag=3;
		}
		else if(event == txttype)  {savetype = ".txt";}
		else if(event == doctype)  {savetype = ".doc";}
		else if(event == xlstype)  {savetype = ".xls";}

	}
	public void change(@SuppressWarnings("rawtypes") Map a){
		/**
		 * ��HashMapת��ΪString��ʾ
		 */
		resultArea.setText("������Ϊ��"+a.size()+"\n");
		StringBuffer string=new StringBuffer("");
		@SuppressWarnings("unchecked")
		Iterator<String> it = a.keySet().iterator();
		while (it.hasNext()) { // ����ѭ��������
			String key = it.next();
			string .append(key + " -------->" + a.get(key)+"\n");				
		}
		resultArea.append(string.toString());
	}
}
