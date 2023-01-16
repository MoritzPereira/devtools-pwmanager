package de.hhn.it.devtools.javafx.controllers.pwmanager;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.pwmanager.Entry;
import de.hhn.it.devtools.apis.pwmanager.PwManagerListener;
import de.hhn.it.devtools.apis.pwmanager.exceptions.IllegalMasterPasswordException;
import de.hhn.it.devtools.components.pwmanager.provider.SimplePwManagerService;
import de.hhn.it.devtools.javafx.Main;
import de.hhn.it.devtools.javafx.controllers.Controller;
import de.hhn.it.devtools.javafx.controllers.template.ScreenController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class PwManagerHomeScreenController extends Controller implements Initializable{
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(PwManagerHomeScreenController.class);

    private SimplePwManagerService pwManagerService = new SimplePwManagerService();
    public ArrayList<Entry> listOfEntrys = new ArrayList<>();
    private boolean checkMasterPw;

    Image imageGenerate = new Image(getClass().getResourceAsStream("/imagesPwmanager/generate.png"));
    Image imageAddEntry = new Image(getClass().getResourceAsStream("/imagesPwmanager/add.png"));
    Image imageChangeMasterPw = new Image(getClass().getResourceAsStream("/imagesPwmanager/key.png"));
    Image imageLogout = new Image(getClass().getResourceAsStream("/imagesPwmanager/logout.png"));
    Image imageDelete = new Image(getClass().getResourceAsStream("/imagesPwmanager/mulleimer.png"));
    Image imageDetails = new Image(getClass().getResourceAsStream("/imagesPwmanager/file.png"));
    Image imageCopy = new Image(getClass().getResourceAsStream("/imagesPwmanager/copy.png"));
    Image imageGeneratePopup = new Image(getClass().getResourceAsStream("/imagesPwmanager/generatepopup.png"));
    Image imageShow = new Image(getClass().getResourceAsStream("/imagesPwmanager/view.png"));
    Image imageHide = new Image(getClass().getResourceAsStream("/imagesPwmanager/hidden.png"));



    @FXML
    private Button buttonAddEntry;

    @FXML
    private Button buttonChangeMasterpassword;

    @FXML
    private Button buttonLogout;

    @FXML
    private Button buttonPassword;

    @FXML
    private AnchorPane controlAnchorPane;

    @FXML
    private TableView<Entry> tableView = new TableView<>();
    TableColumn<Entry, String> urlColumn;
    TableColumn<Entry, String> usernameColumn;

    public PwManagerHomeScreenController() throws IllegalParameterException {
        TestListener testListener = new TestListener();
        pwManagerService.addListener(testListener);
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        //Build the table at the beginning
        urlColumn = new TableColumn<>("URL");
        urlColumn.setMinWidth(200);
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
        usernameColumn = new TableColumn<>("Username");
        usernameColumn.setMinWidth(200);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        buttonPassword.setGraphic(new ImageView(imageGenerate));
        buttonChangeMasterpassword.setGraphic(new ImageView(imageChangeMasterPw));
        buttonAddEntry.setGraphic(new ImageView(imageAddEntry));
        buttonLogout.setGraphic(new ImageView(imageLogout));

        /*passwordColumn = new TableColumn<>("Password");
        passwordColumn.setMinWidth(200);
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));*/


        tableView.getColumns().addAll(urlColumn,usernameColumn);
        addButtonToTable("Copy", "Password");
        addButtonToTable("Details", "");
        addButtonToTable("Delete", "");

        pwManagerService.loggenIn = true;
        //pwManagerService.loadState();
        loadStateFromFile();
        //updateUI();
    }

    private void addButtonToTable(String buttonName, String columnName) {
        TableColumn<Entry, Void> colBtn = new TableColumn(columnName);

        Callback<TableColumn<Entry, Void>, TableCell<Entry, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Entry, Void> call(final TableColumn<Entry, Void> param) {
                final TableCell<Entry, Void> cell = new TableCell<>() {


                    private final Button btn = new Button(buttonName);

                    {
                        switch (buttonName) {
                            case "Copy":
                                btn.setGraphic(new ImageView(imageCopy));
                                break;
                            case "Details":
                                btn.setGraphic(new ImageView(imageDetails));
                                break;
                            case "Delete":
                                btn.setGraphic(new ImageView(imageDelete));
                            break;
                        }
                    }


                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Entry entry = getTableView().getItems().get(getIndex());
                            switch (buttonName){
                                case "Copy":
                                    logger.info("Copied password");
                                    copie(entry.getPassword());
                                    break;
                                case "Show":
                                    /*
                                    try {
                                        pwManagerService.changeHidden(entry.getEntryId());
                                    } catch (IllegalParameterException e) {
                                        e.printStackTrace();
                                    }*/
                                    break;
                                case "Details":
                                    logger.info("Show details of Entry ID: " + entry.getEntryId());
                                    openDialogDetailsEntry(entry);
                                    break;
                                case "Delete":
                                    String password = openDialogCheckMasterPw();
                                    if(password!=null){
                                        try {
                                            pwManagerService.deleteEntry(entry.getEntryId(),password);
                                            updateUI();
                                        } catch (IllegalParameterException e) {
                                            e.printStackTrace();
                                        } catch (IllegalMasterPasswordException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    break;
                            }
                        });

                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                //Einträge in die Mitte des Feldes bringen
                cell.setStyle("-fx-alignment: CENTER");
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        tableView.getColumns().add(colBtn);

    }

    @FXML
    void buttonClicked(MouseEvent event) {

        if (buttonAddEntry == event.getSource()){
            openDialogAddEntry();
        }
        else if(buttonChangeMasterpassword == event.getSource()){
            openDialogChangeMasterPW();
        }
        else if(buttonPassword == event.getSource()){
            openDialogRandomPassword();
        }
        else if(buttonLogout == event.getSource()){
            logout();
        }
    }

    private ObservableList<Entry> getEntries(){
        ObservableList<Entry> entries = FXCollections.observableArrayList();
        for(Entry e : listOfEntrys){
            entries.add(e);
        }
        return entries;
    }

    public void openDialogAddEntry(){

        Dialog dialog = new Dialog();
        dialog.setTitle("Add entry");
        dialog.setHeaderText("Adds an new entry to the system");

        ButtonType loginButton = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));


        TextField usernameText = new TextField();
        usernameText.setPromptText("Username");
        TextField urlText = new TextField();
        urlText.setPromptText("URL");
        TextField emailText = new TextField();
        emailText.setPromptText("E-Mail");
        PasswordField passwordText = new PasswordField();
        passwordText.setPromptText("Password");
        PasswordField rpasswordText = new PasswordField();
        rpasswordText.setPromptText("Repeat password");
        Button buttonGeneratepassword = new Button("");
        buttonGeneratepassword.setGraphic(new ImageView(imageGeneratePopup));

        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameText, 1, 0);
        grid.add(new Label("URL:"), 0, 1);
        grid.add(urlText, 1, 1);
        grid.add(new Label("E-Mail:"), 0, 2);
        grid.add(emailText, 1, 2);
        grid.add(new Label("Password:"), 0, 3);
        grid.add(passwordText, 1, 3);
        grid.add(buttonGeneratepassword,2,3);
        grid.add(new Label("Repeat password:"), 0, 4);
        grid.add(rpasswordText, 1, 4);

        dialog.getDialogPane().setContent(grid);

        Node loginButtonNode = dialog.getDialogPane().lookupButton(loginButton);
        loginButtonNode.setDisable(false);

        final Button btOk = (Button) dialog.getDialogPane().lookupButton(loginButton);
        btOk.addEventFilter(
                ActionEvent.ACTION,
                event -> {
                    if(Objects.equals(usernameText.getText(), "") || Objects.equals(urlText.getText(), "") || Objects.equals(emailText.getText(), "") || Objects.equals(passwordText.getText(), "") || Objects.equals(rpasswordText.getText(), "")){
                        event.consume();
                        dialog.setHeaderText("Please fill out all fields!");
                    }
                    else if(!Objects.equals(passwordText.getText(), rpasswordText.getText())){
                        event.consume();
                        dialog.setHeaderText("Passwords do not match!");
                    }
                    else{
                        try {
                            pwManagerService.addEntry(urlText.getText(), usernameText.getText(), emailText.getText(), passwordText.getText());
                        } catch (IllegalParameterException e){
                            dialog.setHeaderText(e.getMessage());
                            event.consume();
                        }
                    }
                }
        );
        buttonGeneratepassword.addEventFilter(
                ActionEvent.ACTION,
                event -> {
                    openDialogRandomPassword();
                }
        );
        dialog.showAndWait();

    }

    public void openDialogDetailsEntry(Entry entry){

        Dialog dialog = new Dialog();
        dialog.setTitle("Details");

        ButtonType editButtonType = new ButtonType("Edit", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(editButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField usernameText = new TextField();
        usernameText.setText(entry.getUsername());
        usernameText.setDisable(true);
        TextField urlText = new TextField();
        urlText.setText(entry.getUrl());
        urlText.setDisable(true);
        TextField emailText = new TextField();
        emailText.setText(entry.getEmail());
        emailText.setDisable(true);
        TextField passwordText = new TextField();
        passwordText.setText("***********");
        passwordText.setDisable(true);
        Button buttonShowHidden = new Button("");
        buttonShowHidden.setGraphic(new ImageView(imageShow));
        Button buttonCopy = new Button("");
        buttonCopy.setGraphic(new ImageView(imageCopy));

        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameText, 1, 0);
        grid.add(new Label("URL:"), 0, 1);
        grid.add(urlText, 1, 1);
        grid.add(new Label("E-Mail:"), 0, 2);
        grid.add(emailText, 1, 2);
        grid.add(new Label("Password:"), 0, 3);
        grid.add(passwordText, 1, 3);
        grid.add(buttonShowHidden,2,3);
        grid.add(buttonCopy,3,3);

        dialog.getDialogPane().setContent(grid);

        Node loginButtonNode = dialog.getDialogPane().lookupButton(editButtonType);
        loginButtonNode.setDisable(false);

        final Button editButton = (Button) dialog.getDialogPane().lookupButton(editButtonType);
        editButton.addEventFilter(
                ActionEvent.ACTION,
                event -> {
                    openDialogEditEntry(entry);
                    dialog.close();
                }
        );
        buttonCopy.addEventFilter(
                ActionEvent.ACTION,
                event -> {
                    copie(passwordText.getText());
                    logger.info("Password copied");
                    event.consume();
                }
        );
        buttonShowHidden.addEventFilter(
                ActionEvent.ACTION,
                event -> {
                    try {
                        pwManagerService.changeHidden(entry.getEntryId());
                        buttonShowHidden.setGraphic(new ImageView(imageHide));
                    } catch (IllegalParameterException e) {
                        e.printStackTrace();
                    }
                    if(entry.isChangeHidden()){
                        passwordText.setText(entry.getPassword());
                    }
                    else{
                        passwordText.setText("***********");
                        buttonShowHidden.setGraphic(new ImageView(imageShow));
                    }
                    event.consume();
                }
        );
        dialog.showAndWait();


    }

    public void openDialogEditEntry(Entry entry){

        Dialog dialog = new Dialog();
        dialog.setTitle("Edit entry");
        dialog.setHeaderText("Edit the entry and save");

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));


        TextField usernameText = new TextField();
        usernameText.setText(entry.getUsername());
        TextField urlText = new TextField();
        urlText.setText(entry.getUrl());
        TextField emailText = new TextField();
        emailText.setText(entry.getEmail());
        PasswordField passwordText = new PasswordField();
        passwordText.setText(entry.getPassword());
        PasswordField rpasswordText = new PasswordField();
        rpasswordText.setText(entry.getPassword());
        Button buttonGeneratepassword = new Button("");
        buttonGeneratepassword.setGraphic(new ImageView(imageGeneratePopup));

        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameText, 1, 0);
        grid.add(new Label("URL:"), 0, 1);
        grid.add(urlText, 1, 1);
        grid.add(new Label("E-Mail:"), 0, 2);
        grid.add(emailText, 1, 2);
        grid.add(new Label("Password:"), 0, 3);
        grid.add(passwordText, 1, 3);
        grid.add(buttonGeneratepassword,2,3);
        grid.add(new Label("Repeat password:"), 0, 4);
        grid.add(rpasswordText, 1, 4);

        dialog.getDialogPane().setContent(grid);

        final Button saveButton = (Button) dialog.getDialogPane().lookupButton(saveButtonType);
        saveButton.addEventFilter(
                ActionEvent.ACTION,
                event -> {
                    String password = openDialogCheckMasterPw();
                    if(checkMasterPw){
                        try {
                            Entry changedEntry = new Entry(entry.getEntryId(),urlText.getText(),usernameText.getText(),emailText.getText(),passwordText.getText());
                            pwManagerService.changeEntry(changedEntry, password);
                        } catch (IllegalParameterException e) {
                            dialog.setHeaderText(e.getMessage());
                            event.consume();
                        } catch (IllegalMasterPasswordException e) {
                            dialog.setHeaderText(e.getMessage());
                            event.consume();
                        }
                    }
                    else{
                        event.consume();
                    }
                }
        );
        buttonGeneratepassword.addEventFilter(
                ActionEvent.ACTION,
                event -> {
                    openDialogRandomPassword();
                }
        );
        dialog.showAndWait();
    }

    public void openDialogChangeMasterPW(){

        Dialog dialog = new Dialog();
        dialog.setTitle("Change Masterpassword");
        dialog.setHeaderText(" ");
        //dialog.setHeaderText("Adds an new entry to the system");

        ButtonType loginButton = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));


        PasswordField oldmasterpasswordText = new PasswordField();
        oldmasterpasswordText.setPromptText("old masterpassword");
        PasswordField newmasterpasswordText = new PasswordField();
        newmasterpasswordText.setPromptText("new masterpassword");
        PasswordField rnewmasterpasswordText = new PasswordField();
        rnewmasterpasswordText.setPromptText("new masterpassword");
        Button buttonGeneratepassword = new Button("");
        buttonGeneratepassword.setGraphic(new ImageView(imageGeneratePopup));

        grid.add(new Label("Old password:"), 0, 0);
        grid.add(oldmasterpasswordText, 1, 0);
        grid.add(new Label("New password:"), 0, 1);
        grid.add(newmasterpasswordText, 1, 1);
        grid.add(buttonGeneratepassword,2,1);
        grid.add(new Label("Repeat new password:"), 0, 2);
        grid.add(rnewmasterpasswordText, 1, 2);
        grid.add(new Label(" "), 0, 3);

        dialog.getDialogPane().setContent(grid);

        final Button btOk = (Button) dialog.getDialogPane().lookupButton(loginButton);
        btOk.addEventFilter(
                ActionEvent.ACTION,
                event -> {
                    if(oldmasterpasswordText.getText() != "" && newmasterpasswordText.getText() != "" && rnewmasterpasswordText.getText() != ""){
                        //Check if new passwords are equal
                        if(Objects.equals(newmasterpasswordText.getText(), rnewmasterpasswordText.getText())){
                            try {
                                pwManagerService.changeMasterPw(newmasterpasswordText.getText(), oldmasterpasswordText.getText());
                                dialog.setHeaderText("Password changed");
                            } catch (Exception e) {
                                dialog.setHeaderText(e.getMessage());
                            }
                        }
                        else{
                            dialog.setHeaderText("New passwords are not equal");
                        }
                    }
                    else{
                        dialog.setHeaderText("Please fill out all fields!");
                    }
                    event.consume();
                }
        );
        buttonGeneratepassword.addEventFilter(
                ActionEvent.ACTION,
                event -> {
                    openDialogRandomPassword();
                }
        );
        dialog.showAndWait();

    }

    public void openDialogRandomPassword(){

        Dialog dialog = new Dialog();
        dialog.setTitle("Generate password");
        dialog.setHeaderText("Generates a password with given specs");

        ButtonType loginButton = new ButtonType("Generate", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Label useuppercaseLabel = new Label("Use uppercase");
        Label uselowercaseLabel = new Label("Use lowercase");
        Label usenumbersLabel = new Label("Use numbers");
        Label usespecialsLabel = new Label("Use specials");
        Label usecyrillicLabel = new Label("Use cyrillic");
        Label numbersLabel = new Label("Numbers:");
        CheckBox useuppercaseBox = new CheckBox();
        useuppercaseBox.setSelected(true);
        CheckBox uselowercaseBox = new CheckBox();
        CheckBox usenumbersBox = new CheckBox();
        CheckBox usespecialsBox = new CheckBox();
        CheckBox usecyrillicBox = new CheckBox();
        TextField generatedPasswordText = new TextField("");
        TextField numbersText = new TextField("0");
        generatedPasswordText.setDisable(true);
        Button copiePasswordButton = new Button("Copy");
        copiePasswordButton.setGraphic(new ImageView(imageCopy));
        Slider slider = new Slider(0,10,5);


        grid.add(generatedPasswordText, 1,0);
        grid.add(copiePasswordButton, 2,0);
        //grid.add(slider, 0,1);
        grid.add(numbersLabel, 0,1);
        grid.add(numbersText, 1,1);
        grid.add(useuppercaseBox, 0, 2);
        grid.add(useuppercaseLabel, 1, 2);
        grid.add(uselowercaseBox, 0, 3);
        grid.add(uselowercaseLabel, 1, 3);
        grid.add(usenumbersBox, 0, 4);
        grid.add(usenumbersLabel, 1, 4);
        grid.add(usespecialsBox, 0, 5);
        grid.add(usespecialsLabel, 1, 5);
        grid.add(usecyrillicBox, 0, 6);
        grid.add(usecyrillicLabel, 1, 6);

        dialog.getDialogPane().setContent(slider);
        dialog.getDialogPane().setContent(grid);

        final Button btOk = (Button) dialog.getDialogPane().lookupButton(loginButton);
        final String[] password = new String[1];
        btOk.addEventFilter(
                ActionEvent.ACTION,
                event -> {
                    String passwordGenerated = null;
                    //One box has to be selected
                    if(useuppercaseBox.isSelected() || uselowercaseBox.isSelected() || usenumbersBox.isSelected() || usespecialsBox.isSelected()){
                        try {
                            passwordGenerated = pwManagerService.generateNewPw(useuppercaseBox.isSelected(), uselowercaseBox.isSelected(), usenumbersBox.isSelected(), usespecialsBox.isSelected(), usecyrillicBox.isSelected(), Integer.parseInt(numbersText.getText()));
                        } catch (IllegalParameterException e) {
                            dialog.setHeaderText(e.getMessage());
                        }
                        generatedPasswordText.setText(passwordGenerated);
                        dialog.setHeaderText("Password generated");
                    }
                    else{
                        dialog.setHeaderText("Please select at least one box");
                    }
                    password[0] = passwordGenerated;
                    event.consume();

                }
        );

        copiePasswordButton.addEventFilter(
                ActionEvent.ACTION,
                event -> {
                   //If the password != null, copy to clipboard
                    if(password[0] != null){
                        copie(password[0]);
                        logger.info("Password copied");
                        dialog.setHeaderText("copied!");
                    }
                }
        );
        dialog.showAndWait();

    }

    public String openDialogCheckMasterPw(){

        Dialog dialog = new Dialog();
        dialog.setTitle("Master password");
        dialog.setHeaderText("Please enter your master password to verify you");

        ButtonType checkButtonType = new ButtonType("Check", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(checkButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        dialog.getDialogPane().setContent(grid);
        PasswordField passwordText = new PasswordField();
        grid.add(passwordText, 1, 0);
        final Button checkButton = (Button) dialog.getDialogPane().lookupButton(checkButtonType);
        checkButton.addEventFilter(
                ActionEvent.ACTION,
                event -> {
                    try {
                        pwManagerService.login(passwordText.getText());
                    } catch (IllegalMasterPasswordException e) {
                        dialog.setHeaderText("Wrong password");
                        event.consume();
                    }
                }
        );
        dialog.showAndWait();
        if(checkMasterPw){
            return passwordText.getText();
        }
        return null;
    }

    private void copie(String input){
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(input);
        clipboard.setContent(content);
    }

    public void changeWindow(String name) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/" + name + ".fxml"));
        controlAnchorPane.getChildren().clear();
        //GameScreen gameScreen = new GameScreen(snakeService);
        try {
            controlAnchorPane.getChildren().add(fxmlLoader.load());
            logger.info("Der Screen wurde zu: " + name + " gewechselt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateUI(){
        tableView.getItems().clear();
        tableView.setItems(getEntries());
        //addButtonToTable();
    }

    public void logout(){
        pwManagerService.logout();
    }

    private void loadStateFromFile() throws NullPointerException {

        int length = 0;
        String osPath = System.getProperty("user.dir");
        osPath = osPath.replace("components","");
        osPath = osPath.replace("javafx","");
        osPath += "/components/src/main/entries.txt";
        File file = new File(osPath);
        Path filePath = Paths.get(osPath);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            length = (int) Files.lines(filePath).count();
            Files.lines(filePath).close();
            pwManagerService.setMasterPw(br.readLine());

            for (int i = 1; i < length; i++) {
                String line = br.readLine();
                String[] splitline = line.split(",");
                //String decId = this.decrypt(splitline[0]);
                //String decUrl = this.decrypt(splitline[1]);
                //String decUname = this.decrypt(splitline[2]);
                //String decEmail = this.decrypt(splitline[3]);
                //String decPw = this.decrypt(splitline[4]);
                //this.addEntry(decUrl,decUname,decEmail,decPw);

                //Der Service entschlüsselt die einzelnen Entrys und übergibt sie dem Listener welcher anschließend die UI updatet
                pwManagerService.decryptAndLoadEntries(splitline[1],splitline[2],splitline[3],splitline[4]);
            }
            //listeners.forEach((listener) -> listener.showsortedEntryList(listOfEntrys));
            logger.info("Entrylist loaded");
        } catch (IOException e) {
            logger.error("Error with loading the list: " + e.getMessage());
        }
    }

    private void updateFile(String input) throws RuntimeException, IOException {


            String osPath = System.getProperty("user.dir");
            osPath = osPath.replace("components","");
            osPath = osPath.replace("javafx","");
            osPath += "/components/src/main/entries.txt";
            File file = new File(osPath);
            Path filePath = Paths.get(osPath);

            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(new FileOutputStream(osPath, true)));

            //Writer to delete all from file
            //PrintWriter deleteWriter = new PrintWriter(file);
            //deleteWriter.print("");
            //deleteWriter.close();



                try {
                    //Files.newBufferedWriter(filePath, StandardOpenOption.TRUNCATE_EXISTING);
                    writer.write(input);
                    writer.newLine();
                    //writer.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            writer.close();
    }

    private void deleteFile() throws FileNotFoundException {
        String osPath = System.getProperty("user.dir");
        osPath = osPath.replace("components","");
        osPath = osPath.replace("javafx","");
        osPath += "/components/src/main/entries.txt";
        File file = new File(osPath);
        PrintWriter deleteWriter = new PrintWriter(file);
        deleteWriter.print("");
        deleteWriter.close();
    }


    class TestListener implements PwManagerListener{

        @Override
        public void loggingin(String masterPw) {

        }

        @Override
        public void loggedin() {
            checkMasterPw = true;
        }

        @Override
        public void logout() {

        }

        @Override
        public void loggedout() {
            changeWindow("PwManagerLoginScreen");
        }

        @Override
        public void entryAdded(Entry newEntry) {
            logger.info("Listener: Entry mit der id:["+newEntry.getEntryId()+"] wurde hinzugefügt.");
            updateUI();
        }

        @Override
        public void entryDeleted(Entry currentEntry) {
            updateUI();
        }

        @Override
        public void entryChanged(Entry entry) {
            logger.info("Listener: Entry mit der id:["+entry.getEntryId()+"] wurde verändert.");
            updateUI();
        }

        @Override
        public void changePasswordVisibility(int id) {
            //updateUI(); with specs to the changed visibility password
        }

        @Override
        public void updateEntryListFile(String input) {
            try {
                updateFile(input);
            } catch (IOException e) {
                logger.error("Error saving list of entrys");
            }
        }

        @Override
        public void deleteContentOfFile() {
            try {
                deleteFile();
                logger.info("File resetet");
            } catch (FileNotFoundException e) {
                logger.error("Error with reseting the file: " + e.getMessage());
            }
        }

        @Override
        public void generatePw(boolean useUpper, boolean useLower, boolean useDigits, boolean useSpecialChars) {

        }

        @Override
        public void showNewPw(String pw) {

        }

        @Override
        public void showsortedEntryList(ArrayList<Entry> entryList) {
            listOfEntrys = entryList;
            updateUI();
        }
    }

}
