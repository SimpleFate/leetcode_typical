/**
* 回溯、哈希、dfs
*/

class Solution {
    int[][] blockmap = new int[9][9];
    boolean checkRow(char num, int row, char[][] board){
        for(int i = 0; i < 9; i++)
            if(board[row][i] == num)
                return false;
        return true;
    }
    boolean checkColumn(char num, int column, char[][] board){
        for(int i = 0; i < 9; i++)
            if(board[i][column] == num)
                return false;
        return true;
    }
    boolean checkRowAndColumn(char num, int row, int col, char[][] board){
        return checkRow(num,row,board) && checkColumn(num,col,board);
    }
    boolean checkBlock(char num, int row, int col, char[][] board){
        int blockIndex = getBlock(row,col);
        int numIndex = num - '0' - 1;
        return blockmap[blockIndex][numIndex] == 0;
    }
    
    int getStart(int row, int col,char[][] board){
        int blockIndex = getBlock(row,col);
        for(int i = 0; i < 9; i++){
            if(blockmap[blockIndex][i] == 0)
                return i;
        }
        return -1;
    }
    int getNext(int cur, int row, int col, char[][] board){
        int blockIndex = getBlock(row,col);
        if(cur != -1){
            for(int i = cur + 1; i < 9; i++){
                if(blockmap[blockIndex][i] == 0)
                    return i;
            }
        }
        return -1;
    }
    boolean isEnd(int cur){
        return cur == -1;
    }
    /**
    * 0 1 2
    * 3 4 5
    * 6 7 8
    **/
    int getBlock(int row, int col){
        int base = row / 3 * 3;
        int offset = col / 3;
        return base + offset;
    }
    
    void initBlockMap(char[][] board){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.')
                    continue;
                int numIndex = board[i][j] - '0' - 1;
                int blockIndex = getBlock(i,j);
                blockmap[blockIndex][numIndex] = 1;
            }
        }
    }
    
    void add(char num, int row, int col, char[][] board){
        int blockIndex = getBlock(row,col);
        int numIndex = num - '0' - 1;
        blockmap[blockIndex][numIndex] = 1;
        board[row][col] = num;

    }
    void remove(int row, int col, char[][] board){
        int blockIndex = getBlock(row,col);
        int numIndex = board[row][col] - '0' - 1;
        blockmap[blockIndex][numIndex] = 0;
        board[row][col] = '.';
    }
    void display(char[][] board){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    int solveItem(int i, int j, char[][] board){
        if(i >= 9){
            display(board);
            return 1;
        }
        if(j >= 9){
            return solveItem(i+1,0,board);
        }
        if(board[i][j] != '.'){
            return solveItem(i,j+1,board);
        }
        
        for(int index = getStart(i,j,board); 
            !isEnd(index); 
            index = getNext(index,i,j,board) ){    
            
            char num = (char)(index + 1 + '0');
            if(!checkRowAndColumn(num,i,j,board))
                continue;
            add(num,i,j,board);

            if(solveItem(i,j+1,board) == 1)
                return 1;
        
            remove(i,j,board);
        }
        return 0;
            
    }
    
    public void solveSudoku(char[][] board) {
        initBlockMap(board);
        solveItem(0,0,board);
    }
}
