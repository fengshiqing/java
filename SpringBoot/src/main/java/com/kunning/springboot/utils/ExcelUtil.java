package com.kunning.springboot.utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.swing.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能描述：Excel工具类。
 *
 * @author 冯仕清
 */
// https://blog.csdn.net/u011199063/article/details/74504550
// http://blog.csdn.net/lenotang/article/details/2823230
public class ExcelUtil<T> {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 私有化构造函数
     */
    private ExcelUtil(){
    }

    /**
     * 默认sheet名
     */
    private static final String SHEET_NAME = "导出的Excel文档";

    /**
     * 默认日期格式
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 功能描述：导出Excel
     * 3个参数的重载
     *
     * @param thNameArr 表格列名组成的数组。th：意思与<th>标签一样，表示tablehander表头。
     * @param thCodeArr 表格列名对应的Code组成的数组。
     * @param dataList  要导出的数据的集合，集合元素为javabean对象，getXxx()方法的方法名，必须严格遵循javaBean规范。
     * @see #exportExcel(String, String[], String[], List, String)
     */
    public Workbook exportExcel(String[] thNameArr, String[] thCodeArr, List<T> dataList) {
        return exportExcel(SHEET_NAME, thNameArr, thCodeArr, dataList, DATE_FORMAT);
    }

    /**
     * 功能描述：导出Excel
     * 4个参数的重载
     *
     * @param thNameArr  2、表格列名组成的数组。th：意思与<th>标签一样，表示tablehander表头。
     * @param thCodeArr  3、表格列名对应的Code组成的数组。
     * @param dataList   4、要导出的数据的集合，集合元素为javabean对象，getXxx()方法的方法名，必须严格遵循javaBean规范。
     * @param dateFormat 5、日期格式。
     * @see #exportExcel(String, String[], String[], List, String)
     */
    public Workbook exportExcel(String[] thNameArr, String[] thCodeArr, List<T> dataList, String dateFormat) {
        return exportExcel(SHEET_NAME, thNameArr, thCodeArr, dataList, dateFormat);
    }

    /**
     * 功能描述：导出Excel
     * 4个参数的重载
     *
     * @param sheetName 1、表格sheet的名字。
     * @param thNameArr 2、表格列名组成的数组。th：意思与<th>标签一样，表示tablehander表头。
     * @param thCodeArr 3、表格列名对应的Code组成的数组。
     * @param dataList  4、要导出的数据的集合，集合元素为javabean对象，getXxx()方法的方法名，必须严格遵循javaBean规范。
     * @see #exportExcel(String, String[], String[], List, String)
     */
    public Workbook exportExcel(String sheetName, String[] thNameArr, String[] thCodeArr, List<T> dataList) {
        return exportExcel(sheetName, thNameArr, thCodeArr, dataList, DATE_FORMAT);
    }

    /**
     * 功能描述：导出Excel
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
        LOGGER.info("【exportExcel】【开始执行】【请求参数：】【sheetName:{}, thNameArr:{}, thCodeArr:{}, dataList:{}, dateFormat:{}】",
                sheetName, thNameArr, thCodeArr, dataList.size(), dateFormat);

        // 步骤：1，2，3，4
        // 1、创建 Excel对象，和 sheet 表格
        Workbook wb = new HSSFWorkbook();// 创建 Workbook 对象，对应的一个Excel文件。HSSFWorkbook表示以xls为后缀名的文件。此对象不能大于65535条记录。
        Sheet sheet = wb.createSheet(sheetName);// 创建 sheet 对象，对应一个 sheet
        sheet.setDefaultColumnWidth(20);// 设置表格默认列宽度为20个字节，就是一个单元格内能放20个数字

        // 2、生成并设置样式（表头、表体数据 各一个样式）
        // 2.1、这个样式用来修饰表头字段
        CellStyle style1 = wb.createCellStyle();
        style1.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex());// 单元格背景填充色：浅灰色
        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);// 单元格背景色填充的样式：完全填充
        style1.setAlignment(HorizontalAlignment.CENTER);// 单元格内容水平居中
        style1.setVerticalAlignment(VerticalAlignment.CENTER);// 单元格内容垂直居中
        style1.setBorderTop(BorderStyle.THIN);// 边框的样式：实线
        style1.setBorderBottom(BorderStyle.THIN);
        style1.setBorderLeft(BorderStyle.THIN);
        style1.setBorderRight(BorderStyle.THIN);
        style1.setTopBorderColor(HSSFColor.HSSFColorPredefined.GREY_50_PERCENT.getIndex());// 边框颜色
        style1.setBottomBorderColor(HSSFColor.HSSFColorPredefined.GREY_50_PERCENT.getIndex());
        style1.setLeftBorderColor(HSSFColor.HSSFColorPredefined.GREY_50_PERCENT.getIndex());
        style1.setRightBorderColor(HSSFColor.HSSFColorPredefined.GREY_50_PERCENT.getIndex());

        Font font = wb.createFont();// 生成一个字体对象
        font.setColor(Font.COLOR_NORMAL);// 黑色
        font.setFontHeightInPoints((short) 12);// 12号字体
        // font.setBoldweight(Font.BOLDWEIGHT_BOLD);// 字体加粗，废弃API
        font.setBold(true);// 字体加粗
        style1.setFont(font);// 把字体应用到当前的样式

        // 2.2、这个样式用来修饰具体的数据
        CellStyle style2 = wb.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());// 单元格背景填充色：白色
        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);// 单元格背景色填充的样式：完全填充
        style2.setAlignment(HorizontalAlignment.CENTER);// 单元格内容水平居中
        style2.setVerticalAlignment(VerticalAlignment.CENTER);// 单元格内容垂直居中
        style2.setBorderTop(BorderStyle.THIN);// 上边框的边框类型
        style2.setBorderBottom(BorderStyle.THIN);// 下边框的边框类型
        style2.setBorderLeft(BorderStyle.THIN);// 左边框的边框类型
        style2.setBorderRight(BorderStyle.THIN);// 右边框的边框类型

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
            // Field[] fieldArr = object.getClass().getDeclaredFields();//
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值

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
                    Method method = baseClass.getMethod(methodName, new Class[]{});
                    Object fieldValue = method.invoke(baseObj, new Object[]{});// 执行 getXxx() 方法获取DTO字段的值
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
                } catch (SecurityException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException e) {
                    LOGGER.error("【ExcelUtil.exportExcel】【发生异常】", e);// 不需要处理，只是导出的Excel内容为空。
                }
            }
        }
        return wb;
    }

    // 本地测试方法，直接本地运行即可查看效果。注释掉测试代码 modify by 冯仕清 2017年11月6日 16:19:46
    public static void main(String[] args) {
        ExcelUtil<Student> excelUtil1 = new ExcelUtil<>();// 测试学生
        String[] thCodeArr = {"id", "name", "age", "sex", "birthday"};// 设置要导出的Excel的标题行的中文名字
        String[] thNameArr = {"学号", "姓名", "年龄", "性别", "出生日期"};// 设置要导出的Excel的标题行的中文名字
        List<Student> studentDtoList = new ArrayList<>();
        studentDtoList.add(new Student(10000001, "张三", 20, true, new Date()));
        studentDtoList.add(new Student(20000002, "李四", 24, false, new Date()));
        studentDtoList.add(new Student(30000003, "王五", 22, true, new Date()));
//		for (int i=0; i< 65535; i++) {
//			studentDtoList.add(new Student(30000003, "王五", 22, true, new Date()));
//		}

        try {
            FileOutputStream fileOutStream1 = new FileOutputStream("C://a.xls");
            Workbook wb = excelUtil1.exportExcel(thNameArr, thCodeArr, studentDtoList);
            wb.write(fileOutStream1);
            fileOutStream1.close();
            JOptionPane.showMessageDialog(null, "导出成功！");
            System.out.println("excel导出成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能描述：读取Excel
     *
     * @throws IOException IO异常
     */
    @SuppressWarnings("deprecation")
    public static void readExcel() throws IOException {
        String filePath = "D:\\test.xlsx";
        String[] columns = {"上班时间", "下班时间", "总工时"};// Excel表头

        Workbook wb = readExcel(filePath);
        if (wb == null) {
            return;
        }

        Sheet sheet = wb.getSheetAt(0);// 获取第一个sheet
        int rowSumNum = sheet.getPhysicalNumberOfRows();// 获取最大行数
        Row row = sheet.getRow(0);// 获取第一行首行的表头数据
        int colnum = row.getPhysicalNumberOfCells();// 获取最大列数

        List<Map<String, Date>> list = new ArrayList<>();// 读取Excel中数据放在此List中
        for (int i = 1; i < rowSumNum; i++) {// 遍历Excel的行记录，从1开始，0是表头
            Map<String, Date> map = new LinkedHashMap<>();// 一个Map里放一行记录
            row = sheet.getRow(i);
            if (row != null) {
                for (int j = 0; j < colnum; j++) {
                    Cell cell = row.getCell(j);

                    // HSSFDateUtil.isCellDateFormatted(cell)用来判断当前单元格格式是否是日期格式，
                    // 它内部的实现单元格读取成数字（excel日期格式默认是用数字保存的），
                    // 所以调用这个api判断时需先要判断当前单元格格式不是字符串，否则字符串会导致读取失败。
                    // 参考：https://blog.csdn.net/q649381130/article/details/78281103
                    if (cell.getCellType() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                        // String value = sdf.format(date);
                        // System.out.println(value);
                        map.put(columns[j], date);// 存放单个单元格的数据
                        // map.put(columns[j], (String)getCellFormatValue(cell));// 存放单个单元格的数据
                    }
                }
            } else {
                break;
            }
            list.add(map);
        }

        int hour = 0;
        int minutes = 0;
        // 遍历解析出来的list
        for (Map<String, Date> map : list) {
            // System.out.println(map);
            Date date_1 = map.get("上班时间");
            Date date_2 = map.get("下班时间");
            if (date_2.getHours() < 18) {
                date_2.setMinutes(30);
                date_2 = new Date(date_2.getTime() - (90 * 60 * 1000L));
            } else {
                date_2 = new Date(date_2.getTime() - (30 * 60 * 1000L) - (90 * 60 * 1000L));
            }
            System.out.println(TimeDifference(date_1, date_2));

            hour = hour + date_2.getHours() - date_1.getHours();
            minutes = minutes + date_2.getMinutes() - date_1.getMinutes();

//            for (Entry<String, Date> entry : map.entrySet()) {
//                System.out.print(entry.getKey() + " : " + entry.getValue() + ",");
//            }
//            System.out.println();
        }
        int aaa = (hour * 60 + minutes) / 17;
        System.out.println(aaa + "分钟");
        System.out.println(aaa / 60 + "分钟" + aaa % 60);
    }

    /**
     * 功能描述：读取指定路径下的Excel文件
     *
     * @throws IOException IO异常
     */
    public static Workbook readExcel(String filePath) throws IOException {
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = new FileInputStream(filePath);
        if (".xls".equals(extString)) {
            return new HSSFWorkbook(is);
        } else if (".xlsx".equals(extString)) {
            return new XSSFWorkbook(is);
        } else {
            is.close();
            return null;
        }
    }

    /**
     * 功能描述：获取单元格内容
     *
     * @param cell 单元格内容
     * @return 单元格对象
     */
    public static Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {

            switch (cell.getCellType()) {// 判断cell类型
                case NUMERIC: {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case FORMULA: {
                    // 判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        // 转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        // 数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }

    /**
     * 功能描述：获取两个时间的时间差，精确到毫秒
     *
     * @return 时间差值
     */
    public static String TimeDifference(Date start, Date end) {
        long diffVal = end.getTime() - start.getTime();// 时间差值
        long day = diffVal / (24 * 60 * 60 * 1000);
        long hour = (diffVal / (60 * 60 * 1000) - day * 24);
        long minutes = ((diffVal / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (diffVal / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - minutes * 60);
        long ms = (diffVal - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - minutes * 60 * 1000 - s * 1000);
        // String timeDifference = day + "天" + hour + "小时" + minutes + "分" + s + "秒" +
        // ms + "毫秒";
        return hour + "小时" + minutes + "分";
    }

    // 功能描述：此类用来测试，这是一个规范的javaBean。
    static class Student {
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

}
