package com.dousnl.utils.execl;

import org.apache.poi.ss.usermodel.Cell;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author liukai
 */
public class ImportUtils {

    public static String getFileConent(InputStream inputStream) {
        try {
            byte[] bytes = new byte[10];
            inputStream.read(bytes, 0, 10);
            return bytesToHexString(bytes);
        } catch (Exception e) {
            try {
                inputStream.close();
            } catch (IOException e1) {
            }
        }
        return "";
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            } else {
                stringBuilder.append(hv);
            }
        }
        return stringBuilder.toString();
    }
    
    //获取Excel单元格的值
   /* public static String getCellValue(Cell cell) {
       return getCellValue(cell, null);
    }*/
    
    //获取Excel单元格的值(0:整数；1:浮点数)
    public static String getCellValue(Cell cell) {
        String obj = null;
        if(cell ==null){
        	return "";
        }
        int cellType = cell.getCellType();
        if (cellType == Cell.CELL_TYPE_NUMERIC) {
        	obj = new DecimalFormat("0").format(cell.getNumericCellValue()).trim();
            if (obj.contains(".")) { 
            	obj = obj.substring(0, obj.indexOf("."));
            }
        } else if (cellType == Cell.CELL_TYPE_STRING) {
            obj = cell.getStringCellValue().replace((char) 12288, (char) 32);
            obj = obj.replace((char) 160, (char) 32);
            obj = obj.trim();
        }
//		//将空字符串改为NULL值
        if (obj != null && obj.equals("")) {
            obj = null;
        }
        return obj;
    }
    

    //获取Excel单元格的值(0:整数；1:浮点数)
    public static String getCellValue(Cell cell, Integer type) {
        String obj = null;
        if(cell ==null){
        	return "";
        }
        int cellType = cell.getCellType();
        if (cellType == Cell.CELL_TYPE_NUMERIC) {
        	obj = String.valueOf(cell.getNumericCellValue()).trim();
            if (obj.contains(".") && type != null && type == 0) { 
            	obj = obj.substring(0, obj.indexOf("."));
            }
        } else if (cellType == Cell.CELL_TYPE_STRING) {
            obj = cell.getStringCellValue().trim();
        }
//		//将空字符串改为NULL值
        if (obj != null && obj.equals("")) {
            obj = null;
        }
        return obj;
    }
    
  //获取Excel单元格的值(0:整数；1:浮点数)
    public static String getCellValueByFormat(Cell cell, Integer type,Integer format) {
        String obj = null;
        if(cell ==null){
        	return "";
        }
        int cellType = cell.getCellType();
        if (cellType == Cell.CELL_TYPE_NUMERIC || cellType==Cell.CELL_TYPE_FORMULA) {
        	obj = String.valueOf(cell.getNumericCellValue()).trim();
            if (obj.contains(".") && type != null && type == 0) { 
            	obj = obj.substring(0, obj.indexOf("."));
            }else if(obj.contains(".") && type != null && type == 1){
            	if(format==null){
            		format=2;
            	}
            	BigDecimal bd = new BigDecimal(Double.valueOf(obj));
            	bd = bd.setScale(format, BigDecimal.ROUND_HALF_UP);
            	obj = bd.toString();
            }
        } else if (cellType == Cell.CELL_TYPE_STRING) {
            obj = cell.getStringCellValue().trim();
        }
//		//将空字符串改为NULL值
        if (obj != null && obj.equals("")) {
            obj = null;
        }
        return obj;
    }
    
    public static String getScienceCellValue(Cell cell) {
    	 String obj = null;
         if(cell ==null){
         	return "";
         }
         int cellType = cell.getCellType();
         if (cellType == Cell.CELL_TYPE_NUMERIC) {
        	 DecimalFormat df = new DecimalFormat("0");
        	 obj = df.format(cell.getNumericCellValue());
         } else if (cellType == Cell.CELL_TYPE_STRING) {
             obj = cell.getStringCellValue().trim();
         }
         if (obj.contains(".")) { 
          	obj = obj.substring(0, obj.indexOf("."));
         }
// 		//将空字符串改为NULL值
         if (obj != null && obj.equals("")) {
             obj = null;
         }
         return obj;
    }

    public static int getIntValue(String str) {
        try {
            String value = "0";
            if (str.isEmpty()) {
                return 0;
            }
            if (str.contains(".")) {
                value = str.substring(0, str.indexOf("."));
            } else {
                value = str;
            }
            return Integer.valueOf(value);
        } catch (Exception e) {
            return 0;
        }
    }

    public static BigDecimal getDecimalValue(String str) {
        try {
            if (str.isEmpty()) {
                return new BigDecimal(0);
            }
            return new BigDecimal(str);
        } catch (Exception e) {
            return new BigDecimal(0);
        }
    }

    public static Float getFloatValue(String str) {
        try {
            if (str.isEmpty()) {
                return 0f;
            }
            return new Float(str);
        } catch (Exception e) {
            return 0f;
        }
    }

	/*public static void main(String[] args) throws IOException {
        File file = new File("F:/46ecb601-8bc4-4873-86ef-26ee6230b491.xls");
		File file = new File("E:/document/案场管家/故事卡片模板-刘凯.docx");
		File file = new File("d:/fortunedr.part05.rar");
		InputStream in = new FileInputStream(file);
		System.out.println(getFileConent(in));
	}*/
    
//    public static void main(String[] args) {
//        File file = new File("F:/城市区域板块.xlsx");
//    	try {
//			InputStream input = new FileInputStream(file);
//			
//			Workbook book = WorkbookFactory.create(input);
//			Sheet sheet = book.getSheetAt(0);
//			int rowCount = sheet.getLastRowNum();
//			
//			Map<String,String> city = new HashMap<String,String>();
//			Map<String,String> district = new HashMap<String,String>();
//			Map<String,String> plate = new HashMap<String,String>();
//			for (int i = 1; i <= rowCount; i++) {
//				Row row = sheet.getRow(i);
//				String cityId = ImportUtils.getCellValue(row.getCell(1));
//				String cityName = ImportUtils.getCellValue(row.getCell(2));
//				String districtId = ImportUtils.getCellValue(row.getCell(3));
//				String districtName = ImportUtils.getCellValue(row.getCell(4));
//				String plateId = ImportUtils.getCellValue(row.getCell(5));
//				String plateName = ImportUtils.getCellValue(row.getCell(6));
//				String status = ImportUtils.getCellValue(row.getCell(8));
//				if(!districtName.equals("NULL") && !districtName.equals("") &&
//						!plateId.equals("NULL") && !plateId.equals("") &&!plateId.equals("0") && 
//						!plateName.equals("NULL") && !plateName.equals("")&&status.equals("1.0")){
//					city.put(cityId, cityName+"#0");
//					district.put(districtId, districtName+"#"+cityId);
//					plate.put(plateId, plateName+"#"+districtId);
//				}
//			}
//			StringBuilder citysql = new StringBuilder("");
//			StringBuilder districtsql = new StringBuilder("");
//			StringBuilder platesql = new StringBuilder("");
//			citysql.append("INSERT INTO base_city(city_id,city_name,parent_id) VALUES");
//			for(String key : city.keySet()){
//				citysql.append("(").append(StringUtils.substringBefore(key, ".")).append(",'").append(city.get(key).substring(0, city.get(key).indexOf("#"))).append("',").append(StringUtils.substringBefore(city.get(key).substring(city.get(key).indexOf("#")+1),".")).append(")");
//				citysql.append(",");
//			}
//	    	System.err.println(citysql);
//
//	    	districtsql.append("INSERT INTO base_district(district_id,district_name,city_id) VALUES");
//	    	for(String key : district.keySet()){
//	    		districtsql.append("(").append(StringUtils.substringBefore(key, ".")).append(",'").append(district.get(key).substring(0, district.get(key).indexOf("#"))).append("',").append(StringUtils.substringBefore(district.get(key).substring(district.get(key).indexOf("#")+1),".")).append(")");
//	    		districtsql.append(",");
//			}
//	    	System.err.println(districtsql);
//	    	
//	    	platesql.append("INSERT INTO base_plate(plate_id,plate_name,district_id) VALUES");
//	    	for(String key : plate.keySet()){
//	    		platesql.append("(").append(StringUtils.substringBefore(key, ".")).append(",'").append(plate.get(key).substring(0, plate.get(key).indexOf("#"))).append("',").append(StringUtils.substringBefore(plate.get(key).substring(plate.get(key).indexOf("#")+1),".")).append(")");
//	    		platesql.append(",");
//			}
//	    	System.err.println(platesql);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//    public static void main(String[] args) {
//        File file = new File("F:/苏州账号.xlsx");
//        try {
//        	InputStream input = new FileInputStream(file);
//			Workbook book = WorkbookFactory.create(input);
//			Sheet sheet = book.getSheetAt(0);
//			int rowCount = sheet.getLastRowNum();
//			StringBuilder sql1 = new StringBuilder("");
//			StringBuilder sql2 = new StringBuilder("");
//			StringBuilder sql3 = new StringBuilder("");
//			sql1.append("INSERT INTO base_user(work_no,user_name,validate,category,creater_id,create_time,updater_id,update_time) VALUES");
//			sql2.append("INSERT INTO base_role(role_name,category,validate,creater_id,create_time,updater_id,update_time,role_desc,isdel,isdef) VALUES");
//			sql3.append("INSERT INTO base_user_role(work_no,role_id) VALUES");
//			for (int i = 1; i <= rowCount; i++) {
//				Row row = sheet.getRow(i);
//				String workNo =  ImportUtils.getCellValue(row.getCell(1));
//				String userName = ImportUtils.getCellValue(row.getCell(0));
//				String validate = ImportUtils.getCellValue(row.getCell(2));
//				sql1.append("('").append(workNo).append("','").append(userName).append("',").append(validate.equals("1.0")?0:1).append(",");
//				sql1.append("0,'10999',NOW(),'10999',NOW()),");
//				sql2.append("('").append(workNo+"_"+userName).append("',0,0,'10999',NOW(),'10999',NOW(),'").append(workNo).append("工号的默认角色',0,0),");
//				sql3.append("('").append(workNo).append("',").append("(SELECT IFNULL(role_id,0) FROM base_role WHERE role_name = '").append(workNo+"_"+userName).append("')),");
//				
//			}
//			
//			System.err.println(sql1);
//			System.err.println(sql2);
//			System.err.println(sql3);
//        }catch (Exception e) { 
//			e.printStackTrace();
//		}
//    }
}
