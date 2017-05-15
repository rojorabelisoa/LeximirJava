/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author rojo
 */
public class Utils {
    /**
     * This function causes a String to be inverted from right to left
     * @param text is the String to reverse
     * @return 
     */
    public static String reverseString(String text){
        byte [] strAsByteArray = text.getBytes();
        byte [] result = new byte [strAsByteArray.length];

        for(int i = 0; i<strAsByteArray.length; i++){
        result[i] = strAsByteArray[strAsByteArray.length-i-1];
        }
        return new String(result);
    }

    public static void exportJtableToExcel(HSSFWorkbook workbook, Map<String, Object[]> datas,String filename) throws IOException, FileNotFoundException {
        HSSFSheet sheet = workbook.createSheet("Sample sheet");
        Set<String> keyset = datas.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = datas.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof Date) {
                    cell.setCellValue((Date) obj);
                } else if (obj instanceof Boolean) {
                    cell.setCellValue((Boolean) obj);
                } else if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Double) {
                    cell.setCellValue((Double) obj);
                }
            }
        }
        
        try (FileOutputStream out = new FileOutputStream(new File(filename))) {
            workbook.write(out);
        }
        System.out.println("Excel written successfully..");
    }
    
    public static Object[] delasToObject(String lemma, String fstCode, String comment, String Dicname)throws ArrayIndexOutOfBoundsException{
        String pOs;
        try{
            pOs=fstCode.split("[^A-Z0-9]+|(?<=[A-Z])(?=[0-9])|(?<=[0-9])(?=[A-Z])")[0];
        }
        catch(ArrayIndexOutOfBoundsException ex){
            throw new ArrayIndexOutOfBoundsException("Fst code format error");
        }
        String lemmas=lemma;
        String fSTCode=fstCode;
        String sinSem="";
        String comments=comment;
        String lemmaInv=Utils.reverseString(lemma);
        String wn_SinSet="";
        int lemmaId=10;
        String dicFile=Dicname;
        int dicId=0;
        return new Object[]{pOs,lemmas,fSTCode,sinSem,comments,lemmaInv,wn_SinSet,lemmaId,dicFile,dicId};
    }
    public static String getValueXml(String key) throws IOException, FileNotFoundException,IllegalArgumentException {
        File file = new File("configuration.xml");
        Properties properties;
            try (FileInputStream fileInput = new FileInputStream(file)) {
                properties = new Properties();
                properties.loadFromXML(fileInput);
            }
        Enumeration enuKeys = properties.keys();
        while (enuKeys.hasMoreElements()) {
            String keys = (String) enuKeys.nextElement();
            if(keys.equals(key)){
                return properties.getProperty(keys);
            }
        }
        throw new IllegalArgumentException("Key not found in path");
    }
    public static void runCommandTerminal(String[] command) throws IOException{
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);
        pb.start();
    }
}
