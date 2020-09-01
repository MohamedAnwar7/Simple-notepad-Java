import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
 
public class NotePad extends JFrame implements ActionListener {
    
        private JTextArea textArea = new JTextArea("");
        private MenuBar menuBar = new MenuBar(); //menu bar
	private Menu file = new Menu();//file menu
        private Menu edit = new Menu("Edit");//
        private Menu view = new Menu();// view menu
        private Menu about = new Menu();//about menu
        //menu
	private MenuItem openFile = new MenuItem();  // an open option
	private MenuItem saveFile = new MenuItem(); // a save option
	private MenuItem close = new MenuItem(); // and a close option!
        
        //edit items
        private MenuItem red = new MenuItem();
        private MenuItem green = new MenuItem();
        //edit items
        private MenuItem copy = new MenuItem("Copy");
        private MenuItem past = new MenuItem("Past");
        //about
        private MenuItem am = new MenuItem();//>>>my information

 
	public NotePad() {
		this.setSize(500, 300); // set the initial size of the window
		this.setTitle("My java"); // set the title of the window
		setDefaultCloseOperation(EXIT_ON_CLOSE); // set the default close operation (exit when it gets closed)
		this.textArea.setFont(new Font("Century Gothic", Font.BOLD, 12)); // set a default font for the TextArea
		
		
		this.getContentPane().add(textArea);
 
		// add our menu bar into the GUI
		this.setMenuBar(this.menuBar);
                ///file
		this.menuBar.add(this.file); 
		this.file.setLabel("File");
                
                //about menu 
                this.menuBar.add(this.about);
                this.about.setLabel("About");
                //view
                this.menuBar.add(this.view);
                this.view.setLabel("View");
                //edit
                this.menuBar.add(this.edit);
                
            ///file items
		
		this.openFile.setLabel("Open"); // set the label of the menu item
		this.openFile.addActionListener(this); // add an action listener (so we know when it's been clicked
		this.openFile.setShortcut(new MenuShortcut(KeyEvent.VK_O, false)); // set a keyboard shortcut
		this.file.add(this.openFile); // add it to the "File" menu
 
		// and the save...
		this.saveFile.setLabel("Save");
		this.saveFile.addActionListener(this);
		this.saveFile.setShortcut(new MenuShortcut(KeyEvent.VK_S, false));
		this.file.add(this.saveFile);
 
            // , the close option
		this.close.setLabel("Close");
		this.close.setShortcut(new MenuShortcut(KeyEvent.VK_F4, false));
		this.close.addActionListener(this);
		this.file.add(this.close);
            //About item
                this.am.setLabel("my details");
                this.am.addActionListener(this);
                this.about.add(this.am);
            //view items
                this.red.setLabel("red");
                this.red.addActionListener(this);
                this.view.add(this.red);
                
                this.green.setLabel("green");
                this.green.addActionListener(this);
                this.view.add(this.green);
            //Edit items
               this.copy.addActionListener(this);
               this.edit.add(this.copy);
               this.past.addActionListener(this);
               this.edit.add(past);
                
                
               
              
	}
 
  @Override
	public void actionPerformed (ActionEvent e) {
		// if the source of the event was our "close" option
		if (e.getSource() == this.close)
			this.dispose(); // dispose all resources and close the application
 
		// if the source was the "open" option
		else if (e.getSource() == this.openFile) {
			JFileChooser open = new JFileChooser(); // open up a file chooser (a dialog for the user to browse files to open)
			int option = open.showOpenDialog(this); // get the option that the user selected (approve or cancel)
			// NOTE: because we are OPENing a file, we call showOpenDialog~
			// if the user clicked OK, we have "APPROVE_OPTION"
			// so we want to open the file
			if (option == JFileChooser.APPROVE_OPTION) {
				this.textArea.setText(""); // clear the TextArea before applying the file contents
				try {
					// create a scanner to read the file (getSelectedFile().getPath() will get the path to the file)
					Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
					while (scan.hasNext()) // while there's still something to read
						this.textArea.append(scan.nextLine() + "\n"); // append the line to the TextArea
				} catch (Exception ex) { // catch any exceptions, and...
					// ...write to the debug console
					JOptionPane.showMessageDialog(null,"ERROR");
				}
			}
		}
 
		// and lastly, if the source of the event was the "save" option
		else if (e.getSource() == this.saveFile) {
			JFileChooser save = new JFileChooser(); // again, open a file chooser
			int option = save.showSaveDialog(this); // similar to the open file, only this time we call
			// showSaveDialog instead of showOpenDialog
			// if the user clicked OK (and not cancel)
			if (option == JFileChooser.APPROVE_OPTION) {
				try {
					// create a buffered writer to write to a file
				BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
					out.write(this.textArea.getText()); // write the contents of the TextArea to the file
					out.close(); // close the file stream
				} catch (Exception ex) { // again, catch any exceptions and...
					// ...write to the debug console
					System.out.println(ex.getMessage());
				}
			}
		}else if(e.getSource()==this.red){
                    this.textArea.setBackground(Color.red);
                    
                }else if(e.getSource()==this.green){
                    this.textArea.setBackground(Color.green);
                    
                }
                else if(e.getSource()==this.am){
                    JOptionPane.showMessageDialog(null,"\n\n\n\n"+"Name:MOHAMED ANWAR "+"\n"+"Section 8"+"\n"+"NOTEPAD PROJECT"+"\n\n\n\n\n");
                }else if(e.getSource()==this.copy){
                    textArea.copy();   
               }else if(e.getSource()==this.past){
                   textArea.paste();
               }
	}
 
	public static void main(String args[]) {
        NotePad np = new NotePad();
        np.setVisible(true);
	
	
}
}
 