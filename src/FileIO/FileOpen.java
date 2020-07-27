package FileIO;

import java.io.*;
import java.text.NumberFormat;
import java.util.regex.Matcher;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

public class FileOpen {
	private InputStream is;
	StringBuffer allWords; //������е���

	public String[] testFile(String openFile) throws Exception {
		allWords=new StringBuffer("");
		String name = openFile.substring(openFile.lastIndexOf('.'), openFile.length()); // �õ���׺��
		is = new FileInputStream(openFile);	
	
		// ��Ŷ�ȡ�����е��ʵ�һ���ַ���
		switch (name) {
		
		case ".doc": // ֱ�ӳ�ȡȫ������
			WordExtractor extractor = new WordExtractor(is);
			allWords.append(extractor.getText());
			break;
			
		case ".docx":
			OPCPackage opcPackage = POIXMLDocument.openPackage(openFile);
			POIXMLTextExtractor extractor1 = new XWPFWordExtractor(opcPackage);
			allWords.append(extractor1.getText());
			break;
			
		case ".ppt":
			PowerPointExtractor extractor2 = new PowerPointExtractor(is);
			allWords.append(extractor2.getText());
			break;
			
		case ".pptx":
			XSLFPowerPointExtractor extractor4 = new XSLFPowerPointExtractor(POIXMLDocument.openPackage(openFile));
			allWords.append(extractor4.getText());
			break;

		case ".xls":
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(is));
			ExcelExtractor extractor3 = new ExcelExtractor(wb);
			extractor3.setFormulasNotResults(false);
			extractor3.setIncludeSheetNames(true);
			allWords.append( extractor3.getText());
			break;
			
		case ".xlsx":
			StringBuilder content = new StringBuilder();
			XSSFWorkbook workbook = new XSSFWorkbook(openFile);
			for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {
				if (null != workbook.getSheetAt(numSheets)) {
					XSSFSheet aSheet = workbook.getSheetAt(numSheets);// ���һ��sheet
					for (int rowNumOfSheet = 0; rowNumOfSheet <= aSheet.getLastRowNum(); rowNumOfSheet++) {
						if (null != aSheet.getRow(rowNumOfSheet)) {
							XSSFRow aRow = aSheet.getRow(rowNumOfSheet); // ���һ����
							for (short cellNumOfRow = 0; cellNumOfRow <= aRow.getLastCellNum(); cellNumOfRow++) {
								if (null != aRow.getCell(cellNumOfRow)) {
									XSSFCell aCell = aRow.getCell(cellNumOfRow);// �����ֵ
									if (this.convertCell(aCell).length() > 0) {
										content.append(this.convertCell(aCell));
									}
								}
								content.append("\n");
							}
						}
					}
				}
			}
			allWords.append(content);
			break;
			
		case ".pdf":
			String result = null;
			FileInputStream fis = null;
			PDDocument document = null;
			try {
				fis = new FileInputStream(openFile);
				PDFParser parser = new PDFParser(fis);
				parser.parse();
				document = parser.getPDDocument();
				PDFTextStripper stripper = new PDFTextStripper();
				result = stripper.getText(document);
			} finally {
				if (fis != null) {
					fis.close();
				}
				if (document != null) {
					document.close();
				}
			}
			allWords.append(result);
			break;
			
		case ".txt":
			BufferedReader br = new BufferedReader(new FileReader(new File(openFile)));
			String read = "";
			while ((read = br.readLine()) != null) {
				allWords .append(read);
				//System.out.println(read);
			}
			break;
		default:
			
			break;
		}
		String a=allWords.toString();
		a=a.toLowerCase();
		a = a.replaceAll(Matcher.quoteReplacement("[^a-z-]"), " ");// ����26����ĸ��'����������ַ�ȫ�ÿո����
		String[] words = a.split("\\s+"); // ���տհ׷����зָ�ɶ��
		return words;    //���ص�������
	}

	// ���ڶ�ȡ.xlsx�ļ���Ҫ
	private String convertCell(Cell cell) {
		NumberFormat formater = NumberFormat.getInstance();
		formater.setGroupingUsed(false);
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:
			cellValue = formater.format(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_STRING:
			cellValue = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			cellValue = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			cellValue = Boolean.valueOf(cell.getBooleanCellValue()).toString();
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			cellValue = String.valueOf(cell.getErrorCellValue());
			break;
		default:
			cellValue = "";
		}
		return cellValue.trim();
	}
}
