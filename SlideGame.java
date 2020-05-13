import java.util.List;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/**
 * SlideGame creates the GUI for the game an allows for user input to move the ints around. the goal is to get to the highest number possible adding numbers
 * of the same value. the game starts with two 2's placed randomly on the board and with every move either a 2 or a 4 is placed on the board in a random position. 
 * the avaliable moves are up, down, left, right, up-left, up-right, down-left, & down-right.
 * @author Cade
 */
public class SlideGame extends Application {
    /**buttonBoard holds the array of buttons that represents the board */
    private Button[][] buttonBoard;
    /**
     * makeButtonBoard takes a 2D array of ints and makes it into a 2D array of buttons each of which holds the int value of the int array as a string
     * @param intB is the 2D array of ints representing the board
     */
    public void makeButtonBoard(int[][] intB) {
        buttonBoard = new Button[intB.length][intB[0].length];
        for (int i = 0; i < intB.length; i ++) {
            for (int j = 0; j < intB[i].length; j ++) {
                Button b = new Button(intB[i][j]+"");
                b.setPrefSize(80,80);
                this.buttonBoard[i][j] = b;
            }
        }
    }
    
    /**
     * resetBB does not instantiate new buttons but takes in a new board of ints and resets the values held by each button
     * @param intB is the 2D array of ints that holds the values being assigned to the buttons of the board
     */
    public void resetBB(int[][] intB) {
        for (int i = 0; i < intB.length; i ++) {
            for (int j = 0; j < intB[i].length; j ++) {
                this.buttonBoard[i][j].setText(intB[i][j]+"");
            }
        }
    }
    
    /**
     * the start method creates a 2D array of ints using Game mech and then a gridpane of buttons to hold the value of the board using the makeButtonBoard method.
     * it will only take two ints less than 10 as inputs from args. should anything other than an int be provided it will throw an Exception and ask for two ints.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Application.Parameters params = getParameters();
            List<String> inputs = params.getRaw();
            /** row stores the number of rows the board will have */
            int row = Integer.parseInt(inputs.get(0));
            /** col stores the number of columns the board will have */
            int col = Integer.parseInt(inputs.get(1));
            /** should either the row or col ints be greater than 10 they will be set to ten */
            if (row > 10)
                row = 10;
            if (col > 10)
                col = 10;
            /**board holds the 2D array of ints holding the values of the board */
            GameMech board = new GameMech(row, col);
            /**grid is where the array of buttons will be added to create the GUI of the game*/
            GridPane grid = new GridPane();
            /**the buttonBoard is instantiated using the array of ints from board */
            makeButtonBoard(board.getBoard());
            
            // this loop assigns an action event the buttons on the perimeter of the board based on their position
            for (int i = 0; i < buttonBoard.length; i++) {
                for (int j = 0; j< buttonBoard[0].length; j++) {
                    if (i==0 && j==0) { //up and to the left
                        this.buttonBoard[i][j].setOnAction(e -> {
                            board.upLeft();
                            resetBB(board.getBoard());
                        });
                    }
                    else if (i== buttonBoard.length-1 && j==buttonBoard[0].length-1) { //down and right
                        this.buttonBoard[i][j].setOnAction(e -> {
                            board.downRight();
                            resetBB(board.getBoard());
                        });
                    }
                    else if (i== buttonBoard.length-1 && j == 0) { //down and left
                        this.buttonBoard[i][j].setOnAction(e -> {
                            board.downLeft();
                            resetBB(board.getBoard());
                        });
                    }
                    else if (i==0 && j==buttonBoard[0].length-1) { // up and right
                        this.buttonBoard[i][j].setOnAction(e -> {
                            board.upRight();
                            resetBB(board.getBoard());
                        });
                    }
                    else if (i == 0) { //up
                        this.buttonBoard[i][j].setOnAction(e -> {
                            board.up();
                            resetBB(board.getBoard());
                        });
                    }
                    else if (i == buttonBoard.length-1) { //down
                        this.buttonBoard[i][j].setOnAction(e -> {
                            board.down();
                            resetBB(board.getBoard());
                        });
                    }
                    else if (j == 0) { // left
                        this.buttonBoard[i][j].setOnAction(e -> {
                            board.left();
                            resetBB(board.getBoard());
                        });
                    }
                    else if (j == buttonBoard[0].length-1) { //right
                        this.buttonBoard[i][j].setOnAction(e -> {
                            board.right();
                            resetBB(board.getBoard());
                        });
                    }
                    
                }
            }
            // this loop adds the buttons from buttonBoard to the gridPane
            for (int i = 0; i < buttonBoard.length; i++) {
                for(int j = 0; j < buttonBoard[i].length; j++) {
                    grid.add(buttonBoard[i][j], i, j);
                }
            }
            // pane stores the grid
            BorderPane pane = new BorderPane();
            pane.setBottom(grid);
            // the scene should fit the size of the grid perfectly leaving no empty space
            Scene scene = new Scene(pane, row*80,col*80);
            
            // set the title to be the "row x colums"
            String s = "Board: ";
            s+= row +"x"+col;
            primaryStage.setTitle(s);
            
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e){
            //requests valid inputs and resets
            System.out.println("Please put two ints");
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    
    
}