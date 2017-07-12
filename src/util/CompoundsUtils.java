/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.HeadlessException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.StaticValue;

/**
 *
 * @author rojo
 */
public class CompoundsUtils {
    public static List<String> getDlfInFile(String value) throws IOException, HeadlessException {
        List<String> result = new ArrayList<>();
        String tempPath = StaticValue.delafTmpPathDelac;
        Utils.generateDelaf(tempPath, value);
        String path = StaticValue.text_sntAbsPath;
        ArrayList<String> readFile = Utils.readFile(path);
        return readFile;
    }
}
