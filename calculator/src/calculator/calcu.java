package calculator;
//
//public class calcu {
//
//}


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class calcu extends JFrame implements ActionListener{
    String symbol[]={"%","CE","C","⌫","1/x","x²","√x","÷","7","8","9","×","4","5","6","-","1","2","3","+","±","0",".","="};
    JTextField tf1;
    JButton b[];
    JPanel p1;
    boolean flag=true;
    double old;
    char op='\u0000';
    calcu(){
        p1 = new JPanel();
        b=new JButton[24];
        p1.setLayout(new GridLayout(6,4));

        tf1=new JTextField("0");
        tf1.setFont(new Font("Arial",Font.PLAIN,30));
        tf1.setEditable(false);
        tf1.setHorizontalAlignment(SwingConstants.RIGHT);
        tf1.setPreferredSize(new Dimension(tf1.getPreferredSize().width, 50));
        
        for(int i=0;i<24;i++){
        
            b[i]=new JButton(symbol[i]);
        
            b[i].setBackground(Color.white);
            b[i].addActionListener(this);
            b[i].addMouseListener(new A());//ADDING EVENT TO BUTTONS TO CHANGE ITS COLOUR
            b[i].setFont(new Font("Arial",Font.BOLD,20));
            p1.add(b[i]);
        }
        
        add(tf1,BorderLayout.NORTH);
        add(p1,BorderLayout.CENTER);
        setSize(350,500);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String args[]){
        new calcu();
    }

     public void actionPerformed(ActionEvent ev){
        JButton bb=(JButton)ev.getSource();
        String s=bb.getText();
        String current=tf1.getText();

        if(Character.isDigit(s.charAt(0))){
            if(flag==true)
                tf1.setText(filter(current+s));
            else{
                tf1.setText(filter(s));
                flag=true;
            }
        }
        else if(isOperator(s.charAt(0))){
            if(op!='\u0000'){
                double n=Double.parseDouble(current);
                if(op=='+'){
                    old=old+n;
                 }else if(op=='-'){
                    old=old-n;
                 }
                 else if(op=='×'){
                   old=old*n;
    
                 }else if(op=='÷'){
                    old=old/n;
    
                 }
                 else{
                    old=old%n;
    
                 }
                 tf1.setText(filter(old+""));
            
            }
           flag=false;
           old=Double.parseDouble(current);

           op=s.charAt(0);
           
            

        }
        else if(s.equals("=")){
             double n=Double.parseDouble(current);
             if(op=='+'){
                tf1.setText(filter(old+n+""));
             }else if(op=='-'){
                tf1.setText(filter(old-n+""));
             }
             else if(op=='×'){
                tf1.setText(filter(old*n+""));

             }else if(op=='÷'){
                tf1.setText(filter(old/n+""));

             }
             else{
                tf1.setText(filter(old%n+""));

             }
        }
        else if(s.equals("1/x")){
            double n = Double.parseDouble(current);
            if (n == 0) {
                tf1.setText("Cannot divide by zero");
            } else {
                tf1.setText(filter(1 / n + ""));
            }

        }else if(s.equals("x²")){
            double n=Double.parseDouble(current);
             tf1.setText(filter(n*n+""));

        }else if(s.equals("√x")){
            double n=Double.parseDouble(current);
             tf1.setText(filter(Math.sqrt(n)+""));

        }else if(s.equals("CE")){
            tf1.setText("0");

        }else if(s.equals("C")){
            op='\u0000';
            old=0;
            tf1.setText("0");
            
        }else if(s.equals(".")){

        }else{//⌫
              String st=current.substring(0,current.length()-1);
              tf1.setText(filter(st));
        }
     } 
     boolean isOperator(char ch){//TO check operator
    
        if(ch=='%' || ch=='÷' || ch=='-' || ch=='+' || ch=='±' ||ch=='×'){
            return true;
        } else{
            return false;
        }
        
        
        
     }  
     String filter(String value){//TO Remove Zero 
        if(value.isEmpty()){
            return "0";
        }
         double n=Double.parseDouble(value);

       
            
          if(n==(int)n)
               return (int)n+"";
            else
               return n+"";
        }
}

        

                  
    

class A extends MouseAdapter{
    public void mouseEntered(MouseEvent ev){
        ((JButton)ev.getSource()).setBackground(Color.cyan);
    }
    public void mouseExited(MouseEvent ev){
        ((JButton)ev.getSource()).setBackground(Color.white);
    }
}
              


