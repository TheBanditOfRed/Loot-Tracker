package ui.utils;

import com.thebanditofred.utils.ResourceManager;
import ui.GUI;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LanguageHelper {
    private static final Logger logger = Logger.getLogger(LanguageHelper.class.getName());

    /**
     * Changes the application's language and updates the UI text.
     * Includes error handling for preferences and resource operations.
     *
     * @param gui The GUI instance to update
     * @param languageCode The language code to switch to (e.g., "en" for English)
     */
    public static void changeLanguage(GUI gui, String languageCode) {
        String currentLanguage = gui.prefs.get("language", "en");
        logger.log(Level.INFO, "Language changed from " + currentLanguage + " to " + languageCode);

        try {
            if (languageCode == null || (!languageCode.equals("en") && !languageCode.equals("pt"))) {
                JOptionPane.showMessageDialog(gui,
                        ResourceManager.getString("menu.language.invalid"),
                        ResourceManager.getString("menu.language.title.invalid"),
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                gui.prefs.put("language", languageCode);
                logger.log(Level.INFO, "Language preference saved successfully: " + languageCode);
            } catch (Exception e) {
                logger.log(Level.WARNING, "Failed to save language preference", e);
            }

            try {
                ResourceManager.setLocale(languageCode);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(gui,
                        ResourceManager.getString("menu.language.resource") + ": " + e.getMessage(),
                        ResourceManager.getString("menu.language.title.resource"),
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(gui,
                    ResourceManager.getString("menu.language.changed", languageCode),
                    ResourceManager.getString("menu.language.title"),
                    JOptionPane.INFORMATION_MESSAGE);

            updateUIText(gui);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(gui,
                    ResourceManager.getString("menu.language.error.general") + ": " + e.getMessage(),
                    ResourceManager.getString("menu.language.error.title"),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void updateUIText(GUI gui) {
        // TODO: Implement UI text update logic
        //* This method should iterate through all UI components
        //* and update their text based on the current locale.
        //* For example, it could update buttons, labels, and menus.

        System.out.println("Not implemented yet: updateUIText");
    }
}

