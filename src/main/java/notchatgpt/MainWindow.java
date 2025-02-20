package notchatgpt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private NotChatGPT notChatGPT;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Chatbot.png"));

    /**
     * Initializes the main window.
     * Binds the scroll pane to the height of the dialog container
     * and displays the welcome message from the chatbot.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(NotChatGPT.showWelcome(), dukeImage)
        );
    }

    /**
     * Sets the NotChatGPT instance for the main window.
     *
     * @param c The NotChatGPT instance to be used.
     */
    public void setNotChatGPT(NotChatGPT c) {
        notChatGPT = c;
    }


    /**
     * Handles user input by creating dialog boxes for the user and chatbot responses.
     * The user input is processed and displayed in the dialog container,
     * then cleared from the input field.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = notChatGPT.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
