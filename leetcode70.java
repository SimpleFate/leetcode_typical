/**
* 动态规划
* 考虑最后一次抉择（上1阶或上2阶），得到一个递归（循环）式
* 爬n阶楼梯方法 = 爬n-1阶楼梯方法 + 爬n-2阶楼梯方法
*/

class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        for(int i = 1; i <= n; i++){
            if(i <= 2){
                dp[i] = i;
            }else{
                dp[i] = dp[i-1] + dp[i-2];
            }
        }
        return dp[n];
    }
}
