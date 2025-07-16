package notepad;
//
//public class calcu {
//
//}

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;

public class notepad extends JFrame implements ActionListener {

	JMenuBar bar;
	JMenu file,Edit;
	JMenuItem I1,I2,I3,I4,I5,I6,I7,I8,I9;
	JTextArea txt;
	
	File currentfile=null;
	boolean saveflag=true;
	notepad(){
		setTitle("NOTEPAD");
		setExtendedState(MAXIMIZED_BOTH);//TO MAKE NOTEPAD IN FULL SCREEN
		txt=new JTextArea();
		Font f=new Font("ARIAL",Font.PLAIN,20);
		txt.setFont(f);
		I1=new JMenuItem("new");
		I1.setMnemonic(KeyEvent.VK_N);//ShortCut of New
		I2=new JMenuItem("open");
		I3=new JMenuItem("save");
		I3.setMnemonic(KeyEvent.VK_S);//SHORTCUT of Save
		I4=new JMenuItem("save As");
		I5=new JMenuItem("Exit");
        I5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));//ctrl+x
		
		I6=new JMenuItem("cut");
		I7=new JMenuItem("Copy");
		I8=new JMenuItem("Paste");
		I9=new JMenuItem("delete");
		
		I1.addActionListener(this);
		I2.addActionListener(this);
		I3.addActionListener(this);
		I4.addActionListener(this);
		I5.addActionListener(this);
		I6.addActionListener(this);
		I7.addActionListener(this);
		I8.addActionListener(this);
		I9.addActionListener(this);
        file=new JMenu("File");
		Edit=new JMenu("Edit");
		
		file.add(I1);file.add(I2);file.add(I3);file.add(I4);
		file.addSeparator();
		file.add(I5);
        
		Edit.add(I6);Edit.add(I7);Edit.add(I8);
		Edit.addSeparator();
		Edit.add(I9);
		
		bar=new JMenuBar();
		bar.add(file);
		bar.add(Edit);
		add(txt);
		setJMenuBar(bar);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ae) {
				if(saveflag==false) {
					int n=JOptionPane.showConfirmDialog(notepad.this,"save??","Notepad",JOptionPane.YES_NO_CANCEL_OPTION);
					if(n==JOptionPane.YES_OPTION) {
						save();
						dispose();
					}
					else if(n==JOptionPane.NO_OPTION){
						dispose();
					}
				}
				else {
					dispose();			
					}
			}
		});
		
		txt.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
			    saveflag=false;
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				saveflag=false;
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				saveflag=false;
				
			}
		});
		//setBounds(100,100,400,300); this line is not need cuz maximized already done
		setVisible(true);
	}
    public void actionPerformed(ActionEvent e) {
        String str=e.getActionCommand();//to get name of menuItem
        if(str.equalsIgnoreCase("new")) {
        	if(saveflag==false) {
            	int n=JOptionPane.showConfirmDialog(notepad.this,"save??","Notpad",JOptionPane.YES_NO_CANCEL_OPTION);
            	if(n==JOptionPane.YES_OPTION) {
            		save();
            		
            	}else {
            		txt.setText("");
            	}

        	}
        	setTitle("Notepad");
        	txt.setText("");
        	saveflag=true;
             
        }else if(str.equalsIgnoreCase("open")) {
              JFileChooser jfc=new JFileChooser("d:/matrix");
              FileNameExtensionFilter filter1=new FileNameExtensionFilter("Text file", "txt");
              jfc.addChoosableFileFilter(filter1);
              jfc.setFileFilter(filter1);//by default
              int n=jfc.showOpenDialog(notepad.this);
              if(n==JFileChooser.APPROVE_OPTION) {
                  File f=jfc.getSelectedFile();
                  try {
                      setTitle("Notepad "+f.getName());
                      FileInputStream fis=new FileInputStream(f);
                      txt.setText(new String(fis.readAllBytes()));
                      fis.close();
                  }
                  catch(IOException ae) {
                      System.out.println(ae);
                  }
              }
              
            }else if(str.equalsIgnoreCase("save")) {
	    	  
                save();
            }else if(str.equalsIgnoreCase("save as")){
                saveAs();
                
                }
                
          
            
        
            else if(str.equalsIgnoreCase("Exit")) {
                int n=JOptionPane.showConfirmDialog(txt,"Close?","Notepad",JOptionPane.YES_NO_OPTION);
               if(n==JOptionPane.YES_OPTION) {
                   dispose();
  
               }
               
                
            }else if(str.equalsIgnoreCase("Cut")) {
                txt.cut();
               
            }else if(str.equalsIgnoreCase("copy")) {
               txt.copy();
                
            }else {
                txt.paste();
            }
        }

	


        public static void main(String[] args) {
            new notepad();
    
        }
        void save() {
            if(currentfile==null) {
                saveAs();
            }
            else {
                try {
                    FileOutputStream fos=new FileOutputStream(currentfile);
                    fos.write(txt.getText().getBytes());
                    fos.close();
                }
                catch(IOException ae){
                    System.out.println(ae);
                }
        }
            saveflag=true;
    }
         void saveAs() {
            JFileChooser jfc=new JFileChooser("d:/matrix");
            FileNameExtensionFilter filter1=new FileNameExtensionFilter("text file", "txt");
            jfc.addChoosableFileFilter(filter1);
            jfc.setFileFilter(filter1);
            int n=jfc.showSaveDialog(notepad.this);
            if(n==JFileChooser.APPROVE_OPTION) {
                File f=jfc.getSelectedFile();
                try {
                    FileOutputStream fos=new FileOutputStream(f);
                    fos.write(txt.getText().getBytes());
                  currentfile=f;
                    fos.close();
                }
                catch(IOException ae) {
                    System.out.println(ae);
                }
                
            }
            saveflag=true;
         }
        
    }
     			