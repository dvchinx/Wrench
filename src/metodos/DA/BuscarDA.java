package metodos.DA;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author dvchinx_
 */
public class BuscarDA {
    
    public String queBusco;
    
    public void buscar() {
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                try {
                    java.net.URI uri = new java.net.URI(queBusco);
                    desktop.browse(uri);
                } catch (URISyntaxException | IOException ex) {

                }
            }
        }
    }
}
