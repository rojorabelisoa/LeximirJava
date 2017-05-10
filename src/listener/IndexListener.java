/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import leximir.Index;

/**
 *
 * @author rojo
 */
public class IndexListener implements ActionListener{
    private Index panel;

    public IndexListener(Index panel) {
        this.panel = panel;
    }

    public IndexListener() {
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //Open open = new Open();
        //open.setVisible(true);
       
    }
    
}
