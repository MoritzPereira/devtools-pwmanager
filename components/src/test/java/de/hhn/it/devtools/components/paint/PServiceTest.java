package de.hhn.it.devtools.components.paint;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.paint.Color;
import de.hhn.it.devtools.apis.paint.ShapeDescriptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class PServiceTest {
    PService pService;
    ShapeDescriptor shape;

    @BeforeEach
    void setUp() {
        pService = new PService();
        shape = new ShapeDescriptor(1, 2, 3, new Color(1,1,1,1)
                ,false, false, 1);
    }

    @Test
    @DisplayName("Test getShapes method with valid Parameter")
    void testGetShapes() {
        LinkedList<ShapeDescriptor> shapes =  assertDoesNotThrow(() -> pService.getShapes( 0));
        assertNotNull(shapes);
    }

    @Test
    @DisplayName("Test getShapes method with invalid boardId")
    void testGetShapesInvalidBoardId() {
        assertThrows(IllegalParameterException.class, () -> pService.getShapes(42));
    }

    @Test
    @DisplayName("Test getShape method with valid parameters")
    void testGetShape() {
        assertDoesNotThrow(() -> pService.addShape(shape, 0));
        ShapeDescriptor returnedShape = assertDoesNotThrow(() -> pService.getShape(shape.getShapeId(), 0));;
        assertEquals(shape, returnedShape);

    }

    @Test
    @DisplayName("Test getShape method with invalid boardId")
    void testGetShapeInvalidBoardId() {
        assertThrows(IllegalParameterException.class, () -> pService.getShape(1, 1));
    }

    @Test
    @DisplayName("Test addShape method with valid parameters")
    void testAddShape() {
        assertDoesNotThrow(() -> pService.addShape(shape, 0));
        LinkedList<ShapeDescriptor> shapes =  assertDoesNotThrow(() -> pService.getShapes(0));
        assertTrue(Objects.requireNonNull(shapes).contains(shape));
    }

    @Test
    @DisplayName("Test addShape method with invalid boardId")
    void testAddShapeInvalidBoardId() {
        assertThrows(IllegalParameterException.class, () -> pService.addShape(shape, 1));
    }

    @Test
    @DisplayName("Test back method with valid parameters")
    void testBack() {
        assertDoesNotThrow(() -> pService.addShape(shape, 0));
        ShapeDescriptor returnedShape = assertDoesNotThrow(() -> pService.back(0));
        assertEquals(shape, returnedShape);
    }

    @Test
    @DisplayName("Test back method with invalid boardId")
    void testBackInvalidBoardId() {
        assertThrows(IllegalParameterException.class, () -> pService.back(1));
    }

    @Test
    @DisplayName("Test forward method with valid parameters")
    void testForward() {
        assertDoesNotThrow(() -> pService.addShape(shape, 0));
        ShapeDescriptor shape2 = new ShapeDescriptor(1,1,1,
                new Color(1,1,1,1),false,false,2);
        assertDoesNotThrow(() -> pService.addShape(shape2, 0));
        assertDoesNotThrow(() -> pService.back(0));
        assertDoesNotThrow(() -> pService.back(0));
        ShapeDescriptor returnedShape = assertDoesNotThrow(() -> pService.forward(0));
        assertEquals(shape2, returnedShape);
    }

    @Test
    @DisplayName("Test forward method with invalid boardId")
    void testForwardInvalidBoardId() {
        assertThrows(IllegalParameterException.class, () -> pService.forward(1));
    }

    @Test
    @DisplayName("Test deleteShape method with valid parameters")
    void testDeleteShape() {
        assertDoesNotThrow(() -> pService.addShape(shape, 0));
        assertDoesNotThrow(() -> pService.deleteShape(1, 0));
        LinkedList<ShapeDescriptor> shapes =  assertDoesNotThrow(() -> pService.getShapes( 0));
        assertFalse(shapes.contains(shape));
    }


    @Test
    @DisplayName("Test deleteShape method with invalid shapeId")
    void testDeleteShapeInvalidShapeId() {
        assertThrows(IllegalParameterException.class, () -> pService.deleteShape(2, 0));
    }


    @Test
    @DisplayName("Test deleteShape method with invalid boardId")
    void testDeleteShapeInvalidBoardId() {
        assertThrows(IllegalParameterException.class, () -> pService.deleteShape(1, 1));
    }


    @Test
    @DisplayName("Test changeColor method with valid parameters")
    void testChangeColor() {
        assertDoesNotThrow(() -> pService.addShape(shape, 0));
        Color newColor = new Color(1, 0, 0,0);
        assertDoesNotThrow(() -> pService.changeColor(1, 0, newColor));
        ShapeDescriptor returnedShape = assertDoesNotThrow(() -> pService.getShape(1, 0));
        assertEquals(newColor, returnedShape.getShapeColor());
    }

    @Test
    @DisplayName("Test changeColor method with invalid boardId")
    void testChangeColorInvalidBoardId() {
        assertThrows(IllegalParameterException.class, () -> pService.changeColor
                (1, 1, new Color(1, 0, 0,1)));
    }

    @Test
    @DisplayName("Test changeColor method with invalid shapeId")
    void testChangeColorInvalidShapeId() {
        assertThrows(IllegalParameterException.class, () -> pService.changeColor
                (2, 0, new Color(1, 0, 0,1)));
    }
    @Test
    @DisplayName("Test changeColor method with invalid color")
    void testChangeColorInvalidColor() {
        assertThrows(IndexOutOfBoundsException.class, () -> pService.changeColor
                (2, 0, new Color(1, 1, -1,1)));
    }

    @Test
    @DisplayName("Test changeSize method with valid parameters")
    void testChangeSize() {
        assertDoesNotThrow(() -> pService.addShape(shape, 0));
        double newSize = 10;
        assertDoesNotThrow(() -> pService.changeSize(1, 0, newSize));
        ShapeDescriptor returnedShape = assertDoesNotThrow(() -> pService.getShape(1, 0));
        assertEquals(newSize, returnedShape.getThickness());
    }

    @Test
    @DisplayName("Test changeSize method with invalid boardId")
    void testChangeSizeInvalidBoardId() {
        assertThrows(IllegalParameterException.class, () -> pService.changeSize(1, 1, 10));
    }

    @Test
    @DisplayName("Test changeSize method with invalid shapeId")
    void testChangeSizeInvalidShapeId() {
        assertThrows(IllegalParameterException.class, () -> pService.changeSize(2, 0, 10));
    }

    @Test
    @DisplayName("Test changeSize method with invalid size")
    void testChangeSizeInvalidSize() {
        assertThrows(IllegalParameterException.class, () -> pService.changeSize(1, 1, -1));
    }

    @Test
    @DisplayName("Test undo method with valid parameters")
    void testUndo() {
        assertDoesNotThrow(() -> pService.addShape(shape, 0));
        assertDoesNotThrow(() -> pService.undo( 0));
        LinkedList<ShapeDescriptor> shapes = assertDoesNotThrow(() -> pService.getShapes(0));
        assertFalse(shapes.contains(shape));
    }

    @Test
    @DisplayName("Test undo method with invalid boardId")
    void testUndoInvalidBoardId() {
        assertThrows(IllegalParameterException.class, () -> pService.undo(1));
    }

    @Test
    @DisplayName("Test undo method with no shapes on the board")
    void testUndoNoShapes() {
        assertThrows(NoSuchElementException.class, () -> pService.undo(0));
    }

    @Test
    @DisplayName("Test clear method with valid parameters")
    void testClear() {
        assertDoesNotThrow(() -> pService.addShape(shape, 0));
        assertDoesNotThrow(() -> pService.clear(0));
        LinkedList<ShapeDescriptor> shapes = assertDoesNotThrow(() -> pService.getShapes(0));
        assertTrue(shapes.isEmpty());
    }

    @Test
    @DisplayName("Test clear method with invalid boardId")
    void testClearInvalidBoardId() {
        assertThrows(IllegalParameterException.class, () -> pService.clear(1));
    }

    @Test
    @DisplayName("Test redo method with valid parameters")
    void testRedo() {
        assertDoesNotThrow(() -> pService.addShape(shape, 0));
        assertDoesNotThrow(() -> pService.undo( 0));
        assertDoesNotThrow(() -> pService.redo( 0));
        LinkedList<ShapeDescriptor> shapes = assertDoesNotThrow(() -> pService.getShapes(0));
        assertTrue(shapes.contains(shape));
    }

    @Test
    @DisplayName("Test redo method with invalid boardId")
    void testRedoInvalidBoardId() {
        assertThrows(IllegalParameterException.class, () -> pService.redo(1));
    }

    @Test
    @DisplayName("Test redo method with no undo actions")
    void testRedoNoUndoActions() {
        assertThrows(NoSuchElementException.class, () -> pService.redo(0));
    }
}
