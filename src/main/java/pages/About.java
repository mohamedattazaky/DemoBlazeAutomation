package pages;

import logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uitils.ActionBot;
import validation.Verification;

public class About {

    private final WebDriver driver;
    private final ActionBot actionBot;
    private final Verification verification;

    public About(WebDriver driver){
        this.driver = driver;
        this.actionBot = new ActionBot(driver);
        this.verification = new Verification(driver);
    }

    private final By playVideoButton = By.xpath("//button[.='Play Video']");
    private final By pauseVideoButton = By.xpath("//button[@title='Pause']");
    private final By muteButton = By.xpath("//button[@title='Mute']");
    private final By fullScreenButton = By.xpath("//button[@title='Fullscreen']");
    private final By pictureInPictureButton = By.xpath("//button[@title='Picture-in-Picture']");
    private final By closeButton = By.xpath("(//button[.='Close'])[4]");
    private final By hoverOnVideo = By.xpath("//video[@role='application']");

    public void clickOnPlayVideoButton(){
        actionBot.click(playVideoButton);
        LogsManager.info("Video started playing successfully.");
    }

    public void clickOnPauseVideoButton(){
        LogsManager.info("Hovering over the video to reveal controls...");
        actionBot.hover(hoverOnVideo);

        LogsManager.info("Clicking on 'Pause' button...");
        actionBot.click(pauseVideoButton);
        LogsManager.info("Video paused successfully.");
    }

    public void clickOnMuteButton(){
        LogsManager.info("Hovering over the video to reveal controls...");
        actionBot.hover(hoverOnVideo);

        LogsManager.info("Clicking on 'Mute' button...");
        actionBot.click(muteButton);
        LogsManager.info("Video muted successfully.");
    }

    public void clickOnFullScreenButton(){
        LogsManager.info("Hovering over the video to reveal controls...");
        actionBot.hover(hoverOnVideo);

        LogsManager.info("Clicking on 'Fullscreen' button...");
        actionBot.click(fullScreenButton);
        LogsManager.info("Fullscreen mode activated.");
    }

    public void clickOnPictureInPictureButton(){
        LogsManager.info("Hovering over the video to reveal controls...");
        actionBot.hover(hoverOnVideo);

        LogsManager.info("Clicking on 'Picture-in-Picture' button...");
        actionBot.click(pictureInPictureButton);
        LogsManager.info("Picture-in-Picture mode enabled.");
    }

    public void clickOnCloseButton(){
        LogsManager.info("Clicking on 'Close' button...");
        actionBot.click(closeButton);
        LogsManager.info("About modal closed successfully.");
    }
}
