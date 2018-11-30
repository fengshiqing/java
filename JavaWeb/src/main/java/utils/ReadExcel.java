package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * <读取Excel内容>
 * 
 * @see https://www.cnblogs.com/hhhshct/p/7255915.html
 * 
 * https://blog.csdn.net/u011199063/article/details/74504550
 */
public class ReadExcel {

    final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");// 设置时间格式
    
    /**
     * main函数
     * @throws IOException 
     */
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException {
        String filePath = "D:\\test.xlsx";
        String columns[] = {"上班时间", "下班时间", "总工时"};// Excel表头
        
        Workbook wb = readExcel(filePath);
        if(wb == null){
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
                    if (cell.getCellTypeEnum() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(cell)) {
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
        int aaa = (hour*60 + minutes)/17;
        System.out.println(aaa + "分钟");
        System.out.println(aaa/60 + "分钟" + aaa%60);
    }
    
    /**
     * <读取指定路径下的Excel文件>
     * @throws IOException 
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
     * 获取单元格内容
     * @param cell
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if (cell != null) {
            
            switch(cell.getCellType()){//判断cell类型
            case Cell.CELL_TYPE_NUMERIC:{
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            }
            case Cell.CELL_TYPE_FORMULA:{
                //判断cell是否为日期格式
                if(DateUtil.isCellDateFormatted(cell)){
                    //转换为日期格式YYYY-mm-dd
                    cellValue = cell.getDateCellValue();
                }else{
                    //数字
                    cellValue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            case Cell.CELL_TYPE_STRING:{
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
     * 获取两个时间的时间差，精确到毫秒
     * 
     * @param str
     * @return
     */
    @SuppressWarnings("unused")
    public static String TimeDifference(Date start, Date end) {
        long diffVal = end.getTime() - start.getTime();// 时间差值
        long day = diffVal / (24 * 60 * 60 * 1000);
        long hour = (diffVal / (60 * 60 * 1000) - day * 24);
        long minutes = ((diffVal / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (diffVal / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - minutes * 60);
        long ms = (diffVal - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - minutes * 60 * 1000 - s * 1000);
        // String timeDifference = day + "天" + hour + "小时" + minutes + "分" + s + "秒" + ms + "毫秒";
        String timeDifference = hour + "小时" + minutes + "分";
        return timeDifference;
    }
    
}
