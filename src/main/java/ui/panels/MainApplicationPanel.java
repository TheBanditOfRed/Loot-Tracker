package ui.panels;

import com.thebanditofred.utils.ResourceManager;
import ui.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public class MainApplicationPanel extends JPanel {
    private static final Logger logger = Logger.getLogger(MainApplicationPanel.class.getName());

    public static JPanel mainPanel;

    public static void createMainPanel(GUI gui) {
        try {
            mainPanel = new JPanel(new BorderLayout());

            JPanel topPanel = new JPanel(new BorderLayout());
            JButton optionsButton = new JButton(ResourceManager.getString("menu.button"));

            topPanel.add(optionsButton, BorderLayout.EAST);

            //JTabbedPane tabbedPane = new JTabbedPane();
            //tabbedPane.addTab(ResourceManager.getString("tab."), );
            //tabbedPane.addTab(ResourceManager.getString("tab.mybooks"), MyBooksPanel.createMyBooksPanel(gui));

            mainPanel.add(topPanel, BorderLayout.NORTH);
            //mainPanel.add(tabbedPane, BorderLayout.CENTER);

            optionsButton.addActionListener(_ -> gui.showOptionsMenu(optionsButton));

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error creating main application panel", e);
            JOptionPane.showMessageDialog(gui,
                    ResourceManager.getString("error.mainpanel.creation"),
                    ResourceManager.getString("error.title"),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
