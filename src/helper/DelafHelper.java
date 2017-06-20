/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.StaticValue;
import util.Utils;

/**
 *
 * @author Rojo Rabelisoa
 */
public class DelafHelper {
    public static Object[][] getAllDelafFromDelacToObject() throws FileNotFoundException, IOException{
        String path = StaticValue.text_sntAbsPath;
        ArrayList<String> readFile = Utils.readFile(path);
        List<String> result = new ArrayList<>();
        int count = 0;
        int size = 0;
        for (String s : readFile) {
            size++;
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < s.length(); i++) {
                if (count == 0) {
                    i = i++;
                    count++;
                    continue;
                }
                sb.append(s.charAt(i));
                i++;
            }
            if (!sb.toString().isEmpty()) {
                result.add(sb.toString());
            }
        }
        Object[][] ob = new Object[size/2][5];
        Object[][] getAllDelas = DelasHelper.getAllDelasFromDicToObject();
        
        int i=0;
        for (Object[] allDela : getAllDelas) {
            String lema = (String) allDela[1];
            String pos = (String) allDela[0];
            for(String s:result){
                if (lema.equals(getLema(s))) {
                    ob[i][0]=getUlaz(s);
                    ob[i][1]=getPOS(s);
                    ob[i][2]=getLema(s);
                    ob[i][3]=getGramCats(s);
                    if(pos.equals(getPOS(s))){
                        ob[i][4]=allDela[2];
                    }
                    i=i+1;
                }
            }
        }
        return ob;
    }
    public static String getUlaz(String text){
        return text;
    }
    public static String getPOS(String text){
        StringBuilder sb = new StringBuilder();
        boolean begin=false;
        for(int i=0;i<text.length();i++){
            if(text.charAt(i)==':'||text.charAt(i)=='+'){
                break;
            }
            if(text.charAt(i)=='.'){
                begin=true;
                i++;
            }
            if(begin){
                sb.append(text.charAt(i));
            }
        }
        return sb.toString();
    }
    public static String getLema(String text){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<text.length();i++){
            if(text.charAt(i)==','){
                break;
            }
            sb.append(text.charAt(i));
        }
        return sb.toString();
    }
    public static String getGramCats(String text){
        StringBuilder sb = new StringBuilder();
        boolean begin=false;
        for(int i=0;i<text.length();i++){
            if(text.charAt(i)==':'){
                begin=true;
                i++;
            }
            if(begin){
                sb.append(text.charAt(i));
            }
        }
        return sb.toString();
    }
}
