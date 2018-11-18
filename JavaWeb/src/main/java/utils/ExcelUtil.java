package utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Excel工具类
 *
 * @author 冯仕清
 * @see #http://blog.csdn.net/lenotang/article/details/2823230
 */
public class ExcelUtil<T> {

	/**
	 * <日志>
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);

	/**
	 * <默认sheet名>
	 */
	private static final String SHEET_NAME = "导出的Excel文档";

	/**
	 * <默认日期格式>
	 */
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * 
	 * 功能描述: <br>
	 * 3个参数的重载
	 *
	 * @param thNameArr
	 * @param thCodeArr
	 * @param dataList
	 * @see #exportExcel(String, String[], String[], List, String)
	 */
	public Workbook exportExcel(String[] thNameArr, String[] thCodeArr, List<T> dataList) {
		return exportExcel(SHEET_NAME, thNameArr, thCodeArr, dataList, DATE_FORMAT);
	}

	/**
	 * 
	 * 4个参数的重载
	 *
	 * @param thNameArr
	 * @param thCodeArr
	 * @param dataList
	 * @param dateFormat
	 * @see #exportExcel(String, String[], String[], List, String)
	 */
	public Workbook exportExcel(String[] thNameArr, String[] thCodeArr, List<T> dataList, String dateFormat) {
		return exportExcel(SHEET_NAME, thNameArr, thCodeArr, dataList, dateFormat);
	}

	/**
	 * 
	 * 4个参数的重载
	 *
	 * @param sheetName
	 * @param thNameArr
	 * @param thCodeArr
	 * @param dataList
	 * @see #exportExcel(String, String[], String[], List, String)
	 */
	public Workbook exportExcel(String sheetName, String[] thNameArr, String[] thCodeArr, List<T> dataList) {
		return exportExcel(sheetName, thNameArr, thCodeArr, dataList, DATE_FORMAT);
	}

	/**
	 * 
	 * 导出 Excel。用 List 集合中的数据生成一个 Excel。<br>
	 * 注意：参数的顺序！！！示例：见本类中的 main() 方法
	 *
	 * @param sheetName  1、表格sheet的名字。
	 * @param thNameArr  2、表格列名组成的数组。th：意思与HTML中的th标签一样，表示tablehander表头。
	 * @param thCodeArr  3、表格列名对应的Code组成的数组。
	 * @param dataList   4、要导出的数据的集合，集合元素为javabean对象，getXxx()方法的方法名，必须严格遵循javaBean规范。
	 * @param dateFormat 5、日期格式。
	 */
	public Workbook exportExcel(String sheetName, String[] thNameArr, String[] thCodeArr, List<T> dataList,
			String dateFormat) {
		LOGGER.info("【ExcelUtil.exportExcel】【开始执行】【请求参数：】【sheetName:{}, thNameArr:{}, thCodeArr:{}, dataList:{}, dateFormat:{}】", 
				new Object[] {sheetName, thNameArr, thCodeArr, dataList.size(), dateFormat});
		
		// 步骤：1，2，3，4
		// 1、创建 Excel对象，和 sheet 表格
		Workbook wb = new HSSFWorkbook();// 创建 Workbook 对象，对应的一个Excel文件。HSSFWorkbook表示以xls为后缀名的文件。此对象不能大于65535条记录。
		Sheet sheet = wb.createSheet(sheetName);// 创建 sheet 对象，对应一个 sheet
		sheet.setDefaultColumnWidth(20);// 设置表格默认列宽度为20个字节，就是一个单元格内能放20个数字

		// 2、生成并设置样式（表头、表体数据 各一个样式）
		// 2.1、这个样式用来修饰表头字段
		CellStyle style1 = wb.createCellStyle();
		style1.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);// 单元格背景填充色：浅灰色
		style1.setFillPattern(CellStyle.SOLID_FOREGROUND);// 填充的样式
		style1.setAlignment(CellStyle.ALIGN_CENTER);// 单元格内容水平居中
		style1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 单元格内容垂直居中
		style1.setBorderTop(CellStyle.BORDER_THIN);// 边框的样式：实线
		style1.setBorderBottom(CellStyle.BORDER_THIN);
		style1.setBorderLeft(CellStyle.BORDER_THIN);
		style1.setBorderRight(CellStyle.BORDER_THIN);
		style1.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);// 边框颜色
		style1.setBottomBorderColor(HSSFColor.GREY_50_PERCENT.index);
		style1.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
		style1.setRightBorderColor(HSSFColor.GREY_50_PERCENT.index);

		Font font = wb.createFont();// 生成一个字体对象
		font.setColor(HSSFColor.BLACK.index);// 黑色
		font.setFontHeightInPoints((short) 12);// 12号字体
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);// 字体加粗
		style1.setFont(font);// 把字体应用到当前的样式

		// 2.2、这个样式用来修饰具体的数据
		CellStyle style2 = wb.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.WHITE.index);// 白色背景色
		style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style2.setBorderTop(CellStyle.BORDER_THIN);// 上边框的边框类型
		style2.setBorderBottom(CellStyle.BORDER_THIN);// 下边框的边框类型
		style2.setBorderLeft(CellStyle.BORDER_THIN);// 左边框的边框类型
		style2.setBorderRight(CellStyle.BORDER_THIN);// 右边框的边框类型
		style2.setAlignment(CellStyle.ALIGN_CENTER);// 设置单元格的水平对齐类型
		style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 设置单元格的垂直对齐类型

		// 3、创建表格的表头（就是标题行），并生成具体的数据
		Row row = sheet.createRow(0);// 创建表格的表头
		row.setHeight((short) (row.getHeight() * 1.75));// 设置标题行的行高
		for (short i = 0; i < thNameArr.length; i++) {// 生成具体的数据
			Cell cell = row.createCell(i);
			cell.setCellStyle(style1);// 给表格加上样式一
			// sheet.setColumnWidth(i, 15 * 256);// 已经设置了默认的列宽
			RichTextString text = new HSSFRichTextString(thNameArr[i]);
			cell.setCellValue(text);
		}

		// 4、创建数据行，并生成具体的数据
		Iterator<T> it = dataList.iterator();
		int index = 0;
		while (it.hasNext()) {// 遍历集合，集合中每一个元素对应产生excel中的一行数据
			index++;// 数据行下标从1开始，对应 Excel 表格的第 2 行
			row = sheet.createRow(index);// 4.1、创建一个数据行
			T baseObj = (T) it.next();// 从 dataList 获取数据
			// Field[] fieldArr = object.getClass().getDeclaredFields();// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值

			for (int i = 0; i < thCodeArr.length; i++) {
				Cell cell = row.createCell(i);// 4.2、创建一个单元格
				cell.setCellStyle(style2);// 应用样式二
				// Field field = fieldArr[i];
				// String fieldName = field.getName();
				// String
				// methodName="get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);//获取getXxx方法名
				String methodName = "get" + thCodeArr[i].substring(0, 1).toUpperCase() + thCodeArr[i].substring(1);// 获取getXxx()方法的方法名，方法名必须严格遵循javaBean规范
				// sheet.autoSizeColumn(i);// 自动设置宽高
				Class<? extends Object> baseClass = baseObj.getClass();
				try {
					Method method = baseClass.getMethod(methodName, new Class[] {});
					Object fieldValue = method.invoke(baseObj, new Object[] {});// 执行 getXxx() 方法获取DTO字段的值
					fieldValue = fieldValue != null ? fieldValue : "";
					// 类型转换
					String cellValue = null;// 定义单元格值，用来存放：处理后的字段值fieldValue
					if (fieldValue instanceof Date) {// 日期类型
						Date date = (Date) fieldValue;
						SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
						cellValue = sdf.format(date);
					} else {// 其它数据类型都当作字符串简单处理
						cellValue = fieldValue.toString();
					}

					if (cellValue != null) {
						Pattern regexPattern = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = regexPattern.matcher(cellValue);
						if (matcher.matches()) {// 用正则表达式判断 fieldValue 是否全部由数字组成
							cell.setCellValue(Double.parseDouble(cellValue));// 全是数字则当作 double 处理，为了防止开头为0的数字被忽略
						} else {
							cell.setCellValue(cellValue);
						}
					}
				} catch (SecurityException e) {
					LOGGER.error("【ExcelUtil.exportExcel】【发生异常】", e);// 不需要处理，只是导出的Excel内容为空。
				} catch (NoSuchMethodException e) {
					LOGGER.error("【ExcelUtil.exportExcel】【发生异常】", e);// 不需要处理，只是导出的Excel内容为空。
				} catch (IllegalArgumentException e) {
					LOGGER.error("【ExcelUtil.exportExcel】【发生异常】", e);// 不需要处理，只是导出的Excel内容为空。
				} catch (IllegalAccessException e) {
					LOGGER.error("【ExcelUtil.exportExcel】【发生异常】", e);// 不需要处理，只是导出的Excel内容为空。
				} catch (InvocationTargetException e) {
					LOGGER.error("【ExcelUtil.exportExcel】【发生异常】", e);// 不需要处理，只是导出的Excel内容为空。
				}
			}
		}
		return wb;
	}

	// 本地测试方法，直接本地运行即可查看效果。注释掉测试代码 modify by 冯仕清 2017年11月6日 16:19:46
	public static void main(String[] args) {
		ExcelUtil<Student> excelUtil1 = new ExcelUtil<Student>();// 测试学生
		String[] thCodeArr = { "id", "name", "age", "sex", "birthday" };// 设置要导出的Excel的标题行的中文名字
		String[] thNameArr = { "学号", "姓名", "年龄", "性别", "出生日期" };// 设置要导出的Excel的标题行的中文名字
		List<Student> studentDtoList = new ArrayList<Student>();
		studentDtoList.add(new Student(10000001, "张三", 20, true, new Date()));
		studentDtoList.add(new Student(20000002, "李四", 24, false, new Date()));
		studentDtoList.add(new Student(30000003, "王五", 22, true, new Date()));
//		for (int i=0; i< 65535; i++) {
//			studentDtoList.add(new Student(30000003, "王五", 22, true, new Date()));
//		}

		try {
			FileOutputStream fileOutStream1 = new FileOutputStream("E://a.xls");
			Workbook wb = excelUtil1.exportExcel(thNameArr, thCodeArr, studentDtoList);
			wb.write(fileOutStream1);
			fileOutStream1.close();
			JOptionPane.showMessageDialog(null, "导出成功！");
			System.out.println("excel导出成功！");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

// 此类用来测试，这是一个规范的javaBean。
class Student {
	private long id;
	private String name;
	private int age;
	private boolean sex;// true表示男，false表示女
	private Date birthday;

	public Student() {
		super();
	}

	public Student(long id, String name, int age, boolean sex, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.birthday = birthday;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean getSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
