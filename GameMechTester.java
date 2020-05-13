import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;

/**
 * this class tests the GameMech class to make sure that it functions properly
 * @author Cade 
 */
public class GameMechTester {
    int[][] ao = {{1}};
    int[][] bo ={{0}};
    // for uses with diagonals I have a 5x5 board numbered sequentially
    int[][] a55 = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
    // for uses with diagonals I have a sample empty board
    int[][] b55 = {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
    int[][] a43 = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
    int[][] b43 = {{0,0,0},{0,0,0},{0,0,0},{0,0,0}};
    
    @Test
    public void slideLeftAndRightTest() {
        // instantiating a new board with 5 rows and columns
        GameMech g = new GameMech(5,5);
        // the original is the untransformed board 
        int[][] original = {
            {0,0,2,0,0},//middle
            {0,2,0,2,0},
            {0,0,0,0,0},// zero
            {2,2,2,2,2},//completely full
            {2,0,0,0,2}};
        // left is the board slid to the left
        int[][] left = {{2,0,0,0,0},{2,2,0,0,0},{0,0,0,0,0},{2,2,2,2,2},{2,2,0,0,0}};
        // right is the board slid to the right
        int[][] right = {{0,0,0,0,2},{0,0,0,2,2},{0,0,0,0,0},{2,2,2,2,2},{0,0,0,2,2}};
        
        //this loop slides all of the rows in original to the left
        for (int i = 0; i < 5; i++)
            g.slideL(original[i]);
        //check that the rows have ALL been correctly slid
        assertEquals(true, Arrays.deepEquals(left, original));
        
        //using a new array with the same values as the undoctored array "original"
        int[][] original1 = {{0,0,2,0,0},{0,2,0,2,0},{0,0,0,0,0},{2,2,2,2,2},{2,0,0,0,2}};
        //this loop slides every row of original1 to the right
        for (int i = 0; i < 5; i++)
            g.slideR(original1[i]);
        //check that all rows were properly slid to the right
        assertEquals(true, Arrays.deepEquals(right, original1));
    }
    
    @Test
    public void leftAndRightTest() {
        // instantiating a new board with 5 rows and columns
        GameMech g = new GameMech(5,5);
        // the original is the untransformed board 
        int[][] original = {
            {0,0,2,0,0},//middle
            {0,2,0,2,0},
            {0,0,0,0,0},// zero
            {2,2,2,2,2},//completely full
            {2,0,0,0,2}};
        // the board slid to the left
        int[][] left = {{2,0,0,0,0},{4,0,0,0,0},{0,0,0,0,0},{4,4,2,0,0},{4,0,0,0,0}};
        // the board slid to the right
        int[][] right = {{0,0,0,0,2},{0,0,0,0,4},{0,0,0,0,0},{0,0,2,4,4},{0,0,0,0,4}};
        
        // this loop goes through original and adds each row to the left
        for (int i = 0; i < 5; i++)
            g.addL(original[i]);
        // check that the transformed original is properly added
        assertEquals(true, Arrays.deepEquals(left, original));
        
        // re use values from original with variable original1
        int[][] original1 = {{0,0,2,0,0},{0,2,0,2,0},{0,0,0,0,0},{2,2,2,2,2},{2,0,0,0,2}};
        //this loop goes through original1 and adds every row to the right
        for (int i = 0; i < 5; i++)
            g.addR(original1[i]);
        // check that the transformed original1 is properly added to the right
        assertEquals(true, Arrays.deepEquals(right, original1));
    }
    
    @Test 
    public void upAndDownTest() {
        // creating a new board with 5 rows and 5 columns
        GameMech g = new GameMech(5,5);
        // setting the board equal to the array a55
        g.newBoard(a55);
        // cols holds the column values of the array a55
        int[][] cols = {
            { 1, 6, 11, 16, 21 },// beginning or 0
            { 2, 7, 12, 17, 22 }, // middle
            { 3, 8, 13, 18, 23 }, // middle
            { 4, 9, 14, 19, 24 }, // middle
            { 5, 10, 15, 20, 25 }}; // end
        // this loop goes through each column of and checks it with its respective column in cols
        for (int i = 0; i < 5; i++) {
            assertEquals(true, Arrays.equals(cols[i],g.makeCol(g.getBoard(), i)));
        }
        
        // set the board to the empty string b55
        g.newBoard(b55);
        //this loop puts all of the columns back into the board 
        for (int i = 0; i < 5; i++) {
            g.returnToCol(cols[i], i);
        }
        // check that the collumps were put back correctly 
        assertEquals(true, Arrays.deepEquals(g.getBoard(), a55));
    }
    
    @Test
    public void bTDiagonalTest() {
        // creating a new board with 5 rows and 5 columns
        GameMech g = new GameMech(5,5);
        // setting the board equal to the array a55
        g.newBoard(a55);
        // diags holds the diagonals of the board with ints 1 - 25 
        int[][] diags = { { 1 }, { 6, 2 }, { 11, 7, 3 }, { 16, 12, 8, 4 }, { 21, 17, 13, 9, 5 }, { 22, 18, 14, 10 }, { 23, 19, 15 }, { 24, 20 }, { 25 } };
        // this loop takes every diagonal of the board and compares it with the respective daigonal from diags
        for (int i = 0; i < 7; i++){
            assertEquals(true,Arrays.equals(diags[i],g.getDiagonalBT(g.getBoard(), 5, 5, i)));
        }
        // to test makeAllDiagBT the diagonals are compared to the array returned by the method
        assertEquals(true, Arrays.deepEquals(diags, g.makeAllDiagBT()));
        
        // the board is made to be empty
        g.newBoard(b55);
        //making sure that the board is empty
        assertEquals(true, Arrays.deepEquals(b55, g.getBoard()));
        g.putBTBack(diags);
        // checking that the diagonals were correctly put back into place
        assertEquals(true, Arrays.deepEquals(a55, g.getBoard()));
        
        //int d holds the diagonals to an arbiray board
        int[][] d = {
            { 0 }, // zero 
            { 2, 2 },
            { 4, 4, 4 },
            { 0, 0, 0, 2 }, 
            { 0, 0, 0, 0, 0 }, //middle
            { 2, 4, 8, 4 },
            { 0, 2, 0 },
            { 2, 0 },
            { 2 } }; // end
        
        // the arbitry board is formed from the diagonals d
        g.putBTBack(d);
        // the properly down moved diagonals are stored in dDownBt 
        int[][] dDownBT = { { 0 }, { 4, 0 }, { 8, 4, 0 }, { 2, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 2, 4, 8, 4 }, { 2, 0, 0 }, { 2, 0 }, { 2 } };
        // the properly up moved diagonals are stored in dUpBt
        int[][] dUpBT = { { 0 }, { 0, 4 }, { 0, 4, 8 }, { 0, 0, 0, 2 }, { 0, 0, 0, 0, 0 }, { 2, 4, 8, 4 }, { 0, 0, 2 }, { 0, 2 }, { 2 } };
        // check that the board was properly transformed
        assertEquals(true, Arrays.deepEquals(g.downBT(d),dDownBT));
        assertEquals(true, Arrays.deepEquals(g.upBT(d),dUpBT));
    }
    
    @Test
    public void tBDiagonalTest() {
         // creating a new board with 5 rows and 5 columns
        GameMech g = new GameMech(5,5);
        // setting the board equal to the array a55
        g.newBoard(a55);
        // diags holds the diagonals of the board with ints 1 - 25 
        int[][] diags = { { 21 }, { 22, 16 }, { 23, 17, 11 }, { 24, 18, 12, 6 }, { 25, 19, 13, 7, 1 }, { 20, 14, 8, 2 }, { 15, 9, 3 }, { 10, 4 }, { 5 } };
        // this loop takes every diagonal of the board and compares it with the respective daigonal from diags
        for (int i = 0; i < 7; i++){
            assertEquals(true,Arrays.equals(diags[i],g.getDiagonalTB(g.getBoard(), 5, 5, i)));
        }
        // to test makeAllDiagTB the diagonals are compared to the array returned by the method
        assertEquals(true, Arrays.deepEquals(diags, g.makeAllDiagTB()));
        
        // the board is made to be empty
        g.newBoard(b55);
        //making sure that the board is empty
        assertEquals(true, Arrays.deepEquals(b55, g.getBoard()));
        g.putTBBack(diags);
        // checking that the diagonals were correctly put back into place
        assertEquals(true, Arrays.deepEquals(a55, g.getBoard()));
        
        //int d holds the diagonals to an arbiray board
        int[][] d = {
            { 0 }, // zero
            { 2, 2 },
            { 4, 4, 4 },
            { 0, 0, 0, 2 },
            { 0, 0, 0, 0, 0 }, //middle
            { 2, 4, 8, 4 },
            { 0, 2, 0 },
            { 2, 0 },
            { 2 } }; // end
        // the arbitry board is formed from the diagonals d
        g.putTBBack(d);
        // the properly down moved diagonals are stored in dDownTB
        int[][] dDownTB = { { 0 }, { 4, 0 }, { 8, 4, 0 }, { 2, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 2, 4, 8, 4 }, { 2, 0, 0 }, { 2, 0 }, { 2 } };
        // the properly up moved diagonals are stored in dUpTB
        int[][] dUpTB = { { 0 }, { 0, 4 }, { 0, 4, 8 }, { 0, 0, 0, 2 }, { 0, 0, 0, 0, 0 }, { 2, 4, 8, 4 }, { 0, 0, 2 }, { 0, 2 }, { 2 } };
        // check that the board was properly transformed
        assertEquals(true, Arrays.deepEquals(g.downTB(d),dDownTB));
        assertEquals(true, Arrays.deepEquals(g.upTB(d),dUpTB));
    }
    
    @Test
    public void newIntTest() {
        // a new board is instantiated 
        GameMech g = new GameMech(5,5);
        // the board is set to the empty array
        g.newBoard(b55);
        // n holds the number of ints in the board
        int n = 0;
        // this loop goes through the board and checks the number of ints other than 0
        for (int i = 0; i< 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (g.getBoard()[i][j] != 0)
                    n++;
            }
        }
        // check that the board is empty
        assertEquals(0,n);
        
        // this loop runs the newInt() method 25 times
        for (int k = 0; k < 25; k++) {
            g.newInt();
        }
        n = 0;
        // this loop checks for the number of ints in the board
        for (int i = 0; i< 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (g.getBoard()[i][j] != 0)
                    n++;
            }
        }
        // make sure that there are only 25 ints in the board
        assertEquals(25,n);
    }
    
}