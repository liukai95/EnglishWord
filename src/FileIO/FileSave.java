package FileIO;

import java.io.*;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public class FileSave {
	public void save(String saveFile, Map<String, ?> map, String mesage1, String mesage2) throws IOException {
		String name = saveFile.substring(saveFile.lastIndexOf('.'), saveFile.length()); // �õ���׺��
		System.out.println(name);
		switch (name) {
		case ".xls":
			try {
				Workbook wb = new HSSFWorkbook();
				Sheet sheet1 = (Sheet) wb.createSheet("����ͳ��");
				Row row = (Row) (sheet1).createRow(0);
				Cell cell = row.createCell(0); // ��һ�� ��һ��
				cell.setCellValue(mesage1);
				cell = row.createCell(1); // ��һ�� �ڶ���
				cell.setCellValue(mesage2);
				Iterator<String> it = map.keySet().iterator();
				int i = 0;
				while (it.hasNext()) { // ����ѭ��������
					String key = it.next();
					// System.out.println(key + "->" + map.get(key));
					row = (Row) (sheet1).createRow(i + 1);
					cell = row.createCell(0);
					cell.setCellValue(key);
					cell = row.createCell(1);
					cell.setCellValue(map.get(key).toString());
					i++;
				}
				// �����ļ���
				OutputStream os = new FileOutputStream(saveFile);
				wb.write(os);
				// �ر��ļ���
				os.close();
				wb.close();
			} catch (Exception e) {}
			break;
			
		case ".txt":
			PrintWriter pw = new PrintWriter(new FileWriter(saveFile));
			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext()) { // ����ѭ��������
				String key = it.next();
				String str = key + " ->" + map.get(key);
				System.out.println("ehll"+str);
				pw.println(str); // ��ӡ��������ļ�
			}
			pw.close();
			break;
			
		case ".doc":
			try {
				StringBuffer sb = new StringBuffer("");
				Iterator<String> it1 = map.keySet().iterator();
				while (it1.hasNext()) { // ����ѭ��������
					String key = it1.next();
					sb.append(key + "->" + map.get(key) + "\n");
				}
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
				POIFSFileSystem fileSystem = new POIFSFileSystem();
				DirectoryEntry directory = fileSystem.getRoot();
				directory.createDocument("WordDocument", byteArrayInputStream);
				FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
				fileSystem.writeFilesystem(fileOutputStream);
				byteArrayInputStream.close();
				fileOutputStream.close();
				fileSystem.close();
			} catch (IOException e) {}
			break;
		default:
			break;
		}
	}

}
