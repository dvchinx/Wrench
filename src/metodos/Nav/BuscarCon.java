package metodos.Nav;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author dvchinx_
 */
public class BuscarCon {
    
    public static String buscarGoogle;
    public static String buscarBing;
    public static String buscarDuck;
    public static String buscarYahoo;
    
    public static void buscarConGoogle() {
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                try {
                    java.net.URI uri = new java.net.URI("https://www.google.com/search?q=" + buscarGoogle);
                    desktop.browse(uri);
                } catch (URISyntaxException | IOException ex) {

                }
            }
        }
    }

    public static void buscarConBing() {
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                try {
                    java.net.URI uri = new java.net.URI("https://bing.com/?q=" + buscarBing);
                    desktop.browse(uri);
                } catch (URISyntaxException | IOException ex) {

                }
            }
        }
    }

    public static void buscarConDuck() {
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                try {
                    java.net.URI uri = new java.net.URI("https://duckduckgo.com/?q=" + buscarDuck);
                    desktop.browse(uri);
                } catch (URISyntaxException | IOException ex) {

                }
            }
        }
    }

    public static void buscarConYahoo() {
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                try {
                    java.net.URI uri = new java.net.URI("https://espanol.search.yahoo.com/search?p=" + buscarYahoo);
                    desktop.browse(uri);
                } catch (URISyntaxException | IOException ex) {

                }
            }
        }
    }
}
