package ui;

import com.thebanditofred.utils.LoggingManager;
import com.thebanditofred.utils.ResourceManager;
import ui.panels.MainApplicationPanel;
import ui.utils.LanguageHelper;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

public class GUI extends JFrame {
    private static final Logger logger = Logger.getLogger(GUI.class.getName());

    public JPanel cardPanel;

    public CardLayout cardLayout;

    public final Preferences prefs = Preferences.userNodeForPackage(GUI.class);

    public GUI() {
        logger.log(Level.INFO, "Initializing GUI application window");
        initializeWindow();
        initializeLayout();
        initializePanels();
        logger.log(Level.INFO, "GUI application window initialized");
    }

    private void initializeWindow() {
        setTitle(ResourceManager.getString("app.title"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int width = prefs.getInt("window.width", 800);
        int height = prefs.getInt("window.height", 400);
        int x = prefs.getInt("window.x", -1);
        int y = prefs.getInt("window.y", -1);

        setSize(width, height);

        if (x >= 0 && y >= 0) {
            setLocation(x, y);
        } else {
            setLocationRelativeTo(null); // Center on screen if no position is saved
        }

        setApplicationIcon();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                saveWindowState();
            }
        });
    }

    private void saveWindowState() {
        prefs.putInt("window.width", getWidth());
        prefs.putInt("window.height", getHeight());
        prefs.putInt("window.x", getX());
        prefs.putInt("window.y", getY());
    }

    private void initializeLayout() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        setContentPane(cardPanel);
    }

    private void initializePanels() {
        // Create instance of LoginPanel
        //LoginPanel loginPanel = new LoginPanel(this);
        //cardPanel.add(loginPanel, "login");

        // Create main panel (assuming MainApplicationPanel is still static for now)
        MainApplicationPanel.createMainPanel(this);
        cardPanel.add(MainApplicationPanel.mainPanel, "main");
    }

    public void setApplicationIcon() {
        logger.log(Level.INFO, "Attempting to load application icons...");

        try {
            java.util.List<Image> iconImages = new java.util.ArrayList<>();

            // Try to load multiple icon sizes
            String[] iconPaths = {
                    "/icon/icon-16.png",
                    "/icon/icon-32.png",
                    "/icon/icon-64.png",
                    "/icon/icon-128.png",
                    "/icon/icon-256.png",
                    "/icon/icon.png" // fallback
            };

            for (String path : iconPaths) {
                try {
                    if (getClass().getResource(path) != null) {
                        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
                        iconImages.add(icon.getImage());
                        logger.log(Level.INFO, "Successfully loaded icon: " + path);
                    } else {
                        logger.log(Level.WARNING, "Icon resource not found: " + path);
                    }
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Failed to load icon: " + path + " - " + e.getMessage());
                }
            }

            if (!iconImages.isEmpty()) {
                setIconImages(iconImages);
                logger.log(Level.INFO, "Application icons set successfully (" + iconImages.size() + " sizes loaded)");
            } else {
                logger.log(Level.WARNING, "No application icons could be loaded - using default system icon");
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Critical error setting application icons: " + e.getMessage(), e);
        }
    }

    public void showOptionsMenu(JComponent component) {
        JPopupMenu optionsMenu = new JPopupMenu();

        JMenu languageMenu = new JMenu(ResourceManager.getString("menu.language"));

        JMenuItem englishItem = new JMenuItem(ResourceManager.getString("menu.language.english"));
        englishItem.addActionListener(_ -> LanguageHelper.changeLanguage(this, "en"));

        languageMenu.add(englishItem);

        JMenuItem quitItem = new JMenuItem(ResourceManager.getString("menu.quit"));
        quitItem.addActionListener(_ -> exitApplication());

        optionsMenu.add(languageMenu);
        optionsMenu.addSeparator();
        optionsMenu.add(quitItem);

        optionsMenu.show(component, 0, component.getHeight());
    }

    private void exitApplication() {
        int response = JOptionPane.showConfirmDialog(
                this,
                ResourceManager.getString("menu.quit.confirm"),
                ResourceManager.getString("menu.quit.confirm.title"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (response == JOptionPane.YES_OPTION) {
            logger.log(Level.INFO, "User initiated application shutdown");

            try {
                // Ensure all logs are written
                LoggingManager.flushLogs();

                logger.log(Level.INFO, "Application shutdown complete");

                // Exit the application
                System.exit(0);

            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error during application shutdown", e);
                System.exit(1);
            }
        }
    }
}
