public class wordSearch {
    public boolean exist(char[][] board, String word) {
        int n = board.length, m = board[0].length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(DFS(board, word, i, j, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean DFS(char[][] board, String word, int i, int j, int curr){
        if(curr == word.length()) return true;
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(curr)) return false;

        char temp = board[i][j];
        board[i][j] = '0';

        boolean get = DFS(board,word,i+1,j,curr+1)||DFS(board,word,i-1,j,curr+1)||DFS(board,word,i,j+1,curr+1)||DFS(board, word, i, j-1, curr+1);

        board[i][j] = temp;
        return get;
    }
}
