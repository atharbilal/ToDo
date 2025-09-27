import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.stage.Modality;

public class ToDoApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Prompt for user's name
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("Welcome");
        nameDialog.setHeaderText("Hello! What's your name?");
        nameDialog.setContentText("Name:");
        nameDialog.initModality(Modality.APPLICATION_MODAL);
        String userName = nameDialog.showAndWait().orElse("").trim();
        if (userName.isEmpty()) userName = "Friend";

        // Greeting label
        Label greeting = new Label("Welcome, " + userName + "! Let's get things done today.");
        greeting.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 0 0 10 0;");

        // To-do list with checkboxes
        ListView<CheckBox> listView = new ListView<>();
        TextField inputField = new TextField();
        inputField.setPromptText("Enter a new task");
        Button addButton = new Button("Add Task");

        addButton.setOnAction(e -> {
            String task = inputField.getText().trim();
            if (!task.isEmpty()) {
                CheckBox cb = new CheckBox(task);
                cb.setStyle("-fx-font-size: 14px;");
                listView.getItems().add(cb);
                inputField.clear();
            }
        });

        // Mark selected task as done (with tick)
        Button markDoneButton = new Button("Mark Selected as Done");
        markDoneButton.setOnAction(e -> {
            CheckBox selected = listView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selected.setSelected(true);
                selected.setStyle("-fx-font-size: 14px; -fx-text-fill: green; -fx-font-weight: bold;");
            }
        });

        HBox inputBox = new HBox(10, inputField, addButton, markDoneButton);
        VBox vbox = new VBox(10, greeting, inputBox, listView);
        vbox.setPadding(new Insets(20));
        vbox.setPrefWidth(400);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("To-Do List App");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
