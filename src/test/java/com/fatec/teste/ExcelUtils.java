package com.fatec.teste;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;

	/**
	 * este metodo configura o caminho para abrir o arquivo Excel
	 * 
	 * @param Path
	 * @param SheetName
	 *            - nome da pasta
	 * @throws Exception
	 */
	public static void setExcelFile(String Path, String SheetName) throws Exception {
		try {
			// Abre o arquivo Excel
			System.out.println("Paht = " + Path);
			System.out.println("Sheet= " + SheetName);
			FileInputStream ExcelFile = new FileInputStream(Path);
			// Acessa a planilha de dados de teste
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {
			System.out.println("erro = " + e.getMessage());
			throw (e);
		}
	}

	/**
	 * Este metodo le os dados de teste de uma celula do Excel passando como
	 * parametro linha x coluna, retorna o conteudo da celula como um String
	 * 
	 * @param RowNum
	 *            - linha
	 * @param ColNum
	 *            - coluna
	 * @return - conteudo da celula
	 * @throws Exception
	 */
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * Este metodo escreve na celula do Excel passando linha X coluna
	 * 
	 * @param Result
	 * @param RowNum
	 * @param ColNum
	 * @throws Exception
	 */
	public static void setCellData(String Result, int RowNum, int ColNum) throws Exception {
		try {
			Row = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellValue(Result);
			}
			// Constante que define o caminho dos dados e o arquivo de dados
			FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			System.out.println("erro no set excel data = " + e.getMessage());
			throw (e);
		}
	}
}