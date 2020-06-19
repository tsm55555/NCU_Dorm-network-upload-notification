import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class notif {

    public void displayTray() throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
        trayIcon.displayMessage("Warning", "您的網路流量已超標", MessageType.INFO);
    }
}