import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Katrina Balestino
 * Amber Kirk
 */
public class MovieViewer extends JFrame implements ActionListener{
    private JComboBox movieComboBox;
    private JComboBox genreComboBox;
    private final HashMap movieSubItems;
    private JLabel outputLabel;
    private String tempMovie;
    private JButton movieButton;
    private JPanel selectorPanel;
    private JPanel buttonPanel; //For next deliverable
    private final MovieCntl movieCntl;

    public MovieViewer(MovieCntl movieCntl, int startIndex){
        this.movieSubItems = new HashMap();
        this.movieCntl = movieCntl;
        display();
    }
    
    private void display() {
        setTitle("Genie Movie Selector");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        selectorPanel = new JPanel(new GridLayout(5, 5));
        selectorPanel.add(new JLabel("GENIE MOVIE SELECTOR"));
        
        //Add combo boxes to panel
        getComboBox();

        outputLabel = new JLabel();
        selectorPanel.add(outputLabel);
        
        //Code for Deliverable 3
        //Add button to panel
        //buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //movieButton = new JButton("Get Movie");
        //movieButton.addActionListener(this);
        //buttonPanel.add(movieButton);
        
        setContentPane(new JPanel(new BorderLayout()));
        getContentPane().add(selectorPanel, BorderLayout.CENTER);
        //getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void getComboBox()
    {
        //Put all genres on array
        String[] genreArray = new String[6];
        for(int i = 0; i < genreArray.length; i++) {
            genreArray[i] = movieCntl.getGenre(i);
        }
        genreComboBox = new JComboBox(genreArray);
        genreComboBox.addActionListener(this);
        selectorPanel.add(genreComboBox, BorderLayout.WEST);
        
        movieComboBox = new JComboBox();
        selectorPanel.add(movieComboBox, BorderLayout.EAST);
        
        //Puts all fantasy movies on array
        String[] movieArrayFantasy = new String[5];
        for(int i = 0; i < movieArrayFantasy.length; i++) {
            movieArrayFantasy[i] = movieCntl.getMovie(i).toString();
        }
        movieSubItems.put(genreArray[1], movieArrayFantasy);
        
        //Puts all animated movies on array
        String[] movieArrayAnimated = new String[5];
        for(int i = 0; i < movieArrayAnimated.length; i++) {
            movieArrayAnimated[i] = movieCntl.getMovie(i + 5).toString();
        }
        movieSubItems.put(genreArray[2], movieArrayAnimated); 
        
        //Puts all musical movies on array
        String[] movieArrayMusicals = new String[5];
        for(int i = 0; i < movieArrayMusicals.length; i++) {
            movieArrayMusicals[i] = movieCntl.getMovie(i + 10).toString();
        }
        movieSubItems.put(genreArray[3], movieArrayMusicals); 
        
        //Puts all romance movies on array
        String[] movieArrayRomance = new String[5];
        for(int i = 0; i < movieArrayAnimated.length; i++) {
            movieArrayRomance[i] = movieCntl.getMovie(i + 15).toString();
        }
        movieSubItems.put(genreArray[4], movieArrayRomance); 
        
        //Puts all comedy movies on array
        String[] movieArrayComedy = new String[5];
        for(int i = 0; i < movieArrayComedy.length; i++) {
            movieArrayComedy[i] = movieCntl.getMovie(i + 20).toString();
        }
        movieSubItems.put(genreArray[5], movieArrayComedy); 
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String item = (String) genreComboBox.getSelectedItem();
        Object o = movieSubItems.get(item);
        
        if (e.getSource() == movieButton)
        {
            outputLabel.setText("Movie Selected: " + tempMovie);
            //outputLabel.setText("Movie Selected" + movieCntl.getMovie(1).getLeadActors());
        }
        else if (o == null) {
            movieComboBox.setModel(new DefaultComboBoxModel());
        }
        else {
            movieComboBox.setModel(new DefaultComboBoxModel((String[]) o));
            tempMovie = (String) movieComboBox.getSelectedItem();
        }
    }
}