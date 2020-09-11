/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
/**
 *
 * @author Doan Phuc
 */
public class Library_treeNode extends PopupMenu implements MouseListener,MouseMotionListener,ActionListener{
    
    private final JTree tree = new JTree();
    private DefaultMutableTreeNode node;
    private final DefaultMutableTreeNode root;
    private JFileChooser fileChooser = new JFileChooser();
    FileMethods file_method = new FileMethods();
    private ArrayList<SongPanel> arrayList = new ArrayList();
    private JPanel mainPnl;
    private JLabel listName;
    private JScrollPane pane;
    private NowPlayingPanel playingPanel;
    
    ///////////////////////////////////////////////////
    public Library_treeNode(JPanel mainPnl,JScrollPane pane,JLabel listName,NowPlayingPanel playingPanel)
    {
        this.playingPanel = playingPanel;
        this.listName = listName;
        this.mainPnl = mainPnl;
        this.pane = pane;
        root = new DefaultMutableTreeNode("Library");
        tree.setOpaque(false);
        tree.setModel(new DefaultTreeModel(root,false));
        node = new DefaultMutableTreeNode("Playlists");
        root.add(node);
        node = new DefaultMutableTreeNode("My Music");
        root.add(node);
        tree.addMouseMotionListener(this);
        tree.addMouseListener(this);
        create_playlist_item.addActionListener(this);
        add_item.addActionListener(this);
        delete_item.addActionListener(this);
        rename_item.addActionListener(this);
        open_item.addActionListener(this);
        open_mymusic.addActionListener(this);
    }
    public JTree getTree()
    {
        return tree;
    }
    public boolean addToPlaylistNode(String s)
    {
        node = search_node(root, "Playlists");
        if(search_node(root, s)==null)
        {
            node.add(new DefaultMutableTreeNode(s));
            return true;
        }
        else
            JOptionPane.showMessageDialog(null, "A playlist named '"+s+"' alreday exists!","Music Player Demo",JOptionPane.WARNING_MESSAGE);
        return false;
    }
    public DefaultMutableTreeNode search_node(DefaultMutableTreeNode root,String s)
    {
        DefaultMutableTreeNode node;
        int i;
        if(root == null) return null;
        node = root;
        if(s.compareTo(node.toString())==0) 
            return node;
        else
        {
            for(i = 0 ; i< root.getChildCount() ; i++)
            {
                node = search_node((DefaultMutableTreeNode) root.getChildAt(i), s);
                if(node != null) return node;
            }
        }
        return null;
    }
    private void expandAllNodes(JTree tree, int startingIndex, int rowCount)
    {
        for(int i=startingIndex;i<rowCount;i++){
            tree.expandRow(i);
        }
        if(tree.getRowCount()!=rowCount)
        {
            expandAllNodes(tree, rowCount, tree.getRowCount());
        }
    }
    public void expandAllNodes()
    {
        expandAllNodes(tree, 0, tree.getRowCount());
    }
    
    @Override
    public void mouseDragged(MouseEvent me) {}

    @Override
    public void mouseMoved(MouseEvent me) {
        Object ob  = me.getSource();
        int x = me.getX(),y=me.getY();
        TreePath path;
        if(ob == tree)
        {
            path = tree.getPathForLocation(x,y);
            tree.setSelectionPath(path);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        Object ob = me.getSource();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if(ob == tree)
        {
            if(SwingUtilities.isRightMouseButton(me)&& node == search_node(root, "Playlists"))
                showMenu(me , menu_list);
            if(SwingUtilities.isRightMouseButton(me)&& node.getParent() == search_node(root,"Playlists"))
                showMenu(me, menu_listNode);
            if(me.getClickCount()==2&&node.getParent() == search_node(root,"Playlists"))
                readFromFile();
            if(me.getClickCount()==2&& node == search_node(root, "My Music"))
                readMyMusic();
            if(SwingUtilities.isRightMouseButton(me)&& node == search_node(root, "My Music"))
            {
                showMenu(me, mymusic);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {}

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object ob = ae.getSource();
        String s;
        if(ob == create_playlist_item)
        {
            s = JOptionPane.showInputDialog("");
            if(addToPlaylistNode(s)==true)
                file_method.createFile(s);
            tree.updateUI();
        }
        if(ob == rename_item)
            renameFile();
        if(ob == delete_item)
            deleteFile();
        if(ob == add_item)
        {
            try {
                getFile();
            } catch (Exception ex) {
                Logger.getLogger(Library_treeNode.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(ob == open_mymusic)
            readMyMusic();
        if(ob == open_item)
            readFromFile();
        ////////////////////////////////////////////////////////////////////
        for(int i = 0 ; i< arrayList.size();i++)
        {
            if(ob == arrayList.get(i).remove_item)
            {
                String str_temp = arrayList.get(i).removeElementAt(i);
                arrayList.clear();
                mainPnl.removeAll();
                try {
                    file_method.removeLink(listName.getText(), str_temp);
                    arrayList = file_method.readFromFile(listName.getText(),playingPanel);
                    mainPnl.setPreferredSize(new Dimension(480, 90*arrayList.size()));
                    for(int j = 0 ;j < arrayList.size();j++)
                    {
                        mainPnl.add(arrayList.get(j));
                        arrayList.get(j).setBounds(0, j*90, arrayList.get(j).getWidth(), arrayList.get(j).getHeight());
                        arrayList.get(j).remove_item.addActionListener(this);
                    }
                    pane.updateUI();
                } catch (Exception ex) {
                    Logger.getLogger(Library_treeNode.class.getName()).log(Level.SEVERE, null, ex);
                }    
            }
        }
    }    
    ///////////////////////////////////////////////////////////////////////
    private void renameFile()
    {
        String s = JOptionPane.showInputDialog(null, "New playlist name:", "Music Player Demo", JOptionPane.PLAIN_MESSAGE);
        String name;
        if(search_node(root, s)==null)
        {
            node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            name = node.toString();
            node.setUserObject(s);
            file_method.renameFile(name, s);
            tree.updateUI();                
        }
        else
            JOptionPane.showMessageDialog(null, "A playlist named '"+s+"' alreday exists!","Music Player Demo",JOptionPane.WARNING_MESSAGE);
    }
    private void deleteFile()
    {
        node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        file_method.deleteFile(node.toString());
        if(node.toString().compareTo(listName.getText())==0)
        {
            listName.setText("");
            arrayList.clear();
            mainPnl.removeAll();
            pane.updateUI();
        }
        search_node(root, "Playlists").remove(node);
        tree.updateUI();
    }
    private void getFile() throws Exception
    {
        File file[];
        String content = "";
        node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        String nodename = node.toString();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setAcceptAllFileFilterUsed(true);
        int status = fileChooser.showOpenDialog(null);
        if(status == JFileChooser.APPROVE_OPTION)
        {
            file = fileChooser.getSelectedFiles();
            for(File file1 : file)
            {
                if(file1.isFile())
                {
                    String temp = file1.getName();
                    int lastIndex = temp.lastIndexOf(".");
                    if(lastIndex != -1&& temp.substring(lastIndex+1).toLowerCase().compareTo("mp3")==0)
                        content += file1.getPath()+"\n";
                }
            }
            file_method.writeToFile(nodename, content);
        }
        if(nodename.compareTo(listName.getText())==0)
        {
            arrayList.clear();
            mainPnl.removeAll();
            arrayList = file_method.readFromFile(nodename,playingPanel);
            mainPnl.setPreferredSize(new Dimension(480, 90*arrayList.size()));
            for(int i = 0 ;i < arrayList.size();i++)
            {
                mainPnl.add(arrayList.get(i));
                arrayList.get(i).setBounds(0, i*90, arrayList.get(i).getWidth(), arrayList.get(i).getHeight());
                arrayList.get(i).remove_item.addActionListener(this);
            }
            pane.updateUI();
        }
    }
    private void readFromFile()
    {
        mainPnl.removeAll();
        arrayList.clear();
        try {
            node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            arrayList = file_method.readFromFile(node.toString(),playingPanel);
            mainPnl.setPreferredSize(new Dimension(480, 90*arrayList.size()));
            listName.setText(node.toString());
            for(int i = 0 ;i < arrayList.size();i++)
            {
                mainPnl.add(arrayList.get(i));
                arrayList.get(i).setBounds(0, i*90, arrayList.get(i).getWidth(), arrayList.get(i).getHeight());
                arrayList.get(i).remove_item.addActionListener(this);
            }
            pane.updateUI();
        } catch (Exception ex) {
            Logger.getLogger(Library_treeNode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void readMyMusic()
    {
        mainPnl.removeAll();
        arrayList.clear();
        arrayList = file_method.getMyMusicFile(playingPanel);
        mainPnl.setPreferredSize(new Dimension(480, 90*arrayList.size()));
        listName.setText("MY MUSIC");
        for(int i = 0 ;i < arrayList.size();i++)
        {
            mainPnl.add(arrayList.get(i));
            arrayList.get(i).setBounds(0, i*90, arrayList.get(i).getWidth(), arrayList.get(i).getHeight());
            arrayList.get(i).menu_listSong.remove(arrayList.get(i).remove_item);
        }
        pane.updateUI();
    }
}
