import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


import javax.swing.*;
import javax.swing.border.*;


public class TopFiveDestinationList {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	TopDestinationListFrame topDestinationListFrame = new TopDestinationListFrame();
                topDestinationListFrame.setTitle("Top 5 Destination List");
                topDestinationListFrame.setVisible(true);
            }
        });
    }
}

// added ActionListener and implemented method
class TopDestinationListFrame extends JFrame implements ActionListener {
	// added sort button
	JButton myButton = new JButton("Sort");
    private DefaultListModel listModel;
    
    public TopDestinationListFrame() {
        super("Top Five Destination List");
       
        // added button details
        myButton.setFocusable(false);
        myButton.addActionListener(this);
        
        
        listModel = new DefaultListModel();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 750);
        //Make updates to your top 5 list below. Import the new image files to resources directory.
        // added prices to the end of each list in the string variable and the int variable
        addDestinationNameAndPictureAndPrice("1. Jamaica featuring an enormous amount of color, culture and engaging activities. $104 per day", new ImageIcon(getClass().getResource("/resources/Jamaica.jpg")), 104);
        addDestinationNameAndPictureAndPrice("2. Alaska home of the 20 highest peaks in the USA and featuring the Yukon River which is the third longest river in the USA. $400 per day", new ImageIcon(getClass().getResource("/resources/Alaska.jpg")), 400);
        addDestinationNameAndPictureAndPrice("3. Tahiti our honeymoon destination features miles of shorline and dozens of resorts. $164 per day", new ImageIcon(getClass().getResource("/resources/Tahiti.jpg")), 164);
        addDestinationNameAndPictureAndPrice("4. New Zealand offers a grand adventure of Dwarves, Elves, Orcs, and Wizards as we travel to deliver the ring to Moordoor in our new exclusive Hobbit Hole Package. $125 per day", new ImageIcon(getClass().getResource("/resources/NewZealand.jpg")), 125);
        addDestinationNameAndPictureAndPrice("5. England offers great food, and historic experiences as well as a chance to meet the Queen herself in our new exclusive travel package. $163 per day", new ImageIcon(getClass().getResource("/resources/England.jpg")), 163);
        
        JList list = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        TextAndIconListCellRenderer renderer = new TextAndIconListCellRenderer(2);
        list.setCellRenderer(renderer);
        
        // added my name and button
        JLabel nameLabel = new JLabel("Developer: Zachary Meisner");
        getContentPane().add(myButton, BorderLayout.EAST);
        getContentPane().add(nameLabel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        

        
    }
   

     private void addDestinationNameAndPictureAndPrice(String text, Icon icon, Integer num) {
        TextAndIcon tai = new TextAndIcon(text, icon, num);
        listModel.addElement(tai);
        
    }
     // overloaded constructor for sort method
     private void addDestinationNameAndPictureAndPrice(Object [] obj) {
         TextAndIcon tai = new TextAndIcon(obj);
         listModel.addElement(obj);
         
     }
    
//method to sort by price
	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<Object> tais = new ArrayList<Object>();
		
		for (int i = 0; i < listModel.getSize(); i++) {
			tais.add(listModel.getElementAt(i));
		}
		listModel.removeAllElements();
		tais.sort(Collections.reverseOrder());
		for (int j = 0; j < tais.size(); j++) {
		listModel.addElement(tais.get(j));
		
		
		}
	}
			}

	


		


 class TextAndIcon implements Comparable<TextAndIcon>{
	
    private String text;
    private Icon icon;
    // added private int num to allow constructor to set/get variable
	private Integer num;
    
    // Changed Constructure to allow for integer in addition to text and Icon
    public TextAndIcon(String text, Icon icon, Integer num) {
        this.text = text;
        this.icon = icon;
        // added this
        this.num = num;
    }
    
// overloaded constructor that allows adding objects
	public TextAndIcon(Object[] obj) {
		// TODO Auto-generated constructor stub
	}


	// added toString method to print out objects in array for button
    public String toString() {
    	return text + "\n" + icon + "\n" + num + "\n\n";
    }

    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }
    // added getter
    public Integer getNum() {
    	return num;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
    // added setter
    public void setNum(Integer num) {
    	this.num = num;
    }
    // compares two objects
    @Override
    public int compareTo(TextAndIcon t) {
    	return this.getNum().compareTo(t.getNum());
    }
    // compares two objects using lambda expression
    Comparator<TextAndIcon> compareByNum = (TextAndIcon t1, TextAndIcon t2) ->
    t1.getNum().compareTo(t2.getNum());


}


class TextAndIconListCellRenderer extends JLabel implements ListCellRenderer {
    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private Border insideBorder;

    public TextAndIconListCellRenderer() {
        this(0, 0, 0, 0);
    }

    public TextAndIconListCellRenderer(int padding) {
        this(padding, padding, padding, padding);
    }

    public TextAndIconListCellRenderer(int topPadding, int rightPadding, int bottomPadding, int leftPadding) {
        insideBorder = BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding);
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value,
    int index, boolean isSelected, boolean hasFocus) {
        // The object from the combo box model MUST be a TextAndIcon.
        TextAndIcon tai = (TextAndIcon) value;

        // Sets text and icon on 'this' JLabel.
        setText(tai.getText());
        setIcon(tai.getIcon());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        Border outsideBorder;

        if (hasFocus) {
            outsideBorder = UIManager.getBorder("List.focusCellHighlightBorder");
        } else {
            outsideBorder = NO_FOCUS_BORDER;
        }

        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        setComponentOrientation(list.getComponentOrientation());
        setEnabled(list.isEnabled());
        setFont(list.getFont());

        return this;
    }

    // The following methods are overridden to be empty for performance
    // reasons. If you want to understand better why, please read:
    //
    // http://java.sun.com/javase/6/docs/api/javax/swing/DefaultListCellRenderer.html#override

    public void validate() {}
    public void invalidate() {}
    public void repaint() {}
    public void revalidate() {}
    public void repaint(long tm, int x, int y, int width, int height) {}
    public void repaint(Rectangle r) {}
}