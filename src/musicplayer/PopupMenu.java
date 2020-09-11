/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Doan Phuc
 */
public class PopupMenu {
    JPopupMenu menu_list = new JPopupMenu();
    JPopupMenu menu_listNode = new JPopupMenu();
    JPopupMenu mymusic = new JPopupMenu();
    JMenuItem open_item = new JMenuItem("Open List");
    JMenuItem create_playlist_item = new JMenuItem("Create new playlist");
    JMenuItem add_item = new JMenuItem("Add file");
    JMenuItem rename_item = new JMenuItem("Rename List");
    JMenuItem delete_item = new JMenuItem("Delete List");
    JMenuItem open_mymusic = new JMenuItem("Open My Music folder");
    
    
    public PopupMenu()
    {
        menu_list.add(open_item);
        menu_list.add(create_playlist_item);
        menu_listNode.add(open_item);
        menu_listNode.add(add_item);
        menu_listNode.add(rename_item);
        menu_listNode.add(delete_item);
        mymusic.add(open_mymusic);
    }
    public void showMenu(MouseEvent e,JPopupMenu menu)
    {
        Component b = (Component) e.getSource();
        Point p = e.getLocationOnScreen();
        menu.show(b, p.x, p.y);
        menu.setLocation(p.x,p.y);
    }
}