/**
 * this class serves to deal with the mechanics of the game, ie moving up, down, left, right, or diagonal. it uses a 2d array of ints to represent the board.
 * @author cade
 */
public class GameMech {
    /** column stores the number of columns */
    private int column;
    /** row stores the number of rows */
    private int row;
    /** boardInts stores the two dimensional array respresnting the board */
    private int[][] boardInts;
    /** boardSet stores whether the board has been initialized */
    boolean boardSet = false;
    
    /**
     * the contructor sets the board and intializes two random 2's on the board
     * @param row is the number of rows in the board
     * @param column is the number of columns in the board
     */
    public GameMech(int row, int column) {
        this.boardInts = new int[row][column];
        this.column = column;
        this.row = row;
        this.newInt();
        this.newInt();
        boardSet = true;
    }
    /**
     * this class returns the boardInts
     * @return boardInts
     */
    public int[][] getBoard() {
        return this.boardInts;
    }
    /**
     * this is a helper method used to visualize moves more easily during the testing of the game
     */
    public void board() {
        // this loop prints every row of the board
        for (int i = 0; i < this.boardInts.length; i++){
            String s = "";
            for(Object y : this.boardInts[i]) {
                s += "|"+y+"|";
            }
            System.out.println(s);
        }
    }
    /**
     * slideL slides an array towards its origin and any values that are 0 get slid past
     * @param a is a one dimesnional array being slid to the left
     * @return the array slid to the left
     */
    public int[] slideL(int[] a) {
        int n = 0;
        // this loop goes through an array and shifts every int other than 0 to the left
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                a[n] = a[i];
                if (i != n)
                    a[i] = 0;
                n++;
            }
        }
        return a;
    }
    
    /**
     * addL slides the ints in an array to the left and then adds any two ints that have the same value and are next to eachother
     * @param a is the array being transformed
     * @return the new array with the values added to the left
     */
    public int[] addL(int[] a) {
        a = this.slideL(a);
        // this loop goes through an array and adds any two values that are the same together if they are next to eachother
        for(int i = 0; i < a.length-1;i++) {
            if(a[i] == a[i+1]) {
                a[i] = a[i] * 2;
                a[i+1] = 0;
                i++;
            }
        }
        return this.slideL(a);
    }
    
    /**
     * this method goes through boardInts and adds everything to the left
     */
    public void left(){
        // this loop applies the addL method to every row in boardInts
        for (int i = 0; i < this.row; i++) {
            this.boardInts[i] = this.addL(boardInts[i]);
        }
        this.newInt();
    }

    /**
     * slideR slides an array towards its end and any values that are 0 get slid past
     * @param a is a one dimesnional array being slid to the right
     * @return the array slid to the right
     */
    public int[] slideR(int[] a) {
        int n = a.length - 1;
        // this loop goes through an array and shifts every int other than 0 to the left
        for (int i = a.length -1; i >= 0; i--) {
            if (a[i] != 0) {
                a[n] = a[i];
                if (i != n)
                    a[i] = 0;
                n--;
            }
        }
        return a;
    }
    /**
     * addR slides the ints in an array to the right and then adds any two ints that have the same value and are next to eachother
     * @param a is the array being transformed
     * @return the new array with the values added to the right
     */
    public int[] addR(int[] a) {
        a = this.slideR(a);
        // this loop goes through an array and adds any two values that are the same together if they are next to eachother
        for(int i = a.length -1; i > 0;i--) {
            if(a[i] == a[i-1]) {
                a[i] = a[i] * 2;
                a[i-1] = 0;
                i--;
            }
        }
        return this.slideR(a);
    }

    /**
     * this method goes through boardInts and adds everything to the left
     */
    public void right() {
        // this loop applies the addR method to every row in boardInts
        for (int i = 0; i < this.row; i++) {
            this.boardInts[i] = this.addR(boardInts[i]);
        }
        this.newInt();
    }
    /**
     * this method randomly inserts either a 2 or a 4 onto an empty spot on the board;
     */
    public void newInt() {
        // r holds the row positon
        int r = (int)(Math.random()*this.row);
        // c holds the column position
        int c = (int)(Math.random()*this.column);
        int n = 0;
        //this loop looks for an empty spot where the value is 0, it will also stop if it has looked in 20 times 
        //the numberof spots
        while (this.boardInts[r][c] != 0 && n < (20*(this.row * this.column))) {
            r = (int)(Math.random()*this.row);
            c = (int)(Math.random()*this.column);
            n++;
        }
        if(boardSet) // if the board has already been set the value at r and c can be a 2 or a 4
            this.boardInts[r][c] = (Math.random() > .75) ? 4: 2;
        else // if the board has not been set hhe value can only be a 2
            this.boardInts[r][c] = 2;
    }
    
    /**
     * make col takes a 2D array and retrieves a column as an array
     * @param board is the 2D array the column is being retrieved from
     * @param colNum is the index of the column being retrieved
     * @return a 1D array of ints representing the column that was retrieved
     */
    public int[] makeCol(int[][] board, int colNum) {
        //int a holds the ints in the column of postion colNum
        int[] a = new int[this.row];
        // this loop adds the values of the specified column to a
        for(int i = 0; i < board.length; i++) {
            a[i] = board[i][colNum];
        }
        return a;
    }

    /**
     * return to col takes a 1D int and returns it to its correct place in a 2D array
     * @param col is the 1D array representing the column
     * @param colNum is the index of column being added back
     */
    public void returnToCol(int[] col, int colNum){
        //this loop goes through boardInts at the specified colNum and adds the values from the array col back in place
        for (int i = 0; i < this.row; i++) {
            this.boardInts[i][colNum] = col[i];
        }
    }

    /**
     * up moves all of the ints in a column up until they reach another number or the top of the board, should they
     * reach a number of equal value they are added
     */
    public void up() {
        // this loop moves everything in the 1D array representing an individual column to the left which when returned to buttonBoard will move all the
        // ints in the column up
        for (int i = 0; i < this.column; i++) {
            this.returnToCol(this.addL(this.makeCol(this.getBoard(), i)), i);
        }
        this.newInt();
    }

    /**
     * down moves all of the ints in a column down until they reach another number or the top of the board, should they
     * reach a number of equal value they are added
     */
    public void down(){
        // this loop moves everything in the 1D array representing an individual column to the right which when returned to buttonBoard will move all the
        // ints in the column down
        for (int i = 0; i < this.column; i++) {
            this.returnToCol(this.addR(this.makeCol(this.getBoard(), i)), i);
        }
        this.newInt();
    }
    /**
     * newBoard is a helper method for testing that allows for boardInts to be set to a given 2D array
     * @param a is a new 2D array that will be set to boardInts
     */
    public void newBoard(int[][] a) {
        this.boardInts = a;
        
    }
    
    /**
     * getDiagonalBT takes a 2D array of ints and returns an array of ints represnting the diagonal from the direction of bottom to top or left to right
     * @param a is the 2D aaray of ints
     * @param row is the number of rows in a
     * @param col is the number of columns in a
     * @param start is the diagonal number starting in the top left and ending in the bottom right
     * @return a 1d array of ints representing the diagonal indexed by start
     */
    public int[] getDiagonalBT(int[][] a, int row, int col, int start) {
        // n represents the number of diagonals in each row
        int n = 0;
        if (start <= (row - 1)) { // the diagonal starts at the beggining of a row
            // this loop finds the number of elements in the diagonal storing the value in n
            for(int i = start, j = 0; i >=0 && j < col; i--, j++) {
                //System.out.print(a[i][j] + " ");
                n++;
            }
            // diag holds the ints in the diagonal
            int[] diag = new int[n];
            n = 0;
            // this loop adds elements from the diagonal to diag
            for(int i = start, j = 0; i >=0 && j < col; i--, j++) {
                diag[n] = (a[i][j]);
                n++;
            }
            return diag;
        }
        else { // the diagonal starts on the bottom row
            // this loop finds the number of elements in the diagonal storing the value in n 
            for(int i = row -1, j = 1+ (start - row); i >=0 && j < col; j++, i--) {
                //System.out.print(a[i][j] + " ");
                n++;
            }
            // diag holds the ints in the diagonal
            int[] diag = new int[n];
            n = 0;
            // this loop adds elements from the diagonal to diag
            for(int i = row -1, j = 1+ (start - row); i >=0 && j < col; j++, i--) {
                diag[n] = (a[i][j]);
                n++;
            }
            return diag;
        }
    }
    
    /**
     * makeAllDiagBT takes the boardInts using the getBoard method and returns all the diagonals in the direction bottom to top from the boardInts
     * @return a 2D array consisting of all the diagonals from boardInts
     */
    public int[][] makeAllDiagBT() {
        // diag holds the diagonals
        int[][] diag = new int[this.column+this.row-1][];
        // this loop takes individual arrays of diagonals and adds them to diag
        for (int count = 0; count < diag.length; count++) {
            diag[count] = this.getDiagonalBT(this.getBoard(), this.row, this.column, count);
        }
        return diag;
    }

    /**
     * downBT takes a 2D array of ints representing diagonals and adds each 1D array to the left 
     * @param diag is the 2D array of ints 
     * @return a new 2D array of the left added input array
     */
    public int[][] downBT(int[][] diag) {
        // result holds the doctored diagonals 
        int[][] result = new int[diag.length][];
        // this loop adds every row from diag to the left
        for (int i = 0; i < diag.length; i++)
            result[i] = this.addL((this.makeAllDiagBT())[i]);
        return result;
    }

    /**
     * upBT takes a 2D array of ints representing diagonals and adds each 1D array to the right 
     * @param diag is the 2D array of ints 
     * @return a new 2D array of the left added input array
     */
    public int[][] upBT(int[][] diag) {
        // result holds the doctored diagonals 
        int[][] result = new int[diag.length][];
        // this loop adds every row from diag to the right
        for (int i = 0; i < diag.length; i++)
            result[i] = this.addR((this.makeAllDiagBT())[i]);
        return result;
    }

    /**
     * putBTBack reinserts a 2D array of ints representing diagonals back into boardInts 
     * @param diagonals is the array of diagonals that have been shifted in some way and need to be put back into boardInts
     */
    public void putBTBack(int[][] diagonals) {
        //start represents the index of the diagonals
        int start = 0;
        // this loop goes through every 1D array in diagonals and assigns its value to the corresponding positon in boardInts
        while (start < this.row + this.column-1) {
            
            if (start <= (row - 1)) { // the diagonal originates at the start of a row
                // diag holds the 1D array of ints representing the specific diagonal
                int[] diag = diagonals[start];
                // n holds the position in diag
                int n = 0;
                // this loop goes through the diagonals reassigning their values to those from diag
                for(int i = start, j = 0; i >=0 && j < this.column; i--, j++) {
                    (this.boardInts[i][j]) = diag[n];
                    n++;
                }
            }
            else { // the diagonal originates in the last row
                // diag holds the 1D array of ints representing the specific diagonal
                int[] diag = diagonals[start];
                // n holds the position in diag
                int n = 0;
                // this loop goes through the diagonals reassigning their values to those from diag
                for(int i = this.row -1, j = 1+ (start - this.row); i >=0 && j < this.column; j++, i--) {
                    (this.boardInts[i][j]) = diag[n];
                    //System.out.println(n);
                    n++;
                }
                
            }
            
            start++;
        }
    }
    
    /**
     * this method moves all of the ints in boardInts down and to the left
     */
    public void downLeft() {
        // diag holds all the BT diagonals from boardInts
        int[][] diag = this.makeAllDiagBT();
        // diagPrime holds all of the doctored diaganals 
        int[][] diagPrime = this.downBT(diag);
        this.putBTBack(diagPrime);
        this.newInt();
    }
    /**
     * this method moves all of the ints in boardInts up and to the right
     */
     public void upRight() {
        // diag holds all the BT diagonals from boardInts
        int[][] diag = this.makeAllDiagBT();
        // diagPrime holds all of the doctored diaganals 
        int[][] diagPrime = this.upBT(diag);
        this.putBTBack(diagPrime);
        this.newInt();
    }
    
    /**
     * getDiagonalBT takes a 2D array of ints and returns an array of ints represnting the diagonal from the direction of top to bottom or right to left
     * @param a is the 2D aaray of ints
     * @param row is the number of rows in a
     * @param col is the number of columns in a
     * @param start is the diagonal number starting in the bottom left and ending in the top right
     * @return a 1d array of ints representing the diagonal indexed by start
     */
    public int[] getDiagonalTB(int[][] a, int row, int col, int start) {
        // n represents the number of diagonals in each row
        int n = 0;
        if (start <= (col - 1)) { // diagonal starts on bottom row
            // this loop finds the number of elements in the diagonal storing the value in n
            for(int i = row - 1, j = start; i >=0 && j >=0; i--, j--) {
                //System.out.print(a[i][j] + " ");
                n++;
            }
            // diag is a 1D array that holds the ints of the diagonal indexed by start
            int[] diag = new int[n];
            n = 0;
            // this loop adds elements from the diagonal to diag 
            for(int i = row - 1, j = start; i >=0 && j >=0; i--, j--) {
                diag[n] = (a[i][j]);
                n++;
            }
            return diag;
        }

        else { // diagonal starts on last column
            // this loop finds the number of elements in the diagonal storing the value in n
            for(int i =  row - 2 - (start-row), j = col - 1; i >=0 && j >=0; j--, i--) {
                //System.out.print(a[i][j] + " ");
                n++;
            }
            // diag is a 1D array that holds the ints of the diagonal indexed by start
            int[] diag = new int[n];
            n = 0;
            // this loop adds elements from the diagonal to diag
            for(int i =  row - 2 - (start-row), j = col - 1; i >=0 && j >=0; j--, i--) {
                diag[n] = (a[i][j]);
                n++;
            }
            return diag;
        }
    }

    /**
     * makeAllDiagTB takes the boardInts using the getBoard method and returns all the diagonals in the direction top to bottom from the boardInts
     * @return a 2D array consisting of all the diagonals from boardInts
     */
    public int[][] makeAllDiagTB() {
        // diag holds the diagonals of boardInts
        int[][] diag = new int[this.column+this.row-1][];
        // this loop takes individual arrays of diagonals and adds them to diag
        for (int count = 0; count < diag.length; count++) {
            diag[count] = this.getDiagonalTB(this.getBoard(), this.row, this.column, count);
        }
        return diag;
    }

    /**
     * putTBBack reinserts a 2D array of ints representing diagonals back into boardInts 
     * @param diagonals is the array of diagonals that have been shifted in some way and need to be put back into boardInts
     */
    public void putTBBack(int[][] diagonals) {
        //start represents the index of the diagonals
        int start = 0;
        // this loop goes through every 1D array in diagonals and assigns its value to the corresponding positon in boardInts
        while (start < this.row + this.column-1) {
            
            if (start <= (this.column - 1)) {  //the diagonal originates in the bottom row  
                // diag holds the 1D array of ints representing the specific diagonal
                int[] diag = diagonals[start];
                // n holds the position in diag
                int n = 0;
                // this loop goes through the diagonals reassigning their values to those from diag
                for(int i = row - 1, j = start; i >=0 && j >=0; i--, j--) {
                    (this.boardInts[i][j]) = diag[n];
                    n++;
                }
            }
            else { //the diagonal origates in the last column
                // diag holds the 1D array of ints representing the specific diagonal
                int[] diag = diagonals[start];
                // n holds the position in diag
                int n = 0;
                // this loop goes through the diagonals reassigning their values to those from diag
                for(int i =  row - 2 - (start-row), j = this.column - 1; i >=0 && j >=0; j--, i--) {
                    (this.boardInts[i][j]) = diag[n];
                    //System.out.println(n);
                    n++;
                }
                
            }
            
            start++;
        }
    }
    
    /**
     * downTB takes a 2D array of ints representing diagonals and adds each 1D array to the left 
     * @param diag is the 2D array of ints 
     * @return a new 2D array of the left added input array
     */
    public int[][] downTB(int[][] diag) {
        // result holds the values of the doctured diagonals
        int[][] result = new int[diag.length][];
        //this loop goes through each row of diag and adds them to the left
        for (int i = 0; i < diag.length; i++)
            result[i] = this.addL((this.makeAllDiagTB())[i]);
        return result;
    }

    /**
     * upTB takes a 2D array of ints representing diagonals and adds each 1D array to the right 
     * @param diag is the 2D array of ints 
     * @return a new 2D array of the left added input array
     */
    public int[][] upTB(int[][] diag) {
        // result holds the values of the doctured diagonals
        int[][] result = new int[diag.length][];
        //this loop goes through each row of diag and adds them to the right
        for (int i = 0; i < diag.length; i++)
            result[i] = this.addR((this.makeAllDiagTB())[i]);
        return result;
    }

    /**
     * this method moves all of the ints in boardInts down and to the right
     */
    public void downRight() {
        // diag holds all the TB diagonals from boardInts
        int[][] diag = this.makeAllDiagTB();
        // diagPrime holds all of the doctored diaganals 
        int[][] diagPrime = this.downTB(diag);
        this.putTBBack(diagPrime);
        this.newInt();
    }

    /**
     * this method moves all of the ints in boardInts up and to the left
     */
     public void upLeft() {
        // diag holds all the TB diagonals from boardInts
        int[][] diag = this.makeAllDiagTB();
        // diagPrime holds all of the doctored diaganals 
        int[][] diagPrime = this.upTB(diag);
        this.putTBBack(diagPrime);
        this.newInt();
    }
}    
