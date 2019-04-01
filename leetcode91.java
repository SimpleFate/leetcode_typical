/**
* 动态规划，一维dp，多情况分析
* dp[n]为s的子字符串(0,n)的结果，最终结果为dp[s.length()]，dp数组由1遍历到s.length()
* 分情况讨论：
* 1.当前为1-9，可以和上一个元素组成字母编码， dp[i] = dp[i-1] + dp[i-2]
* 2.当前为1-9，不可以和上一个元素组成字母编码，dp[i] = dp[i-1]
* 3.当前为0，可以和上一个元素组成字母编码，dp[i] = dp[i-2]
* 4.当前为0，不可以和上一个元素组成字母编码，dp[i] = 0
*/
class Solution {
    public int numDecodings(String s) {
        if(Objects.isNull(s) || s.length() == 0 || s.charAt(0) == '0'){
            return 0;
        }
        int len = s.length();
        int last = 0;
        int[] dp  = new int[len+1];
        dp[0] = 1;
        for(int i = 1 ; i <= len; i++){
            int curNum = s.charAt(i-1) - '0';
            if(i == 1){
                dp[i] = 1;
            }else{
                if(curNum == 0){
                    dp[i] = last==1||last==2?dp[i-2]:0;
                }else{
                    int comb = last*10 + curNum;
                    if(last==0 || comb > 26){
                        dp[i] = dp[i-1];
                    }else{
                        dp[i] = dp[i-1] + dp[i-2];
                    }
                }
            }
            last = curNum;
        }
        return dp[len];
    }
}
