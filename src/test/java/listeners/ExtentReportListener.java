package listeners;

import com.aventstack.extentreports.service.ExtentService;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;

public class ExtentReportListener implements ConcurrentEventListener {

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        // You can add event subscriptions here if needed in future
        ExtentService.getInstance().setSystemInfo("QA Engineer", "Sai");
        ExtentService.getInstance().setSystemInfo("OS", System.getProperty("os.name"));
        ExtentService.getInstance().setSystemInfo("Java Version", System.getProperty("java.version"));
    }
}
