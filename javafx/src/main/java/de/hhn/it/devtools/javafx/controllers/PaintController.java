package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.paint.Color;
import de.hhn.it.devtools.apis.paint.PaintService;
import de.hhn.it.devtools.apis.paint.ShapeDescriptor;
import de.hhn.it.devtools.components.paint.PService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Objects;
import static de.hhn.it.devtools.javafx.PaintLauncher.stage;

public class PaintController {

    public Button newPage;
    public Button load;
    public Button save;
    public RadioButton pencil;
    public RadioButton eraser;
    public RadioButton line;
    public Slider thickness;
    public ColorPicker colorPicker;
    private final ColorPicker actualColor;
    public Button back;
    public Button forward;
    public Button delete;
    public Button clear;
    public Button undo;
    public Button redo;
    private final PaintService paintService;
    private GraphicsContext graphicsContext;
    private ShapeDescriptor actualShape;
    private Boolean redoFlag;
    private  ShapeDescriptor changeShape;

    @FXML
    private  Canvas drawingCanvas;



    public PaintController() {
        paintService = new PService();
        colorPicker = new ColorPicker(javafx.scene.paint.Color.BLACK);
        actualColor = new ColorPicker(colorPicker.getValue());
        thickness = new Slider();
        redoFlag=false;
        changeShape =null;
    }


    public void newPage() throws IOException {
        Stage primaryStage = new Stage();
        stage = primaryStage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/paint/paint.fxml")));
        primaryStage.setTitle("Paint");
        primaryStage.setScene(new Scene(root, 1500, 1000));
        primaryStage.show();
    }

    public void load() {
        FileChooser openFile = new FileChooser();
        openFile.setTitle("Open File");
        File file = openFile.showOpenDialog(stage);
        if (file != null) {
            try {
                InputStream io = new FileInputStream(file);
                Image img = new Image(io);
                drawingCanvas.getGraphicsContext2D().drawImage(img, 0, 0);
            } catch (IOException ex) {
                System.out.println("Error!");
            }
        }
    }

    public void save() {
    }

    private void setTool(ToggleButton selectedTool) {
        pencil.setSelected(selectedTool == pencil);
        eraser.setSelected(selectedTool == eraser);
        line.setSelected(selectedTool == line);
    }
    public void setPencil() throws IllegalParameterException {
        setTool(pencil);
        setColor();
    }

    public void setEraser() {
        setTool(eraser);
        actualColor.setValue(javafx.scene.paint.Color.WHITE);
    }

    public void setLine() throws IllegalParameterException {
        setTool(line);
        setColor();
    }

    public void setColor() throws IllegalParameterException {
        actualColor.setValue(colorPicker.getValue());
        if (changeShape !=null) {
            paintService.changeColor(changeShape.getShapeId(),
                    paintService.getActualBoard().getBoardId(),
                    new Color(colorPicker.getValue().getRed(), colorPicker.getValue().getGreen(),
                            colorPicker.getValue().getBlue(), colorPicker.getValue().getOpacity()));
            redraw();
            changeShape=null;
        }
    }

    @FXML
    public void initialize() {
        thickness.setOnMouseDragged(event -> {
            try {
                handleThickness();
            } catch (IllegalParameterException e) {
                e.printStackTrace();
            }
        });
        thickness.setOnMouseReleased(event -> {
            changeShape =null;
        });
    }

    private void handleThickness() throws IllegalParameterException {
        paintService.changeSize(changeShape.getShapeId(), paintService.getActualBoard().getBoardId(), thickness.getValue());
        changeShape.setShapeColor(paintService.getLastColor());
        redraw();
    }

    public void startDraw(MouseEvent event) throws IllegalParameterException {
        graphicsContext = drawingCanvas.getGraphicsContext2D();
        graphicsContext.setLineWidth(thickness.getValue());
        graphicsContext.setStroke(actualColor.getValue());
        actualShape = new ShapeDescriptor((int) event.getX(), (int) event.getY(), thickness.getValue(),
                new Color((int) actualColor.getValue().getRed(), (int) actualColor.getValue().getGreen(),
                        (int) actualColor.getValue().getBlue(), (int) actualColor.getValue().getOpacity()),
                line.isSelected(), eraser.isSelected(), paintService.getActualBoard().getShapeIdCounter());
        actualShape.setStraightMode(line.isSelected());
        graphicsContext.beginPath();
        if (pencil.isSelected() || eraser.isSelected()) {
            graphicsContext.lineTo(event.getX(), event.getY());
        }
    }

    public void drag(MouseEvent event) {
        if (pencil.isSelected() || eraser.isSelected()) {
            actualShape.addPoint((int) event.getX(), (int) event.getY());
            graphicsContext.lineTo(event.getX(), event.getY());
            graphicsContext.stroke();
        }
    }

    public void endDraw(MouseEvent event) throws IllegalParameterException {
        if (pencil.isSelected() || eraser.isSelected()) {
            graphicsContext.lineTo(event.getX(), event.getY());
        }
        if (line.isSelected()) {
            graphicsContext.moveTo(actualShape.getStartPoint().getxValue(), actualShape.getStartPoint().getyValue());
            graphicsContext.lineTo(event.getX(), event.getY());
            graphicsContext.stroke();
        }
        actualShape.setEndPoint((int)event.getX(),(int) event.getY());
        actualShape.setLineThickness(thickness.getValue());
        actualShape.setShapeColor(new Color(actualColor.getValue().getRed(),actualColor.getValue().getGreen(),
                actualColor.getValue().getBlue(), actualColor.getValue().getOpacity()));
        paintService.addShape(actualShape, paintService.getActualBoard().getBoardId());
    }

    public void undo() throws NoSuchElementException {
        try {
            paintService.undo(paintService.getActualBoard().getBoardId());
            redraw();
        } catch(NoSuchElementException | IllegalParameterException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Nothing more to undo");
            alert.setContentText("Enjoy painting!");
            alert.showAndWait();
        }
    }

    private void redoHelper() throws IllegalParameterException {
        while (!paintService.getActualBoard().getRedoHistory().isEmpty()  && redoFlag && paintService.getRedoCounter()>0) {
            paintService.redo(paintService.getActualBoard().getBoardId());
            redraw();
        }
        if (!redoFlag){
            paintService.redo(paintService.getActualBoard().getBoardId());
            redraw();
        }
    }

    public void redo() {
        try {
            redoHelper();
            redoFlag=false;
        } catch(NoSuchElementException | IllegalParameterException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Nothing more to redo");
            alert.setContentText("Enjoy painting!");
            alert.showAndWait();
        }

    }

    private void redraw(){
        graphicsContext.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
        for (ShapeDescriptor shape : paintService.getActualBoard().getUndoHistory()) {
            graphicsContext.setStroke(new javafx.scene.paint.Color
                    (shape.getShapeColor().getRed(), shape.getShapeColor().getGreen(), shape.getShapeColor().getBlue(), shape.getShapeColor().getTransparency()));
            graphicsContext.setLineWidth(shape.getThickness());
            graphicsContext.beginPath();
            graphicsContext.moveTo(shape.getStartPoint().getxValue(), shape.getStartPoint().getyValue());
            if (shape.getStraightMode()) {
                graphicsContext.lineTo(shape.getEndPoint().getxValue(), shape.getEndPoint().getyValue());
            } else {
                for (int i = 0; i < shape.getPoints().size(); i++) {
                    graphicsContext.lineTo(shape.getPoints().get(i).getxValue(), shape.getPoints().get(i).getyValue());
                }
            }
            graphicsContext.stroke();
            graphicsContext.closePath();
            actualShape = paintService.getActualBoard().getUndoHistory().getLast();
        }
    }

    public void back() throws IndexOutOfBoundsException, NoSuchElementException {
        try {
            changeShape = paintService.back(paintService.getActualBoard().getBoardId());
            redraw();

        } catch (NoSuchElementException|IndexOutOfBoundsException | IllegalParameterException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("No Shape to move");
            alert.setContentText("Enjoy painting!");
            alert.showAndWait();
        }
    }

    public void forward() throws IndexOutOfBoundsException {
        try {
            changeShape = paintService.forward(paintService.getActualBoard().getBoardId());
            redraw();
        } catch (IndexOutOfBoundsException | IllegalParameterException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("No Shape to move");
            alert.setContentText("Enjoy painting!");
            alert.showAndWait();
        }
    }

    public void delete() throws IllegalParameterException {
        try {
            paintService.deleteShape(changeShape.getShapeId(),paintService.getActualBoard().getBoardId());
            changeShape=null;
            redraw();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("No Shape selected");
            alert.setContentText("Select a shape to delete");
            alert.showAndWait();
        }

    }

    public void clear() throws IllegalParameterException {
        paintService.clear(paintService.getActualBoard().getBoardId());
        graphicsContext = drawingCanvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
        changeShape=null;
        redoFlag = true;
    }
}
