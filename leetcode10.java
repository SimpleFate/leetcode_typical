/**
* 回溯，正则表达式
* 判断是否存在一个符合条件的划分
*
* 效率有点低，应该可以优化下。
* 就是把模式串p分成多个子模式串sub_p，然后在原字符串s中找到一个划分，使划分出来的子字符串sub_s可以和sub_p一一匹配。
* 主要是注意空字符串的问题。
*/
class Solution {
    boolean subMatch(String s, String p){
        if(p.length() == 0)
            return s.equals(p);
        if(p.length() == 1)
            return s.length() == 1 && chMatch(s.charAt(0),p.charAt(0));
        for(int i=0;i<s.length();i++){
            if(!chMatch(s.charAt(i),p.charAt(0)))
                return false;
        }
        return true;
    }
    boolean chMatch(char sch, char pch){
        return pch =='.' || sch == pch;
    }
    public boolean isMatch(String s, String p) {
        List<String> ps = new ArrayList<>();
        for(int i = 0; i < p.length();i++){
            if(p.charAt(i) != '*'){
                ps.add(String.valueOf(p.charAt(i)));
            }else{
                String last = ps.get(ps.size()-1);
                ps.set(ps.size()-1,last+"*");
            }
        }
        if(ps.size() == 0)
            ps.add("");
        return dfs(0,s,0,ps);
    }
    boolean dfs(int index, String s,int pIndex, List<String> ps){
        if(pIndex == ps.size()){
            return index == s.length();
        }
        for(int i = index;i <= s.length();i++){
            String subStr = "";
            if(index != s.length())
                subStr = s.substring(index,i);   
            boolean res = false;
            if( subMatch(subStr,ps.get(pIndex)) ){
                res = dfs(i,s,pIndex+1,ps);
            }
            if(res) return true;
        }
        return false;
    }
}
